package com.agree.web.system.service.impl;

import com.agree.common.utils.DateUtils;
import com.agree.common.utils.SecurityUtils;
import com.agree.common.utils.StringUtils;
import com.agree.common.utils.uuid.IdUtils;
import com.agree.web.fwlb.mapper.OprScenarioVersionMapper;
import com.agree.web.fwlb.mapper.OprServiceMapper;
import com.agree.web.system.domain.OprSystem;
import com.agree.web.system.domain.Trend;
import com.agree.web.system.mapper.OprSystemMapper;
import com.agree.web.system.service.IOprSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-06-16
 */
@Service
public class OprSystemServiceImpl implements IOprSystemService
{
    @Autowired
    private OprSystemMapper oprSystemMapper;

    @Autowired
    private OprScenarioVersionMapper oprScenarioVersionMapper;
    
    @Autowired
    private OprServiceMapper oprServiceMapper;

    /**
     * 查询系统信息
     *
     * @param sysId 系统信息ID
     * @return 系统信息
     */
    @Override
    public OprSystem selectOprSystemById(String sysId)
    {
        return oprSystemMapper.selectOprSystemById(sysId);
    }

    /**
     * 查询系统信息列表
     *
     * @param oprSystem 系统信息
     * @return 系统信息
     */
    @Override
    public List<OprSystem> selectOprSystemList(OprSystem oprSystem)
    {
        if (!oprSystem.getSwitchs()){
            oprSystem.setManagerId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        return oprSystemMapper.selectOprSystemList(oprSystem);
    }

    /**
     * 新增系统信息
     *
     * @param oprSystem 系统信息
     * @return 结果
     */
    @Override
    public int insertOprSystem(OprSystem oprSystem)
    {
        oprSystem.setSysId(IdUtils.fastUUID());
        oprSystem.setAreaId("f02073b3-dd0e-426e-a86a-740d32a48804");
        oprSystem.setSysCreateTime(DateUtils.getNowDate());
        oprSystem.setSysCreateUser(SecurityUtils.getUsername());
        oprSystem.setSysUpdateTime(DateUtils.getNowDate());
        oprSystem.setSysScId("1");
        return oprSystemMapper.insertOprSystem(oprSystem);
    }

    /**
     * 修改系统信息
     *
     * @param oprSystem 系统信息
     * @return 结果
     */
    @Override
    public int updateOprSystem(OprSystem oprSystem)
    {
        return oprSystemMapper.updateOprSystem(oprSystem);
    }

    /**
     * 批量删除系统信息
     *
     * @param sysIds 需要删除的系统信息ID
     * @return 结果
     */
    @Override
    public int deleteOprSystemByIds(String[] sysIds)
    {
        return oprSystemMapper.deleteOprSystemByIds(sysIds);
    }

    /**
     * 删除系统信息信息
     *
     * @param sysId 系统信息ID
     * @return 结果
     */
    @Override
    public int deleteOprSystemById(String sysId)
    {
        return oprSystemMapper.deleteOprSystemById(sysId);
    }

    @Override
    public int getSystemNum() {
        return oprSystemMapper.getSystemNum();
    }

    @Override
    public int getSceneNum() {
        return oprSystemMapper.getSceneNum();
    }

    @Override
    public int getConsumeNum() {
        return oprSystemMapper.getConsumeNum();
    }

    public List<Trend> getTrendStatistics(Integer timeType) {
        return oprSystemMapper.getTrendStatistics(timeType);
    }

    public List<Map> getPrincipals() {
        return oprSystemMapper.getPrincipals();
    }

    public List<String> getSystemLayers(){
        return oprSystemMapper.getSystemLayers();
    }

    public List<String> getTechDepts() {
        List<String> techDepts = oprSystemMapper.getTechDepts();
        for (String techDept : techDepts) {
            if (StringUtils.isEmpty(techDept)){
                techDepts.remove(techDept);
            }
        }
        return techDepts;
    }

    public List<Map<String, Object>> sceneRatioChart() {
        //服务场景占比前10
        List<Map<String, Object>> list = oprSystemMapper.sceneRatioChart();
        //系统以及服务信息
        for (Map<String, Object> map : list) {
            //服务场景名称
            String svName = map.get("svName").toString();
            //系统名称
            String sysName = map.get("sysName").toString();
            String serviceId = map.get("serviceId").toString();
            Map<String, Object> serviceInfo = oprServiceMapper.getServiceInfo(serviceId);
            //服务编号
            String svcCode = serviceInfo.get("svcCode").toString();
            //赋值
            map.put("svcCode",svcCode);
            map.put("svName",svName);
            map.put("sysName",sysName);

        }
        return list;
    }
    public List<Map<String, Object>> serviceScene(){
        List<Map<String, Object>> list = oprSystemMapper.serviceScene();
        for (Map<String, Object> map : list) {
            OprSystem oprSystem = oprSystemMapper.getSystemInfoById(map.get("sysId").toString());
            map.put("sysCode", oprSystem.getSysCode());
            map.put("sysName", oprSystem.getSysName());
            map.put("principal", oprSystem.getPrincipal());
        }
        return list;
    }
}
