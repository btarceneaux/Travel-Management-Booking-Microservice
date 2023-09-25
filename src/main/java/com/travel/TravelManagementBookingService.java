package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan(basePackages = "com.travel.bean")
@EnableJpaRepositories(basePackages = "com.travel.repository")
@EnableDiscoveryClient
public class TravelManagementBookingService
{
    public static void main(String[] args)
    {
        SpringApplication.run(TravelManagementBookingService.class, args);
    }

}