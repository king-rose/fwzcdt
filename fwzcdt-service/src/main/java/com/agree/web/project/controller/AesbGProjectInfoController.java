package com.agree.web.project.controller;

import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.core.page.TableDataInfo;
import com.agree.common.enums.BusinessType;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.web.project.domain.AesbGProjectInfo;
import com.agree.web.project.service.IAesbGProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目信息Controller
 * 
 * @author ruoyi
 * @date 2022-06-21
 */
@RestController
@RequestMapping("/project")
public class AesbGProjectInfoController extends BaseController
{
    @Autowired
    private IAesbGProjectInfoService aesbGProjectInfoService;

    /**
     * 查询项目信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AesbGProjectInfo aesbGProjectInfo)
    {
        startPage();
        List<AesbGProjectInfo> list = aesbGProjectInfoService.selectAesbGProjectInfoList(aesbGProjectInfo);
        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:project:export')")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AesbGProjectInfo aesbGProjectInfo)
    {
        List<AesbGProjectInfo> list = aesbGProjectInfoService.selectAesbGProjectInfoList(aesbGProjectInfo);
        ExcelUtil<AesbGProjectInfo> util = new ExcelUtil<AesbGProjectInfo>(AesbGProjectInfo.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 获取项目信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:project:query')")
    @GetMapping(value = "/{projectCode}")
    public AjaxResult getInfo(@PathVariable("projectCode") String projectCode)
    {
        return AjaxResult.success(aesbGProjectInfoService.selectAesbGProjectInfoById(projectCode));
    }

    /**
     * 新增项目信息
     */
    @PreAuthorize("@ss.hasPermi('project:project:add')")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AesbGProjectInfo aesbGProjectInfo)
    {
        return toAjax(aesbGProjectInfoService.insertAesbGProjectInfo(aesbGProjectInfo));
    }

    /**
     * 修改项目信息
     */
    @PreAuthorize("@ss.hasPermi('project:project:edit')")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AesbGProjectInfo aesbGProjectInfo)
    {
        return toAjax(aesbGProjectInfoService.updateAesbGProjectInfo(aesbGProjectInfo));
    }

    /**
     * 删除项目信息
     */
    @PreAuthorize("@ss.hasPermi('project:project:remove')")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectCodes}")
    public AjaxResult remove(@PathVariable String[] projectCodes)
    {
        return toAjax(aesbGProjectInfoService.deleteAesbGProjectInfoByIds(projectCodes));
    }
}
