package com.innovation.service;

import com.innovation.pojo.User;

public interface UserService {


    User login(String userName,String password);

    boolean register(User user);

}
