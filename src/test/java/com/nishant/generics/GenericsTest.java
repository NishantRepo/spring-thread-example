package com.nishant.generics;

import org.junit.jupiter.api.Test;

public class GenericsTest {


    @Test
    public void TestGenerics() {

        StudentGenerics<Integer> stud = new StudentGenerics<>();

        stud.setName(1234);
        System.out.println(stud.toString());
    }

    @Test
    public void demoTest() {
        double value=0;

        Double fdfd = value;

        System.out.println(fdfd);
    }
}
