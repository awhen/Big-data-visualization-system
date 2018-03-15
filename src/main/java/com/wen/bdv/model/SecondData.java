package com.wen.bdv.model;

public class SecondData {
    private Integer id;

    private String name;

    private String value;

    private String data;

    private String code;

    private Integer analysisId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Integer analysisId) {
        this.analysisId = analysisId;
    }

	@Override
	public String toString() {
		return "SecondData [id=" + id + ", name=" + name + ", value=" + value
				+ ", data=" + data + ", code=" + code + ", analysisId="
				+ analysisId + "]";
	}
}