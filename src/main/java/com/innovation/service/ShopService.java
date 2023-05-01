package com.innovation.service;

import com.innovation.pojo.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> selectShop();

    boolean shopCar(int shopId);

    List<Shop> selectAllCount();

    List<Shop> selectByVoice(String information);
}
