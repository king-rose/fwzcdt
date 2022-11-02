package com.agree.web.fwlb.domain;


import com.agree.activiti.domain.ProcessEntity;
import com.agree.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务场景
 */
public class OprScenarioVersion extends ProcessEntity {
    //关联场景
    private String svScnId;
    //ID(主键)
    private String svId;
    //版本号
    private String svNo;
    //服务场景类型: 服务场景/主题场景/组合服务场景
    @Excel(name = "服务场景类型")
    private String svType;
    //场景码
    @Excel(name = "服务场景码")
    private String svCode;
    //名称
    @Excel(name = "服务场景名称")
    private String svName;
    //场景描述信息
    @Excel(name = "场景描述")
    private String scnDes;
    //关联接口
    @Excel(name = "接口交易码")
    private String svInterface;
    //关联接口所属系统ID
    private String scnSysId;
    private String sysName;

    //单双向标志
    private String responseFlag;
    //超时时间
    private long timeOut;
    //是否引用报文头
    private String headCode;
    //变更原因
    @Excel(name = "变更原因")
    private String updateReason;
    //变更内容
    @Excel(name = "变更内容")
    private String updateContent;

    //服务编号
    private String serviceId;
    //服务版本ID
    private String svSvId;
    //项目编号
    private String projectCode;
    //关联申请单号
    private String applicationId;
    //关联分组
    private String svGrpId;
    //报文校验标志
    private String xmlValidFlag;
    //是否对标
    private String stdFieldCheck;
    //是否适配
    private String jkAdapFlag;
    //适配类型
    private String jkAdapType;
    //锁定标志位
    private String lockType;
    //审核状态
    private String auditState;
    //状态
    private String svStatus;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date svCreateTime;
    //创建用户
    private String svCreateUser;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date svUpdateTime;
    //更新用户
    private String svUpdateUser;

    private Date startTime;
    private Date endTime;

    private String jkVersionId;
    private String serviceVersionId;
    private String appGlobetradecode;
    private String multiVersion;
    @Excel(name = "报文头")
    private String svMessageFormat;
    private String svCommunicationProtocol;

    private String svHttpUrl;
    //外部接口码
    private String svExternalInterfaceCode;
    //旁路输出系统
    private String bypassOutputSys;
    //集成方式：0-内联ESB；1-外联ESB；2-直连
    private String svIntegrationMode;


    /** 申请人 */
    private String applyUserId;

    /** 申请人 */
    private String applyUserName;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyTime;

    //流程实例ID
    private String instanceId;
    //流程定义key
    private String processKey;

    private String svcCode;
    private String svcName;

    private List<Map<String,Object>> maps;
    private String resourceFile;
    private String resourceBall;
    private Boolean show;

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getResourceFile() {
        return resourceFile;
    }

    public void setResourceFile(String resourceFile) {
        this.resourceFile = resourceFile;
    }

    public String getResourceBall() {
        return resourceBall;
    }

