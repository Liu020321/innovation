package com.innovation.service.impl;

import com.innovation.mapper.AlarmMapper;
import com.innovation.mapper.ShopMapper;
import com.innovation.pojo.Down;
import com.innovation.service.AlarmService;
import com.innovation.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AlarmServiceImpl implements AlarmService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Down getOneCount(int id) {
        SqlSession sqlSession = factory.openSession();

        AlarmMapper mapper = sqlSession.getMapper(AlarmMapper.class);

        Down count=mapper.getOneCount(id);
        sqlSession.close();
        return count;
    }
}
