package com.agree.web.fwlb.domain;

public class AesbGChange {

    private String svName;

    private String svcName;

    private String svStatus;

    private String projectCode;

    private String fileName;

    private String resource;

    public String getSvName() {
        return svName;
    }

    public void setSvName(String svName) {
        this.svName = svName;
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    public String getSvStatus() {
        return svStatus;
    }

    public void setSvStatus(String svStatus) {
        this.svStatus = svStatus;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "AesbGChange{" +
                "svName='" + svName + '\'' +
                ", svcName='" + svcName + '\'' +
                ", svStatus='" + svStatus + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", fileName='" + fileName + '\'' +
                ", resource='" + resource + '\'' +
                '}';
    }
}
