package com.agree.web.fwlb.service;

import com.agree.web.fwlb.domain.AesbGServiceSceneMapping;
import com.agree.web.system.domain.OprSystem;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 服务场景消费关系Service接口
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
public interface IAesbGServiceSceneMappingService 
{
    /**
     * 查询服务场景消费关系
     * 
     * @param mappingId 服务场景消费关系ID
     * @return 服务场景消费关系
     */
    public AesbGServiceSceneMapping selectAesbGServiceSceneMappingById(String mappingId);

    /**
     * 查询服务场景消费关系列表
     * 
     * @param aesbGServiceSceneMapping 服务场景消费关系
     * @return 服务场景消费关系集合
     */
    public List<AesbGServiceSceneMapping> selectAesbGServiceSceneMappingList(AesbGServiceSceneMapping aesbGServiceSceneMapping);

    /**
     * 新增服务场景消费关系
     * 
     * @param aesbGServiceSceneMapping 服务场景消费关系
     * @return 结果
     */
    public int insertAesbGServiceSceneMapping(AesbGServiceSceneMapping aesbGServiceSceneMapping);

    /**
     * 修改服务场景消费关系
     * 
     * @param aesbGServiceSceneMapping 服务场景消费关系
     * @return 结果
     */
    public int updateAesbGServiceSceneMapping(AesbGServiceSceneMapping aesbGServiceSceneMapping);

    /**
     * 批量删除服务场景消费关系
     * 
     * @param mappingIds 需要删除的服务场景消费关系ID
     * @return 结果
     */
    public int deleteAesbGServiceSceneMappingByIds(String[] mappingIds);

    /**
     * 删除服务场景消费关系信息
     * 
     * @param mappingId 服务场景消费关系ID
     * @return 结果
     */
    public int deleteAesbGServiceSceneMappingById(String mappingId);

    public Map<String,Object> sceneRatio(String proSysId);

}
