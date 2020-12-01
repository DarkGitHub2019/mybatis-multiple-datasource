package com.lyle.sample.mybatis.service;

import com.lyle.sample.mybatis.mapper.test2.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public Long queryUserCount(){
        return  userMapper.queryTotalCount();
    }
}
