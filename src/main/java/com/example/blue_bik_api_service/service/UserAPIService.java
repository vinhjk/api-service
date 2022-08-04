package com.example.blue_bik_api_service.service;

import com.example.blue_bik_api_service.request.UserRequest;
import com.example.blue_bik_api_service.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAPIService {
    ResponseEntity<List<UserResponse>> getUsers(String keyword);

    ResponseEntity<UserResponse> createUser(UserRequest request);

    ResponseEntity<List<UserResponse>> createUsers(List<UserRequest> requests);

    ResponseEntity<UserResponse> updateUser(UserRequest request);

    ResponseEntity<List<UserResponse>> updateUserInBulk(List<UserRequest> requests);

    ResponseEntity<String> deleteUser(Long id);

    ResponseEntity<String> deleteUserInBulk(List<Long> ids);
}
