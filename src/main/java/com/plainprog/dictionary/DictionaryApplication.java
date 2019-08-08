package com.plainprog.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DictionaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}

}
