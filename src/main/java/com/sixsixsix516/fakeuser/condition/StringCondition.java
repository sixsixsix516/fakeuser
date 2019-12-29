package com.sixsixsix516.fakeuser.condition;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sun 2019/12/29 22:26
 */
@Accessors(chain = true)
@Data
public class StringCondition {

    private IntegerCodition length;

    private String format;

    public StringCondition() {
        // 默认占全部字符
        this.format = "%s";
    }


    /**
     * 控制生成的字符串长度
     *
     * @param integerCodition
     */
    public StringCondition setLength(IntegerCodition integerCodition) {
        this.length = integerCodition;
        return this;
    }
}
