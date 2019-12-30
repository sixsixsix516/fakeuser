package com.sixsixsix516.fakeuser.condition;

import com.sixsixsix516.fakeuser.wrapper.StringWrapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * 数值的条件
 *
 * @author sun 2019/12/29 22:28
 */
@Accessors(chain = true,fluent = true)
@Data
public class IntegerCodition implements Condition {

    /**
     * 此条件属于哪个对象下
     * 存储此对象是为了 链式调用时能够跳回去
     */
    private StringWrapper stringWrapper;

    public void setStringWrapper(StringWrapper stringWrapper){
        this.stringWrapper = stringWrapper;
    }

    public StringWrapper and(){
        return stringWrapper;
    }


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

    /**
     * 对对象中的条件进行解析
     *
     * @param data 需要校验的数据
     * @return
     */
    @Override
    public boolean check(Object data) {
        if (data instanceof String) {
            String str = (String) data;
            if (StringUtils.isNoneEmpty(str.trim())) {
                int length = str.length();

                // 当条件有值的时候才判断
                if (this.eq() != null) {
                    // 如果eq有值则判断eq
                    return this.eq() == length;
                }
                if (this.gt() != null) {
                    if (this.gt() >= length) {
                        return false;
                    }
                }
                if (this.lt() != null) {
                    return this.lt() > length;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public Object modify(Object data) {
        return data;
    }
}

