package com.agree.web.fwlb.domain;

import java.util.Date;

import com.agree.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.agree.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务场景消费关系对象 aesb_g_service_scene_mapping
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
public class AesbGServiceSceneMapping extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消费关系组ID：相当于某一次消费关系提交 */
    @Excel(name = "消费关系组ID：相当于某一次消费关系提交")
    private String mappingGroupId;

    /** 消费关系ID */
    private String mappingId;

    /** 消费关系版本号：本质就是服务场景版本 */
    @Excel(name = "消费关系版本号：本质就是服务场景版本")
    private String mappingVersionId;

    /** 消费关系类型：1-服务场景消费，2-内部接口消费，3-外部接口消费 */
    @Excel(name = "消费关系类型：1-服务场景消费，2-内部接口消费，3-外部接口消费")
    private String mappingType;

    /** 请求方系统编号 */
    @Excel(name = "请求方系统编号")
    private String reqSysId;

    /** 请求方系统功能编号 */
    @Excel(name = "请求方系统功能编号")
    private String reqFunctionName;

    /** 请求方接口码 */
    @Excel(name = "请求方接口码")
    private String reqTradeCode;

    /** 场景ID */
    @Excel(name = "场景ID")
    private String sceneId;

    /** 服务ID */
    @Excel(name = "服务ID")
    private String serviceId;

    /** 提供方系统编号 */
    @Excel(name = "提供方系统编号")
    private String proSysId;

    /** 提供方接口码 */
    @Excel(name = "提供方接口码")
    private String proTradeCode;

    /** 超时时间:单位为毫秒 */
    @Excel(name = "超时时间:单位为毫秒")
    private Long timeOut;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 消费状态：1-预消费，2-审核中，3-已发布，4-已废弃 */
    @Excel(name = "消费状态：1-预消费，2-审核中，3-已发布，4-已废弃")
    private String state;

    /** 创建用户 */
    @Excel(name = "创建用户")
    private String crtUserId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crtTime;

    /** $column.columnComment */
    @Excel(name = "创建时间")
    private String businessCode;

    /** $column.columnComment */
    @Excel(name = "创建时间")
    private String businessName;

    /** 是否对标 0:不对标 1:对标 */
    @Excel(name = "是否对标 0:不对标 1:对标")
    private String fieldCheck;

    /** 数据来源 0:场景地图 1:治理平台 */
    @Excel(name = "数据来源 0:场景地图 1:治理平台")
    private String dataResource;

    private String reqSysName;
    private String proSysName;
    private String projectName;
    private String svcName;
    private String svName;
    private String svCode;
    private String svcCode;
    private String svInterface;

    public String getSvcCode() {
        return svcCode;
    }

    public void setSvcCode(String svcCode) {
        this.svcCode = svcCode;
    }

    public String getSvCode() {
        return svCode;
    }

    public void setSvCode(String svCode) {
        this.svCode = svCode;
    }

    private String svIntegrationMode;

    public String getSvIntegrationMode() {
        return svIntegrationMode;
    }

    public void setSvIntegrationMode(String svIntegrationMode) {
        this.svIntegrationMode = svIntegrationMode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReqSysName() {
        return reqSysName;
    }

    public void setReqSysName(String reqSysName) {
        this.reqSysName = reqSysName;
    }

    public String getProSysName() {
        return proSysName;
    }

    public void setProSysName(String proSysName) {
        this.proSysName = proSysName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    public String getSvName() {
        return svName;
    }

    public void setSvName(String svName) {
        this.svName = svName;
    }

    public String getSvInterface() {
        return svInterface;
    }

    public void setSvInterface(String svInterface) {
        this.svInterface = svInterface;
    }

    public void setMappingGroupId(String mappingGroupId)
    {
        this.mappingGroupId = mappingGroupId;
    }

    public String getMappingGroupId() 
    {
        return mappingGroupId;
    }
    public void setMappingId(String mappingId) 
    {
        this.mappingId = mappingId;
    }

    public String getMappingId() 
    {
        return mappingId;
    }
    public void setMappingVersionId(String mappingVersionId) 
    {
        this.mappingVersionId = mappingVersionId;
    }

    public String getMappingVersionId() 
    {
        return mappingVersionId;
    }
    public void setMappingType(String mappingType) 
    {
        this.mappingType = mappingType;
    }

    public String getMappingType() 
    {
        return mappingType;
    }
    public void setReqSysId(String reqSysId) 
    {
        this.reqSysId = reqSysId;
    }

    public String getReqSysId() 
    {
        return reqSysId;
    }
    public void setReqFunctionName(String reqFunctionName) 
    {
        this.reqFunctionName = reqFunctionName;
    }

    public String getReqFunctionName() 
    {
        return reqFunctionName;
    }
    public void setReqTradeCode(String reqTradeCode) 
    {
        this.reqTradeCode = reqTradeCode;
    }

    public String getReqTradeCode() 
    {
        return reqTradeCode;
    }
    public void setSceneId(String sceneId) 
    {
        this.sceneId = sceneId;
    }

    public String getSceneId() 
    {
        return sceneId;
    }
    public void setServiceId(String serviceId) 
    {
        this.serviceId = serviceId;
    }

    public String getServiceId() 
    {
        return serviceId;
    }
    public void setProSysId(String proSysId) 
    {
        this.proSysId = proSysId;
    }

    public String getProSysId() 
    {
        return proSysId;
    }
    public void setProTradeCode(String proTradeCode) 
    {
        this.proTradeCode = proTradeCode;
    }

    public String getProTradeCode() 
    {
        return proTradeCode;
    }
    public void setTimeOut(Long timeOut) 
    {
        this.timeOut = timeOut;
    }

    public Long getTimeOut() 
    {
        return timeOut;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setCrtUserId(String crtUserId) 
    {
        this.crtUserId = crtUserId;
    }

    public String getCrtUserId() 
    {
        return crtUserId;
    }
    public void setCrtTime(Date crtTime) 
    {
        this.crtTime = crtTime;
    }

    public Date getCrtTime() 
    {
        return crtTime;
    }
    public void setBusinessCode(String businessCode) 
    {
        this.businessCode = businessCode;
    }

    public String getBusinessCode() 
    {
        return businessCode;
    }
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    public void setFieldCheck(String fieldCheck) 
    {
        this.fieldCheck = fieldCheck;
    }

    public String getFieldCheck() 
    {
        return fieldCheck;
    }
    public void setDataResource(String dataResource) 
    {
        this.dataResource = dataResource;
    }

    public String getDataResource() 
    {
        return dataResource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("mappingGroupId", getMappingGroupId())
            .append("mappingId", getMappingId())
            .append("mappingVersionId", getMappingVersionId())
            .append("mappingType", getMappingType())
            .append("reqSysId", getReqSysId())
            .append("reqFunctionName", getReqFunctionName())
            .append("reqTradeCode", getReqTradeCode())
            .append("sceneId", getSceneId())
            .append("serviceId", getServiceId())
            .append("proSysId", getProSysId())
            .append("proTradeCode", getProTradeCode())
            .append("timeOut", getTimeOut())
            .append("projectCode", getProjectCode())
            .append("state", getState())
            .append("crtUserId", getCrtUserId())
            .append("crtTime", getCrtTime())
            .append("businessCode", getBusinessCode())
            .append("businessName", getBusinessName())
            .append("fieldCheck", getFieldCheck())
            .append("dataResource", getDataResource())
            .toString();
    }
}
