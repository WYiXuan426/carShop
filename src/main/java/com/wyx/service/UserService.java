package com.wyx.service;

import com.wyx.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    Map<String,Object> searchUserByActive(Map<String,Object> paramActive);

    Map<String, Object>  updateUser(Map<String,Object> paramUser);

    Map<String, Object>  insertUser(User paramUser);

    Map<String,Object> loginUser(Map<String,Object> paramUid);

}
