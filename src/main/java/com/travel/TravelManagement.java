package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.travel.bean")
@EnableJpaRepositories(basePackages = "com.travel.repository")
public class TravelManagement
{
    public static void main(String[] args)
    {
        SpringApplication.run(TravelManagement.class, args);
    }

}