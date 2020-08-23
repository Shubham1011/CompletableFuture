package com.example.multithreading;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "hh")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String username;


    String password;

    public MyUser(String username,String password) {
        this.username=username;
        this.password=password;
    }

    public MyUser() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
