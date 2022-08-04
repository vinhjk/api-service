package com.example.blue_bik_api_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcClientCommandLineRunner implements CommandLineRunner {

    private final ClientConfig config;

    @Override
    public void run(String... args) {
        config.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                config.shutdown();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }));
    }
}
