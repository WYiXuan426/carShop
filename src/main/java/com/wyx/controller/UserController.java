package com.wyx.controller;

import com.wyx.model.User;
import com.wyx.service.UserService;
import com.wyx.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/searchUserByActive" )
    public Map<String,Object> searchUserByActive(@RequestParam(required = false) Map<String,Object> paramUser){
        return userService.searchUserByActive(paramUser);
    }
    @PostMapping(value = "/insertUser")
    public Map<String, Object>  insertUser(@ModelAttribute User paramUser,@RequestParam(value = "realPassword") String realPassword) {
        paramUser.setPassword(realPassword);
        return userService.insertUser(paramUser);
    }
    @PostMapping(value = "/loginUser")
    public Map<String,Object>  loginUser(@RequestParam Map<String,Object> paramUid){
        return userService.loginUser(paramUid);
    }
}
