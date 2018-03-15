package com.wen.bdv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wen.bdv.model.Appserial;
import com.wen.bdv.wrapper.AppWrapper;

public interface AppserialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Appserial record);

    int insertSelective(Appserial record);

    Appserial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Appserial record);

    int updateByPrimaryKey(Appserial record);
    
    int selectAllUserCount();
    
    int selectAllPhoneCount();
    
    List<AppWrapper> selectAppWrapperByAppName();
    
    List<AppWrapper> selectAppWrapperByAppModel();
    
    List<AppWrapper> selectAppWrapperByAppSystem();
    
    List<String> selectAllPhone();
    
    List<String> selectAllPhoneByApp(@Param(value="appName") String appName, 
    		@Param(value="appVersion") String appVersion);
}