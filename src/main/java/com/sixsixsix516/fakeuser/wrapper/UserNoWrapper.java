package com.sixsixsix516.fakeuser.wrapper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun 2020/3/17 10:13
 */
@Data
public class UserNoWrapper {

    private List<Long> userNoList;

    private Long begin;

    public UserNoWrapper(){
        this.userNoList = new ArrayList<>();
    }


    public void start(Integer num) {
        String substring = String.valueOf(System.currentTimeMillis());
        substring = substring.substring(substring.length() / 2);
        this.begin = Long.valueOf(substring);

        for (int i = 0; i < num; i++) {
            userNoList.add(++begin);
        }
    }

    public List<Long> getUserNoList() {
        return userNoList;
    }

}
