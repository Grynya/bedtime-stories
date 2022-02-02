package com.bedtimestories.demo.domain;

import com.bedtimestories.demo.domain.request.UserRequest;
import com.bedtimestories.demo.model.User;
import com.bedtimestories.demo.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class UserController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ApiOperation(value = "Get all users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return new ResponseEntity<>(userService.users(), HttpStatus.OK);
    }


    @ApiOperation(value = "Create user")
    @PostMapping("/website-user")
    public ResponseEntity<List<User>> add(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.add(userRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/website-user/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable Long id){
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

}