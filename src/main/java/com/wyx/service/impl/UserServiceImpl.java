package com.wyx.service.impl;

import com.wyx.mapper.UserMapper;
import com.wyx.model.User;
import com.wyx.service.UserService;
import com.wyx.util.NumberUtil;
import com.wyx.util.PasswordUtil;
import com.wyx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String, Object> searchUserByActive(Map<String, Object> paramActive) {
        Map<String,Object> searchUserMap = new HashMap<String,Object>();
        List<Map<String,Object>> searchUserList = userMapper.searchUserByActive(paramActive);
        if (searchUserList.size()>0){
            searchUserMap.put("data",searchUserList);
        }else {
            searchUserMap.put("error","查询不到用户，请重试!");
        }
        return searchUserMap;
    }

    @Override
    public Map<String, Object>  updateUser(Map<String, Object> paramUser) {


        return null;
    }

    @Override
    public Map<String, Object>  insertUser(User paramUser) {
        Map<String, Object> insertMap = new HashMap<String, Object>();
        //生成一个随机id
        String userId = NumberUtil.getRandomNumber();
        paramUser.setRegTime(TimeUtil.dateUtil());
        Map<String, Object> userMap = userMapper.selectUser(paramUser);

        //判断是否重复的用户id，邮箱，手机号
        if (userMap!=null) {
            //如果用户id相同则从新调用该方法新生成一个用户id即可
            paramUser.setUserId(userId);
            if (userMapper.selectUser(paramUser) != null) {
                insertUser(paramUser);
            } else if (userMap.get("telephone") != null) {
                insertMap.put("error", "该手机号已被绑定，请注册时更换手机号");
            }else {
                insertMap.put("error","注册失败");
            }
        }else {
            paramUser.setUserId(userId);
            if (userMapper.selectUser(paramUser) != null) {
                insertUser(paramUser);
            } else {
                Integer insertResult = userMapper.insertUser(paramUser);

                if (insertResult > 0) {
                    insertMap.put("data", "注册成功");

                } else {
                    insertMap.put("error", "注册失败,请重试");
                }
            }
        }
        return insertMap;
    }

    @Override
    public Map<String, Object> loginUser(Map<String, Object> paramUid) {
        Map<String, Object> loginMap = new HashMap<String, Object>();
//        byte[] realPwd = new byte[0];
        String realPwd = PasswordUtil.encrypt((String) paramUid.get("password"));
        paramUid.remove("password");
        paramUid.put("password",realPwd);
        Map<String, Object> userMap =userMapper.loginUser(paramUid);
        if(userMap!=null&&userMap.size()>0){
            loginMap.put("data",userMapper.loginUser(paramUid));
        }else if(userMapper.searchUserByActive(paramUid).size()>0){
            loginMap.put("error","密码错误请重新输入");
        }else {
            loginMap.put("error","登陆失败,可能是用户名/邮箱/手机号不存在，请重新输入");
        }
        return loginMap;
    }
}
