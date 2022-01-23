package com.chufinfo.sorting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author kenyon
 */
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
public class SortingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SortingApplication.class, args);
    }

}