package com.wen.bdv.wrapper;

public class AppWrapper {
	
	private String skey;
	
	private String svalue;

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}

	@Override
	public String toString() {
		return "AppWrapper [skey=" + skey + ", svalue=" + svalue + "]";
	}

}
