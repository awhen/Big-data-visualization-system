package com.wen.bdv.controller;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wen.bdv.model.PhoneModelSystem;
import com.wen.bdv.service.AppService;
import com.wen.bdv.util.SpringRedisUtil;
import com.wen.bdv.wrapper.AppWrapper;

@Controller
public class IndexController {
	
	@Autowired
	AppService appService;
	
	@RequestMapping("getindexpei")
	public void getIndexPei(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;  
        JSONArray jsonArray = new JSONArray();
        List<AppWrapper> appNameList = appService.getAppWrapperByAppName();
		List<AppWrapper> appModelList = appService.getAppWrapperByAppModel();
		List<AppWrapper> appSystemList = appService.getAppWrapperByAppSystem();
        try {  
            wirte = response.getWriter();
            JSONArray appNameJsonArray = new JSONArray();
            for(AppWrapper ap : appNameList) {
            	JSONObject json = new JSONObject();
            	json.put("value", ap.getSvalue());
            	json.put("name", ap.getSkey());
            	appNameJsonArray.put(json);
            }
            JSONArray appModelJsonArray = new JSONArray();
            for(AppWrapper ap : appModelList) {
            	JSONObject json = new JSONObject();
            	json.put("value", ap.getSvalue());
            	json.put("name", ap.getSkey());
            	appModelJsonArray.put(json);
            }
            JSONArray appSystemJsonArray = new JSONArray();
            for(AppWrapper ap : appSystemList) {
            	JSONObject json = new JSONObject();
            	json.put("value", ap.getSvalue());
            	json.put("name", ap.getSkey());
            	appSystemJsonArray.put(json);
            }
            jsonArray.put(appNameJsonArray);
            jsonArray.put(appModelJsonArray);
            jsonArray.put(appSystemJsonArray);
        } catch (Exception e) {  
        }finally{  
            wirte.print(jsonArray);  
            wirte.flush();  
            wirte.close();  
        }
	}
	
	@RequestMapping("goindexpei")
	public String goIndexPei(){
		return "indexpie";
	}

	//首页数据
	@RequestMapping("goindex")
	public String goIndex(Model model){
		//直接从数据库取出
		//终端指数
		int userCount = appService.getAllUserCount();
		//设备指数
		int phoneCount = appService.getAllPhoneCount();
		List<AppWrapper> appNameList = appService.getAppWrapperByAppName();
		List<AppWrapper> appModelList = appService.getAppWrapperByAppModel();
		List<AppWrapper> appSystemList = appService.getAppWrapperByAppSystem();
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
		int iOS = 0;
		int Android = 0;
		//app种类
		for(AppWrapper aw : appNameList) {
			float num= (float)Integer.parseInt(aw.getSvalue())/userCount;   
			String s = df.format(num*100); 
			aw.setSvalue(s + "%");
		}
		//型号
		for(int i = 0; i < appModelList.size(); i++) {
			float num= (float)Integer.parseInt(appModelList.get(i).getSvalue())/userCount;   
			String s = df.format(num*100); 
			appModelList.get(i).setSvalue(s + "%");
		}
		//系统
		for(AppWrapper aw : appSystemList) {
			String[] keys = aw.getSkey().split(" ");
			if(keys[0].equals("iOS")) iOS+=Integer.parseInt(aw.getSvalue());
			if(keys[0].equals("Android")) Android+=Integer.parseInt(aw.getSvalue());
			float num= (float)Integer.parseInt(aw.getSvalue())/userCount;
			String s = df.format(num*100);
			aw.setSvalue(s + "%");
		}
		//统计安卓iOS所占的比例
		float ios= (float)iOS/(iOS + Android);
		String s_ios = df.format(ios*100);
		float android= (float)Android/(iOS + Android);
		String s_android = df.format(android*100);
		model.addAttribute("iOS", s_ios);
		model.addAttribute("Android", s_android);
		model.addAttribute("userCount", userCount);
		model.addAttribute("phoneCount", phoneCount);
		model.addAttribute("appNameList", appNameList);
		model.addAttribute("appModelList", appModelList);
		model.addAttribute("appSystemList", appSystemList);
		return "index";
	}
}
