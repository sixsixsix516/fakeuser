package com.sixsixsix516.fakeuser;

import com.sixsixsix516.fakeuser.model.UserBase;
import com.sixsixsix516.fakeuser.start.FakeUser;

import java.util.List;

public class FakeuserApplicationTests {


    public static void main(String[] args) {

        FakeUser fakeUser = new FakeUser();
        fakeUser.setNum(10);
        // 对用户的限制
        fakeUser.username
                .addStringCondition().format("SX_%s").and()
                .addIntegerCodition().gt(3).lt(5);


        List<UserBase> userBases = fakeUser.get();
        System.out.println("=================================================================================");
        userBases.stream().forEach(System.err::println);
        System.out.println("=================================================================================");



    }

}
