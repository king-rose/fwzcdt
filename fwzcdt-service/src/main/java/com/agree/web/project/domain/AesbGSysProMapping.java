package com.agree.web.project.domain;

import com.agree.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统项目关联对象 aesb_g_sys_pro_mapping
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
public class AesbGSysProMapping extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统ID */
    private String systemId;

    /** 系统用途：0-服务提供方,1-服务请求方,2-主题发布方,3-主题订阅方 */
    private String systemUsage;

    /** 项目编号 */
    private String projectCode;

    public void setSystemId(String systemId) 
    {
        this.systemId = systemId;
    }

    public String getSystemId() 
    {
        return systemId;
    }
    public void setSystemUsage(String systemUsage) 
    {
        this.systemUsage = systemUsage;
    }

    public String getSystemUsage() 
    {
        return systemUsage;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("systemId", getSystemId())
            .append("systemUsage", getSystemUsage())
            .append("projectCode", getProjectCode())
            .toString();
    }
}
