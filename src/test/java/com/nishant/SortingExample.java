package com.nishant;

import com.nishant.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortingExample {

    @Test
    public void bubbleSort() {
        int[] arr = {2, 3, 4, 5, 6, 7, 9, 1, 6, 7, 3, 11, 4};
        boolean swapped;
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {

                count++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                System.out.println("swapped " + arr[i]);
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("count of iterations: " + count);

    }

    //{7, 1, 3, 4, 7, 1, 7, 1, 4, 5, 1, 9, 3}   --> {1,1,1,1,7,7,7,3,3,4,4,5,9}
    @Test
    public void sortByFrequency() {
        int[] arr = {7, 1, 3, 4, 7, 1, 7, 1, 4, 5, 1, 9, 3};

        Map<Integer, Long> map = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(data -> data, Collectors.counting()));

        List<Map.Entry<Integer, Long>> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

        List<Map.Entry<Integer, Long>> fdfdfdf = map.entrySet().stream().sorted((key1, key2) -> key1.getValue().compareTo(key2.getValue())).collect(Collectors.toList());
        System.out.println(fdfdfdf.toString());

        System.out.println(list.toString());

        List<Integer> list1 = new ArrayList();

        for (Map.Entry<Integer, Long> data : list) {
            Integer key = data.getKey();
            Long value = data.getValue();
            for (int i = 0; i < value; i++) {
                list1.add(key);
            }
        }
        System.out.println(list1.toString());

    }

    // sal avarage group by dept in sql and java 8
    @Test
    public void groupByDept() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("IT", "Nishant", 1000));
        list.add(new Employee("IT", "Nishant", 2000));
        list.add(new Employee("IT", "Nishant", 3000));
        list.add(new Employee("HR", "Nishant", 4000));
        list.add(new Employee("HR", "Nishant", 5000));
        list.add(new Employee("HR", "Nishant", 6000));
        list.add(new Employee("IT", "Nishant", 7000));
        list.add(new Employee("IT", "Nishant", 8000));
        list.add(new Employee("IT", "Nishant", 9000));
        list.add(new Employee("HR", "Nishant", 10000));
        list.add(new Employee("HR", "Nishant", 11000));
        list.add(new Employee("HR", "Nishant", 12000));

        Map<String, Double> map = list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(map.toString());
    }

    @Test
    //selection sort
    public void selectionSort() {
        int[] arr = {2, 3, 4, 5, 6, 7, 9, 1, 6, 7, 3, 11, 4};
        boolean swapped = false;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            if (!swapped) {
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void duplicate() {
        String str = "Nishant Shejule";
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        map.entrySet().forEach(data -> System.out.println(data.getKey() + " " + data.getValue()));
    }


}
