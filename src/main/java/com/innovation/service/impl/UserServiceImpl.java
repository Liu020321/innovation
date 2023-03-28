package com.innovation.service.impl;

import com.innovation.mapper.UserMapper;
import com.innovation.pojo.User;
import com.innovation.service.UserService;
import com.innovation.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    public User login(String userName, String password, Integer status) {
        SqlSession sqlSession = factory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User userLogin = userMapper.login(userName, password, status);

        sqlSession.close();

        return userLogin;

    }

}
