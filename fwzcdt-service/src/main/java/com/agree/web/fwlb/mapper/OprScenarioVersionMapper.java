package com.agree.web.fwlb.mapper;

import com.agree.web.fwlb.domain.*;
import com.agree.web.system.domain.OprSystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OprScenarioVersionMapper {

    public List<OprScenarioVersion> selectOsvList(OprScenarioVersion oprScenarioVersion);

    public int removeScene(String[] svIds);

    public Map<String,Object> getBasicByServiceId(String svId);

    public OprScenarioVersionParam getQuatoInfo(String sceneVersionId);

    public List<AesbGSceneResource> getResourcefo(String svId);

    public List<Map<String,Object>> getConsumeIfo(String svId);

    public OprScenarioVersion getVersionInfoBySvId(String svId);

    public String selectResourceName(@Param("svId") String svId,@Param("resourceType")  int resourceType);

    public String getSceneVersionNameBySvId(String svId);

    public List<OprScenarioVersion> getVersionInfo(@Param("serviceId") String serviceId,@Param("svCode") String svCode);

    public List<Map<String, Object>> getChangeRecord(String svId);

    public OprSystem getSysInfoByCode(String projectCode);

    public String getBasicByCodeAndName(@Param("svCode") String svCode,@Param("svName") String svName);

    public int saveBatch(List<OprScenarioVersion> list);

    public int saveParamBatch(List<OprScenarioVersionParam> list);

    public int updateScenarioVersion(OprScenarioVersion oprScenarioVersion);

    public OprScenarioVersion getDetailBySvId(String svId);

    public Map<String, Object> getInstanceBusinessByInstanceId(String instanceId);

    void updateAuditState(@Param("businessKey") String businessKey,@Param("module") String module);

    List<Map> getSceneFiledInfo(SceneFieldRequest request);

    int updateScenarioVersionParam(OprScenarioVersionParam versionParam);

    int updateBatch(List<OprScenarioField> list);

    int getSceneFieldBySvId(OprScenarioField field);

    int updateScenarioVersionField(OprScenarioField field);

    int insertScenarioVersionField(OprScenarioField field);

    void deleteScenarioVersionField(String svId);

    int insertBatchField(List<OprScenarioField> list);
}
