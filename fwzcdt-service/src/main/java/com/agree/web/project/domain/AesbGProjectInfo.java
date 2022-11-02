package com.agree.web.project.domain;

import java.util.Date;

import com.agree.common.core.domain.BaseEntity;
import com.agree.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目信息对象 aesb_g_project_info
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
public class AesbGProjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String projectCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String projectName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String projectType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String projectDesc;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String principal;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String planDevDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String planSitDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String planUatDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String planTrlDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String planPrdDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String prdFlag;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String availState;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date crtTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String attachmentName;

    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setProjectDesc(String projectDesc) 
    {
        this.projectDesc = projectDesc;
    }

    public String getProjectDesc() 
    {
        return projectDesc;
    }
    public void setPrincipal(String principal) 
    {
        this.principal = principal;
    }

    public String getPrincipal() 
    {
        return principal;
    }
    public void setPlanDevDate(String planDevDate) 
    {
        this.planDevDate = planDevDate;
    }

    public String getPlanDevDate() 
    {
        return planDevDate;
    }
    public void setPlanSitDate(String planSitDate) 
    {
        this.planSitDate = planSitDate;
    }

    public String getPlanSitDate() 
    {
        return planSitDate;
    }
    public void setPlanUatDate(String planUatDate) 
    {
        this.planUatDate = planUatDate;
    }

    public String getPlanUatDate() 
    {
        return planUatDate;
    }
    public void setPlanTrlDate(String planTrlDate) 
    {
        this.planTrlDate = planTrlDate;
    }

    public String getPlanTrlDate() 
    {
        return planTrlDate;
    }
    public void setPlanPrdDate(String planPrdDate) 
    {
        this.planPrdDate = planPrdDate;
    }

    public String getPlanPrdDate() 
    {
        return planPrdDate;
    }
    public void setPrdFlag(String prdFlag) 
    {
        this.prdFlag = prdFlag;
    }

    public String getPrdFlag() 
    {
        return prdFlag;
    }
    public void setAvailState(String availState) 
    {
        this.availState = availState;
    }

    public String getAvailState() 
    {
        return availState;
    }
    public void setCrtTime(Date crtTime) 
    {
        this.crtTime = crtTime;
    }

    public Date getCrtTime() 
    {
        return crtTime;
    }
    public void setAttachmentName(String attachmentName) 
    {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentName() 
    {
        return attachmentName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("projectType", getProjectType())
            .append("projectDesc", getProjectDesc())
            .append("principal", getPrincipal())
            .append("planDevDate", getPlanDevDate())
            .append("planSitDate", getPlanSitDate())
            .append("planUatDate", getPlanUatDate())
            .append("planTrlDate", getPlanTrlDate())
            .append("planPrdDate", getPlanPrdDate())
            .append("prdFlag", getPrdFlag())
            .append("availState", getAvailState())
            .append("crtTime", getCrtTime())
            .append("attachmentName", getAttachmentName())
            .toString();
    }
}
