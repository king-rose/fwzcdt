package com.agree.web.project.service.impl;

import com.agree.web.project.domain.AesbGProjectInfo;
import com.agree.web.project.mapper.AesbGProjectInfoMapper;
import com.agree.web.project.service.IAesbGProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
@Service
public class AesbGProjectInfoServiceImpl implements IAesbGProjectInfoService
{
    @Autowired
    private AesbGProjectInfoMapper aesbGProjectInfoMapper;

    /**
     * 查询项目信息
     * 
     * @param projectCode 项目信息ID
     * @return 项目信息
     */
    @Override
    public AesbGProjectInfo selectAesbGProjectInfoById(String projectCode)
    {
        return aesbGProjectInfoMapper.selectAesbGProjectInfoById(projectCode);
    }

    /**
     * 查询项目信息列表
     * 
     * @param aesbGProjectInfo 项目信息
     * @return 项目信息
     */
    @Override
    public List<AesbGProjectInfo> selectAesbGProjectInfoList(AesbGProjectInfo aesbGProjectInfo)
    {
        return aesbGProjectInfoMapper.selectAesbGProjectInfoList(aesbGProjectInfo);
    }

    /**
     * 新增项目信息
     * 
     * @param aesbGProjectInfo 项目信息
     * @return 结果
     */
    @Override
    public int insertAesbGProjectInfo(AesbGProjectInfo aesbGProjectInfo)
    {
        return aesbGProjectInfoMapper.insertAesbGProjectInfo(aesbGProjectInfo);
    }

    /**
     * 修改项目信息
     * 
     * @param aesbGProjectInfo 项目信息
     * @return 结果
     */
    @Override
    public int updateAesbGProjectInfo(AesbGProjectInfo aesbGProjectInfo)
    {
        return aesbGProjectInfoMapper.updateAesbGProjectInfo(aesbGProjectInfo);
    }

    /**
     * 批量删除项目信息
     * 
     * @param projectCodes 需要删除的项目信息ID
     * @return 结果
     */
    @Override
    public int deleteAesbGProjectInfoByIds(String[] projectCodes)
    {
        return aesbGProjectInfoMapper.deleteAesbGProjectInfoByIds(projectCodes);
    }

    /**
     * 删除项目信息信息
     * 
     * @param projectCode 项目信息ID
     * @return 结果
     */
    @Override
    public int deleteAesbGProjectInfoById(String projectCode)
    {
        return aesbGProjectInfoMapper.deleteAesbGProjectInfoById(projectCode);
    }
}
