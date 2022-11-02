package com.agree.web.fwlb.domain;


import java.util.Date;

import com.agree.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.agree.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务场景资源对象 aesb_g_scene_resource
 *
 * @author ruoyi
 * @date 2022-06-27
 */
public class AesbGSceneResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景ID */
    @Excel(name = "场景ID")
    private String sceneId;

    /** 场景版本号 */
    private String sceneVersionId;

    /** 资源类型：1-schema压缩包，2-场景Excel */
    private String resourceType;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String resourceName;

    /** 资源大小 */
    @Excel(name = "资源大小")
    private Long resourceSize;

    /** 资源路径 */
    @Excel(name = "资源路径")
    private String resourceUrl;

    /** 资源描述信息 */
    @Excel(name = "资源描述信息")
    private String resourceDesc;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crtTime;

    public void setSceneId(String sceneId)
    {
        this.sceneId = sceneId;
    }

    public String getSceneId()
    {
        return sceneId;
    }
    public void setSceneVersionId(String sceneVersionId)
    {
        this.sceneVersionId = sceneVersionId;
    }

    public String getSceneVersionId()
    {
        return sceneVersionId;
    }
    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceType()
    {
        return resourceType;
    }
    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceName()
    {
        return resourceName;
    }
    public void setResourceSize(Long resourceSize)
    {
        this.resourceSize = resourceSize;
    }

    public Long getResourceSize()
    {
        return resourceSize;
    }
    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrl()
    {
        return resourceUrl;
    }
    public void setResourceDesc(String resourceDesc)
    {
        this.resourceDesc = resourceDesc;
    }

    public String getResourceDesc()
    {
        return resourceDesc;
    }
    public void setCrtTime(Date crtTime)
    {
        this.crtTime = crtTime;
    }

    public Date getCrtTime()
    {
        return crtTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sceneId", getSceneId())
                .append("sceneVersionId", getSceneVersionId())
                .append("resourceType", getResourceType())
                .append("resourceName", getResourceName())
                .append("resourceSize", getResourceSize())
                .append("resourceUrl", getResourceUrl())
                .append("resourceDesc", getResourceDesc())
                .append("crtTime", getCrtTime())
                .toString();
    }
}