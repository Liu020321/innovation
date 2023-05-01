package com.innovation.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("shop")
@Data
public class Shop {
    private int shopId;
    private String shopImg;
    private String shopName;
    private float shopPrice;
    private String shopType;
    private String shopMes;
    private String shopCount;

    public Shop(int shopId) {
        super();
        this.shopId = shopId;
    }

    public Shop(int shopId, String shopImg, String shopName, float shopPrice, String shopType, String shopMes, String shopCount) {
        super();
        this.shopId = shopId;
        this.shopImg = shopImg;
        this.shopName = shopName;
        this.shopPrice = shopPrice;
        this.shopType = shopType;
        this.shopMes = shopMes;
        this.shopCount = shopCount;

    }

}
