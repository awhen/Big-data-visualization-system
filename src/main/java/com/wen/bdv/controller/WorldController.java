package com.wen.bdv.controller;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wen.bdv.model.Gps;
import com.wen.bdv.model.SecondData;
import com.wen.bdv.service.BehaviorService;
import com.wen.bdv.service.UserService;
import com.wen.bdv.util.StringUtil;
import com.wen.bdv.wrapper.AppSerialWrapper;

@Controller
public class WorldController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BehaviorService behaviorService;

	//用户散点图
	@RequestMapping("goalluser")
	public String goAllUser(Model model){
		 return "alluser";
	}
	
	//给用户散点图返回数据
	@RequestMapping("getalluserlocate")
	public void getAllUserLocate(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        try {
        	 wirte = response.getWriter();
        	//取出所有gps
        	List<Gps> allGps = userService.getAllAppSerialWithOutSelected();
        	for(Gps a : allGps) {
        		//将非法的gps格式改正
                if(a.getGps().contains("\t")) {
                	String[] c_gps = a.getGps().split("\t");
        			a.setGps(c_gps[0]);
        			userService.changeGps(a);
        			System.out.println(a.getGps());
        		}
        		jsonObject = new JSONObject();
        		String[] s_gps = a.getGps().split(",");
    			jsonObject.put("latitude", Double.parseDouble(s_gps[0]));
        		jsonObject.put("longitude", Double.parseDouble(s_gps[1]));
        		jsonArray.put(jsonObject);
        	}
        } catch (Exception e) {  
        }finally{  
            wirte.print(jsonArray);
            wirte.flush();  
            wirte.close();  
        }  
	}
	
	//给国家分布图返回数据
	@RequestMapping("getuserdetail")
	public void getUserDetail(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;
        AppSerialWrapper appSerialWrapper = new AppSerialWrapper();
        JSONArray jsonArray = new JSONArray(); 
        List<SecondData> SecondDataList = userService.getSecondData(1);
        try {  
            wirte = response.getWriter();  
            for(SecondData sd : SecondDataList) {
            	appSerialWrapper.setCode(sd.getCode());
            	appSerialWrapper.setName(sd.getName());
            	appSerialWrapper.setValue(sd.getValue());
            	appSerialWrapper.setColor(StringUtil.getColor());
            	JSONObject jsonObject = JSONObject.fromObject(appSerialWrapper);
            	jsonArray.put(jsonObject);
            }
            
        } catch (Exception e) {  
        }finally{  
            wirte.print(jsonArray);  
            wirte.flush();  
            wirte.close();  
        }  
	}
}
