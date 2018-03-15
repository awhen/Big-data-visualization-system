package com.wen.bdv.util;

import java.util.ArrayList;
import java.util.List;

import com.wen.bdv.model.SecondData;

public class SortUtil {
	
	//冒泡排序
	public static List<SecondData> BubbleSort(List<SecondData> secondDatas) {
		for(int i = 0; i < secondDatas.size() - 1; i++) {
			for(int j = 1; j < secondDatas.size() - 1 - i; j++) {
				if(Integer.parseInt(secondDatas.get(j).getName()) > Integer.parseInt(secondDatas.get(j+1).getName())) {
					SecondData temp = secondDatas.get(j);
					secondDatas.set(j, secondDatas.get(j+1));
					secondDatas.set(j+1, temp);
				}
			}
		}
/*		for(SecondData secondData : secondDatas)
        	System.out.println("|||||" + secondData.toString());*/
		return secondDatas;
	}

}
