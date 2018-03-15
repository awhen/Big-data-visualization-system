package com.wen.bdv.controller;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wen.bdv.model.SecondData;
import com.wen.bdv.service.UserService;
import com.wen.bdv.util.SortUtil;

@Controller
public class TimeController {
	
	@Autowired
	UserService userService;
	
	//跳转到时间页面
	@RequestMapping("gotime")
	public String goTime() {
		return "time";
	}
	
	//获取信息
	@RequestMapping("gethours")
	public void getHours(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;  
        JSONArray hours = new JSONArray();
        JSONArray json = null;
        List<SecondData> secondDatas = userService.getSecondData(3);
		secondDatas = SortUtil.BubbleSort(secondDatas);
		//将0点和24点相加
        secondDatas.get(secondDatas.size() - 1).setValue("" + 
        (Integer.parseInt(secondDatas.get(secondDatas.size() - 1).getValue()) + Integer.parseInt(secondDatas.get(0).getValue())));
        secondDatas.remove(0);
        try {  
            wirte = response.getWriter();
            for(SecondData secondData : secondDatas){
            	json = new JSONArray(); 
            	json.put(secondData.getName());
            	json.put(Integer.parseInt(secondData.getValue()));
            	hours.put(json);
    		}
        } catch (Exception e) {  
        }finally{
            wirte.print(hours);
            wirte.flush();  
            wirte.close();  
        }  
	}
	
	//获取最大值
	@RequestMapping("getmax")
	public void getMax(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;  
        JSONObject max = new JSONObject();
        List<SecondData> secondDatas = userService.getSecondData(3);
        try {  
            wirte = response.getWriter();
            for(int i = 0; i < secondDatas.size() - 1; i++){
            	if(Integer.parseInt(secondDatas.get(i).getValue()) < Integer.parseInt(secondDatas.get(i+1).getValue())) {
            		max.put("max",(Integer.parseInt(secondDatas.get(i + 1).getValue())) * 4);
            	}
            }
        } catch (Exception e) {  
        }finally{
            wirte.print(max);
            wirte.flush();  
            wirte.close();  
        }  
	}

}
