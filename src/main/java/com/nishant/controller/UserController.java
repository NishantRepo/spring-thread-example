package com.nishant.controller;

import com.nishant.entity.User;
import com.nishant.entity.UserInfo;
import com.nishant.model.AuthRequest;
import com.nishant.service.JwtService;
import com.nishant.service.UserInfoService;
import com.nishant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserInfoService userInfoService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @GetMapping(value = "/test")
    public String testMethod() {
        return "hello";
    }


    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {

        for (MultipartFile file : files) {
            CompletableFuture<List<User>> listCompletableFuture = userService.saveUser(file);
            List<User> users = listCompletableFuture.get();
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/v1/findAll")
    public CompletableFuture<List<User>> findAllUsers() {
        return userService.findAllUsers();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/v1/{id}")
    public CompletableFuture<User> getUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @GetMapping(value = "/findUsers")
    public void getUsers() {

        CompletableFuture<List<User>> users = userService.findAllUsers();
        CompletableFuture<List<User>> users1 = userService.findAllUsers();
        CompletableFuture<List<User>> users2 = userService.findAllUsers();

        CompletableFuture.allOf(users, users1, users2).join();

    }

    @PostMapping("/addUserInfo")
    public String addUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.addUserInfo(userInfo);
    }

    @GetMapping("/get")
    public List<UserInfo> getAllUserInfo() {
        return userInfoService.gerAllUserInfo();
    }


    @PostMapping("/jwt")
    public String authAndGetJwt(@RequestBody AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateJwt(request.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request!!!");
        }
    }

    @GetMapping(value = "/{id}")
    public User getUser1(@PathVariable int id) {

        return userService.findUser(id).join();
    }

    @GetMapping("/short")
    public String add() {
        userService.add();
        return "hello";

    }


}
