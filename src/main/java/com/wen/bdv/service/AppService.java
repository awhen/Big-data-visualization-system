package com.wen.bdv.service;

import java.util.List;
import com.wen.bdv.model.PhoneModelSystem;
import com.wen.bdv.service.AppService;
import com.wen.bdv.wrapper.AppWrapper;

public interface AppService {
	
	//获取用户总数
	int getAllUserCount();
	
	//获取手机型号总数
	int getAllPhoneCount();
	
	//获取app列表
	List<AppWrapper> getAppWrapperByAppName();
	
	//获取手机列表
	List<AppWrapper> getAppWrapperByAppModel();
	
	//获取操作系统列表
	List<AppWrapper> getAppWrapperByAppSystem();
	
	//获取手机型号系统列表
	List<PhoneModelSystem> getPhoneModelSystem();

}
