package org.xunqi.service;

/**
 * @author Jerry
 */
public interface MemberService {

    /**
     *  判断手机号是否存在
     * @param phone
     * @return
     */
    boolean exists(Long phone);


    /**
     *  保存手机号与对应的验证码的MD5缓存中
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
    boolean saveCode(Long phone,String code);


    /**
     *  下发短信验证码
     * @param phone
     * @param content
     * @return
     */
    boolean sendCode(Long phone,String content);


    /**
     *  根据手机号获取验证码MD5码值
     * @param phone
     * @return
     */
    String getCode(Long phone);


    /**
     *  保存token与对应的手机号
     * @param token
     * @param phone
     */
    void saveToken(String token,Long phone);


    /**
     *  根据token获取用户信息（手机号）
     * @param token
     * @return
     */
    Long getPhone(String token);


    /**
     *  根据手机号获取会员表主键
     * @param phone
     * @return
     */
    Long getIdByPhone(Long phone);
}
