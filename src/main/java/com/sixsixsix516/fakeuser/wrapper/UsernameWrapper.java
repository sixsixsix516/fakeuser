package com.sixsixsix516.fakeuser.wrapper;

import com.sixsixsix516.fakeuser.condition.StringCondition;
import lombok.Data;

import java.util.List;

/**
 * @author sun 2019/12/29 22:57
 */
@Data
public class UsernameWrapper extends StringCondition {

    /**
     * 网名列表
     */
    private List<String> nikenameList;

}
