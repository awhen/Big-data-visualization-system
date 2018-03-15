package com.wen.bdv.dao;

import java.util.List;

import com.wen.bdv.model.FunctionBehavior;

public interface FunctionBehaviorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FunctionBehavior record);

    int insertSelective(FunctionBehavior record);

    FunctionBehavior selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FunctionBehavior record);

    int updateByPrimaryKey(FunctionBehavior record);
    
    void deleteMFunctionBehavior(int functionId);
    
    List<String> selectBehaviorByFunction(int functionId);
    
    void deleteFunctionBehaviorByMe(FunctionBehavior functionBehavior);
}