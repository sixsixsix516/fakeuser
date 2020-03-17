package com.sixsixsix516.fakeuser;

import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.start.FakeUser;

import java.util.List;

public class FakeuserApplicationTests {


    public static void main(String[] args) {

        FakeUser fakeUser = new FakeUser(10);
        // 用户名的配置
        fakeUser.username
                .addStringCondition().format("SX_%s").and()
                .addIntegerCodition().gt(3).lt(5);



        // 获取结果
        List<UserBase> userBases = fakeUser.get();
        System.out.println("--------------------------------------------------");
        userBases.forEach(System.out::println);
        System.out.println("--------------------------------------------------");


    }

}
