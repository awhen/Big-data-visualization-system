package com.wen.bdv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wen.bdv.model.Behavior;
import com.wen.bdv.wrapper.BehaviorWrapper;

public interface BehaviorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Behavior record);

    int insertSelective(Behavior record);

    Behavior selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Behavior record);

    int updateByPrimaryKey(Behavior record);
    
    //默认
    List<BehaviorWrapper> selectBehaviorByPageAndApp0(@Param(value="startPos") int startPos, @Param(value="pageSize") int pageSize);
    
    //按事件分组
    List<BehaviorWrapper> selectBehaviorByPageAndApp1(@Param(value="eventName") String eventName, 
    		@Param(value="startPos") int startPos, @Param(value="pageSize") int pageSize);
    
    //按照事件和app名称分组
    List<BehaviorWrapper> selectBehaviorByPageAndApp2(@Param(value="appName") String appName, @Param(value="eventName") String eventName, 
    		@Param(value="startPos") int startPos, @Param(value="pageSize") int pageSize);
    
    //按照事件和app名称、app版本分组
    List<BehaviorWrapper> selectBehaviorByPageAndApp3(@Param(value="appName") String appName, 
    		@Param(value="appVersion") String appVersion, @Param(value="eventName") String eventName, 
    		@Param(value="startPos") int startPos, @Param(value="pageSize") int pageSize);
    
    int selectBehaviorCountByApp0();
    
    int selectBehaviorCountByApp1(@Param(value="eventName") String eventName);
    
    int selectBehaviorCountByApp2(@Param(value="appName") String appName, @Param(value="eventName") String eventName);
    
    int selectBehaviorCountByApp3(@Param(value="appName") String appName, 
    		@Param(value="appVersion") String appVersion, @Param(value="eventName") String eventName);
    
    int selectAllBehaviorCountByApp(@Param(value="appName") String appName, 
    		@Param(value="appVersion") String appVersion, @Param(value="eventName") String eventName);
    
    List<String> getAllAppFormBehavior();
    
    List<Integer> selectCountByAppNameAndEventName(@Param(value="appName") String appName, 
    		@Param(value="eventName") String eventName);
}