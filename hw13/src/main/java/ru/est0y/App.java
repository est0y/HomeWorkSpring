package ru.est0y;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
/*
Юзер с правами только на просмотр книг:
user
password

Юзер со правами на просмотр и запись книг:
editor
password
*/
@EnableMongoRepositories
@EnableMongock
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
