package com.wen.bdv.model;

public class Gps {
    private Integer id;

    private String appserial;

    private String gps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppserial() {
        return appserial;
    }

    public void setAppserial(String appserial) {
        this.appserial = appserial == null ? null : appserial.trim();
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps == null ? null : gps.trim();
    }
}