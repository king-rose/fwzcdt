package com.agree.web.sysinter.service;

import java.util.List;
import com.agree.web.sysinter.domain.AesbGSysInterfaceInfo;

/**
 * 系统接口信息Service接口
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
public interface IAesbGSysInterfaceInfoService 
{
    /**
     * 查询系统接口信息
     * 
     * @param jkVersionId 系统接口信息ID
     * @return 系统接口信息
     */
    public AesbGSysInterfaceInfo selectAesbGSysInterfaceInfoById(String jkVersionId);

    /**
     * 查询系统接口信息列表
     * 
     * @param aesbGSysInterfaceInfo 系统接口信息
     * @return 系统接口信息集合
     */
    public List<AesbGSysInterfaceInfo> selectAesbGSysInterfaceInfoList(AesbGSysInterfaceInfo aesbGSysInterfaceInfo);

    /**
     * 新增系统接口信息
     * 
     * @param aesbGSysInterfaceInfo 系统接口信息
     * @return 结果
     */
    public int insertAesbGSysInterfaceInfo(AesbGSysInterfaceInfo aesbGSysInterfaceInfo);

    /**
     * 修改系统接口信息
     * 
     * @param aesbGSysInterfaceInfo 系统接口信息
     * @return 结果
     */
    public int updateAesbGSysInterfaceInfo(AesbGSysInterfaceInfo aesbGSysInterfaceInfo);

    /**
     * 批量删除系统接口信息
     * 
     * @param jkVersionIds 需要删除的系统接口信息ID
     * @return 结果
     */
    public int deleteAesbGSysInterfaceInfoByIds(String[] jkVersionIds);

    /**
     * 删除系统接口信息信息
     * 
     * @param jkVersionId 系统接口信息ID
     * @return 结果
     */
    public int deleteAesbGSysInterfaceInfoById(String jkVersionId);

    public List<AesbGSysInterfaceInfo> queryIFaceListBySysId(String systemId);
}
