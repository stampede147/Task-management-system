package ru.ekudashov.taskms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.FilterChainProxy;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"ru.ekudashov.taskms.configuration"})
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
    }
}