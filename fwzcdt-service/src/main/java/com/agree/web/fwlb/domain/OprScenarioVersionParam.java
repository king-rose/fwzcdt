package com.agree.web.fwlb.domain;

import com.agree.common.annotation.Excel;
import com.agree.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 场景指标信息对象 opr_scenario_version_param
 *
 * @author ruoyi
 * @date 2022-06-27
 */
@Data
public class OprScenarioVersionParam
{
    private static final long serialVersionUID = 1L;

    /** 场景ID：36位UUID，自动生成 */
    @Excel(name = "场景ID：36位UUID，自动生成")
    private String sceneId;

    /** 场景版本ID */
    private String sceneVersionId;

    /** 服务场景服役时间 */
    @Excel(name = "服务场景服役时间")
    private String sceneDutyTime;

    /** 服务场景窗口时间 */
    @Excel(name = "服务场景窗口时间")
    private String sceneWindowTime;

    /** 服务场景超时时间 */
    @Excel(name = "服务场景超时时间")
    private String sceneTimeout;

    /** 并发用户数 */
    @Excel(name = "并发用户数")
    private String oneTimeUserNum;

    /** TPS-每秒交易笔数 */
    @Excel(name = "TPS-每秒交易笔数")
    private String tradePerSecond;

    /** 场景可用性 */
    @Excel(name = "场景可用性")
    private String sceneAvailable;

    /** 故障修复时间 */
    @Excel(name = "故障修复时间")
    private String sceneFixTime;

    /** 响应时间 */
    @Excel(name = "响应时间")
    private String rspTime;

    /** 优先级 ：0-9，默认9，默认9 */
    @Excel(name = "优先级 ：0-9，默认9，默认9")
    private String scenePriority;

    /** 服务场景生效时间 */
    @Excel(name = "服务场景生效时间")
    private String onlineDate;

    /** 服务场景失效时间 */
    @Excel(name = "服务场景失效时间")
    private String offlineDate;

    /** 是否对外开放：1-是，0-否，默认0-否 */
    @Excel(name = "是否对外开放：1-是，0-否，默认0-否")
    private String isOpen;

    /** 方法名称 */
    @Excel(name = "方法名称")
    private String methodName;

    /** API类型 */
    @Excel(name = "API类型")
    private String apiType;

    /** 用户授权标志：1-授权，0-不授权 */
    @Excel(name = "用户授权标志：1-授权，0-不授权")
    private String authFlag;

    /** 免费标志：1-免费，0-不免费，默认1-免费 */
    @Excel(name = "免费标志：1-免费，0-不免费，默认1-免费")
    private String freeFlag;

    /** 请求URL */
    @Excel(name = "请求URL")
    private String requestUrl;

    /** API单价 */
    @Excel(name = "API单价")
    private String apiPrice;

    /** API对外版本号 */
    @Excel(name = "API对外版本号")
    private String apiVersion;

    /** HTTP方法 */
    @Excel(name = "HTTP方法")
    private String httpMethod;

    @Excel(name = "服务场景码")
    private String svCode;
    @Excel(name = "服务场景名称")
    private String svName;

    private String checkFlag;
    private String dataSource;

}