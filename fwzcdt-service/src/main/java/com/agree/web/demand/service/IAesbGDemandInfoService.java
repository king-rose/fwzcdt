package com.agree.web.demand.service;


import com.agree.web.demand.domain.AesbGDemandInfo;
import com.agree.web.demand.domain.AesbSystemInterfaceDemand;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 需求信息Service接口
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
public interface IAesbGDemandInfoService 
{
    /**
     * 查询需求信息
     * 
     * @param demandId 需求信息ID
     * @return 需求信息
     */
    public AesbGDemandInfo selectAesbGDemandInfoById(String demandId);

    /**
     * 查询需求信息列表
     * 
     * @param aesbGDemandInfo 需求信息
     * @return 需求信息集合
     */
    public List<AesbGDemandInfo> selectAesbGDemandInfoList(AesbGDemandInfo aesbGDemandInfo);

    /**
     * 新增需求信息
     * 
     * @param aesbGDemandInfo 需求信息
     * @return 结果
     */
    public int insertAesbGDemandInfo(AesbGDemandInfo aesbGDemandInfo);

    /**
     * 修改需求信息
     * 
     * @param aesbGDemandInfo 需求信息
     * @return 结果
     */
    public int updateAesbGDemandInfo(AesbGDemandInfo aesbGDemandInfo);

    /**
     * 批量删除需求信息
     * 
     * @param demandIds 需要删除的需求信息ID
     * @return 结果
     */
    public int deleteAesbGDemandInfoByIds(String demandIds);

    /**
     * 删除需求信息信息
     * 
     * @param demandId 需求信息ID
     * @return 结果
     */
    public int deleteAesbGDemandInfoById(String demandId);

    public int insertAssociationDemand(AesbSystemInterfaceDemand systemInterfaceDemand);

    //删除原关联数据
    public int deleteAssociationDemand(String demandId);

    PageInfo<AesbGDemandInfo> selectMyTaskList(AesbGDemandInfo demandInfo, Integer pageNum,Integer pageSize);

    public int updateSystemInterFaceInfo(AesbSystemInterfaceDemand aesbSystemInterfaceDemand);

    List<AesbGDemandInfo> selectDemandAcceptList(AesbGDemandInfo aesbGDemandInfo);

    public List<Map<String,Object>> getTaskProcess(AesbGDemandInfo aesbGDemandInfo, Integer pageNum,Integer pageSize);

    public int downSystemInterFaceInfo(AesbSystemInterfaceDemand aesbSystemInterfaceDemand);
}
