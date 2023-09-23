package com.wyx.mapper;

import com.wyx.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository("userMapper")
public interface UserMapper {
    List<Map<String,Object>> searchUserByActive(Map<String,Object> paramActive);

    Integer updateUser(Map<String,Object> paramUser);

    Integer insertUser(User paramUser);

    Map<String,Object> loginUser(Map<String,Object> paramUid);


}
