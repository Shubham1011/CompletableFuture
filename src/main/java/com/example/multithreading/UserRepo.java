package com.example.multithreading;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.multithreading.MyUser;
public interface UserRepo extends JpaRepository<MyUser,String>{

}


