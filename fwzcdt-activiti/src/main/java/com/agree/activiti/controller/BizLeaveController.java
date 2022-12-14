package com.agree.activiti.controller;

import java.util.List;

import com.agree.activiti.service.IProcessService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.enums.BusinessType;
import com.agree.activiti.domain.BizLeave;
import com.agree.activiti.service.IBizLeaveService;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.common.core.page.TableDataInfo;

import static com.agree.common.core.domain.AjaxResult.error;
import static com.agree.common.core.domain.AjaxResult.success;

/**
 * 请假Controller
 *
 * @author 一只闲鹿
 */
@RestController
@RequestMapping("/leave/leave")
@AllArgsConstructor
public class BizLeaveController extends BaseController
{
    private IBizLeaveService bizLeaveService;
    private IProcessService processService;

    /**
     * 查询请假列表
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizLeave bizLeave)
    {
        startPage();
        List<BizLeave> list = bizLeaveService.selectBizLeaveList(bizLeave);
        return getDataTable(list);
    }

    /**
     * 导出请假列表
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:export')")
    @Log(title = "请假", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizLeave bizLeave)
    {
        List<BizLeave> list = bizLeaveService.selectBizLeaveList(bizLeave);
        ExcelUtil<BizLeave> util = new ExcelUtil<BizLeave>(BizLeave.class);
        return util.exportExcel(list, "leave");
    }

    /**
     * 获取请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizLeaveService.selectBizLeaveById(id));
    }

    /**
     * 新增请假
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:add')")
    @Log(title = "请假", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizLeave bizLeave)
    {
        return toAjax(bizLeaveService.insertBizLeave(bizLeave));
    }

    /**
     * 修改请假
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:edit')")
    @Log(title = "请假", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizLeave bizLeave)
    {
        return toAjax(bizLeaveService.updateBizLeave(bizLeave));
    }

    /**
     * 删除请假
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:remove')")
    @Log(title = "请假", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizLeaveService.deleteBizLeaveByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "请假业务", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply/{id}")
    @ResponseBody
    public AjaxResult submitApply(@PathVariable Long id) {
        try {
            BizLeave bizLeave = bizLeaveService.selectBizLeaveById(id);
            processService.submitApply(bizLeave, "leave");
            bizLeaveService.updateBizLeave(bizLeave);
        } catch (Exception e) {
            e.printStackTrace();
            return error("提交申请出错：" + e.getMessage());
        }
        return success();
    }
}
