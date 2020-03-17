package com.sixsixsix516.fakeuser.wrapper;

import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sun 2020/3/17 9:54
 */
@Data
public class SexWrapper {

    /**
     * 1男 2女 3未知
     */
    private static int[] SEX = {1,2,3};

    /**
     * 存储生成的数据列表
     */
    private List<Integer> sexList;

    /**
     * 生成策略 1EQ（指定值） 2RANDOM（随机） 3（百分比）
     * todo
     */
    private Integer strategy;


    public void start(Integer num) {
        this.sexList = new Random().ints(1, (3 + 1)).limit(num).boxed().collect(Collectors.toList());
    }

    public List<Integer> getSexList(){
        return this.sexList;
    }


}
