package com.agree.web.sysinter.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.agree.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.agree.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统接口信息对象 aesb_g_sys_interface_info
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
public class AesbGSysInterfaceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接口编号 */
    private String jkId;

    /** 接口版本号 */
    private String jkVersionId;

    /** 接口版本名 */
    @Excel(name = "接口版本名")
    private String jkVersionName;

    /** 交易码 */
    private String tradeCode;

    /** 接口名称 */
    private String jkNameEn;

    /** 接口中文名 */
    @Excel(name = "接口中文名")
    private String jkNameCn;

    /** 接口描述 */
    private String jkDesc;

    /** 接口类型：1-对外接口，2-对内接口，默认对外 */
    private String jkType;

    /** 系统编号 */
    @Excel(name = "系统编号")
    private String systemId;

    /** 是否适配 ：0-适配，1-不适配 */
    private String isfit;

    /** $column.columnComment */
    private String msgType;

    /** 是否包含报文头长度 ：0-包含，1-不包含 */
    private String isContainshead;

    /** 报文头长度 */
    private String headLength;

    /** 投产状态：0-设计态，1-投产态 */
    private String deliverState;

    /** 状态：1-待新增，2-待修改，3-待废弃，4-待删除，5-审批中，6-已发布，7-已废弃 */
    private String state;

    /** 变更原因 */
    private String modReason;

    /** 变更类型 */
    private String modContent;

    /** 最后修改时间 */
    private Date lastModTime;

    /** $column.columnComment */
    private String projectCode;

    /** $column.columnComment */
    private String protocol;

    /** 接口调用说明 */
    private String invokeDesc;

    //系统名称
    private String sysName;
    //系统负责人
    private String principal;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    private List<Map<String,Object>> sysList;

    public List<Map<String, Object>> getSysList() {
        return sysList;
    }

    public void setSysList(List<Map<String, Object>> sysList) {
        this.sysList = sysList;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public void setJkId(String jkId)
    {
        this.jkId = jkId;
    }

    public String getJkId() 
    {
        return jkId;
    }
    public void setJkVersionId(String jkVersionId) 
    {
        this.jkVersionId = jkVersionId;
    }

    public String getJkVersionId() 
    {
        return jkVersionId;
    }
    public void setJkVersionName(String jkVersionName) 
    {
        this.jkVersionName = jkVersionName;
    }

    public String getJkVersionName() 
    {
        return jkVersionName;
    }
    public void setTradeCode(String tradeCode) 
    {
        this.tradeCode = tradeCode;
    }

    public String getTradeCode() 
    {
        return tradeCode;
    }
    public void setJkNameEn(String jkNameEn) 
    {
        this.jkNameEn = jkNameEn;
    }

    public String getJkNameEn() 
    {
        return jkNameEn;
    }
    public void setJkNameCn(String jkNameCn) 
    {
        this.jkNameCn = jkNameCn;
    }

    public String getJkNameCn() 
    {
        return jkNameCn;
    }
    public void setJkDesc(String jkDesc) 
    {
        this.jkDesc = jkDesc;
    }

    public String getJkDesc() 
    {
        return jkDesc;
    }
    public void setJkType(String jkType) 
    {
        this.jkType = jkType;
    }

    public String getJkType() 
    {
        return jkType;
    }
    public void setSystemId(String systemId) 
    {
        this.systemId = systemId;
    }

    public String getSystemId() 
    {
        return systemId;
    }
    public void setIsfit(String isfit) 
    {
        this.isfit = isfit;
    }

    public String getIsfit() 
    {
        return isfit;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setIsContainshead(String isContainshead) 
    {
        this.isContainshead = isContainshead;
    }

    public String getIsContainshead() 
    {
        return isContainshead;
    }
    public void setHeadLength(String headLength) 
    {
        this.headLength = headLength;
    }

    public String getHeadLength() 
    {
        return headLength;
    }
    public void setDeliverState(String deliverState) 
    {
        this.deliverState = deliverState;
    }

    public String getDeliverState() 
    {
        return deliverState;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setModReason(String modReason) 
    {
        this.modReason = modReason;
    }

    public String getModReason() 
    {
        return modReason;
    }
    public void setModContent(String modContent) 
    {
        this.modContent = modContent;
    }

    public String getModContent() 
    {
        return modContent;
    }
    public void setLastModTime(Date lastModTime) 
    {
        this.lastModTime = lastModTime;
    }

    public Date getLastModTime() 
    {
        return lastModTime;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProtocol(String protocol) 
    {
        this.protocol = protocol;
    }

    public String getProtocol() 
    {
        return protocol;
    }
    public void setInvokeDesc(String invokeDesc) 
    {
        this.invokeDesc = invokeDesc;
    }

    public String getInvokeDesc() 
    {
        return invokeDesc;
    }

    @Override
    public String toString() {
        return "AesbGSysInterfaceInfo{" +
                "jkId='" + jkId + '\'' +
                ", jkVersionId='" + jkVersionId + '\'' +
                ", jkVersionName='" + jkVersionName + '\'' +
                ", tradeCode='" + tradeCode + '\'' +
                ", jkNameEn='" + jkNameEn + '\'' +
                ", jkNameCn='" + jkNameCn + '\'' +
                ", jkDesc='" + jkDesc + '\'' +
                ", jkType='" + jkType + '\'' +
                ", systemId='" + systemId + '\'' +
                ", isfit='" + isfit + '\'' +
                ", msgType='" + msgType + '\'' +
                ", isContainshead='" + isContainshead + '\'' +
                ", headLength='" + headLength + '\'' +
                ", deliverState='" + deliverState + '\'' +
                ", state='" + state + '\'' +
                ", modReason='" + modReason + '\'' +
                ", modContent='" + modContent + '\'' +
                ", lastModTime=" + lastModTime +
                ", projectCode='" + projectCode + '\'' +
                ", protocol='" + protocol + '\'' +
                ", invokeDesc='" + invokeDesc + '\'' +
                ", sysName='" + sysName + '\'' +
                ", sysList=" + sysList +
                '}';
    }
}
