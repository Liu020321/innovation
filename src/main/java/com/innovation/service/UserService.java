package com.innovation.service;

import com.innovation.pojo.User;
import com.innovation.pojo.shoppingcarts;
import com.innovation.pojo.things;

import java.util.List;

public interface UserService {


    User login(String userName,String password);

    boolean register(User user);

    List<shoppingcarts> selectAllThings();

    boolean addThings(shoppingcarts sc);

    boolean deleteThings(int id);

    boolean deleteAll();

    List<things> getAllThings();

    shoppingcarts getSelf(int id);
}
