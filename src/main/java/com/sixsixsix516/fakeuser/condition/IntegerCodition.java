package com.sixsixsix516.fakeuser.condition;

import lombok.Data;

/**
 * @author sun 2019/12/29 22:28
 */
@Data
public class IntegerCodition {

    /**
     * 大于的数
     */
    private Integer gt;

    /**
     * 小于的数
     */
    private Integer lt;

    /**
     * 等于的数
     */
    private Integer eq;


    public IntegerCodition gt(Integer num){
        this.gt = num;
        return this;
    }

    public IntegerCodition lt(Integer num){
        this.lt = num;
        return this;
    }

    public IntegerCodition eq(Integer num){
        this.eq = num;
        return this;
    }


}
