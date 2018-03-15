package com.wen.bdv.wrapper;

public class BehaviorWrapper {
	
	private Integer id;
	
	private String event;
	
	private String appname;
	
	private String appversion;
	
	private Integer sumcount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public Integer getSumcount() {
		return sumcount;
	}

	public void setSumcount(Integer sumcount) {
		this.sumcount = sumcount;
	}

	@Override
	public String toString() {
		return "BehaviorWrapper [id=" + id + ", event=" + event + ", appname="
				+ appname + ", appversion=" + appversion + ", sumcount="
				+ sumcount + "]";
	}

}
