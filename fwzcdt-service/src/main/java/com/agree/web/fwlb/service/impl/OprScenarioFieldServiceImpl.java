package com.agree.web.fwlb.service.impl;

import java.util.List;

import com.agree.web.fwlb.domain.OprScenarioField;
import com.agree.web.fwlb.mapper.OprScenarioFieldMapper;
import com.agree.web.fwlb.service.IOprScenarioFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务场景字段详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
@Service
public class OprScenarioFieldServiceImpl implements IOprScenarioFieldService
{
    @Autowired
    private OprScenarioFieldMapper oprScenarioFieldMapper;

    /**
     * 查询服务场景字段详情
     * 
     * @param sfSvId 服务场景字段详情ID
     * @return 服务场景字段详情
     */
    @Override
    public OprScenarioField selectOprScenarioFieldById(String sfSvId)
    {
        return oprScenarioFieldMapper.selectOprScenarioFieldById(sfSvId);
    }

    /**
     * 查询服务场景字段详情列表
     * 
     * @param oprScenarioField 服务场景字段详情
     * @return 服务场景字段详情
     */
    @Override
    public List<OprScenarioField> selectOprScenarioFieldList(OprScenarioField oprScenarioField)
    {
        return oprScenarioFieldMapper.selectOprScenarioFieldList(oprScenarioField);
    }

    /**
     * 新增服务场景字段详情
     * 
     * @param oprScenarioField 服务场景字段详情
     * @return 结果
     */
    @Override
    public int insertOprScenarioField(OprScenarioField oprScenarioField)
    {
        return oprScenarioFieldMapper.insertOprScenarioField(oprScenarioField);
    }

    /**
     * 修改服务场景字段详情
     * 
     * @param oprScenarioField 服务场景字段详情
     * @return 结果
     */
    @Override
    public int updateOprScenarioField(OprScenarioField oprScenarioField)
    {
        return oprScenarioFieldMapper.updateOprScenarioField(oprScenarioField);
    }

    /**
     * 批量删除服务场景字段详情
     * 
     * @param sfSvIds 需要删除的服务场景字段详情ID
     * @return 结果
     */
    @Override
    public int deleteOprScenarioFieldByIds(String[] sfSvIds)
    {
        return oprScenarioFieldMapper.deleteOprScenarioFieldByIds(sfSvIds);
    }

    /**
     * 删除服务场景字段详情信息
     * 
     * @param sfSvId 服务场景字段详情ID
     * @return 结果
     */
    @Override
    public int deleteOprScenarioFieldById(String sfSvId)
    {
        return oprScenarioFieldMapper.deleteOprScenarioFieldById(sfSvId);
    }
}
