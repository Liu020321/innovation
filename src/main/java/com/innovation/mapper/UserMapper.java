package com.innovation.mapper;

import com.innovation.pojo.User;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    /**
     * 查询所有用户信息
     * @return
     */

    @Select("select * from tb_user where user_name=#{userName} and password=#{#password} and status=#{status}")
    @ResultMap("userResultMap")
    User login(String userName,String password,Integer status);
}
