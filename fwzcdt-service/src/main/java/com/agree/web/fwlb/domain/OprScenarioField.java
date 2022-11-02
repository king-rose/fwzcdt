package com.agree.web.fwlb.domain;

import com.agree.common.annotation.Excel;
import com.agree.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务场景字段详情对象 opr_scenario_field
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
@Data
public class OprScenarioField extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景ID：36位UUID，自动生成 */
    @Excel(name = "场景ID：36位UUID，自动生成")
    private String sfId;

    /** 服务场景版本号ID：36位UUID，自动生成 */
    private String sfSvId;

    /** 场景码 */
    private String scnCode;

    /** 场景字段计数 */
    private Long sfCount;

    /** 字段英文名 */
    @Excel(name = "英文名称")
    private String sfEnName;

    /** 字段中文名 */
    @Excel(name = "中文名称")
    private String sfCnName;

    /** 输入输出 */
    @Excel(name = "输入输出")
    private String sfType;

    /** 字段类型 */
    @Excel(name = "数据类型")
    private String sfDataType;

    /** 字段长度 */
    @Excel(name = "字段长度（BYTE）")
    private String sfLength;

    /** 字段小数位 */
    @Excel(name = "字段小数位")
    private String sfFraction;

    /** 必输项 */
    @Excel(name = "是否必输")
    private String sfIsEnssential;

    /** 循环次数 */
    @Excel(name = "循环次数")
    private String sfCycleTimes;

    /** 数据格式 */
    @Excel(name = "数据格式")
    private String sfFormat;

    /** 枚举值 */
    @Excel(name = "枚举列表")
    private String sfEnum;

    /** 字段填写说明 */
    @Excel(name = "备注")
    private String sfDes;

    /** 字段默认值 */
    @Excel(name = "默认值")
    private String sfDefault;

    /** 是否敏感字段：0-否，1-是，默认0 */
    @Excel(name = "是否敏感字段")
    private String sfIsSensitive;

    /** 字段路径 */
    @Excel(name = "字段位置")
    private String sfPath;

    /** 引用名 */
    @Excel(name = "引用名")
    private String sfQuoteName;

    /** 引用名空间 */
    @Excel(name = "引用名空间")
    private String sfQuoteNamespace;

    /** 引用文件名 */
    @Excel(name = "引用文件名")
    private String sfQuoteFileName;

    /** 引用字段名 */
    @Excel(name = "引用字段名")
    private String sfQuoteFileNameEn;

    /** $column.columnComment */
    @Excel(name = "引用字段名")
    private String sfQuoteFieldNameEn;

    /** 字段长度类型:0-最长，1-定长 */
    @Excel(name = "长度类型")
    private String sfLengthType;

    /** 是否对标 */
    @Excel(name = "是否对标")
    private String sfIsStdCheck;

    /** 对标编号 */
    @Excel(name = "对标编号")
    private String sfStdCode;

    /** HTTP参数出现位置？枚举值？PATH URL-ENCODED JSON-BODY(DEFAULT) */
    @Excel(name = "HTTP参数出现位置？枚举值？PATH URL-ENCODED JSON-BODY(DEFAULT)")
    private String sfHttpParamType;

    /** 字段示例 */
    @Excel(name = "字段示例")
    private String sfExample;

    /** 加密模式？不填不加密？枚举？EBC CBC */
    @Excel(name = "加密模式？不填不加密？枚举？EBC CBC")
    private String sfEncryptMode;

    /** 标准中文名称 */
    @Excel(name = "标准中文名称")
    private String sfStdNameCn;

    /** 数据来源 0：场景地图 1:治理平台 */
    @Excel(name = "数据来源 0：场景地图 1:治理平台")
    private String sfDataSource;


}
