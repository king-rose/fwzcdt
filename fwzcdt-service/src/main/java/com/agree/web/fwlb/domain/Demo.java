package com.agree.web.fwlb.domain;

import com.agree.common.annotation.Excel;
import com.agree.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class Demo extends BaseEntity {
    @Excel(name = "一级分类")
    private String serviceHeadClass;
    @Excel(name = "二级分类")
    private String serviceSmallClass;
    @Excel(name = "服务名称")
    private String svName;
    @Excel(name = "服务ID")
    private String svcCode;
    @Excel(name = "服务场景名称")
    private String svcName;
    @Excel(name = "服务场景码")
    private String svCode;
  /*  @Excel(name = "预计结束日期")
    private String endTime;
    @Excel(name = "工作日")
    private String workDay;
    @Excel(name = "培训所在项目编号")
    private String projectCode;
    @Excel(name = "培训所在项目名称")
    private String projectName;
    @Excel(name = "项目类型")
    private String projectType;
    @Excel(name = "是否结算")
    private String isPay;
    @Excel(name = "培训人")
    private String man;
    @Excel(name = "备注说明")
    private String remark;*/

}
