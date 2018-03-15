package com.wen.bdv.wrapper;

public class AppSerialWrapper {
	
	private String code;
	
	private String name;
	
	private String value;
	
	private String color;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "[code=" + code + ", name="
				+ name + ", value=" + value + ", color=" + color + "]";
	}

}
