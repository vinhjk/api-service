package com.example.blue_bik_api_service.controller;

import com.example.blue_bik_api_service.request.UserRequest;
import com.example.blue_bik_api_service.response.UserResponse;
import com.example.blue_bik_api_service.service.UserAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
    private final UserAPIService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(value = "keyword", required = false) String keyword){
        return service.getUsers(keyword);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        return service.createUser(request);
    }

    @PostMapping("/multiple-users")
    public ResponseEntity<List<UserResponse>> createUsers(@RequestBody List<UserRequest> requests){
        return service.createUsers(requests);
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request){
        return service.updateUser(request);
    }

    @PutMapping("/multiple-users")
    public ResponseEntity<List<UserResponse>> updateUsers(@RequestBody List<UserRequest> requests){
        return service.updateUserInBulk(requests);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

    @DeleteMapping("/multiple-users")
    public ResponseEntity<String> deleteUsers(@RequestBody List<Long> ids){
        return service.deleteUserInBulk(ids);
    }
}
