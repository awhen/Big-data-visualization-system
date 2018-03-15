package com.wen.bdv.dao;

import java.util.List;

import com.wen.bdv.model.SecondData;

public interface SecondDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondData record);

    int insertSelective(SecondData record);

    SecondData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondData record);

    int updateByPrimaryKey(SecondData record);
    
    SecondData checkMySecondData(SecondData secondData);
    
    List<SecondData> selectSecondData(int analysisId);
}