package com.innovation.service;

import com.innovation.pojo.User;

import java.util.List;

public interface UserService {


    User login(String userName,String password,Integer status);
}
