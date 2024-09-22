package com.nishant.service;

import com.nishant.entity.Product;
import com.nishant.entity.User;
import com.nishant.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Async
    public CompletableFuture<List<User>> saveUser(MultipartFile file) throws Exception {

        long start = System.currentTimeMillis();
        List<User> users = parseCSVFile(file);
        log.info("{} saving list of user of size: {}", Thread.currentThread().getName(), users.size());
        userRepository.saveAll(users);
        long end = System.currentTimeMillis();
        log.info("total time {}", end - start);
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<List<User>> findAllUsers() {
        log.info("{} find all users", Thread.currentThread().getName());
        List<User> users = userRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<User> findUser(int id) {
        log.info("{} find all users", Thread.currentThread().getName());
        Optional<User> user = userRepository.findById(id);
        return CompletableFuture.completedFuture(user.get());
    }

    private List<User> parseCSVFile(final MultipartFile file) throws Exception {
        final List<User> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setGender(data[2]);
                    users.add(user);
                }
                return users;
            }
        } catch (final IOException e) {
            log.error("Failed to parse CSV file: ", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    public void add() {

        ////List<User> users = userRepository.findAll();
        List<Product> products = loadProductsFromDB();
//        TreeSet<Product> treeSet = products.stream().sorted(Comparator.comparingDouble(p -> Double.parseDouble(p.getPrice())))
//                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingDouble(e -> Double.parseDouble(e.getPrice())))));
//        //Set<User> set = new HashSet<>(users);
       // List<Product> productList = products.stream().sorted(Comparator.comparing(Product::getPrice)).toList();
        Comparator<Product> proComparator = Comparator.comparingDouble(p -> Double.parseDouble(p.getPrice()));
        Set<Product> set1 = new TreeSet<>(proComparator);
        set1.addAll(products);
        // return entityScheduleLine.stream()
        // .sorted(Comparator.comparingDouble(model -> model.getScheduleLineNumber())).toList()


        for (Product product : set1) {
            log.info("id:" + product.getProductId() + "  price: " + product.getPrice() + "    name: " + product.getName());
        }

        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return 0;
            }
        });
        log.info("***************************************************************");
        for (Product product : products) {

            log.info("id:" + product.getProductId() + "  price: " + product.getPrice() + "    name: " + product.getName());
        }
    }

    public List<Product> loadProductsFromDB() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(String.valueOf(new Random().nextInt(5000))).build()
                ).collect(Collectors.toList());
    }
}
