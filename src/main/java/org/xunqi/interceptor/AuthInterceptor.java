package org.xunqi.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xunqi.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-15 15:34
 **/
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 在进入Handler方法执行之前执行本方法
     *
     * @return true:执行下一个拦截器，直到所有拦截器都执行完，再执行被拦截的Controller
     *         false:从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        if (CommonUtil.contains(request.getSession(),request.getServletPath(),request.getMethod())) {
            return true;
        }
        // 针对ajax请求处理
        if (request.getHeader("x-requested-with") != null) {
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            response.setHeader("url",basePath + "/login/noAuth");
        } else {
            request.getRequestDispatcher("login/noAuth").forward(request,response);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
