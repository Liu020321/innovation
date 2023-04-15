package com.innovation.service;

import com.innovation.pojo.User;
import com.innovation.pojo.shoppingcarts;
import com.innovation.pojo.things;
import org.apache.ibatis.annotations.Param;

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

    boolean ifOk(String name);

    boolean ifUpdateOk(String name);

    int getOnePrice(String name);

    String getOneType(String name);

    List<things> getType(String type);
}
