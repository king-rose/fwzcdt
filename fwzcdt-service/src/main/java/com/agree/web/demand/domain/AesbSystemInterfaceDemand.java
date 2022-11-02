package com.agree.web.demand.domain;

import com.agree.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AesbSystemInterfaceDemand extends BaseEntity {
    private Long id;
    //需求编号
    private String demandId;
    //系统编号
    private String sysId;
    //接口版本编号
    private String jkVersionId;
    //系统名称
    private String sysName;
    //接口中文名
    private String jkNameCn;
    //接口英文名
    private String jkNameEn;
    //负责人
    private String principal;
    //是否受理
    private String isAccept;
    //受理时间
    private Date acceptTime;
    //工作用时
    private String workDay;
}
