package com.innovation.service.impl;

import com.innovation.mapper.UserMapper;
import com.innovation.pojo.User;
import com.innovation.pojo.shoppingcarts;
import com.innovation.service.UserService;
import com.innovation.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    public User login(String userName, String password) {
        SqlSession sqlSession = factory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User userLogin = userMapper.login(userName, password);

        sqlSession.close();

        return userLogin;

    }

    @Override
    public boolean register(User user) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4. 判断用户名是否存在
        User u = mapper.selectByUserName(user.getUserName());

        if(u == null){
            // 用户名不存在，注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();

        return u == null;
    }

    @Override
    public List<shoppingcarts> selectAllThings() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<shoppingcarts> list=mapper.selectAllThings();

        sqlSession.close();

        return list;
    }

    public boolean addThings(shoppingcarts sc){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        boolean b=mapper.addThings(sc);
        sqlSession.commit();
        sqlSession.close();

        return b;
    }

    public boolean deleteThings(int id){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        boolean b=mapper.deleteThings(id);
        sqlSession.commit();
        sqlSession.close();

        return b;
    }

    public boolean deleteAll(){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        boolean b=mapper.deleteAll();

        sqlSession.commit();
        sqlSession.close();

        return b;
    }

}
