package com.example.multithreading;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Async("shubhamthread")
    public CompletableFuture<List<MyUser>> addusers(MultipartFile multipartFile) {
        long start = System.currentTimeMillis();
        List<MyUser>  uList=parsecsv(multipartFile);
        logger.info("Done parsing csv now savinf to db "+Thread.currentThread().getName());
        long end = System.currentTimeMillis();
        userRepo.saveAll(uList);

        logger.info("Time taken "+(end-start));
        return CompletableFuture.completedFuture(uList);
    }

    public CompletableFuture<List<MyUser>> addusedrs(MultipartFile multipartFile) {
        long start = System.currentTimeMillis();
        List<MyUser>  uList=parsecsv(multipartFile);
        logger.info("Done parsing csv now savinf to db "+Thread.currentThread().getName());
        long end = System.currentTimeMillis();
        userRepo.saveAll(uList);

        logger.info("Time taken "+(end-start));
        return CompletableFuture.completedFuture(uList);
    }

    public List<MyUser> getnorma(){
        return userRepo.findAll();
    }

    @Async("shubhamthread")
    public CompletableFuture<List<MyUser>> myUsers(){
        logger.info("Thread working is "+ Thread.currentThread().getName());
        return CompletableFuture.completedFuture(userRepo.findAll());
    }

    private List<MyUser> parsecsv(MultipartFile multipartFile) {
        List<MyUser>myUsers =new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                // use comma as separator
                String[] user = line.split(",");
                MyUser myUser = new MyUser(user[0], user[1]);
                myUsers.add(myUser);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return myUsers;
    }
}


