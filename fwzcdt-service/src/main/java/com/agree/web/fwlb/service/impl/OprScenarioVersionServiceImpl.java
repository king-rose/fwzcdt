package com.agree.web.fwlb.service.impl;

import com.agree.activiti.service.IProcessService;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.exception.CustomException;
import com.agree.common.utils.DateUtils;
import com.agree.common.utils.JsonObjectUtils;
import com.agree.common.utils.SecurityUtils;
import com.agree.common.utils.uuid.IdUtils;
import com.agree.web.fwlb.domain.*;
import com.agree.web.fwlb.mapper.OprScenarioFieldMapper;
import com.agree.web.fwlb.mapper.OprScenarioVersionMapper;
import com.agree.web.fwlb.mapper.OprServiceMapper;
import com.agree.web.fwlb.service.OprScenarioVersionService;
import com.agree.web.system.domain.OprSystem;
import com.agree.web.system.mapper.OprSystemMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OprScenarioVersionServiceImpl implements OprScenarioVersionService {

    @Resource
    private OprScenarioVersionMapper oprScenarioVersionMapper;
    @Resource
    private OprScenarioFieldMapper oprScenarioFieldMapper;
    @Resource
    private OprServiceMapper oprServiceMapper;
    @Resource
    private OprSystemMapper oprSystemMapper;
    @Resource
    private IProcessService processService;

    /**
     * 服务场景列表
     *
     * @param oprScenarioVersion 查询条件
     * @return
     */
    public List<OprScenarioVersion> selectOsvList(OprScenarioVersion oprScenarioVersion) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            oprScenarioVersion.setCreateBy(SecurityUtils.getUsername());
        }
        List<OprScenarioVersion> list = oprScenarioVersionMapper.selectOsvList(oprScenarioVersion);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                try {
                    processService.richProcessField(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }

    public int removeScene(String[] svIds) {
        return oprScenarioVersionMapper.removeScene(svIds);
    }

    public Map<String, Object> getBasic(String svId) {
        return oprScenarioVersionMapper.getBasicByServiceId(svId);
    }

    public OprScenarioVersionParam getQuatoInfo(String svId) {
        return oprScenarioVersionMapper.getQuatoInfo(svId);
    }

    @Override
    public List<AesbGSceneResource> getResourcefo(String svId) {
        return oprScenarioVersionMapper.getResourcefo(svId);
    }

    @Override
    public List<Map<String, Object>> getConsumeIfo(String svId) {
        return oprScenarioVersionMapper.getConsumeIfo(svId);
    }

    @Override
    public List<OprScenarioVersion> selectChangeRecordList(String svId) {

        Map<String, Object> map = new HashMap<>();

        //根据svid获取版本号服务编号等信息
        OprScenarioVersion oprScenarioVersion = oprScenarioVersionMapper.getVersionInfoBySvId(svId);
        List<AesbGSceneResource> resourcefo = oprScenarioVersionMapper.getResourcefo(svId);
        //获取场景版本列表
        List<OprScenarioVersion> list = oprScenarioVersionMapper.getVersionInfo(oprScenarioVersion.getServiceId(), oprScenarioVersion.getSvCode());
        //查询变更记录表
        for (OprScenarioVersion scenarioVersion : list) {
            List<Map<String, Object>> maps = oprScenarioVersionMapper.getChangeRecord(scenarioVersion.getSvId());
            scenarioVersion.setShow(true);
            for (AesbGSceneResource resource : resourcefo) {
                if (resource.getResourceType().equals("2")) {
                    scenarioVersion.setResourceFile(resource.getResourceName());
                }
                if (resource.getResourceType().equals("1")) {
                    scenarioVersion.setResourceBall(resource.getResourceName());
                }
            }
            scenarioVersion.setMaps(maps);
        }
        return list;
    }

    public List<OprScenarioField> scenarioDefinedIn(String svId) {
        return oprScenarioFieldMapper.scenarioDefinedIn(svId);
    }

    public List<OprScenarioField> scenarioDefinedOut(String svId) {
        return oprScenarioFieldMapper.scenarioDefinedOut(svId);
    }

    @Override
    public OprSystem getSysInfoByCode(String projectCode) {
        return oprScenarioVersionMapper.getSysInfoByCode(projectCode);
    }

    @Override
    public String getBasicByCodeAndName(String svCode, String svName) {
        return oprScenarioVersionMapper.getBasicByCodeAndName(svCode, svName);
    }

    @Transactional
    public AjaxResult release(Map<String, Object> map) {
        String instanceId;

        //服务分类
        List<OprService> oprServices = JsonObjectUtils.changeList(map.get("serviceData"), OprService.class);
        //服务场景
        List<OprScenarioVersion> oprScenarioVersions = JsonObjectUtils.changeList(map.get("svSceneData"), OprScenarioVersion.class);
        //场景指标
        List<OprScenarioVersionParam> oprScenarioVersionParams = JsonObjectUtils.changeList(map.get("svParamsData"), OprScenarioVersionParam.class);
        //场景字段
        Map<String, Object> filedMap = (Map<String, Object>) map.get("sceneFieldData");
        //场景字段
        List<OprScenarioField> fieldList = JsonObjectUtils.changeList(filedMap.get("newInData"), OprScenarioField.class);
        fieldList.addAll(JsonObjectUtils.changeList(filedMap.get("newOutData"), OprScenarioField.class));


        //系统名称
        String sysName = oprScenarioVersions.get(0).getSysName();
        String scnSysId = oprSystemMapper.getSysIdByName(sysName);

        //查询服务是否存在
        OprService oprService = oprServiceMapper.selectServiceById(oprServices.get(0).getSvcCode());
        if (ObjectUtils.isEmpty(oprService)) {
            throw new CustomException("发布失败,服务不存在!");
        }

        for (OprScenarioVersion oprScenarioVersion : oprScenarioVersions) {
            oprScenarioVersion.setServiceId(oprService.getSvcId());
            oprScenarioVersion.setSvCreateTime(DateUtils.getNowDate());
            oprScenarioVersion.setCreateBy(SecurityUtils.getUsername());
            oprScenarioVersion.setSvScnId(IdUtils.fastUUID());
            oprScenarioVersion.setSvId(IdUtils.fastUUID());
            oprScenarioVersion.setResponseFlag("1");
            oprScenarioVersion.setJkAdapType("2");
            oprScenarioVersion.setLockType("0");
            oprScenarioVersion.setAuditState("0");
            oprScenarioVersion.setSvStatus("1");
            oprScenarioVersion.setCreateTime(DateUtils.getNowDate());
            oprScenarioVersion.setCreateBy(SecurityUtils.getUsername());
            oprScenarioVersion.setScnSysId(scnSysId);
            oprScenarioVersion.setSvNo("1.0");
            oprScenarioVersion.setSvSvId("1.0.0");
        }
        int svRow = oprScenarioVersionMapper.saveBatch(oprScenarioVersions);
        if (svRow == 0) {
            throw new CustomException("服务场景添加失败!");
        }

        for (OprScenarioVersionParam versionParam : oprScenarioVersionParams) {
            versionParam.setSceneId(IdUtils.fastSimpleUUID());
            versionParam.setSceneVersionId(oprScenarioVersions.get(0).getSvId());
            versionParam.setScenePriority("9");
        }


        int paramRow = oprScenarioVersionMapper.saveParamBatch(oprScenarioVersionParams);
        if (paramRow == 0) {
            throw new CustomException("场景指标添加失败!");
        }

        for (int i = 0; i < fieldList.size(); i++) {
            fieldList.get(i).setSfId(IdUtils.fastSimpleUUID());
            fieldList.get(i).setSfType("1");//输入
            fieldList.get(i).setSfSvId(oprScenarioVersions.get(0).getSvId());//场景版本Id
            fieldList.get(i).setScnCode(oprScenarioVersions.get(0).getSvCode());//场景码
            fieldList.get(i).setSfCount(Long.valueOf(i));
            if (fieldList.get(i).getSfDataType().equals("String")) {
                fieldList.get(i).setSfDataType("1");
            } else {
                fieldList.get(i).setSfDataType("2");
            }
            if (fieldList.get(i).getSfLengthType().equals("定长")) {
                fieldList.get(i).setSfLengthType("1");
            } else if (fieldList.get(i).getSfLengthType().equals("最长")) {
                fieldList.get(i).setSfLengthType("0");
            }
        }
        int inFiledRow = oprScenarioFieldMapper.saveBatch(fieldList);
        if (inFiledRow == 0) {
            throw new CustomException("场景字段添加失败!");
        }
        return AjaxResult.success("发布成功!");
    }

    public int updateScenarioVersion(OprScenarioVersion oprScenarioVersion) {
        return oprScenarioVersionMapper.updateScenarioVersion(oprScenarioVersion);
    }

    public OprScenarioVersion getDetailBySvId(String svId) {
        return oprScenarioVersionMapper.getDetailBySvId(svId);
    }

    public List<Map> getSceneFiledInfo(SceneFieldRequest request) {
        return oprScenarioVersionMapper.getSceneFiledInfo(request);
    }

    public int updateScenarioVersionParam(OprScenarioVersionParam versionParam) {
        return oprScenarioVersionMapper.updateScenarioVersionParam(versionParam);
    }

    public AjaxResult updateScenarioVersionField(String svId, List<OprScenarioField> list) {

        oprScenarioVersionMapper.deleteScenarioVersionField(svId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSfId()==null){
                list.get(i).setSfId(DateUtils.dateTimeNow());
                list.get(i).setSfCount(i+1L);
            }
            list.get(i).setSfSvId(svId);
        }
        oprScenarioVersionMapper.insertBatchField(list);
        return AjaxResult.success();
    }

}
