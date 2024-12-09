package com.nishant.prgrams;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionsExamples {

    public static void main(String[] args) {

        int result = testTryCatchFinally();
        System.out.println("result is: " + result);

        try {
            demo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void demo() throws IOException, FileNotFoundException {

    }

    // Is it possible to throw checked exceptions from a static block?
    // We cannot throw a check exception from a static block. However,
    // we can have try-catch logic that handles the exception within the scope
    // of that static block without rethrowing the exception using the throw keyword.
    // The exceptions cannot be propagated from static blocks because static blocks are invoked
    // at the compiled time only once and no method invokes these blocks
//    static  {
//        throw  new FileNotFoundException();
//    }

    public static int testTryCatchFinally() {
        try {
            System.out.println("try");
            int a = 10 / 0;
            return 1;
        } catch (Exception e) {
            System.out.println("Exception");
            //System.exit(0); //
            return 2;
        } finally {
            System.out.println("finally");
            return 3;
        }
    }
}


class Parent {
    void add() throws IOException { // parent class must throws parent exception

    }
}

class Child extends Parent {

    @Override
    void add() throws FileNotFoundException { // child class must throws same or child exception compare to parent class

    }

//    @Override
//    void add() throws Exception { // child class must throws same or child exception compare to parent class
//
//    }

}
