package com.agree.web.fwlb.service;

import com.agree.common.core.domain.AjaxResult;
import com.agree.web.fwlb.domain.*;
import com.agree.web.system.domain.OprSystem;

import java.util.List;
import java.util.Map;

public interface OprScenarioVersionService {

    public List<OprScenarioVersion> selectOsvList(OprScenarioVersion oprScenarioVersion);

    public int removeScene(String[] svIds);

    public Map<String,Object> getBasic(String svId);

    public OprScenarioVersionParam getQuatoInfo(String svId);

    public List<AesbGSceneResource> getResourcefo(String svId);

    public List<Map<String,Object>> getConsumeIfo(String svId);

    public List<OprScenarioVersion> selectChangeRecordList(String svId);

    public List<OprScenarioField> scenarioDefinedIn(String svId);

    public List<OprScenarioField> scenarioDefinedOut(String svId);

    public OprSystem getSysInfoByCode(String projectCode);

    public String  getBasicByCodeAndName(String svCode, String svName);

    public AjaxResult release(Map<String, Object> map);

    public int updateScenarioVersion(OprScenarioVersion oprScenarioVersion);

    public OprScenarioVersion getDetailBySvId(String svId);

    List<Map> getSceneFiledInfo(SceneFieldRequest request);

    public int updateScenarioVersionParam(OprScenarioVersionParam versionParam);

    public AjaxResult updateScenarioVersionField(String svId,List<OprScenarioField> list);
}
