package com.wen.bdv.dao;

import java.util.List;

import com.wen.bdv.model.Eventname;

public interface EventnameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Eventname record);

    int insertSelective(Eventname record);

    Eventname selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Eventname record);

    int updateByPrimaryKey(Eventname record);
    
    List<Eventname> selectAllEventname();
    
    List<Eventname> selectEventnameByName(String eventName);
    
    List<Eventname> selectEventnameByFunctionId(int functionId);
}