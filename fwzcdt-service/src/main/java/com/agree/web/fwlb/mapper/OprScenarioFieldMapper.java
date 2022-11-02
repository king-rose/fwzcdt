package com.agree.web.fwlb.mapper;

import com.agree.web.fwlb.domain.OprScenarioField;
import com.agree.web.fwlb.domain.OprScenarioVersion;

import java.util.List;

/**
 * 服务场景字段详情Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
public interface OprScenarioFieldMapper 
{
    /**
     * 查询服务场景字段详情
     * 
     * @param sfSvId 服务场景字段详情ID
     * @return 服务场景字段详情
     */
    public OprScenarioField selectOprScenarioFieldById(String sfSvId);

    /**
     * 查询服务场景字段详情列表
     * 
     * @param oprScenarioField 服务场景字段详情
     * @return 服务场景字段详情集合
     */
    public List<OprScenarioField> selectOprScenarioFieldList(OprScenarioField oprScenarioField);

    /**
     * 新增服务场景字段详情
     * 
     * @param oprScenarioField 服务场景字段详情
     * @return 结果
     */
    public int insertOprScenarioField(OprScenarioField oprScenarioField);

    /**
     * 修改服务场景字段详情
     * 
     * @param oprScenarioField 服务场景字段详情
     * @return 结果
     */
    public int updateOprScenarioField(OprScenarioField oprScenarioField);

    /**
     * 删除服务场景字段详情
     * 
     * @param sfSvId 服务场景字段详情ID
     * @return 结果
     */
    public int deleteOprScenarioFieldById(String sfSvId);

    /**
     * 批量删除服务场景字段详情
     * 
     * @param sfSvIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOprScenarioFieldByIds(String[] sfSvIds);

    public List<OprScenarioField> scenarioDefinedIn(String svId);

    public List<OprScenarioField> scenarioDefinedOut(String svId);

    public int saveBatch(List<OprScenarioField> list);

}
