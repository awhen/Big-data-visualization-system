package com.wen.bdv.wrapper;

import java.util.Arrays;
import java.util.List;

public class FunctionWrapper {
	
	private String name;
	
	private String type;
	
	private List<Integer> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FunctionWrapper [name=" + name + ", type=" + type + ", data="
				+ data + "]";
	}
}
