package com.wen.bdv.service;

import java.util.List;

import com.wen.bdv.model.Gps;
import com.wen.bdv.model.SecondData;

public interface UserService {
	
	//获取所有用户
	List<Gps> getAllAppSerial();
	
	//获取没有经过选择的用户
	List<Gps> getAllAppSerialWithOutSelected();
	
	//改正gps
	void changeGps(Gps gps);
	
	//获取所有gps
    List<String> getAllGPS();
    
    //获取二级数据
    List<SecondData> getSecondData(int analysisId);
}
