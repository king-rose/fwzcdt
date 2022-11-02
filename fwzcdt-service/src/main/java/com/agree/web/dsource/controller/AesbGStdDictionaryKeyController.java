package com.agree.web.dsource.controller;

import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.core.page.TableDataInfo;
import com.agree.common.enums.BusinessType;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.web.dsource.domain.AesbGStdDictionaryKey;
import com.agree.web.dsource.service.IAesbGStdDictionaryKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 标准数据元字典键Controller
 *
 * @author ruoyi
 * @date 2022-05-31
 */
@RestController
@RequestMapping("/management/dataSource")
public class AesbGStdDictionaryKeyController extends BaseController
{
    @Autowired
    private IAesbGStdDictionaryKeyService aesbGStdDictionaryKeyService;

    /**
     * 查询标准数据元字典键列表
     */
    @PreAuthorize("@ss.hasPermi('system:dataSource:list')")
    @GetMapping("/list")
    public TableDataInfo list(AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        startPage();
        List<AesbGStdDictionaryKey> list = aesbGStdDictionaryKeyService.selectAesbGStdDictionaryKeyList(aesbGStdDictionaryKey);
        return getDataTable(list);
    }

    /**
     * 导出标准数据元字典键列表
     */
    @PreAuthorize("@ss.hasPermi('system:dataSource:export')")
    @Log(title = "标准数据元字典键", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        List<AesbGStdDictionaryKey> list = aesbGStdDictionaryKeyService.selectAesbGStdDictionaryKeyList(aesbGStdDictionaryKey);
        ExcelUtil<AesbGStdDictionaryKey> util = new ExcelUtil<AesbGStdDictionaryKey>(AesbGStdDictionaryKey.class);
        return util.exportExcel(list, "dataSource");
    }

    /**
     * 获取标准数据元字典键详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dataSource:query')")
    @GetMapping(value = "/{stdDictId}")
    public AjaxResult getInfo(@PathVariable("stdDictId") String stdDictId)
    {
        return AjaxResult.success(aesbGStdDictionaryKeyService.selectAesbGStdDictionaryKeyById(stdDictId));
    }

    /**
     * 新增标准数据元字典键
     */
    @PreAuthorize("@ss.hasPermi('system:dataSource:add')")
    @Log(title = "标准数据元字典键", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        return toAjax(aesbGStdDictionaryKeyService.insertAesbGStdDictionaryKey(aesbGStdDictionaryKey));
    }
    /**
     * 批量新增标准数据元字典键
     */
    @PostMapping("foreachAdd")
    public AjaxResult foreachAdd(@RequestBody List<Map<String,Object>> list)
    {
        return toAjax(aesbGStdDictionaryKeyService.foreachAddAesbGStdDictionaryKey(list));
    }
    /**
     * 修改标准数据元字典键
     */
    @PreAuthorize("@ss.hasPermi('system:dataSource:edit')")
    @Log(title = "标准数据元字典键", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        return toAjax(aesbGStdDictionaryKeyService.updateAesbGStdDictionaryKey(aesbGStdDictionaryKey));
    }

    /**
     * 删除标准数据元字典键
     */
    @PreAuthorize("@ss.hasPermi('system:dataSource:remove')")
    @Log(title = "标准数据元字典键", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stdDictIds}")
    public AjaxResult remove(@PathVariable String[] stdDictIds)
    {
        return toAjax(aesbGStdDictionaryKeyService.deleteAesbGStdDictionaryKeyByIds(stdDictIds));
    }
}
