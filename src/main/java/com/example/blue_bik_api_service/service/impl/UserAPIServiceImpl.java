package com.example.blue_bik_api_service.service.impl;

import com.example.blue_bik_api_service.config.ClientConfig;
import com.example.blue_bik_api_service.request.UserRequest;
import com.example.blue_bik_api_service.response.UserResponse;
import com.example.blue_bik_api_service.service.UserAPIService;
import com.example.proto.GetUser;
import com.example.proto.UserRequests;
import com.example.proto.Users;
import com.example.proto.listIds;
import com.google.protobuf.Int64Value;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAPIServiceImpl implements UserAPIService {
    private final ClientConfig config;

    @Override
    public ResponseEntity<List<UserResponse>> getUsers(String keyword) {
        GetUser getUser = Objects.isNull(keyword) ? null : GetUser.newBuilder().setKeyword(keyword).build();
        Users users = config.getServiceBlockingStub().getUsers(getUser);
        return new ResponseEntity<>(users.getUserResponseList().stream().map(u -> UserResponse.builder()
                .fullName(u.getFullName())
                .address(u.getAddress())
                .age((int) u.getAge())
                .gender(u.getGender())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .build()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest request) {
        com.example.proto.UserResponse response = config.getServiceBlockingStub().createUser(com.example.proto.UserRequest.newBuilder()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setFullName(request.getFirstName().concat(" ").concat(request.getLastName()))
                .setAddress(request.getAddress())
                .setGender(request.getGender())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setAge(request.getAge())
                .build()
        );
        return new ResponseEntity<>(UserResponse.builder()
                .fullName(response.getFullName())
                .address(response.getAddress())
                .age((int) response.getAge())
                .gender(response.getGender())
                .phoneNumber(response.getPhoneNumber())
                .email(response.getEmail())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponse>> createUsers(List<UserRequest> requests) {
        List<com.example.proto.UserRequest> requestList = requests.stream().map(request -> com.example.proto.UserRequest.newBuilder()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setFullName(request.getFirstName().concat(" ").concat(request.getLastName()))
                .setAddress(request.getAddress())
                .setGender(request.getGender())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setAge(request.getAge())
                .build()).collect(Collectors.toList());
        Users users = config.getServiceBlockingStub().createUsers(
                UserRequests.newBuilder().addAllUserRequest(requestList).build());
        List<UserResponse> responses = users.getUserResponseList().stream().map(response -> UserResponse.builder()
                .fullName(response.getFullName())
                .address(response.getAddress())
                .age((int) response.getAge())
                .gender(response.getGender())
                .phoneNumber(response.getPhoneNumber())
                .email(response.getEmail())
                .build()).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UserRequest request) {
        com.example.proto.UserResponse response = config.getServiceBlockingStub().updateUser(com.example.proto.UserRequest.newBuilder()
                .setId(request.getId())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setFullName(request.getFirstName().concat(" ").concat(request.getLastName()))
                .setAddress(request.getAddress())
                .setGender(request.getGender())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setAge(request.getAge())
                .build()
        );
        return new ResponseEntity<>(UserResponse.builder()
                .fullName(response.getFullName())
                .address(response.getAddress())
                .age((int) response.getAge())
                .gender(response.getGender())
                .phoneNumber(response.getPhoneNumber())
                .email(response.getEmail())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponse>> updateUserInBulk(List<UserRequest> requests) {
        List<com.example.proto.UserRequest> requestList = requests.stream().map(request -> com.example.proto.UserRequest.newBuilder()
                .setId(request.getId())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setFullName(request.getFirstName().concat(" ").concat(request.getLastName()))
                .setAddress(request.getAddress())
                .setGender(request.getGender())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setAge(request.getAge())
                .build()).collect(Collectors.toList());
        Users users = config.getServiceBlockingStub().updateUserInBulk(
                UserRequests.newBuilder().addAllUserRequest(requestList).build());
        List<UserResponse> responses = users.getUserResponseList().stream().map(response -> UserResponse.builder()
                .fullName(response.getFullName())
                .address(response.getAddress())
                .age((int) response.getAge())
                .gender(response.getGender())
                .phoneNumber(response.getPhoneNumber())
                .email(response.getEmail())
                .build()).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        return new ResponseEntity<>(config.getServiceBlockingStub().deleteUser(Int64Value.of(id)).getMessage()
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteUserInBulk(List<Long> ids) {
        return new ResponseEntity<>(config.getServiceBlockingStub().deleteUserInBulk(listIds.newBuilder().addAllIds(ids).build()).getMessage()
                , HttpStatus.OK);
    }
}
