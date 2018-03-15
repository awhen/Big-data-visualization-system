package com.wen.bdv.wrapper;

public class EventNameWrapper {
	
	private Integer id;

    private String eventName;

    private String content;
    
    private Integer check;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "EventNameWrapper [id=" + id + ", eventName=" + eventName
				+ ", content=" + content + ", check=" + check + "]";
	}

}
