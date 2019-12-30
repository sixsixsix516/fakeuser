package com.sixsixsix516.fakeuser.condition;

/**
 * @author sun 2019/12/29 22:15
 */
public interface Condition {


    /**
     * 校验数据是否正确
     *
     * @param data 需要校验的数据
     * @return
     */
    boolean check(Object data);

    /**
     * 用对应的条件对象修改值
     *
     * @param data
     * @return
     */
    Object modify(Object data);

}
