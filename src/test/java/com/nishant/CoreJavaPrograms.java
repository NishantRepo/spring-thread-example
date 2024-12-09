package com.nishant;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoreJavaPrograms {

    @Test
    public void sortArray() {
        int arr[] = {10, 4, 6, 7, 2, 1, -1};
        System.out.println("before");

        Arrays.stream(arr).forEach(System.out::println);
        int len = arr.length;
        boolean swapped = false;

        for (int i = 0; i < len; i++) {
            swapped = false;
            for (int j = 0; j < len - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    swapped = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println("after");

        Arrays.stream(arr).forEach(System.out::println);


    }


    @Test
    public void countOfChars() {

        String str = "nishant shejule";
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
