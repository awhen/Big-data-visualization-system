package com.wen.bdv.service;

import java.util.List;

import com.wen.bdv.dao.AnalysisMapper;
import com.wen.bdv.model.Analysis;
import com.wen.bdv.model.Behavior;
import com.wen.bdv.model.CustomFunction;
import com.wen.bdv.model.Eventname;
import com.wen.bdv.model.FunctionBehavior;
import com.wen.bdv.model.SecondData;
import com.wen.bdv.wrapper.BehaviorWrapper;

public interface BehaviorService {
	
	//获取所有事件
	List<Eventname> getAllEventname();
	
	//获取所有的手机型号
	List<String> getAllPhone();
	
	//根据条件筛选行为
	List<BehaviorWrapper> getBehaviorByPageAndApp(String appName, String appVersion, String eventName, int startPos, int pageSize);
	
	//计算条件筛选行为的个数
	int getBehaviorCountByApp(String appName, String appVersion, String eventName);
	
	//计算总数
	int getAllBehaviorCountByApp(String appName, String appVersion, String eventName);
	
	List<String> getAllPhoneByApp(String appName, String appVersion);
	
	//添加AnalysisMapper
	void addAnalysis(Analysis analysis);
	
	//添加二级数据
	void addSecondData(SecondData secondData);
	
	//判断二级数据是否已经存在
	SecondData checkSecondData(SecondData secondData);
	
	//更新SecondData
	void updateSecondData(SecondData secondData);
	
	//保存CustomFunction
	void saveCustomFunction(CustomFunction customFunction);
	
	//保存CustomFunction
	void saveFunctionBehavior(FunctionBehavior functionBehavior);
	
	//模糊查询事件
	List<Eventname> getEventnameByName(String eventName);
	
	//取出全部功能
	List<CustomFunction> getCustomFunction();
	
	//删除一个功能
	void deletefunction(int functionId);
	
	//从用户行为中获取app
	List<String> getAllAppFormBehavior();
	
	//根据功能查找事件
	List<String> getBehaviorByFunction(int functionId);
	
	//根据事件和app获取次数
	List<Integer> getCountByAppNameAndEventName(String appName, String eventName);
	
	//根据id获取function
	CustomFunction getFuntion(int functionId);
	
	//根据functionId 获取事件名称
	List<Eventname> getAllEventnameByFunctionId(int functionId);
	
	//更新function
	void updateCustomFunction(CustomFunction customFunction);
	
	//删除一个功能-事件关联
	void deleteFunctionBehavior(FunctionBehavior functionBehavior);
	
	//根据功能id删除
	void deleteFunctionBehaviorByFunctionId(int functionId);

}
