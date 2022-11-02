package com.agree.web.fwlb.domain;


import com.agree.common.annotation.Excel;
import com.agree.common.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 服务分类
 */
public class OprService extends BaseEntity {
    private String svcId;
    @Excel(name = "服务ID")
    private String svcCode;
    @Excel(name = "服务名称")
    private String svcName;
    @Excel(name = "服务描述")
    private String svcDes;
    @Excel(name = "一级分类")
    private String serviceHeadClass;
    @Excel(name = "二级分类")
    private String serviceSmallClass;

    private Date svcCreateTime;

    private String svcCreateUser;

    private Date svcUpdateTime;

    private String svcUpdateUser;

    private String svcSgId;

    /***********************************************************/
    @Excel(name = "用户序号")
    private String userId;

    @Excel(name = "部门编号")
    private String deptId;

    @Excel(name = "登录名称")
    private String loginName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSvcId() {
        return svcId;
    }

    public void setSvcId(String svcId) {
        this.svcId = svcId;
    }


    public String getSvcCode() {
        return svcCode;
    }

    public void setSvcCode(String svcCode) {
        this.svcCode = svcCode;
    }


    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }


    public String getSvcDes() {
        return svcDes;
    }

    public void setSvcDes(String svcDes) {
        this.svcDes = svcDes;
    }


    public String getServiceHeadClass() {
        return serviceHeadClass;
    }

    public void setServiceHeadClass(String serviceHeadClass) {
        this.serviceHeadClass = serviceHeadClass;
    }


    public String getServiceSmallClass() {
        return serviceSmallClass;
    }

    public void setServiceSmallClass(String serviceSmallClass) {
        this.serviceSmallClass = serviceSmallClass;
    }


    public Date getSvcCreateTime() {
        return svcCreateTime;
    }

    public void setSvcCreateTime(Date svcCreateTime) {
        this.svcCreateTime = svcCreateTime;
    }


    public String getSvcCreateUser() {
        return svcCreateUser;
    }

    public void setSvcCreateUser(String svcCreateUser) {
        this.svcCreateUser = svcCreateUser;
    }


    public Date getSvcUpdateTime() {
        return svcUpdateTime;
    }

    public void setSvcUpdateTime(Date svcUpdateTime) {
        this.svcUpdateTime = svcUpdateTime;
    }


    public String getSvcUpdateUser() {
        return svcUpdateUser;
    }

    public void setSvcUpdateUser(String svcUpdateUser) {
        this.svcUpdateUser = svcUpdateUser;
    }


    public String getSvcSgId() {
        return svcSgId;
    }

    public void setSvcSgId(String svcSgId) {
        this.svcSgId = svcSgId;
    }


    @Override
    public String toString() {
        return "OprService{" +
                "svcId='" + svcId + '\'' +
                ", svcCode='" + svcCode + '\'' +
                ", svcName='" + svcName + '\'' +
                ", svcDes='" + svcDes + '\'' +
                ", serviceHeadClass='" + serviceHeadClass + '\'' +
                ", serviceSmallClass='" + serviceSmallClass + '\'' +
                ", svcCreateTime=" + svcCreateTime +
                ", svcCreateUser='" + svcCreateUser + '\'' +
                ", svcUpdateTime=" + svcUpdateTime +
                ", svcUpdateUser='" + svcUpdateUser + '\'' +
                ", svcSgId='" + svcSgId + '\'' +
                ", userId='" + userId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
