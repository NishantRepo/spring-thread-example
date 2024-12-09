package com.nishant;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RemoveDuplicate {


    @Test
    public void duplicate() {
        int[] arr = {1,2,3,3,5,6,6,7,7,8,9,9,9};
        int index = 0;
        for(int i=1; i< arr.length; i++) {
            if(arr[index] != arr[i]) {
                index ++;
                arr[index] = arr[i];
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
