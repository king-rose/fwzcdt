package com.agree.web.demand.domain;

import java.util.Date;
import java.util.List;

import com.agree.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.agree.common.annotation.Excel;
import lombok.Data;

/**
 * 需求信息对象 aesb_g_demand_info
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
@Data
public class AesbGDemandInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 需求编号 */
    private String demandId;

    /** 需求名称 */
    @Excel(name = "需求名称")
    private String demandName;

    /** 需求描述 */
    @Excel(name = "需求描述")
    private String demandDes;

    /** 需求预计完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求预计完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plantFinishTime;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    private String applyUserId;
    /** 申请人名称 */
    @Excel(name = "申请人名称")
    private String applyUserName;

    /** 登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 服务方系统 */
    @Excel(name = "服务方系统")
    private String serviceSystemId;

    /** 服务方系统负责人 */
    @Excel(name = "服务方系统负责人")
    private String serviceSystemUserId;

    /** 任务单状态 0:未受理 */
    @Excel(name = "任务单状态 0:未受理")
    private String taskState;

    /** 需求状态 0:未分解-1:已分解-2:开发中-3:测试中 */
    @Excel(name = "需求状态 0:未分解-1:已分解-2:开发中-3:测试中")
    private String demandState;
    //变更内容
    private String changeContent;
    //变更原因
    private String changeReason;
    //变更周期
    private String changeCycle;
    //延期时间
    private String delayTime;
    //关闭原因
    private String closeReason;

    //流程实例ID
    private String instanceId;
    //流程定义key
    private String processKey;
    //接口编号
    private String jkId;
    //审批时间
    private Date acceptTime;
    //审批人
    private String acceptUser;
    //审批结果(同意：1，驳回：0)
    private String isAccept;
    //备注信息
    private String acceptContent;


    private String useTime;
    private String workDay;
    //百分比
    private Double percentage;

    private List<AesbSystemInterfaceDemand> list;

}
