package org.xunqi.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xunqi.constant.SessionKeyConst;
import org.xunqi.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Jerry
 */
public class SessionInterceptor implements HandlerInterceptor {

    /**
     * 在进入Handler方法执行之前执行本方法
     *
     * @return true:执行下一个拦截器，直到所有拦截器都执行完，再执行被拦截的Controller
     *         false:从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute(SessionKeyConst.USER_INFO);

        if (user == null) {
            //未登录，返回登录页面
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('请先进行登录，再进行后续操作！');location.href='"+request.getContextPath()+"/login'</script>");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
