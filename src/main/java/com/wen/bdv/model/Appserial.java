package com.wen.bdv.model;

public class Appserial {
    private Integer id;

    private String appSerial;

    private String appSystem;

    private String appModel;

    private String appName;

    private String appVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppSerial() {
        return appSerial;
    }

    public void setAppSerial(String appSerial) {
        this.appSerial = appSerial == null ? null : appSerial.trim();
    }

    public String getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(String appSystem) {
        this.appSystem = appSystem == null ? null : appSystem.trim();
    }

    public String getAppModel() {
        return appModel;
    }

    public void setAppModel(String appModel) {
        this.appModel = appModel == null ? null : appModel.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }
}