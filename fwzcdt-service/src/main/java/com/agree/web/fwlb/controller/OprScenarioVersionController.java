package com.agree.web.fwlb.controller;

import com.agree.activiti.service.IProcessService;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.core.page.TableDataInfo;
import com.agree.web.fwlb.domain.OprScenarioField;
import com.agree.web.fwlb.domain.OprScenarioVersion;
import com.agree.web.fwlb.domain.OprScenarioVersionParam;
import com.agree.web.fwlb.domain.SceneFieldRequest;
import com.agree.web.fwlb.service.OprScenarioVersionService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.agree.common.core.domain.AjaxResult.error;
import static com.agree.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("oprScene")
public class OprScenarioVersionController extends BaseController {

    @Autowired
    private OprScenarioVersionService oprScenarioVersionService;
    @Autowired
    private IProcessService processService;

    @GetMapping("list")
    public TableDataInfo list(OprScenarioVersion oprScenarioVersion){
        startPage();
        List<OprScenarioVersion> list = oprScenarioVersionService.selectOsvList(oprScenarioVersion);
        return getDataTable(list);
    }

    /**
     * 场景发布
     * @param map map:excel解析获取的数据
     * @return
     */
    @PostMapping("/release")
    public AjaxResult release(@RequestBody Map<String,Object> map){
        return AjaxResult.success(oprScenarioVersionService.release(map));
    }

    /**
     * 提交申请
     */
    @PostMapping("submitApply/{svId}")
    public AjaxResult submitApply(@PathVariable("svId") String svId){
        try {
            OprScenarioVersion oprScenarioVersion = oprScenarioVersionService.getDetailBySvId(svId);
            oprScenarioVersion.setAuditState("1");
            processService.submitApply(oprScenarioVersion, "sceneRelease");
            oprScenarioVersionService.updateScenarioVersion(oprScenarioVersion);
        } catch (Exception e) {
            e.printStackTrace();
            return error("提交申请出错：" + e.getMessage());
        }
        return success();
    }


    /**
     * 删除/批量删除场景
     * @param svIds 服务编号
     */
    @DeleteMapping("remove/{svIds}")
    public AjaxResult removeScene(@PathVariable String[] svIds){
        return toAjax(oprScenarioVersionService.removeScene(svIds));
    }

    /**
     * 场景基本信息
     * @param svId ID
     * @return
     */
    @GetMapping("/basic/{svId}")
    public AjaxResult getBasic(@PathVariable("svId") String svId){
        Map basic = oprScenarioVersionService.getBasic(svId);
        return AjaxResult.success(basic);
    }

    /**
     * 场景指标
     * @param svId ID
     * @return
   */
    @GetMapping("/quato/{svId}")
    public AjaxResult getQuatoInfo(@PathVariable String svId){
        return AjaxResult.success(oprScenarioVersionService.getQuatoInfo(svId));
    }

    /**
     * 场景资源表
     */
    @GetMapping("/resource/{svId}")
    public AjaxResult getResourcefo(@PathVariable String svId){
        return AjaxResult.success(oprScenarioVersionService.getResourcefo(svId));
    }

    /**
     * 场景字段(输入)
     */
    @GetMapping("getSceneFiledIn")
    public TableDataInfo getSceneFiledIn(SceneFieldRequest sceneFieldRequest){
        startPage();
        List<Map> list = oprScenarioVersionService.getSceneFiledInfo(sceneFieldRequest);
        return getDataTable(list);
    }
    /**
     * 场景字段(输出)
     */
    @GetMapping("getSceneFiledOut")
    public TableDataInfo getSceneFiledOut(SceneFieldRequest sceneFieldRequest){
        startPage();
        List<Map> list = oprScenarioVersionService.getSceneFiledInfo(sceneFieldRequest);
        return getDataTable(list);
    }



    /**
     * 被消费列表
     */
    @GetMapping("/consume/{svId}")
    public AjaxResult getConsumeIfo(@PathVariable String svId){
        return AjaxResult.success(oprScenarioVersionService.getConsumeIfo(svId));
    }

    /**
     * 场景变更列表
     */
    @GetMapping("/change/{svId}")
    public AjaxResult selectChangeRecordList(@PathVariable String svId){
        return AjaxResult.success(oprScenarioVersionService.selectChangeRecordList(svId));
    }



    /**
     * 场景编辑
     */
    @PostMapping("/edit")
    public AjaxResult edit(OprScenarioVersion oprScenarioVersion){
        return AjaxResult.success(oprScenarioVersionService.updateScenarioVersion(oprScenarioVersion));
    }
    /**
     * 场景指标编辑
     */
    @PostMapping("/sceneQuatodEdit")
    public AjaxResult quatoEdit(OprScenarioVersionParam versionParam){
        return AjaxResult.success(oprScenarioVersionService.updateScenarioVersionParam(versionParam));
    }


    /**
     * 场景字段编辑
     */
    @GetMapping("/sceneFieldEdit/{svId}")
    public AjaxResult fieldEdit(@PathVariable String svId,String formData){
        List<OprScenarioField> list = JSON.parseArray(formData).toJavaList(OprScenarioField.class);
        return AjaxResult.success(oprScenarioVersionService.updateScenarioVersionField(svId,list));
    }


    /**
     * 场景定义
     */
    @GetMapping("defined/in")
    public TableDataInfo inScenarioDefined(@RequestParam String svId){
        startPage();
        List<OprScenarioField> inList = oprScenarioVersionService.scenarioDefinedIn(svId);
        return getDataTable(inList);
    }
    /**
     * 场景定义
     */
    @GetMapping("defined/out")
    public TableDataInfo outScenarioDefined(@RequestParam String svId){
        startPage();
        List<OprScenarioField> outList = oprScenarioVersionService.scenarioDefinedOut(svId);
        return getDataTable(outList);
    }

    /**
     * 获取场景信息
     */
    @GetMapping("getServiceSceneInfo/{svId}")
    public AjaxResult getServiceSceneInfo(@PathVariable("svId") String svId){
        System.out.println("svId = " + svId);
        return null;
    }
}
