package com.agree.web.dsource.domain;

import com.agree.common.annotation.Excel;
import com.agree.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 标准数据元字典键对象 aesb_g_std_dictionary_key
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public class AesbGStdDictionaryKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标准字典ID */
    private String stdDictId;

    /** 数据分类（标准字典键分组）：1-客户2-组织机构3-渠道4-产品5-交易6-协议7-账户8-财务 */
    @Excel(name = "数据分类", readConverterExp = "标准字典键分组")
    private String stdDictKeyGroup;

    /** 数据标准类型：S-标准类型 */
    @Excel(name = "数据标准类型：S-标准类型")
    private String stdType;

    /** 数据标准编号 */
    @Excel(name = "数据标准编号")
    private String stdCode;

    /** 标准中文名称 */
    @Excel(name = "标准中文名称")
    private String stdNameCn;

    /** 数据标识 */
    @Excel(name = "数据标识")
    private String stdDictKey;

    /** 银标分类：0-编码类，1-标志类，2-代码类，3-余额类，4-日期类，5-日期时间类，6-数值类，7-文本类，8-时间类，9-比例类 */
    @Excel(name = "银标分类：0-编码类，1-标志类，2-代码类，3-余额类，4-日期类，5-日期时间类，6-数值类，7-文本类，8-时间类，9-比例类")
    private String stdDictKeyRel;

    /** 数据元 */
    @Excel(name = "数据元")
    private String stdData;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String stdDictType;

    /** 长度 */
    @Excel(name = "长度")
    private String stdDictLength;

    /** 小数位 */
    @Excel(name = "小数位")
    private String stdDictNumber;

    /** 数据格式 */
    @Excel(name = "数据格式")
    private String stdDictFormat;

    /** 业务含义 */
    @Excel(name = "业务含义")
    private String stdDictDesc;

    /** 启用状态：0-启用 1-禁用 */
    @Excel(name = "启用状态：0-启用 1-禁用")
    private String enable;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date crtTime;

    /** $column.columnComment */
    @Excel(name = "创建时间")
    private String stdRef;

    /** 长度类型，0-定长，1-最长 */
    @Excel(name = "长度类型，0-定长，1-最长")
    private String stdDictLengthType;

    private String keywords;

    private Boolean search;

    public Boolean getSearch() {
        return search;
    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setStdDictId(String stdDictId)
    {
        this.stdDictId = stdDictId;
    }

    public String getStdDictId() 
    {
        return stdDictId;
    }
    public void setStdDictKeyGroup(String stdDictKeyGroup) 
    {
        this.stdDictKeyGroup = stdDictKeyGroup;
    }

    public String getStdDictKeyGroup() 
    {
        return stdDictKeyGroup;
    }
    public void setStdType(String stdType) 
    {
        this.stdType = stdType;
    }

    public String getStdType() 
    {
        return stdType;
    }
    public void setStdCode(String stdCode) 
    {
        this.stdCode = stdCode;
    }

    public String getStdCode() 
    {
        return stdCode;
    }
    public void setStdNameCn(String stdNameCn) 
    {
        this.stdNameCn = stdNameCn;
    }

    public String getStdNameCn() 
    {
        return stdNameCn;
    }
    public void setStdDictKey(String stdDictKey) 
    {
        this.stdDictKey = stdDictKey;
    }

    public String getStdDictKey() 
    {
        return stdDictKey;
    }
    public void setStdDictKeyRel(String stdDictKeyRel) 
    {
        this.stdDictKeyRel = stdDictKeyRel;
    }

    public String getStdDictKeyRel() 
    {
        return stdDictKeyRel;
    }
    public void setStdData(String stdData) 
    {
        this.stdData = stdData;
    }

    public String getStdData() 
    {
        return stdData;
    }
    public void setStdDictType(String stdDictType) 
    {
        this.stdDictType = stdDictType;
    }

    public String getStdDictType() 
    {
        return stdDictType;
    }
    public void setStdDictLength(String stdDictLength) 
    {
        this.stdDictLength = stdDictLength;
    }

    public String getStdDictLength() 
    {
        return stdDictLength;
    }
    public void setStdDictNumber(String stdDictNumber) 
    {
        this.stdDictNumber = stdDictNumber;
    }

    public String getStdDictNumber() 
    {
        return stdDictNumber;
    }
    public void setStdDictFormat(String stdDictFormat) 
    {
        this.stdDictFormat = stdDictFormat;
    }

    public String getStdDictFormat() 
    {
        return stdDictFormat;
    }
    public void setStdDictDesc(String stdDictDesc) 
    {
        this.stdDictDesc = stdDictDesc;
    }

    public String getStdDictDesc() 
    {
        return stdDictDesc;
    }
    public void setEnable(String enable) 
    {
        this.enable = enable;
    }

    public String getEnable() 
    {
        return enable;
    }
    public void setCrtTime(Date crtTime) 
    {
        this.crtTime = crtTime;
    }

    public Date getCrtTime() 
    {
        return crtTime;
    }
    public void setStdRef(String stdRef) 
    {
        this.stdRef = stdRef;
    }

    public String getStdRef() 
    {
        return stdRef;
    }
    public void setStdDictLengthType(String stdDictLengthType) 
    {
        this.stdDictLengthType = stdDictLengthType;
    }

    public String getStdDictLengthType() 
    {
        return stdDictLengthType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stdDictId", getStdDictId())
            .append("stdDictKeyGroup", getStdDictKeyGroup())
            .append("stdType", getStdType())
            .append("stdCode", getStdCode())
            .append("stdNameCn", getStdNameCn())
            .append("stdDictKey", getStdDictKey())
            .append("stdDictKeyRel", getStdDictKeyRel())
            .append("stdData", getStdData())
            .append("stdDictType", getStdDictType())
            .append("stdDictLength", getStdDictLength())
            .append("stdDictNumber", getStdDictNumber())
            .append("stdDictFormat", getStdDictFormat())
            .append("stdDictDesc", getStdDictDesc())
            .append("enable", getEnable())
            .append("crtTime", getCrtTime())
            .append("stdRef", getStdRef())
            .append("stdDictLengthType", getStdDictLengthType())
            .toString();
    }
}
