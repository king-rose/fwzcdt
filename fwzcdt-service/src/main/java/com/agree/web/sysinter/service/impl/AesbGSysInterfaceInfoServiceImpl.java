package com.agree.web.sysinter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agree.web.sysinter.mapper.AesbGSysInterfaceInfoMapper;
import com.agree.web.sysinter.domain.AesbGSysInterfaceInfo;
import com.agree.web.sysinter.service.IAesbGSysInterfaceInfoService;

/**
 * 系统接口信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@Service
public class AesbGSysInterfaceInfoServiceImpl implements IAesbGSysInterfaceInfoService 
{
    @Autowired
    private AesbGSysInterfaceInfoMapper aesbGSysInterfaceInfoMapper;

    /**
     * 查询系统接口信息
     * 
     * @param jkVersionId 系统接口信息ID
     * @return 系统接口信息
     */
    @Override
    public AesbGSysInterfaceInfo selectAesbGSysInterfaceInfoById(String jkVersionId)
    {
        return aesbGSysInterfaceInfoMapper.selectAesbGSysInterfaceInfoById(jkVersionId);
    }

    /**
     * 查询系统接口信息列表
     * 
     * @param aesbGSysInterfaceInfo 系统接口信息
     * @return 系统接口信息
     */
    @Override
    public List<AesbGSysInterfaceInfo> selectAesbGSysInterfaceInfoList(AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        return aesbGSysInterfaceInfoMapper.selectAesbGSysInterfaceInfoList(aesbGSysInterfaceInfo);
    }

    /**
     * 新增系统接口信息
     * 
     * @param aesbGSysInterfaceInfo 系统接口信息
     * @return 结果
     */
    @Override
    public int insertAesbGSysInterfaceInfo(AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        return aesbGSysInterfaceInfoMapper.insertAesbGSysInterfaceInfo(aesbGSysInterfaceInfo);
    }

    /**
     * 修改系统接口信息
     * 
     * @param aesbGSysInterfaceInfo 系统接口信息
     * @return 结果
     */
    @Override
    public int updateAesbGSysInterfaceInfo(AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        return aesbGSysInterfaceInfoMapper.updateAesbGSysInterfaceInfo(aesbGSysInterfaceInfo);
    }

    /**
     * 批量删除系统接口信息
     * 
     * @param jkVersionIds 需要删除的系统接口信息ID
     * @return 结果
     */
    @Override
    public int deleteAesbGSysInterfaceInfoByIds(String[] jkVersionIds)
    {
        return aesbGSysInterfaceInfoMapper.deleteAesbGSysInterfaceInfoByIds(jkVersionIds);
    }

    /**
     * 删除系统接口信息信息
     * 
     * @param jkVersionId 系统接口信息ID
     * @return 结果
     */
    @Override
    public int deleteAesbGSysInterfaceInfoById(String jkVersionId)
    {
        return aesbGSysInterfaceInfoMapper.deleteAesbGSysInterfaceInfoById(jkVersionId);
    }

    @Override
    public List<AesbGSysInterfaceInfo> queryIFaceListBySysId(String systemId) {
        return aesbGSysInterfaceInfoMapper.queryIFaceListBySysId(systemId);
    }
}
