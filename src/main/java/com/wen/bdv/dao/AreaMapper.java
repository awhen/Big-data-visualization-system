package com.wen.bdv.dao;

import com.wen.bdv.model.Area;

public interface AreaMapper {
    int insert(Area record);

    int insertSelective(Area record);
}