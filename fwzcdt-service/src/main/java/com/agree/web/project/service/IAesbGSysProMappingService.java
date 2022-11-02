package com.agree.web.project.service;


import com.agree.web.project.domain.AesbGSysProMapping;

import java.util.List;

/**
 * 系统项目关联Service接口
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
public interface IAesbGSysProMappingService 
{
    /**
     * 查询系统项目关联
     * 
     * @param systemId 系统项目关联ID
     * @return 系统项目关联
     */
    public AesbGSysProMapping selectAesbGSysProMappingById(String systemId);

    /**
     * 查询系统项目关联列表
     * 
     * @param aesbGSysProMapping 系统项目关联
     * @return 系统项目关联集合
     */
    public List<AesbGSysProMapping> selectAesbGSysProMappingList(AesbGSysProMapping aesbGSysProMapping);

    /**
     * 新增系统项目关联
     * 
     * @param aesbGSysProMapping 系统项目关联
     * @return 结果
     */
    public int insertAesbGSysProMapping(AesbGSysProMapping aesbGSysProMapping);

    /**
     * 修改系统项目关联
     * 
     * @param aesbGSysProMapping 系统项目关联
     * @return 结果
     */
    public int updateAesbGSysProMapping(AesbGSysProMapping aesbGSysProMapping);

    /**
     * 批量删除系统项目关联
     * 
     * @param systemIds 需要删除的系统项目关联ID
     * @return 结果
     */
    public int deleteAesbGSysProMappingByIds(String[] systemIds);

    /**
     * 删除系统项目关联信息
     * 
     * @param systemId 系统项目关联ID
     * @return 结果
     */
    public int deleteAesbGSysProMappingById(String systemId);
}
