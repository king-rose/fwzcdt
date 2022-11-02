package com.agree.web.project.mapper;

import com.agree.web.project.domain.AesbGProjectInfo;

import java.util.List;

/**
 * 项目信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
public interface AesbGProjectInfoMapper 
{
    /**
     * 查询项目信息
     * 
     * @param projectCode 项目信息ID
     * @return 项目信息
     */
    public AesbGProjectInfo selectAesbGProjectInfoById(String projectCode);

    /**
     * 查询项目信息列表
     * 
     * @param aesbGProjectInfo 项目信息
     * @return 项目信息集合
     */
    public List<AesbGProjectInfo> selectAesbGProjectInfoList(AesbGProjectInfo aesbGProjectInfo);

    /**
     * 新增项目信息
     * 
     * @param aesbGProjectInfo 项目信息
     * @return 结果
     */
    public int insertAesbGProjectInfo(AesbGProjectInfo aesbGProjectInfo);

    /**
     * 修改项目信息
     * 
     * @param aesbGProjectInfo 项目信息
     * @return 结果
     */
    public int updateAesbGProjectInfo(AesbGProjectInfo aesbGProjectInfo);

    /**
     * 删除项目信息
     * 
     * @param projectCode 项目信息ID
     * @return 结果
     */
    public int deleteAesbGProjectInfoById(String projectCode);

    /**
     * 批量删除项目信息
     * 
     * @param projectCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteAesbGProjectInfoByIds(String[] projectCodes);
}
