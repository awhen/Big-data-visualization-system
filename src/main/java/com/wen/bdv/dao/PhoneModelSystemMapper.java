package com.wen.bdv.dao;

import java.util.List;

import com.wen.bdv.model.PhoneModelSystem;

public interface PhoneModelSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneModelSystem record);

    int insertSelective(PhoneModelSystem record);

    PhoneModelSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhoneModelSystem record);

    int updateByPrimaryKey(PhoneModelSystem record);
    
    List<PhoneModelSystem> selectPhoneModelSystem();
}