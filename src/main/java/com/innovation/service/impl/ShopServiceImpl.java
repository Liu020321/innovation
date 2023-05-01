package com.innovation.service.impl;

import com.innovation.mapper.ShopMapper;
import com.innovation.mapper.UserMapper;
import com.innovation.pojo.Shop;
import com.innovation.pojo.User;
import com.innovation.service.ShopService;
import com.innovation.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ShopServiceImpl implements ShopService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Shop> selectShop() {

        SqlSession sqlSession = factory.openSession();

        ShopMapper mapper = sqlSession.getMapper(ShopMapper.class);

        List<Shop> shops = mapper.selectShop();

        sqlSession.close();

        return shops;
    }

    @Override
    public boolean shopCar(int shopId) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        ShopMapper mapper = sqlSession.getMapper(ShopMapper.class);

        List<Shop> shopC = mapper.selectByshopId(shopId);

        System.out.println(shopC);

        System.out.println("shopCount="+ shopC.get(0).getShopCount());
        String shopCount=String.valueOf(shopC.get(0).getShopCount());
        System.out.println(shopCount);

//        if (null == shopCount && "".equals(shopCount)) {
//            shopCount = "1";
//        } else {
//            int count=Integer.parseInt(shopCount);
//            count+=1;
//            shopCount=Integer.toString(count);
//        }

        Shop shop=new Shop();
        shop.setShopId(shopId);
        shop.setShopCount(shopCount);

        mapper.addShop(shop);
        sqlSession.commit();

        sqlSession.close();

        return 1==1;

    }
}
