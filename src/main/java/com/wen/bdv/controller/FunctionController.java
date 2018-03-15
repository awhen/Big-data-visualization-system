package com.wen.bdv.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wen.bdv.model.CustomFunction;
import com.wen.bdv.model.Eventname;
import com.wen.bdv.model.FunctionBehavior;
import com.wen.bdv.model.SecondData;
import com.wen.bdv.service.BehaviorService;
import com.wen.bdv.service.UserService;
import com.wen.bdv.util.StringUtil;
import com.wen.bdv.wrapper.EventNameWrapper;
import com.wen.bdv.wrapper.FunctionWrapper;

@Controller
public class FunctionController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BehaviorService behaviorService;
	
	//前往修改功能界面
	@RequestMapping("gochangefunction")
	public String goChangeFunction(Model model, @RequestParam("functionId") String functionId) {
		change(model, functionId);
		return "changefunction";
	}

	private void change(Model model, String functionId) {
		CustomFunction function = behaviorService.getFuntion(Integer.parseInt(functionId));
		List<EventNameWrapper> elist = new ArrayList<EventNameWrapper>();
		List<Eventname> e_elist = behaviorService.getAllEventname();
		List<Eventname> c_elist = behaviorService.getAllEventnameByFunctionId(function.getId());
		EventNameWrapper eventNameWrapper = null;
		for(Eventname ee : e_elist) {
			eventNameWrapper = new EventNameWrapper();
			eventNameWrapper.setId(ee.getId());
			eventNameWrapper.setContent(ee.getContent());
			eventNameWrapper.setEventName(ee.getEventName());
			for(Eventname ce : c_elist) {
				if(ee.getEventName().equals(ce.getEventName())) {
					eventNameWrapper.setCheck(1);
				}
			}
			elist.add(eventNameWrapper);
		}
		model.addAttribute("elist", elist);
		model.addAttribute("function", function);
	}
	
	//修改功能
	@RequestMapping("changefunction")
	public String changeFunction(Model model,  @RequestParam("functionId") String functionId, @RequestParam("name") String name, 
			@RequestParam("description") String description, @RequestParam("eventId") String[] eventIds) {
		if(eventIds.length != 0) {
			CustomFunction customFunction = new CustomFunction();
			customFunction.setId(Integer.parseInt(functionId));
			customFunction.setName(name);
			customFunction.setDescription(description);
			//修改功能名称
			behaviorService.updateCustomFunction(customFunction);
			//删除功能下的所有事件关联
			behaviorService.deleteFunctionBehaviorByFunctionId(Integer.parseInt(functionId));
			FunctionBehavior functionBehavior = null; 
			//重新添加事件
			for(String eventId : eventIds) {
				functionBehavior = new FunctionBehavior(); 
				functionBehavior.setFunctionId(customFunction.getId());
				functionBehavior.setBehaviorId(Integer.parseInt(eventId));
				behaviorService.saveFunctionBehavior(functionBehavior);
			}
		}
		change(model, functionId);
		return "changefunction";
	}
	
	//添加功能
	@RequestMapping("createfunction")
	public String createFunction(Model model, @RequestParam("name") String name, 
			@RequestParam("description") String description, @RequestParam("eventId") String[] eventIds) {
		if(eventIds.length != 0) {
			CustomFunction customFunction = new CustomFunction();
			customFunction.setName(name);
			customFunction.setDescription(description);
			behaviorService.saveCustomFunction(customFunction);
			FunctionBehavior functionBehavior = null; 
			for(String eventId : eventIds) {
				functionBehavior = new FunctionBehavior(); 
				functionBehavior.setFunctionId(customFunction.getId());
				functionBehavior.setBehaviorId(Integer.parseInt(eventId));
				behaviorService.saveFunctionBehavior(functionBehavior);
				
				String a = "abc";
				String b = "abcd";
				if(b.indexOf(a) == 0)
				{
					
				}
			}
		}
		//所有事件
		List<Eventname> elist = behaviorService.getAllEventname();
		//所有功能
		List<CustomFunction> flist = behaviorService.getCustomFunction();
		model.addAttribute("elist", elist);
		model.addAttribute("flist", flist);
		return "createfunction";
	}
	
	//删除功能
	@RequestMapping("deletefunction")
	public String deleteFunction(Model model, @RequestParam("functionId") String functionId) {
		behaviorService.deletefunction(Integer.parseInt(functionId));
		List<Eventname> elist = behaviorService.getAllEventname();
		List<CustomFunction> flist = behaviorService.getCustomFunction();
		model.addAttribute("elist", elist);
		model.addAttribute("flist", flist);
		return "createfunction";
	}
	
	//前往创建功能页面
	@RequestMapping("gocreatefunction")
	public String goCreateFunction(Model model) {
		List<Eventname> elist = behaviorService.getAllEventname();
		List<CustomFunction> flist = behaviorService.getCustomFunction();
		model.addAttribute("elist", elist);
		model.addAttribute("flist", flist);
		return "createfunction";
	}
	
	//跳转到功能分析图界面
	@RequestMapping("gofunction")
	public String goFunction(){
		return "function";
	}
	
	//获取各个功能
	@RequestMapping("getdetail")
	public void getFunctionDetail(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;
        JSONArray jsonArray = new JSONArray();
        JSONArray apps = new JSONArray();
        JSONArray functions = new JSONArray();
        JSONArray colors = new JSONArray();
        //获取各个功能
        List<CustomFunction> flist = behaviorService.getCustomFunction();
        //获取各个app
        List<String> alist = behaviorService.getAllAppFormBehavior();
        try {  
        	for(CustomFunction f : flist) {
        		functions.put(f.getName());
        	}
        	for(int i = 0; i < alist.size(); i++) {
        		apps.put(alist.get(i));
        		colors.put(StringUtil.getFunctionColor(i));
        	}
        	jsonArray.put(apps);
        	jsonArray.put(functions);
        	jsonArray.put(colors);
            wirte = response.getWriter();      
        } catch (Exception e) {  
        }finally{  
            wirte.print(jsonArray);  
            wirte.flush();  
            wirte.close();  
        }  
	}
	
	//获取各个功能的数据
	@RequestMapping("getfunction")
	public void getFunctionCount(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        List<Integer> data = null;
        List<Integer> clist = null;
        List<String> elist = null;
        int count;
        List<String> alist = behaviorService.getAllAppFormBehavior();
        List<CustomFunction> flist = behaviorService.getCustomFunction();
        try {
        	//遍历app
        	for(String a : alist) {
        		data = new ArrayList<Integer>();
        		//app的每个功能
        		for(CustomFunction f: flist) {
        			count = 0;
        			 //功能下的事件
        			elist = behaviorService.getBehaviorByFunction(f.getId());
        			//事件的出现频率
        			for(String e : elist) {
        				clist = behaviorService.getCountByAppNameAndEventName(a, e);
        				//每个频率的总数
        				for(Integer c : clist) {
        					count += c;
        				}
        			}
        			data.add(count);
        		}
        		jsonObject = new JSONObject();
        		jsonObject.put("name", a);
        		jsonObject.put("type", "bar");
        		jsonObject.put("data", data);
        		jsonArray.put(jsonObject);
        	}
            wirte = response.getWriter();  
        } catch (Exception e) {  
        }finally{
            wirte.print(jsonArray);  
            wirte.flush();  
            wirte.close();  
        }  
	}

}
