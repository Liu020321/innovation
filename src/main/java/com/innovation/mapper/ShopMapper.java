package com.innovation.mapper;

import com.innovation.pojo.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShopMapper {
    @Select("select * from innovation.shop")
    @ResultMap("shopResultMap")
    List<Shop> selectShop();

    @Insert("UPDATE innovation.shop set shopCount = #{shopCount} where shopId=#{shopId}")
    @ResultMap("shopResultMap")
    void addShop(@Param("shopCount") String shopCount, @Param("shopId") int shopId);

    @Select("select shop.shopCount from innovation.shop where shop.shopId=#{shopId}")
    @ResultMap("shopResultMap")
    Shop selectByshopId(@Param("shopId") int shopId);

    @Select("select  * from innovation.shop where shop.shopCount != ''")
    @ResultMap("shopResultMap")
    List<Shop> selectAllCount();
}
