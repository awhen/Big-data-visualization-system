package com.wen.bdv.dao;

import java.util.List;

import com.wen.bdv.model.CustomFunction;

public interface CustomFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomFunction record);

    int insertSelective(CustomFunction record);

    CustomFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomFunction record);

    int updateByPrimaryKey(CustomFunction record);
    
    int insertCustomFunction(CustomFunction record);
    
    List<CustomFunction> selectCustomFunction();
    
    void deleteMfunction(int functionId);
}