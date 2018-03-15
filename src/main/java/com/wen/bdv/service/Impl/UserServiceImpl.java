package com.wen.bdv.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wen.bdv.dao.GpsMapper;
import com.wen.bdv.dao.SecondDataMapper;
import com.wen.bdv.model.Gps;
import com.wen.bdv.model.SecondData;
import com.wen.bdv.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	SecondDataMapper secondDataDao;
	
	@Resource
	GpsMapper gpsDao;
	
	@Override
	public List<SecondData> getSecondData(int analysisId) {
		return secondDataDao.selectSecondData(analysisId);
	}

	@Override
	public List<Gps> getAllAppSerial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllGPS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gps> getAllAppSerialWithOutSelected() {
		return gpsDao.selectAllAppSerialWithOutSelected();
	}

	@Override
	public void changeGps(Gps gps) {
		gpsDao.updateByPrimaryKeySelective(gps);
	}

}
