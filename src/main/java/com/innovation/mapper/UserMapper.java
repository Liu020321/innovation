package com.innovation.mapper;

import com.innovation.pojo.User;
import com.innovation.pojo.shoppingcarts;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {


    /**
     * 查询所有用户信息
     *
     * @return
     */

    @Select("select * from tb_user where user_name=#{userName} and password=#{password} ")
    @ResultMap("userResultMap")
    User login(@Param("userName") String userName, @Param("password") String password);

    @Select("select * from tb_user where user_name = #{userName}")
    User selectByUserName(String userName);

    @Insert("insert into tb_user values(null,#{userName},#{password},#{status})")
    void add(User user);

    @Select("select * from shoppingcarts")
    List<shoppingcarts> selectAllThings();

    @Insert("insert into shoppingcarts values(#{id},#{name},#{type},#{price},#{count})")
    boolean addThings(shoppingcarts sc);

    @Delete("delete from shoppingcarts where id=#{id}")
    boolean deleteThings(int id);

    @Delete("delete from shoppingcarts")
    boolean deleteAll();
}
