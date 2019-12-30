package com.sixsixsix516.fakeuser.condition;

import com.sixsixsix516.fakeuser.wrapper.StringWrapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sun 2019/12/29 22:26
 */
@Accessors(chain = true, fluent = true)
@Data
public class StringCondition implements Condition {

    private IntegerCodition length;

    private String format;


    /**
     * 此条件属于哪个对象下
     * 存储此对象是为了 链式调用时能够跳回去
     */
    private StringWrapper stringWrapper;

    public void setStringWrapper(StringWrapper stringWrapper) {
        this.stringWrapper = stringWrapper;
    }

    public StringWrapper and() {
        return stringWrapper;
    }


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

    @Override
    public boolean check(Object data) {

        return true;
    }

    @Override
    public Object modify(Object data) {
        if (data instanceof String) {
            String str = (String) data;
            if (StringUtils.isNotEmpty(str.trim())) {
                return String.format(format,str);
            }
        }
        return data;
    }
}