    public void setResourceBall(String resourceBall) {
        this.resourceBall = resourceBall;
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    public List<Map<String, Object>> getMaps() {
        return maps;
    }

    public void setMaps(List<Map<String, Object>> maps) {
        this.maps = maps;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSvcCode() {
        return svcCode;
    }

    public void setSvcCode(String svcCode) {
        this.svcCode = svcCode;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSvScnId() {
        return svScnId;
    }

    public void setSvScnId(String svScnId) {
        this.svScnId = svScnId;
    }


    public String getSvId() {
        return svId;
    }

    public void setSvId(String svId) {
        this.svId = svId;
    }


    public String getSvNo() {
        return svNo;
    }

    public void setSvNo(String svNo) {
        this.svNo = svNo;
    }


    public String getSvType() {
        return svType;
    }

    public void setSvType(String svType) {
        this.svType = svType;
    }


    public String getSvCode() {
        return svCode;
    }

    public void setSvCode(String svCode) {
        this.svCode = svCode;
    }


    public String getSvName() {
        return svName;
    }

    public void setSvName(String svName) {
        this.svName = svName;
    }


    public String getScnDes() {
        return scnDes;
    }

    public void setScnDes(String scnDes) {
        this.scnDes = scnDes;
    }


    public String getSvInterface() {
        return svInterface;
    }

    public void setSvInterface(String svInterface) {
        this.svInterface = svInterface;
    }


    public String getScnSysId() {
        return scnSysId;
    }

    public void setScnSysId(String scnSysId) {
        this.scnSysId = scnSysId;
    }


    public String getResponseFlag() {
        return responseFlag;
    }

    public void setResponseFlag(String responseFlag) {
        this.responseFlag = responseFlag;
    }


    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }


    public String getHeadCode() {
        return headCode;
    }

    public void setHeadCode(String headCode) {
        this.headCode = headCode;
    }


    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }


    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public String getSvSvId() {
        return svSvId;
    }

    public void setSvSvId(String svSvId) {
        this.svSvId = svSvId;
    }


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


    public String getSvGrpId() {
        return svGrpId;
    }

    public void setSvGrpId(String svGrpId) {
        this.svGrpId = svGrpId;
    }


    public String getXmlValidFlag() {
        return xmlValidFlag;
    }

    public void setXmlValidFlag(String xmlValidFlag) {
        this.xmlValidFlag = xmlValidFlag;
    }


    public String getStdFieldCheck() {
        return stdFieldCheck;
    }

    public void setStdFieldCheck(String stdFieldCheck) {
        this.stdFieldCheck = stdFieldCheck;
    }


    public String getJkAdapFlag() {
        return jkAdapFlag;
    }

    public void setJkAdapFlag(String jkAdapFlag) {
        this.jkAdapFlag = jkAdapFlag;
    }


    public String getJkAdapType() {
        return jkAdapType;
    }

    public void setJkAdapType(String jkAdapType) {
        this.jkAdapType = jkAdapType;
    }


    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }


    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }


    public String getSvStatus() {
        return svStatus;
    }

    public void setSvStatus(String svStatus) {
        this.svStatus = svStatus;
    }


    public Date getSvCreateTime() {
        return svCreateTime;
    }

    public void setSvCreateTime(Date svCreateTime) {
        this.svCreateTime = svCreateTime;
    }


    public String getSvCreateUser() {
        return svCreateUser;
    }

    public void setSvCreateUser(String svCreateUser) {
        this.svCreateUser = svCreateUser;
    }

    public Date getSvUpdateTime() {
        return svUpdateTime;
    }

    public void setSvUpdateTime(Date svUpdateTime) {
        this.svUpdateTime = svUpdateTime;
    }

    public String getSvUpdateUser() {
        return svUpdateUser;
    }

    public void setSvUpdateUser(String svUpdateUser) {
        this.svUpdateUser = svUpdateUser;
    }

    public String getJkVersionId() {
        return jkVersionId;
    }

    public void setJkVersionId(String jkVersionId) {
        this.jkVersionId = jkVersionId;
    }


    public String getServiceVersionId() {
        return serviceVersionId;
    }

    public void setServiceVersionId(String serviceVersionId) {
        this.serviceVersionId = serviceVersionId;
    }


    public String getAppGlobetradecode() {
        return appGlobetradecode;
    }

    public void setAppGlobetradecode(String appGlobetradecode) {
        this.appGlobetradecode = appGlobetradecode;
    }


    public String getMultiVersion() {
        return multiVersion;
    }

    public void setMultiVersion(String multiVersion) {
        this.multiVersion = multiVersion;
    }


    public String getSvMessageFormat() {
        return svMessageFormat;
    }

    public void setSvMessageFormat(String svMessageFormat) {
        this.svMessageFormat = svMessageFormat;
    }


    public String getSvCommunicationProtocol() {
        return svCommunicationProtocol;
    }

    public void setSvCommunicationProtocol(String svCommunicationProtocol) {
        this.svCommunicationProtocol = svCommunicationProtocol;
    }


    public String getSvHttpUrl() {
        return svHttpUrl;
    }

    public void setSvHttpUrl(String svHttpUrl) {
        this.svHttpUrl = svHttpUrl;
    }


    public String getSvExternalInterfaceCode() {
        return svExternalInterfaceCode;
    }

    public void setSvExternalInterfaceCode(String svExternalInterfaceCode) {
        this.svExternalInterfaceCode = svExternalInterfaceCode;
    }


    public String getBypassOutputSys() {
        return bypassOutputSys;
    }

    public void setBypassOutputSys(String bypassOutputSys) {
        this.bypassOutputSys = bypassOutputSys;
    }


    public String getSvIntegrationMode() {
        return svIntegrationMode;
    }

    public void setSvIntegrationMode(String svIntegrationMode) {
        this.svIntegrationMode = svIntegrationMode;
    }

    @Override
    public String toString() {
        return "OprScenarioVersion{" +
                "svScnId='" + svScnId + '\'' +
                ", svId='" + svId + '\'' +
                ", svNo='" + svNo + '\'' +
                ", svType='" + svType + '\'' +
                ", svCode='" + svCode + '\'' +
                ", svName='" + svName + '\'' +
                ", scnDes='" + scnDes + '\'' +
                ", svInterface='" + svInterface + '\'' +
                ", scnSysId='" + scnSysId + '\'' +
                ", sysName='" + sysName + '\'' +
                ", responseFlag='" + responseFlag + '\'' +
                ", timeOut=" + timeOut +
                ", headCode='" + headCode + '\'' +
                ", updateReason='" + updateReason + '\'' +
                ", updateContent='" + updateContent + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", svSvId='" + svSvId + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", svGrpId='" + svGrpId + '\'' +
                ", xmlValidFlag='" + xmlValidFlag + '\'' +
                ", stdFieldCheck='" + stdFieldCheck + '\'' +
                ", jkAdapFlag='" + jkAdapFlag + '\'' +
                ", jkAdapType='" + jkAdapType + '\'' +
                ", lockType='" + lockType + '\'' +
                ", auditState='" + auditState + '\'' +
                ", svStatus='" + svStatus + '\'' +
                ", svCreateTime=" + svCreateTime +
                ", svCreateUser='" + svCreateUser + '\'' +
                ", svUpdateTime=" + svUpdateTime +
                ", svUpdateUser='" + svUpdateUser + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", jkVersionId='" + jkVersionId + '\'' +
                ", serviceVersionId='" + serviceVersionId + '\'' +
                ", appGlobetradecode='" + appGlobetradecode + '\'' +
                ", multiVersion='" + multiVersion + '\'' +
                ", svMessageFormat='" + svMessageFormat + '\'' +
                ", svCommunicationProtocol='" + svCommunicationProtocol + '\'' +
                ", svHttpUrl='" + svHttpUrl + '\'' +
                ", svExternalInterfaceCode='" + svExternalInterfaceCode + '\'' +
                ", bypassOutputSys='" + bypassOutputSys + '\'' +
                ", svIntegrationMode='" + svIntegrationMode + '\'' +
                ", applyUserId='" + applyUserId + '\'' +
                ", applyUserName='" + applyUserName + '\'' +
                ", applyTime=" + applyTime +
                ", instanceId='" + instanceId + '\'' +
                ", processKey='" + processKey + '\'' +
                ", svcCode='" + svcCode + '\'' +
                ", svcName='" + svcName + '\'' +
                ", maps=" + maps +
                ", resourceFile='" + resourceFile + '\'' +
                ", resourceBall='" + resourceBall + '\'' +
                ", show=" + show +
                '}';
    }
}
