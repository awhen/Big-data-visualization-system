package com.wen.bdv.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wen.bdv.dao.AnalysisMapper;
import com.wen.bdv.dao.AppserialMapper;
import com.wen.bdv.dao.BehaviorMapper;
import com.wen.bdv.dao.CustomFunctionMapper;
import com.wen.bdv.dao.EventnameMapper;
import com.wen.bdv.dao.FunctionBehaviorMapper;
import com.wen.bdv.dao.SecondDataMapper;
import com.wen.bdv.model.Analysis;
import com.wen.bdv.model.Behavior;
import com.wen.bdv.model.CustomFunction;
import com.wen.bdv.model.Eventname;
import com.wen.bdv.model.FunctionBehavior;
import com.wen.bdv.model.SecondData;
import com.wen.bdv.service.BehaviorService;
import com.wen.bdv.wrapper.BehaviorWrapper;
@Service("behaviorService")
public class BehaviorServiceImpl implements BehaviorService {

	@Resource
	EventnameMapper eventnameDao;
	
	@Resource
	AnalysisMapper analysisDao;
	
	@Resource
	SecondDataMapper secondDataDao;
	
	@Resource
	BehaviorMapper behaviorDao;
	
	@Resource
	AppserialMapper appSerialDao;
	
	@Resource
	CustomFunctionMapper customFunctionDao;
	
	@Resource
	FunctionBehaviorMapper functionBehaviorDao;
	
	@Override
	public List<Eventname> getAllEventname() {
		return eventnameDao.selectAllEventname();
	}

	@Override
	public void addAnalysis(Analysis analysis) {
		analysisDao.insertSelective(analysis);
	}

	@Override
	public void addSecondData(SecondData secondData) {
		secondDataDao.insertSelective(secondData);
	}

	@Override
	public SecondData checkSecondData(SecondData secondData) {
		return secondDataDao.checkMySecondData(secondData);
	}

	@Override
	public void updateSecondData(SecondData secondData) {
		secondDataDao.updateByPrimaryKeySelective(secondData);
	}

	@Override
	public List<String> getAllPhone() {
		return appSerialDao.selectAllPhone();
	}
	
	@Override
	public List<BehaviorWrapper> getBehaviorByPageAndApp(String appName, String appVersion, String eventName, int startPos, int pageSize) {
		List<BehaviorWrapper> BehaviorWrappers = new ArrayList<BehaviorWrapper>();
		if(!eventName.equals("") && eventName!= null) {
			BehaviorWrappers = behaviorDao.selectBehaviorByPageAndApp1(eventName, startPos, pageSize);
			if(!appName.equals("") && appName!= null) {
				BehaviorWrappers = behaviorDao.selectBehaviorByPageAndApp2(appName, eventName, startPos, pageSize);
				if(!appVersion.equals("") && appVersion!= null) {
					BehaviorWrappers = behaviorDao.selectBehaviorByPageAndApp3(appName,appVersion, eventName, startPos, pageSize);
					/*System.out.println("---------eventName---------:" + eventName + "---------appName---------:" + appName + "--------appVersion---------:" + appVersion);*/
				}
			}
		}
		else {
			BehaviorWrappers = behaviorDao.selectBehaviorByPageAndApp0(startPos, pageSize);
		}
		return BehaviorWrappers;
	}

	@Override
	public int getBehaviorCountByApp(String appName, String appVersion, String eventName) {
		int count;
		if(!eventName.equals("") && eventName != null) {
			count = behaviorDao.selectBehaviorCountByApp1(eventName);
			if(!appName.equals("") && appName != null) {
				count = behaviorDao.selectBehaviorCountByApp2(appName, eventName);
				if(!appVersion.equals("") && appVersion != null) {
					count = behaviorDao.selectBehaviorCountByApp3(appName,appVersion, eventName);
				}
			}
		}
		else {
			count = behaviorDao.selectBehaviorCountByApp0();
		}
		return count;
	}

	@Override
	public List<String> getAllPhoneByApp(String appName, String appVersion) {
		return appSerialDao.selectAllPhoneByApp(appName, appVersion);
	}

	@Override
	public void saveCustomFunction(CustomFunction customFunction) {
		customFunctionDao.insertCustomFunction(customFunction);
	}

	@Override
	public void saveFunctionBehavior(FunctionBehavior functionBehavior) {
		functionBehaviorDao.insert(functionBehavior);
	}

	@Override
	public List<Eventname> getEventnameByName(String eventName) {
		return eventnameDao.selectEventnameByName(eventName);
	}

	@Override
	public List<CustomFunction> getCustomFunction() {
		return customFunctionDao.selectCustomFunction();
	}

	@Override
	public void deletefunction(int functionId) {
		functionBehaviorDao.deleteMFunctionBehavior(functionId);
		customFunctionDao.deleteMfunction(functionId);
	}

	@Override
	public List<String> getAllAppFormBehavior() {
		return behaviorDao.getAllAppFormBehavior();
	}

	@Override
	public List<String> getBehaviorByFunction(int functionId) {
		return functionBehaviorDao.selectBehaviorByFunction(functionId);
	}

	@Override
	public List<Integer> getCountByAppNameAndEventName(String appName,
			String eventName) {
		return behaviorDao.selectCountByAppNameAndEventName(appName, eventName);
	}

	@Override
	public CustomFunction getFuntion(int functionId) {
		return customFunctionDao.selectByPrimaryKey(functionId);
	}

	@Override
	public List<Eventname> getAllEventnameByFunctionId(int functionId) {
		return eventnameDao.selectEventnameByFunctionId(functionId);
	}

	@Override
	public void updateCustomFunction(CustomFunction customFunction) {
		customFunctionDao.updateByPrimaryKeySelective(customFunction);
	}

	@Override
	public void deleteFunctionBehavior(FunctionBehavior functionBehavior) {
		functionBehaviorDao.deleteFunctionBehaviorByMe(functionBehavior);
	}

	@Override
	public void deleteFunctionBehaviorByFunctionId(int functionId) {
		functionBehaviorDao.deleteMFunctionBehavior(functionId);
	}

	@Override
	public int getAllBehaviorCountByApp(String appName, String appVersion,
			String eventName) {
		return behaviorDao.selectAllBehaviorCountByApp(appName, appVersion, eventName);
	}

}
