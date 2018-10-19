package com.sdproject.entities;

import com.sdproject.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages={"com.sdproject.controllers"})
@EntityScan(basePackages={"com.sdproject.entities"})
@EnableJpaRepositories("com.sdproject.repositories")
public class Main {

    public static void main(String args[]) {

        SpringApplication.run(Main.class, args);

    }
}
