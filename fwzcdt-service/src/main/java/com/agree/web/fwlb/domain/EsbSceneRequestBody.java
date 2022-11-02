package com.agree.web.fwlb.domain;

import lombok.Data;

/**
 * 场景新增接收请求实体类
 */
@Data
public class EsbSceneRequestBody {

    /*** **********************场景基本信息 ***********************************/
    /** 项目编号 */
    private String projectCode;

    /** 关联系统 */
    private String scnSysId;

    /** 关联接口 */
    private String svInterface;

    /** 所属服务 */
    private String serviceId;

    /** 场景码 */
    private String svCode;

    /** 场景名称 */
    private String svName;

    /** 场景类型 */
    private String svType;

    /** 报文格式 */
    private String svMessageFormat;

    /** 通讯协议 */
    private String svCommunicationProtocol;

    /** HttpUrl */
    private String svHttpUrl;

    /** 是否适配 */
    private String jkAdapFlag;

    /** 是否校验 */
    private String xmlValidFlag;

    /** 是否对标 */
    private String stdFieldCheck;

    /** 外部接口码 */
    private String svExternalInterfaceCode;

    /** 场景描述信息 */
    private String scnDes;

    /** 变更原因 */
    private String updateReason;

    /** 变更内容 */
    private String updateContent;

    /*** **********************场景指标信息 ***********************************/

    /** 服务场景窗口时间 */
    private String sceneWindowTime;

    /** 服务场景超时时间 */
    private String sceneTimeout;

    /** 并发用户数 */
    private String oneTimeUserNum;

    /** TPS-每秒交易笔数 */
    private String tradePerSecond;

    /** 服务场景生效时间 */
    private String onlineDate;

    /** 服务场景失效时间 */
    private String offlineDate;

    /** 是否对外开放：1-是，0-否，默认0-否 */
    private String isOpen;

    /** 优先级 ：0-9，默认9，默认9 */
    private String scenePriority;

    /** 方法名称 */
    private String methodName;

    /** 请求URL */
    private String requestUrl;

    /** API单价 */
    private String apiPrice;

    /** API类型 */
    private String apiType;

    /** 免费标志：1-免费，0-不免费，默认1-免费 */
    private String freeFlag;

    /** API对外版本号 */
    private String apiVersion;

    /** 用户授权标志：1-授权，0-不授权 */
    private String authFlag;

    /** HTTP方法 */
    private String httpMethod;

}
