package com.agree.web.system.domain;

import com.agree.common.annotation.Excel;
import com.agree.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统信息对象 opr_system
 *
 * @author ruoyi
 * @date 2022-06-16
 */
@Data
public class OprSystem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统ID */
    @Excel(name = "系统ID")
    private String sysId;

    /** 系统代码：全大写字母 */
    private String sysCode;

    /** 系统中文名 */
    @Excel(name = "系统中文名")
    private String sysName;

    /** 系统描述 */
    private String sysDes;

    /** 区域ID */
    private String areaId;

    /** 系统所属层 */
    @Excel(name = "系统所属层")
    private String systemLayer;

    /** 开发厂商 */
    private String developer;

    /** 系统上线日期 */
    private String onlineDate;

    /** 系统下线日期 */
    private String offlineDate;

    /** 技术部门 */
    @Excel(name = "技术部门")
    private String techDept;

    /** 应用负责人：用户信息表的USER_ID */
    @Excel(name = "应用负责人：用户信息表的USER_ID")
    private String managerId;
    private String principal;

    /** 应用配合人 */
    private String appCoorUsers;

    /** 业务负责部门 */
    private String busiDept;

    /** 业务负责人 */
    private String busiLeader;

    /** 运维负责人 */
    private String oprLeader;

    /** 灾备等级 */
    private String disaLevel;

    /** 灾备模式 */
    private String disaMode;

    /** 灾备恢复时间目标RTO（分钟）： */
    private String disaRecRtoTime;

    /** 灾备恢复点目标RPO（分钟） */
    private String disaRecRpoTime;

    /** 灾备系统情况说明： */
    private String disaSystemNote;

    /** 安全保护等级 */
    private String secLevel;

    /** 是否建立应急预案 */
    private String contPlan;

    /** 是否已纳入监控 */
    private String monitor;

    /** 服务时间 */
    private String serviceTime;

    /** 使用用户 */
    private String sysUser;

    /** 服务范围 */
    private String serviceArea;

    /** 是否直接面向客户 */
    private String faceToCust;

    /** 系统超时时间 ：时间单位为毫秒，默认30000 */
    private String timeOut;

    /** 系统类型 */
    private String sysType;

    /** 访问方式 */
    private String accessMode;

    /** 兼容浏览器 */
    private String compBrowser;

    /** 应用服务器高可用技术 */
    private String highAvailableTech;

    /** 应用服务器是否负载均衡 */
    private String loadBalance;

    /** 操作系统 */
    private String oprSystem;

    /** 数据库 */
    private String sysDatabase;

    /** 中间件 */
    private String middleware;

    /** 是否管控到内部接口：0-否，1-是 */
    private String innerJkPass;

    /** 系统状态：0-审核中，1-在建/审核通过，2-审核失败，3-已下线，4-运行 */
    @Excel(name = "系统状态：0-审核中，1-在建/审核通过，2-审核失败，3-已下线，4-运行")
    private String sysStatus;

    /** 创建时间 */
    private Date sysCreateTime;

    /** 创建用户 */
    private String sysCreateUser;

    /** 修改时间 */
    private Date sysUpdateTime;

    /** 修改用户 */
    private String sysUpdateUser;

    /** 运行平台ID */
    private String sysPvId;

    /** 系统分类ID */
    private String sysScId;

    /** 鉴权类型：0-不认证，1-数字认证，2-Oauth认证，3-数字认证和Oauth认证 */
    private String authType;

    /** 接入ESB，0-未接入，1-已接入 */
    private String connectToEsb;

    /** 渠道编码 */
    private String channelCode;

    /** 通讯协议 */
    private String communicationProtocol;

    /** 报文格式 */
    private String messageFormat;

    /** 所属分区 */
    private String sysPartition;

    /** 集成方式：0-内联ESB；1-外联ESB；2-直连 */
    private String integrationMode;

    private String projectCode;
    private String systemId;
    private String nickName;
    private Boolean switchs = false;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sysId", getSysId())
                .append("sysCode", getSysCode())
                .append("sysName", getSysName())
                .append("sysDes", getSysDes())
                .append("areaId", getAreaId())
                .append("systemLayer", getSystemLayer())
                .append("developer", getDeveloper())
                .append("onlineDate", getOnlineDate())
                .append("offlineDate", getOfflineDate())
                .append("techDept", getTechDept())
                .append("managerId", getManagerId())
                .append("appCoorUsers", getAppCoorUsers())
                .append("busiDept", getBusiDept())
                .append("busiLeader", getBusiLeader())
                .append("oprLeader", getOprLeader())
                .append("disaLevel", getDisaLevel())
                .append("disaMode", getDisaMode())
                .append("disaRecRtoTime", getDisaRecRtoTime())
                .append("disaRecRpoTime", getDisaRecRpoTime())
                .append("disaSystemNote", getDisaSystemNote())
                .append("secLevel", getSecLevel())
                .append("contPlan", getContPlan())
                .append("monitor", getMonitor())
                .append("serviceTime", getServiceTime())
                .append("sysUser", getSysUser())
                .append("serviceArea", getServiceArea())
                .append("faceToCust", getFaceToCust())
                .append("timeOut", getTimeOut())
                .append("sysType", getSysType())
                .append("accessMode", getAccessMode())
                .append("compBrowser", getCompBrowser())
                .append("highAvailableTech", getHighAvailableTech())
                .append("loadBalance", getLoadBalance())
                .append("oprSystem", getOprSystem())
                .append("sysDatabase", getSysDatabase())
                .append("middleware", getMiddleware())
                .append("innerJkPass", getInnerJkPass())
                .append("sysStatus", getSysStatus())
                .append("sysCreateTime", getSysCreateTime())
                .append("sysCreateUser", getSysCreateUser())
                .append("sysUpdateTime", getSysUpdateTime())
                .append("sysUpdateUser", getSysUpdateUser())
                .append("sysPvId", getSysPvId())
                .append("sysScId", getSysScId())
                .append("authType", getAuthType())
                .append("connectToEsb", getConnectToEsb())
                .append("channelCode", getChannelCode())
                .append("communicationProtocol", getCommunicationProtocol())
                .append("messageFormat", getMessageFormat())
                .append("sysPartition", getSysPartition())
                .append("integrationMode", getIntegrationMode())
                .toString();
    }
}
