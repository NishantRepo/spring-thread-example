package com.nishant.java8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class javaEightTestClass {

    private static List<String> strList;

    private static List<Integer> intList;

    @BeforeAll
    public static void setup() {

        strList = Arrays.asList("nishant", "prashant", "visheta", "surekha", "abc", "abc");

        intList = Arrays.asList(5, 4, 6, 7, 8, 91, 2, 66, 5, 8);
    }

    @Test
    public void countOfWordsAndNumbers() {
        long count = strList.stream().count();
        System.out.println("count of words:" + count);

        long count1 = intList.stream().count();

        System.out.println("count of numbers:" + count1);
    }

    @Test
    public void countOfEachWordAndNumber() {
        strList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);

        intList.stream().collect(Collectors.groupingBy(data -> data, Collectors.counting()))
                .entrySet().forEach(System.out::println);
    }

    @Test
    public void countOfEachCharInWord() {
        Set<Map.Entry<String, Integer>> map = strList.stream()
                .distinct()
                .collect(Collectors.toMap(data -> data, String::length))
                .entrySet();
        map.forEach(System.out::println);
    }

    @Test
    public void uniquechar() {
        String str = "abfgtrfdghnbfd";

        String uniqueStr = str.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(data -> data, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(data -> data.getValue() == 1)
                .map(data -> String.valueOf(data.getKey()))
                .collect(Collectors.joining());
        System.out.println(uniqueStr);

    }

    @Test
    public void countOfCharInString() {

        String str = "nishant";
        char c = 'n';

        long count = str.chars().mapToObj(ch -> (char) ch)
                .filter(data -> data == c).count();

        System.out.println(count);

        long count1 = strList.stream().filter(data -> data.equals(str)).count();

        System.out.println("count of word: " +count1);
    }

    @Test
    public void countOfChar() {

        String s ="nishant";
        char c = 'n';
        long count = s.chars().mapToObj(ch -> (char) ch).count();
        long count1 = s.chars().mapToObj(ch -> (char) ch).filter(ch -> ch == c).count();

        List<Map.Entry<Character, Long>> collect = s.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(data -> data, Collectors.counting()))
                .entrySet().stream().filter(data -> data.getValue() == 1).collect(Collectors.toList());


        System.out.println(count1);
    }
}
