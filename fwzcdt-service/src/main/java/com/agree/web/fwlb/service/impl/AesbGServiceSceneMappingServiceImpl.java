package com.agree.web.fwlb.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.agree.web.fwlb.domain.AesbGServiceSceneMapping;
import com.agree.web.fwlb.mapper.AesbGServiceSceneMappingMapper;
import com.agree.web.fwlb.service.IAesbGServiceSceneMappingService;
import com.agree.web.system.domain.OprSystem;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务场景消费关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
@Service
@AllArgsConstructor
public class AesbGServiceSceneMappingServiceImpl implements IAesbGServiceSceneMappingService
{
    private AesbGServiceSceneMappingMapper aesbGServiceSceneMappingMapper;

    /**
     * 查询服务场景消费关系
     * 
     * @param mappingId 服务场景消费关系ID
     * @return 服务场景消费关系
     */
    public AesbGServiceSceneMapping selectAesbGServiceSceneMappingById(String mappingId)
    {
        return aesbGServiceSceneMappingMapper.selectAesbGServiceSceneMappingById(mappingId);
    }

    /**
     * 查询服务场景消费关系列表
     * 
     * @param aesbGServiceSceneMapping 服务场景消费关系
     * @return 服务场景消费关系
     */
    @Override
    public List<AesbGServiceSceneMapping> selectAesbGServiceSceneMappingList(AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        return aesbGServiceSceneMappingMapper.selectAesbGServiceSceneMappingList(aesbGServiceSceneMapping);
    }

    /**
     * 新增服务场景消费关系
     * 
     * @param aesbGServiceSceneMapping 服务场景消费关系
     * @return 结果
     */
    @Override
    public int insertAesbGServiceSceneMapping(AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        return aesbGServiceSceneMappingMapper.insertAesbGServiceSceneMapping(aesbGServiceSceneMapping);
    }

    /**
     * 修改服务场景消费关系
     * 
     * @param aesbGServiceSceneMapping 服务场景消费关系
     * @return 结果
     */
    @Override
    public int updateAesbGServiceSceneMapping(AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        return aesbGServiceSceneMappingMapper.updateAesbGServiceSceneMapping(aesbGServiceSceneMapping);
    }

    /**
     * 批量删除服务场景消费关系
     * 
     * @param mappingIds 需要删除的服务场景消费关系ID
     * @return 结果
     */
    @Override
    public int deleteAesbGServiceSceneMappingByIds(String[] mappingIds)
    {
        return aesbGServiceSceneMappingMapper.deleteAesbGServiceSceneMappingByIds(mappingIds);
    }

    /**
     * 删除服务场景消费关系信息
     * 
     * @param mappingId 服务场景消费关系ID
     * @return 结果
     */
    @Override
    public int deleteAesbGServiceSceneMappingById(String mappingId)
    {
        return aesbGServiceSceneMappingMapper.deleteAesbGServiceSceneMappingById(mappingId);
    }

    @Override
    public Map<String,Object> sceneRatio(String proSysId) {

        Map<String,Object> data = new HashMap<>();
        List<String> reqSysList = new ArrayList<>();
        List<String> proSysList = new ArrayList<>();
        List<Map<String, Object>> list = aesbGServiceSceneMappingMapper.sceneRatio(proSysId);
        for (Map<String, Object> map : list) {
            if (!ObjectUtils.isEmpty(map.get("reqSysName"))){
                reqSysList.add(map.get("reqSysName").toString());
            }
            proSysList.add(map.get("proSysName").toString());
        }
        //去重
        reqSysList=reqSysList.stream().distinct().collect(Collectors.toList());
        proSysList=proSysList.stream().distinct().collect(Collectors.toList());
        data.put("list",list);
        data.put("reqSysNames",reqSysList);
        data.put("proSysNames",proSysList);
        data.put("svName",list.get(0).get("svName"));
        return data;
    }

}
