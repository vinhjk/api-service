package com.example.blue_bik_api_service.config;

import com.example.proto.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ClientConfig {

    @Value("${server-host}")
    private String host;

    @Value("${server-port}")
    private int port;

    private ManagedChannel managedChannel;

    private UserServiceGrpc.UserServiceBlockingStub serviceBlockingStub;

    public void start() {
        managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        serviceBlockingStub = UserServiceGrpc.newBlockingStub(managedChannel);
        log.info("gRPC client started, server address: {}:{}", host, port);
    }

    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
        log.info("gRPC client shut down successfully.");
    }

    public UserServiceGrpc.UserServiceBlockingStub getServiceBlockingStub(){
        return this.serviceBlockingStub;
    }
}
