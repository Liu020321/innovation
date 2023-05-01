package com.innovation.service;

import com.innovation.pojo.User;
import com.innovation.pojo.Shop;
import com.innovation.pojo.things;

import java.util.List;

public interface UserService {


    User login(String userName,String password);

    boolean register(User user);

}
