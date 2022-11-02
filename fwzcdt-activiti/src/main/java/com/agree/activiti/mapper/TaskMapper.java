package com.agree.activiti.mapper;

import com.agree.activiti.domain.InstanceBusiness;
import com.agree.activiti.domain.TaskVo;
import org.activiti.engine.history.HistoricProcessInstance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 一只闲鹿
 */
@Repository
public interface TaskMapper {

    List<Map> findTodoList(TaskVo taskVo);

    @Select(" <script>SELECT\n" +
            "\tCOUNT(*) AS COUNT_\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tA.*\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT DISTINCT\n" +
            "\t\t\t\t\tRES.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tACT_RU_TASK RES\n" +
            "\t\t\t\tLEFT JOIN ACT_RU_IDENTITYLINK I ON I.TASK_ID_ = RES.ID_\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tRES.SUSPENSION_STATE_ = 1\n" +
            "\t\t\t\tAND (\n" +
            "\t\t\t\t\tRES.ASSIGNEE_ = #{userId}\n" +
            "\t\t\t\t\tOR (\n" +
            "\t\t\t\t\t\tRES.ASSIGNEE_ IS NULL\n" +
            "\t\t\t\t\t\tAND (\n" +
            "\t\t\t\t\t\t\tI.USER_ID_ = #{userId}\n" +
            "\t\t\t\t\t\t\tOR I.GROUP_ID_ IN (\n" +
            "\t\t\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\t\t\tG.GROUP_ID_\n" +
            "\t\t\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\t\t\tACT_ID_MEMBERSHIP G\n" +
            "\t\t\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\t\t\tG.USER_ID_ = #{userId}\n" +
            "\t\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\t)\n" +
            "\t\t\t\tORDER BY\n" +
            "\t\t\t\t\tRES.CREATE_TIME_ DESC\n" +
            "\t\t\t) A\n" +
            "\t) A\n" +
            "WHERE\n" +
            "\t1 = 1 <if test='taskId != null'> AND ID_ = #{taskId} </if><if test='taskName != null'> AND NAME_ = #{taskName} </if><if test='instanceId != null'> AND PROC_INST_ID_ = #{instanceId} </if> \n" +
            " </script> ")
    Integer findTodoCount(TaskVo taskVo);

    List<Map> findDoneList(TaskVo taskVo);

    @Select(" <script>SELECT\n" +
            "\tCOUNT(*) AS COUNT_\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tA.*\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT DISTINCT\n" +
            "\t\t\t\t\tRES.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tACT_HI_TASKINST RES\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tRES.ASSIGNEE_ = #{userId}\n" +
            "\t\t\t\tAND RES.END_TIME_ IS NOT NULL\n" +
            "\t\t\t\tORDER BY\n" +
            "\t\t\t\t\tRES.END_TIME_ DESC\n" +
            "\t\t\t) A\n" +
            "\t\tWHERE\n" +
            "\t\t\t1 = 1\n" +
            "\t) A\n" +
            "WHERE\n" +
            "\t1 = 1 <if test='taskId != null'> AND ID_ = #{taskId} </if><if test='taskName != null'> AND NAME_ = #{taskName} </if><if test='instanceId != null'> AND PROC_INST_ID_ = #{instanceId} </if> \n" +
            " </script> ")
    Integer findDoneCount(TaskVo taskVo);

    @Insert(" INSERT INTO INSTANCE_BUSINESS (id,instance_id, business_key, module) VALUES (( SELECT nvl( max( id ), 1 ) FROM INSTANCE_BUSINESS ) ,#{instanceId}, #{businessKey}, #{module}) ")
    int insertInstanceBusiness(InstanceBusiness instanceBusiness);

    @Select(" SELECT * FROM INSTANCE_BUSINESS WHERE 1=1 AND INSTANCE_ID = #{instanceId} AND ROWNUM <= 1 ")
    Map<String, Object> selectInstanceBusinessByInstanceId(String instanceId);

    @Select(" SELECT * FROM ${module} WHERE SV_ID = #{id} ")
    Map<String, Object> selectBusinessByBusinessKeyAndModule(@Param("id") String id, @Param("module") String module);

    @Select(" SELECT * FROM ${module} WHERE DEMAND_ID = #{id} ")
    Map<String, Object> selectBusinessByBusinessKeyAndModuleToDemand(@Param("id") String id, @Param("module") String module);

    @Select(" SELECT * FROM ${module} WHERE id = #{id} ")
    Map<String, Object> selectBusinessByBusinessKeyAndModuleToOther(@Param("id") String id, @Param("module") String module);

    @Select(" SELECT END_ACT_ID_ as endActId FROM act_hi_procinst WHERE PROC_INST_ID_ = #{instanceId} and BUSINESS_KEY_ = #{businessKey} ")
    String getEndActIdByInstanceIdAndBusinessKey(@Param("instanceId") String instanceId, @Param("businessKey") String businessKey);

    @Select(" SELECT instance_id as instanceId,business_key as businessKey,module FROM instance_business WHERE instance_id = #{instanceId} ")
    Map<String, Object> getInstanceBusinessByInstanceId(String instanceId);

    @Update("  update ${module} set AUDIT_STATE = #{auditState} where SV_ID = #{businessKey}")
    int updateAuditState(@Param("businessKey") String businessKey,@Param("module") String module,@Param("auditState") String auditState);

    @Select(" SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\ta.*,\n" +
            "\t\tROWNUM rnum \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT \n" +
            "\t\t\tRES.* ,\n" +
            "\t\t\tDEF.KEY_ AS PROC_DEF_KEY_,\n" +
            "\t\t\tDEF.NAME_ AS PROC_DEF_NAME_,\n" +
            "\t\t\tDEF.VERSION_ AS PROC_DEF_VERSION_,\n" +
            "\t\t\tDEF.DEPLOYMENT_ID_ AS DEPLOYMENT_ID_ \n" +
            "\t\tFROM\n" +
            "\t\t\tACT_HI_PROCINST RES LEFT outer\n" +
            "\t\t\tJOIN ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_ \n" +
            "\t\tWHERE\n" +
            "\t\t\tRES.PROC_INST_ID_ = #{pProcessInstanceId}\n" +
            "\t\tORDER BY\n" +
            "\t\t\tRES.ID_ ASC \n" +
            "\t\t) a \n" +
            "\tWHERE\n" +
            "\t\tROWNUM <10\n" +
            "\t) \n" +
            "WHERE\n" +
            "\trnum >= 0")
    HistoricProcessInstance selectProcessByInstanceId(String pProcessInstanceId);
}
