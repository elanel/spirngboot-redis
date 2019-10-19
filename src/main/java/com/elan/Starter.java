package com.elan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication//springboot启动器注解
@EnableCaching
public class Starter {
public static void main(String[] args) {
	SpringApplication.run(Starter.class, args);
}
}
