package com.agree.web.system.controller;

import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.core.page.TableDataInfo;
import com.agree.common.enums.BusinessType;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.web.system.domain.OprSystem;
import com.agree.web.system.domain.Trend;
import com.agree.web.system.service.IOprSystemService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system")
@AllArgsConstructor
public class OprSystemController extends BaseController {

    private IOprSystemService oprSystemService;

    /**
     * 查询系统信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OprSystem oprSystem)
    {
        startPage();
        List<OprSystem> list = oprSystemService.selectOprSystemList(oprSystem);
        return getDataTable(list);
    }
    /**
     * 查询负责人列表
     */
    @GetMapping("/principals")
    public AjaxResult getPrincipals()
    {
        List<Map> list = oprSystemService.getPrincipals();
        return AjaxResult.success(list);
    }

    /**
     * 查询系统所属层列表
     */
    @GetMapping("/systemLayers")
    public AjaxResult getSystemLayers()
    {
        List<String> list = oprSystemService.getSystemLayers();
        return AjaxResult.success(list);
    }

    /**
     * 查询技术部门列表
     */
    @GetMapping("/techDepts")
    public AjaxResult getTechDepts()
    {
        List<String> list = oprSystemService.getTechDepts();
        return AjaxResult.success(list);
    }


    /**
     * 导出系统信息列表
     */
    @PreAuthorize("@ss.hasPermi('list:list:export')")
    @Log(title = "系统信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OprSystem oprSystem)
    {
        List<OprSystem> list = oprSystemService.selectOprSystemList(oprSystem);
        ExcelUtil<OprSystem> util = new ExcelUtil<>(OprSystem.class);
        return util.exportExcel(list, "list");
    }

    /**
     * 获取系统信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('list:list:query')")
    @GetMapping(value = "/{sysId}")
    public AjaxResult getInfo(@PathVariable("sysId") String sysId)
    {
        return AjaxResult.success(oprSystemService.selectOprSystemById(sysId));
    }

    /**
     * 新增系统信息
     */
    @PreAuthorize("@ss.hasPermi('list:list:add')")
    @Log(title = "系统信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OprSystem oprSystem)
    {
        return toAjax(oprSystemService.insertOprSystem(oprSystem));
    }

    /**
     * 修改系统信息
     */
    @PreAuthorize("@ss.hasPermi('list:list:edit')")
    @Log(title = "系统信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OprSystem oprSystem)
    {
        return toAjax(oprSystemService.updateOprSystem(oprSystem));
    }

    /**
     * 删除系统信息
     */
    @PreAuthorize("@ss.hasPermi('list:list:remove')")
    @Log(title = "系统信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sysIds}")
    public AjaxResult remove(@PathVariable String[] sysIds)
    {
        return toAjax(oprSystemService.deleteOprSystemByIds(sysIds));
    }

    /**
     * 系统数
     */
    @GetMapping("sysNum")
    public AjaxResult getSystemNum()
    {
        return AjaxResult.success(oprSystemService.getSystemNum());
    }

    /**
     * 场景数
     */
    @GetMapping("sceneNum")
    public AjaxResult getSceneNum()
    {
        return AjaxResult.success(oprSystemService.getSceneNum());
    }

    /**
     * 消费关系
     */
    @GetMapping("consumeNum")
    public AjaxResult getConsumeNum()
    {
        return AjaxResult.success(oprSystemService.getConsumeNum());
    }

    /**
     * 服务场景趋势变化
     */
    @GetMapping("/trend/{timeType}")
    public AjaxResult getChangeTrend(@PathVariable("timeType") Integer timeType){

        List<Trend> statistics = oprSystemService.getTrendStatistics(timeType);

        return AjaxResult.success(statistics);
    }

    /**
     * 服务场景占比/消费关系占比
     */
    @GetMapping("/scene/ratio")
    public AjaxResult sceneRatioChart(){
        return AjaxResult.success(oprSystemService.sceneRatioChart());
    }

    /**
     * 服务场景占比
     */
    @GetMapping("/scene/serviceScene")
    public AjaxResult serviceScene(){
        return AjaxResult.success(oprSystemService.serviceScene());
    }


}
