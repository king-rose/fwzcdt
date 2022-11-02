package com.agree.web.system.service;

import com.agree.web.system.domain.OprSystem;
import com.agree.web.system.domain.Trend;

import java.util.List;
import java.util.Map;

/**
 * 系统信息Service接口
 *
 * @author ruoyi
 * @date 2022-06-16
 */
public interface IOprSystemService
{
    /**
     * 查询系统信息
     *
     * @param sysId 系统信息ID
     * @return 系统信息
     */
    public OprSystem selectOprSystemById(String sysId);

    /**
     * 查询系统信息列表
     *
     * @param oprSystem 系统信息
     * @return 系统信息集合
     */
    public List<OprSystem> selectOprSystemList(OprSystem oprSystem);

    /**
     * 新增系统信息
     *
     * @param oprSystem 系统信息
     * @return 结果
     */
    public int insertOprSystem(OprSystem oprSystem);

    /**
     * 修改系统信息
     *
     * @param oprSystem 系统信息
     * @return 结果
     */
    public int updateOprSystem(OprSystem oprSystem);

    /**
     * 批量删除系统信息
     *
     * @param sysIds 需要删除的系统信息ID
     * @return 结果
     */
    public int deleteOprSystemByIds(String[] sysIds);

    /**
     * 删除系统信息信息
     *
     * @param sysId 系统信息ID
     * @return 结果
     */
    public int deleteOprSystemById(String sysId);

    public int getSystemNum();

    public int getSceneNum();

    public int getConsumeNum();

    public List<Map<String,Object>> sceneRatioChart();

    public List<Map<String, Object>> serviceScene();

    List<Trend> getTrendStatistics(Integer timeType);

    List<Map> getPrincipals();

    List<String> getSystemLayers();

    List<String> getTechDepts();
}
