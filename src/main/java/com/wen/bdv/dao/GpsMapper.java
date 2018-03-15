package com.wen.bdv.dao;

import java.util.List;

import com.wen.bdv.model.Gps;

public interface GpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gps record);

    int insertSelective(Gps record);

    Gps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gps record);

    int updateByPrimaryKey(Gps record);
    
    List<Gps> selectAllAppSerialWithOutSelected();
}