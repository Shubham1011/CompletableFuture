package com.example.multithreading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public MyUser add(){
        MyUser myUser=new MyUser("shu","as");
        System.out.println(myUser.password);
         return userRepo.save(myUser);
    }

    @PostMapping(value = "/parse",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filetrace(@RequestParam(name = "files") MultipartFile[] multipartFiles){
        System.out.println(multipartFiles[0]);
        System.out.println(multipartFiles[1]);
        for(MultipartFile multipartFile:multipartFiles){
            userService.addusers(multipartFile);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping(value = "/parsewithoutasync",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filetrasce(@RequestParam(name = "files") MultipartFile[] multipartFiles){
        System.out.println(multipartFiles[0]);
        System.out.println(multipartFiles[1]);
        for(MultipartFile multipartFile:multipartFiles){
            userService.addusedrs(multipartFile);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/getmyusers")
    public CompletableFuture<ResponseEntity>  findusers(){
        return userService.myUsers().thenApply(ResponseEntity::ok);
    }
    @GetMapping("/getmy")
    public CompletableFuture<List<MyUser>> fisndusers(){
        CompletableFuture<List<MyUser>> listCompletableFuture=userService.myUsers();
        CompletableFuture<List<MyUser>> listCompletableFuture1=userService.myUsers();
        CompletableFuture<List<MyUser>> listCompletableFuture2=userService.myUsers();
   //     CompletableFuture.allOf(listCompletableFuture,listCompletableFuture1,listCompletableFuture2).join();
        return listCompletableFuture;
    }


}
