package com.wen.bdv.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wen.bdv.dao.AppserialMapper;
import com.wen.bdv.dao.PhoneModelSystemMapper;
import com.wen.bdv.model.PhoneModelSystem;
import com.wen.bdv.service.AppService;
import com.wen.bdv.wrapper.AppWrapper;

@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Resource
	AppserialMapper appSerialMapper;
	
	@Resource
	PhoneModelSystemMapper phoneModelSystemDao;

	@Override
	public List<PhoneModelSystem> getPhoneModelSystem() {
		return phoneModelSystemDao.selectPhoneModelSystem();
	}

	@Override
	public int getAllUserCount() {
		return appSerialMapper.selectAllUserCount();
	}

	@Override
	public int getAllPhoneCount() {
		return appSerialMapper.selectAllPhoneCount();
	}

	@Override
	public List<AppWrapper> getAppWrapperByAppName() {
		return appSerialMapper.selectAppWrapperByAppName();
	}

	@Override
	public List<AppWrapper> getAppWrapperByAppModel() {
		return appSerialMapper.selectAppWrapperByAppModel();
	}

	@Override
	public List<AppWrapper> getAppWrapperByAppSystem() {
		return appSerialMapper.selectAppWrapperByAppSystem();
	}

}
