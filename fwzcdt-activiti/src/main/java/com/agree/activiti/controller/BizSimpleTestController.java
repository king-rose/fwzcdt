package com.agree.activiti.controller;

import java.util.List;

import com.agree.activiti.service.IProcessService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.enums.BusinessType;
import com.agree.activiti.domain.BizSimpleTest;
import com.agree.activiti.service.IBizSimpleTestService;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.common.core.page.TableDataInfo;

import static com.agree.common.core.domain.AjaxResult.error;
import static com.agree.common.core.domain.AjaxResult.success;

/**
 * 请假测试Controller
 * 
 * @author luoxu
 * @date 2022-04-24
 */
@RestController
@RequestMapping("/simple/simple")
public class BizSimpleTestController extends BaseController
{
    @Autowired
    private IBizSimpleTestService bizSimpleTestService;

    @Autowired
    private IProcessService processService;

    /**
     * 查询请假测试列表
     */
    @PreAuthorize("@ss.hasPermi('simple:simple:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizSimpleTest bizSimpleTest)
    {
        startPage();
        List<BizSimpleTest> list = bizSimpleTestService.selectBizSimpleTestList(bizSimpleTest);
        return getDataTable(list);
    }

    /**
     * 导出请假测试列表
     */
    @PreAuthorize("@ss.hasPermi('simple:simple:export')")
    @Log(title = "请假测试", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizSimpleTest bizSimpleTest)
    {
        List<BizSimpleTest> list = bizSimpleTestService.selectBizSimpleTestList(bizSimpleTest);
        ExcelUtil<BizSimpleTest> util = new ExcelUtil<BizSimpleTest>(BizSimpleTest.class);
        return util.exportExcel(list, "simple");
    }

    /**
     * 获取请假测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('simple:simple:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizSimpleTestService.selectBizSimpleTestById(id));
    }

    /**
     * 新增请假测试
     */
    @PreAuthorize("@ss.hasPermi('simple:simple:add')")
    @Log(title = "请假测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizSimpleTest bizSimpleTest)
    {
        return toAjax(bizSimpleTestService.insertBizSimpleTest(bizSimpleTest));
    }

    /**
     * 修改请假测试
     */
    @PreAuthorize("@ss.hasPermi('simple:simple:edit')")
    @Log(title = "请假测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizSimpleTest bizSimpleTest)
    {
        return toAjax(bizSimpleTestService.updateBizSimpleTest(bizSimpleTest));
    }

    /**
     * 删除请假测试
     */
    @PreAuthorize("@ss.hasPermi('simple:simple:remove')")
    @Log(title = "请假测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizSimpleTestService.deleteBizSimpleTestByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "请假业务", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply/{id}")
    @ResponseBody
    public AjaxResult submitApply(@PathVariable Long id) {
        try {
            BizSimpleTest bizSimpleTest = bizSimpleTestService.selectBizSimpleTestById(id);
            processService.submitApply(bizSimpleTest, "process");
            bizSimpleTestService.updateBizSimpleTest(bizSimpleTest);
        } catch (Exception e) {
            e.printStackTrace();
            return error("提交申请出错：" + e.getMessage());
        }
        return success();
    }
}
