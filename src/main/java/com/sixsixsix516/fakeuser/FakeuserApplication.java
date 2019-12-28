package com.sixsixsix516.fakeuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FakeuserApplication {

    public static List<String> name = new ArrayList();

    public static void main(String[] args) {
        SpringApplication.run(FakeuserApplication.class, args);
    }

}
