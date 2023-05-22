package com.innovation.mapper;

import com.innovation.pojo.Down;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AlarmMapper {
    @Select("select * from down where id =#{id}")
    Down getOneCount(@Param("id")int id);
}
