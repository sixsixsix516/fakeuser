package com.sixsixsix516.fakeuser.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户基本信息
 *
 * @author sun 2019/12/29 21:34
 */
@Data
public class UserBase {

    /**
     * 昵称
     */
    private Object nikename;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 用户编号
     */
    private Integer userNo;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 性别 1男 2女
     */
    private Integer sex;


}