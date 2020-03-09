package org.xunqi.mapper;

import org.xunqi.pojo.Member;

import java.util.List;

/**
 * @author Jerry
 */
public interface MemberMapper {

    List<Member> select(Member member);

}
