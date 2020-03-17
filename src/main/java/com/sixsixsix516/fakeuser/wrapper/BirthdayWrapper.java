package com.sixsixsix516.fakeuser.wrapper;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sun 2020/3/17 10:23
 */
@Data
public class BirthdayWrapper {

    private List<Date> dateList;

    /**
     * 生日区间设置
     * todo
     */


    public BirthdayWrapper(){
        this.dateList = new ArrayList<>();
    }

    public void start(Integer num){
        for (int i = 0; i < num; i++) {
            dateList.add(new Date());
        }
    }

    public List<Date> getDateList(){
        return this.dateList;
    }

}
