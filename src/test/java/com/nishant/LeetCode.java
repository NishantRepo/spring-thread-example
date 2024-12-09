package com.nishant;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {

    //low
    @Test
    void twoSumTest() {
        int[] numbers = {2, 11, 15, 7};
        int target = 9;
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < numbers.length; i++) {

            if (map.containsKey(numbers[i])) {
                result[0] = map.get(numbers[i]);
                result[1] = i;
            } else {
                map.put(target - numbers[i], i);
            }

        }

        System.out.println(result[0] + " " + result[1]);
    }

    //low

    @Test
    public void palindromeNumber() {
        boolean result = false;
        int input = 121;
        int reverse = 0;
        int temp = input;
        if(input < 0) result  = false;

        while(temp != 0) {
            int digit = (int) temp % 10;
            reverse = reverse * 10 + digit;
            temp = temp/10;
        }
        System.out.println(reverse == input);



    }

    @Test
    public void palindromeString() {
       String str = "ni1tin";
       int start = 0;
       int end = str.length()-1;
       boolean result = true;

       while(start < end) {
           if(str.charAt(start) != str.charAt(end)) {
               result = false;
               break;
           } start++;
           end--;
       }
    System.out.println(result);

    }

    @Test
    public void reverseString() {

        String str = "nishant";

        StringBuilder builder = new StringBuilder();
        for(int i= str.length() -1; i>=0; i--) {
            builder.append(str.charAt(i));
        }

        System.out.println(builder);
    }

}
