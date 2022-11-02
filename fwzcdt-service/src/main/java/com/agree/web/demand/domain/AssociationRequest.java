package com.agree.web.demand.domain;

import lombok.Data;

@Data
public class AssociationRequest {
    //负责人
    private String principal;
    //接口版本ID
    private String jkVersionId;
    //系统名称
    private String sysName;
    //接口名称
    private String jkNameCn;
    //接口编号
    private String jkNameEn;

    private String demandId;

    //系统ID
    private String sysId;
    private String label;
}
