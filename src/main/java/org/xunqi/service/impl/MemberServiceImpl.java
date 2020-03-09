package org.xunqi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xunqi.cache.CodeCache;
import org.xunqi.cache.TokenCache;
import org.xunqi.mapper.MemberMapper;
import org.xunqi.pojo.Member;
import org.xunqi.service.MemberService;
import org.xunqi.util.MD5Util;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jerry
 */
@Service("memberService")
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public boolean exists(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberMapper.select(member);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveCode(Long phone, String code) {

        //TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();

        return codeCache.save(phone, MD5Util.getMD5(code));
    }

    @Override
    public boolean sendCode(Long phone, String content) {
        log.info(phone + "|" + content);
        return true;
    }

    @Override
    public String getCode(Long phone) {
        //TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    @Override
    public void saveToken(String token, Long phone) {

        TokenCache tokenCache = TokenCache.getInstance();
        tokenCache.save(token,phone);
    }

    @Override
    public Long getPhone(String token) {
        TokenCache tokenCache = TokenCache.getInstance();
        tokenCache.getPhone(token);
        return null;
    }

    @Override
    public Long getIdByPhone(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberMapper.select(member);
        if (list != null && list.size() > 0) {
            return list.get(0).getId();
        }
        return null;
    }
}
