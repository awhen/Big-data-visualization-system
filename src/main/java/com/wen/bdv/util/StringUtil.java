package com.wen.bdv.util;

import java.util.Random;

public class StringUtil {
	
	//随机返回分布图颜色
	public static String getColor() {
        String[] colors = {"#eea638","#a7a737","#00EEEE","#FF6347"};
        int index= (int)(colors.length * Math.random());
        return colors[index]; 
	}
	
	//随机返回功能颜色
	public static String getFunctionColor(int index) {
        String[] colors = {"#003366", "#006699", "#4cabce", "#e5323e", "#EE2C2C", "#1A1A1A", "#32CD32"};
        return colors[index % colors.length]; 
	}
	
}
