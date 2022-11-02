package com.agree.web.demand.mapper;


import com.agree.web.demand.domain.AesbGDemandInfo;
import com.agree.web.demand.domain.AesbSystemInterfaceDemand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 需求信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
@Mapper
public interface AesbGDemandInfoMapper 
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
     * 删除需求信息
     * 
     * @param demandId 需求信息ID
     * @return 结果
     */
    public int deleteAesbGDemandInfoById(String demandId);

    /**
     * 批量删除需求信息
     * 
     * @param demandIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAesbGDemandInfoByIds(String demandIds);

    /**
     * 新增关联数据
     * @param systemInterfaceDemand 需求额分解关联系统实体
     * @return 1：success
     */
    public int insertAssociationDemand(AesbSystemInterfaceDemand systemInterfaceDemand);

    //删除原关联数据
    public int deleteAssociationDemand(String demandId);

    List<AesbSystemInterfaceDemand> selectInterfaceDemandById(@Param("demandId") String demandId,@Param("principal") String principal);

    List<AesbGDemandInfo> selectMyTaskList(AesbGDemandInfo demandInfo);

    public int updateSystemInterFaceInfo(AesbSystemInterfaceDemand aesbSystemInterfaceDemand);

    List<AesbGDemandInfo> selectDemandAcceptList(AesbGDemandInfo aesbGDemandInfo);

    public String getMaxWorkDay(@Param("demandId")String demandId,@Param("principal")String principal);

    int downSystemInterFaceInfo(AesbSystemInterfaceDemand aesbSystemInterfaceDemand);

    public AesbSystemInterfaceDemand isAccepted(String demandId);

    AesbSystemInterfaceDemand isNotAccepted(String demandId);

    List<AesbGDemandInfo> selectFaceDemandListByPrincipal(String username);
}
