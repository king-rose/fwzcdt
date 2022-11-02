package com.agree.web.fwlb.controller;

import java.util.List;
import java.util.Map;

import com.agree.web.fwlb.domain.AesbGServiceSceneMapping;
import com.agree.web.fwlb.service.IAesbGServiceSceneMappingService;
import com.agree.web.system.domain.OprSystem;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.enums.BusinessType;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.common.core.page.TableDataInfo;

/**
 * 服务场景消费关系Controller
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
@RestController
@RequestMapping("/mapping")
@AllArgsConstructor
public class AesbGServiceSceneMappingController extends BaseController
{
    private IAesbGServiceSceneMappingService aesbGServiceSceneMappingService;

    /**
     * 查询服务场景消费关系列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        startPage();
        List<AesbGServiceSceneMapping> list = aesbGServiceSceneMappingService.selectAesbGServiceSceneMappingList(aesbGServiceSceneMapping);
        return getDataTable(list);
    }

    /**
     * 导出服务场景消费关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:export')")
    @Log(title = "服务场景消费关系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        List<AesbGServiceSceneMapping> list = aesbGServiceSceneMappingService.selectAesbGServiceSceneMappingList(aesbGServiceSceneMapping);
        ExcelUtil<AesbGServiceSceneMapping> util = new ExcelUtil<AesbGServiceSceneMapping>(AesbGServiceSceneMapping.class);
        return util.exportExcel(list, "mapping");
    }

    /**
     * 获取服务场景消费关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:query')")
    @GetMapping(value = "/{mappingId}")
    public AjaxResult getInfo(@PathVariable("mappingId") String mappingId)
    {
        return AjaxResult.success(aesbGServiceSceneMappingService.selectAesbGServiceSceneMappingById(mappingId));
    }

    /**
     * 新增服务场景消费关系
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:add')")
    @Log(title = "服务场景消费关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        return toAjax(aesbGServiceSceneMappingService.insertAesbGServiceSceneMapping(aesbGServiceSceneMapping));
    }

    /**
     * 修改服务场景消费关系
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:edit')")
    @Log(title = "服务场景消费关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AesbGServiceSceneMapping aesbGServiceSceneMapping)
    {
        return toAjax(aesbGServiceSceneMappingService.updateAesbGServiceSceneMapping(aesbGServiceSceneMapping));
    }
    /**
     * 删除服务场景消费关系
     */
    @PreAuthorize("@ss.hasPermi('system:mapping:remove')")
    @Log(title = "服务场景消费关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mappingIds}")
    public AjaxResult remove(@PathVariable String[] mappingIds)
    {
        return toAjax(aesbGServiceSceneMappingService.deleteAesbGServiceSceneMappingByIds(mappingIds));
    }

    /**
     * 服务场景消费关系
     */
    @GetMapping("relation/{proSysId}")
    public AjaxResult sceneRatio(@PathVariable("proSysId") String proSysId){
        Map<String,Object> map = aesbGServiceSceneMappingService.sceneRatio(proSysId);
        return AjaxResult.success(map);
    }
}
