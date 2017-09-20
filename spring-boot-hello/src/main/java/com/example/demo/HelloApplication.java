package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//EnableDiscoveryClient	创建DiscoveryClient接口的实现，对象为Eureka客户端EurekaDiscoveryClient实例
@EnableDiscoveryClient
public class HelloApplication {

	public static void main(String[] args) {
		System.out.println("");
		SpringApplication.run(HelloApplication.class, args);
	}
}
