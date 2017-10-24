package com.training.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.mongodb.domain.Domain;
import com.training.mongodb.repository.DomainRepository;

@SpringBootApplication
public class MongoDbTrainningApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbTrainningApplication.class, args);
	}
	
	 @Bean
	    CommandLineRunner init(DomainRepository domainRepository) {

	        return args -> {

	            Domain obj = domainRepository.findOne(7L);
	            System.out.println(obj);

	            Domain obj2 = domainRepository.findFirstByDomain("mkyong.com");
	            System.out.println(obj2);

	            int n = domainRepository.updateDomain("mkyong.com", true);
	            System.out.println("Number of records updated : " + n);

	        };

	    }
}
