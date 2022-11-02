package com.agree.web.project.service.impl;

import java.util.List;

import com.agree.web.project.domain.AesbGSysProMapping;
import com.agree.web.project.mapper.AesbGSysProMappingMapper;
import com.agree.web.project.service.IAesbGSysProMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统项目关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
@Service
public class AesbGSysProMappingServiceImpl implements IAesbGSysProMappingService
{
    @Autowired
    private AesbGSysProMappingMapper aesbGSysProMappingMapper;

    /**
     * 查询系统项目关联
     * 
     * @param systemId 系统项目关联ID
     * @return 系统项目关联
     */
    @Override
    public AesbGSysProMapping selectAesbGSysProMappingById(String systemId)
    {
        return aesbGSysProMappingMapper.selectAesbGSysProMappingById(systemId);
    }

    /**
     * 查询系统项目关联列表
     * 
     * @param aesbGSysProMapping 系统项目关联
     * @return 系统项目关联
     */
    @Override
    public List<AesbGSysProMapping> selectAesbGSysProMappingList(AesbGSysProMapping aesbGSysProMapping)
    {
        return aesbGSysProMappingMapper.selectAesbGSysProMappingList(aesbGSysProMapping);
    }

    /**
     * 新增系统项目关联
     * 
     * @param aesbGSysProMapping 系统项目关联
     * @return 结果
     */
    @Override
    public int insertAesbGSysProMapping(AesbGSysProMapping aesbGSysProMapping)
    {
        return aesbGSysProMappingMapper.insertAesbGSysProMapping(aesbGSysProMapping);
    }

    /**
     * 修改系统项目关联
     * 
     * @param aesbGSysProMapping 系统项目关联
     * @return 结果
     */
    @Override
    public int updateAesbGSysProMapping(AesbGSysProMapping aesbGSysProMapping)
    {
        return aesbGSysProMappingMapper.updateAesbGSysProMapping(aesbGSysProMapping);
    }

    /**
     * 批量删除系统项目关联
     * 
     * @param systemIds 需要删除的系统项目关联ID
     * @return 结果
     */
    @Override
    public int deleteAesbGSysProMappingByIds(String[] systemIds)
    {
        return aesbGSysProMappingMapper.deleteAesbGSysProMappingByIds(systemIds);
    }

    /**
     * 删除系统项目关联信息
     * 
     * @param systemId 系统项目关联ID
     * @return 结果
     */
    @Override
    public int deleteAesbGSysProMappingById(String systemId)
    {
        return aesbGSysProMappingMapper.deleteAesbGSysProMappingById(systemId);
    }
}
