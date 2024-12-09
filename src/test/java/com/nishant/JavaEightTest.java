package com.nishant;

import com.nishant.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class JavaEightTest {

    @Test
    public void demoTest() {
        log.info("test");

        BiConsumer<Integer, Integer> aa = (a, b) -> System.out.println(a + b);

        aa.accept(10, 65);

        Runnable run = () -> System.out.println("Hiiiii");

        new Thread(run).start();


    }

    @Test
    public void fdf() {
        List<Product> products = loadProductsFromDB();
        products.stream().map(getGetPrice()).filter(Objects::nonNull);
        // Function example
        products.stream().map(getGetPrice());
        // Predicate example
        products.stream().map(getGetPrice()).filter(getIsBlank());
        //Consumer example
        products.forEach(getProductConsumer());


        //************************************************

        Optional<String> op = Optional.of("null");
        System.out.println("************" + op.isPresent());

    }


    private static Consumer<Product> getProductConsumer() {
        return data -> System.out.println(data.getName());
    }

    private static Function<Product, String> getGetPrice() {
        return data -> data.getName();
    }

    private Predicate<String> getIsBlank() {
        return data -> data.isBlank();
    }

    public List<Product> loadProductsFromDB() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(String.valueOf(new Random().nextInt(5000))).build()
                ).collect(Collectors.toList());
    }

    @Test
    public void javaEightPrograms() {
        //Max and min value
        int asInt = IntStream.rangeClosed(1, 100).max().getAsInt();
        System.out.println("max number is" + asInt);

        int min = IntStream.rangeClosed(10, 100).min().getAsInt();
        System.out.println("min number is" + min);

        List<Integer> list = Arrays.asList(10, 12, 45, 76, 83, 90, 55, 34, 50, 5);
        Optional<Integer> minned = list.stream().min(Comparator.naturalOrder());
        Integer max1 = list.stream().max(Comparator.naturalOrder()).get();

        System.out.println("min number is" + minned.get());
        System.out.println("max number is" + max1);


        //reverse sort list
        List<Product> products = loadProductsFromDB();
        System.out.println("REverse list");
        products.stream().map(Product::getName).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // sort list
        System.out.println("order list");
        products.stream().map(Product::getProductId).sorted(Comparator.naturalOrder()).forEach(System.out::println);
    }

    @Test
    public void predicateTest() {

        Predicate<String> predicate = data -> data.length() == 5;

        System.out.println(predicate.test("nisha"));
    }

    @Test
    public void predicateJoiningTest() {

        Predicate<String> predicate = data -> data.length() == 6;
        Predicate<String> predicate1 = data -> data.length() % 3 == 0;
//      and joining predicate
        System.out.println(predicate.and(predicate1).test("nishan"));
//      or joining predicate
        System.out.println(predicate.or(predicate1).test("nishant12"));
//      negate joining predicate
        System.out.println(predicate.negate().test("nishanfsdfsdfsfsd"));
    }

    @Test
    public void functionTest() {
//               input     output
        Function<Integer, Integer> function = data -> data * data;

        System.out.println(function.apply(3));
    }

    @Test
    public void functionChainingTest() {
//               input     output
        Function<Integer, Integer> square = data -> 2 * data;
        Function<Integer, Integer> cube = data -> data * data * data;
        // and then function chaining
        System.out.println(square.andThen(cube).apply(2));

        //compose chaining
        System.out.println(square.compose(cube).apply(2));
    }

    @Test
    public void oddEvenNumbers() {
        List<Integer> list = Arrays.asList(12, 45, 66, 75, 89, 453, 15, 66);

        Map<Boolean, List<Integer>> map = list.stream().collect(Collectors.groupingBy(data -> data % 2 == 0));
        Set<Map.Entry<Boolean, List<Integer>>> entries = map.entrySet();
        for (Map.Entry<Boolean, List<Integer>> data : entries) {
            if (data.getKey()) { //if the key is true then it wil be the even numbers
                System.out.println("even numbers");
            } else {
                System.out.println("odd numbers");
            }
            for (Integer value : data.getValue())
                System.out.println(value);
        }
    }

    @Test
    public void removeDuplicate() {
        List<Integer> list = Arrays.asList(12, 45, 66, 75, 89, 453, 15, 66, 89, 12, 15);
        list.stream().distinct().forEach(System.out::println);
        List<Integer> sorted = list.stream().sorted((c1, c2) -> c1 -c2).collect(Collectors.toList());
    }

    @Test
    public void countOfChars() {

        //old way to print count
        Map<Character, Integer> map = new HashMap<>();
        String str = "nishant rohidas shejule";
        char[] charArray = str.toCharArray();
        int count = 1;
        for (char ch : charArray) {
            if (map.get(ch) == null) {
                map.put(ch, count);
            } else {
                map.put(ch, map.get(ch) + 1);
            }
        }
        System.out.println(map);

        //using java 8

        Map<Character, Long> charCount = str.chars()
                .mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("charCount using java 8");
        System.out.println(charCount);
    }

    @Test
    public void countOfElements() {

        List<String> list = Arrays.asList("java", "dotnet", "java Script", "python", "java", "angular", "nodejs");

        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);

        //Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?

        String str = list.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(str);

    }

    @Test
    public void multiples() {
        List<Integer> list = Arrays.asList(10, 12, 45, 76, 83, 90, 55, 34, 50, 5);
        list.stream().filter(data -> data % 5 == 0).forEach(System.out::println);
    }

    @Test
    public void orderProducts() {
        List<Product> products = loadProductsFromDB();
        products.forEach(System.out::println);
        System.out.println("reverse order by quantity*************");
        products.stream().sorted((k, v) -> v.getQty() - k.getQty()).forEach(System.out::println);
        System.out.println("natural order quantity *************");
        products.stream().sorted(Comparator.comparing(Product::getQty)).forEach(System.out::println);

        System.out.println("reverse order by name*************");
        products.stream().sorted((k, v) -> v.getName().compareTo(k.getName())).forEach(System.out::println);
        System.out.println("natural order name *************");
        products.stream().sorted(Comparator.comparing(Product::getName)).forEach(System.out::println);


    }

    @Test
    public void findFileLocation() {

//        Path path = Paths.get("src/main/resources/application.yaml") ;
//
//        System.out.println(path.getFileName());

        File file = new File("src/main/resources");
        File file1 = new File(file, "application1.yaml");
        System.out.println(file1.exists());
    }

    @Test
    public void charCount() {

        String str = "communication";
        Map<char[], Long> map1 = Stream.of(str.toCharArray()).collect(Collectors.groupingBy(data -> data, Collectors.counting()));
        Map<String, Long> map = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        str.chars().mapToObj(data -> (char) data)
                .collect(Collectors.groupingBy(data -> data, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1)
                .forEach(data -> System.out.println(data.getKey() + "=" + data.getValue()));
    }

    @Test
    public void adddd() {
        String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";

        // Create a DateTimeFormatter with the pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // Example date and time string
        String dateTimeString = "2024-02-14T12:30:00+0530"; // Indian Standard Time

        // Parse the date and time string to ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeString, formatter);

        // Convert to LocalDateTime representing Indian time
        LocalDateTime indianDateTime = zonedDateTime.toLocalDateTime();

        // Output the result
        System.out.println("Original DateTime: " + dateTimeString);
        System.out.println("Indian DateTime: " + indianDateTime);


    }

    @Test
    public void fdfdf() {

        String localDateTimeString = "2024-02-14T12:30:30";

        // Parse the LocalDateTime string into a LocalDateTime object
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);


        // Create a ZonedDateTime object with the system's default time zone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        // Format the ZonedDateTime object to the desired string format
        String resultString = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX"));

        // Output the result
        System.out.println(localDateTimeString + " Original LocalDateTime: ");
        System.out.println(resultString + " DateTime with system's default time zone: ");
    }

    @Test
    public void twoSumFindOutIndex() {
        int[] numbers = {1, 2, 5, 9, 4};
        int target = 15;
        Map<Integer, Integer> map = new HashMap<>();
        int[] output = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                output[0] = map.get(numbers[i]);
                output[1] = i;
                break;
            } else map.put(target - numbers[i], i);
        }
        System.out.println(Arrays.toString(output));
    }

    @Test
    public void twoSumFindOutIndexSortedArray() {
        int[] numbers = {1, 2, 5, 9, 15};
        int target = 10;
        Map<Integer, Integer> map = new HashMap<>();
        int[] output = new int[2];

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                output = new int[]{left, right};
                break;
            } else if (sum < target) {
                left++;
            } else right--;
        }
        System.out.println(Arrays.toString(output));
    }

    @Test
    public void twoSumFindOutGIvenSumIsPresent() {
        int[] numbers = {1, 2, 5, 9, 15};
        int target = 24;

        boolean result = false;
        Set<Integer> set = new HashSet<>();
        for (int no : numbers) {
            int res = target - no;
            if (set.contains(res)) {
                result = true;
                break;
            }
            set.add(no);
        }
        System.out.println(result);
    }

    @Test
    public void minSubArrayLen() {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        // return minLength == Integer.MAX_VALUE ? 0 : minLength;
        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }

    @Test
    public void testCountOfWords() {
        String str = "java and java Script     is not same";


        long count = Arrays.stream(str.split("\\s*")).count();
        System.out.println("Count of worlds in the given string:" + count);

        Map<String, Long> map = Arrays.stream(str.split(" ")).filter(data -> !data.isBlank()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("count of each word");
        map.forEach((key, value) -> {

            System.out.println(key + ":" + value);
        });
        String ss = "java";
        Set<Map.Entry<String, Long>> set = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet();

    }


    @Test
    public void sortingNumbers() {
        List<Integer> list = Arrays.asList(12, 1, 5, 4, 3, 8, 9, 0);

        System.out.println("sort ascending order");

        List<Integer> ascSorted = list.stream().sorted().collect(Collectors.toList());
        System.out.println(ascSorted);

        List<Integer> descSorted = list.stream().sorted((a, b) -> b - a).collect(Collectors.toList());
        list.stream().sorted((Comparator.naturalOrder())).collect(Collectors.toList());
        System.out.println("desc sort order");
        System.out.println(descSorted);

    }

    @Test
    public void sortingWords() {

        List<String> list = Arrays.asList("nishant", "prashant", "visheta", "surekha");

        List<String> sorted = list.stream().sorted().collect(Collectors.toList());

        System.out.println("asc sort order");
        System.out.println(sorted);

        //List<String> descSorteed = list.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        List<String> descSorteed = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println("desc sort order");
        System.out.println(descSorteed);

        String str = "java";
        String charsort = str.chars().sorted().mapToObj(c -> (char) c).map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println("char asc sort order");
        System.out.println(charsort);

        String descSort = str.chars().boxed().sorted((a, b) -> b - a).map(data -> (char) data.intValue())
                .map(String::valueOf).collect(Collectors.joining());

        System.out.println("char desc sort order");
        System.out.println(descSort);

        List<Product> productList = Arrays.asList(
                new Product(1, "abc", 1, "300"),
                new Product(2, "qwe", 6, "500"),
                new Product(3, "fgh", 5, "600"),
                new Product(4, "nhgf", 16, "100"),
                new Product(6, "zaq", 11, "50")

        );
        List<Product> sortedProductList = productList.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).toList();
        //List<Product> sortedProductList = productList.stream().sorted(Comparator.comparing(Product::getName)).toList();
        System.out.println("sorted product list by name:");
        sortedProductList.forEach(System.out::println);

        // List<Product> descSortedProductList = productList.stream().sorted(Comparator.comparing(Product::getName).reversed()).toList();
        List<Product> descSortedProductList = productList.stream().sorted((a, b) -> b.getName().compareTo(a.getName())).toList();
        System.out.println("desc sorted product list by name:");
        descSortedProductList.forEach(System.out::println);


        System.out.println("asc sorted product list by id:");
        List<Product> sortById = productList.stream().sorted((a, b) -> a.getProductId() - b.getProductId()).collect(Collectors.toList());
        sortById.forEach(System.out::println);


        System.out.println("desc sorted product list by id:");
        List<Product> descsortbyId = productList.stream().sorted((a, b) -> b.getProductId() - a.getProductId()).collect(Collectors.toList());
        descsortbyId.forEach(System.out::println);

        Product product = productList.stream().max((e1, e2) -> e1.getProductId() - e2.getProductId()).get();
        System.out.println("products details with highest id:"+ product.toString());

    }

    @Test
    public void findMinMaxVAlues() {

        List<Integer> list = Arrays.asList(43, 5, 7, 8, 3, 5, 66, 11);

        //list.stream().count();
        // int max = list.stream().max(Integer::compareTo).get();
        int max = list.stream().max((o1, o2) -> o1 - o2).get();
        System.out.println("max value is:" + max);


        // int min = list.stream().min(Integer::compareTo).get();
        int min = list.stream().min((o1, o2) -> o1 - o2).get();
        System.out.println("min value is:" + min);
    }

    @Test
    public void firstNonRepeatedCharacter() {
        String str = "swiss";

        Character aaa = str.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(data -> data.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);


        System.out.println("first non repeating charector is:" + aaa);
    }

    @Test
    public void findDuplicateValues() {
        List<Integer> list = Arrays.asList(5, 1, 4, 5, 7, 6, 3, 0, 7, 4, 1, 7);

        List<Integer> aa = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter((data -> data.getValue() > 1))
                .map(Map.Entry::getKey).toList();

        System.out.println("duplicate numbers:" + aa);
    }

    @Test
    public void sortByStringLength() {
        List<String> list = Arrays.asList("apple", "mango", "pineapple", "orange", "date", "cherry");

       // List<String> list1 = list.stream().sorted((a,b) -> a.length() - b.length()).toList();
        //List<String> list1 = list.stream().sorted((a, b) -> Integer.compare(a.length(), b.length())).toList();
        List<String> list1 = list.stream().sorted(Comparator.comparingInt(String::length)).toList();

        System.out.println("sort string by length:" + list1);
    }

    @Test
    public void sumOfListNumbers() {
        List<Integer> list = Arrays.asList(4, 3, 7, 8, 1, 6);

        Optional<Integer> reduce = list.stream().reduce(Integer::sum);

        System.out.println("sum of numbers:" + reduce.get());
    }

    @Test
    public void findPair() {

        int target = 2;
        List<Integer> list = Arrays.asList(1,2,3, 2);

        Integer i = list.stream().sorted((c1, c2) -> c2 - c1).skip(1).findFirst().get();
        System.out.println(i);


        list.stream().flatMap(num1 ->
                list.stream().filter(num2 -> (num1 + num2) == target && num1 < num2)
                        .map(num2 -> num1 + "," + num2)).distinct().forEach(data -> System.out.println("pair: " + data));

       // System.out.println("pair is:" + pair);
    }

    @Test
    public void dddeded() {
        int count = 0;
        char c = 'n';
        String str = "nishnat shejule";
        for(int i=0; i<=str.length() -1 ;i++) {
            char ch = str.charAt(i);
            if(ch ==c) {
                count++;
            }
        }
        System.out.println(count);
    }


    @Test
    public void testete() {
        String str = "apple";
        Optional<Character> first = str.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(data -> data, Collectors.counting())).entrySet().stream()
                .filter(data -> data.getValue() == 1).map(data -> data.getKey()).toList().stream().findFirst();

        System.out.println(first.get());


    }
}
