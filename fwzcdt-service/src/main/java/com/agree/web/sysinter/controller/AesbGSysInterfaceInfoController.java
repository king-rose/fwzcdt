package com.agree.web.sysinter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.enums.BusinessType;
import com.agree.web.sysinter.domain.AesbGSysInterfaceInfo;
import com.agree.web.sysinter.service.IAesbGSysInterfaceInfoService;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.common.core.page.TableDataInfo;

/**
 * 系统接口信息Controller
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@RestController
@RequestMapping("/sysinter/sysinter")
public class AesbGSysInterfaceInfoController extends BaseController
{
    @Autowired
    private IAesbGSysInterfaceInfoService aesbGSysInterfaceInfoService;
    @Autowired
    private IAesbGSysInterfaceInfoService interfaceInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 查询系统接口信息列表
     */
    @PreAuthorize("@ss.hasPermi('sysinter:sysinter:list')")
    @GetMapping("/list")
    public TableDataInfo list(AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        startPage();
        List<AesbGSysInterfaceInfo> list = aesbGSysInterfaceInfoService.selectAesbGSysInterfaceInfoList(aesbGSysInterfaceInfo);
        return getDataTable(list);
    }


    /**
     * 查询关联系统接口列表
     */
    @RequestMapping("/getListBySysIds")
    public AjaxResult getListBySysIds(@RequestParam("sysList") String sysList)
    {
        JSONArray jsonArray = JSONArray.parseArray(sysList);
        List<Map> maps = jsonArray.toJavaList(Map.class);

        List<AesbGSysInterfaceInfo> list = new ArrayList<>();

        for (Map map : maps) {
            //获取关联接口列表
            List<AesbGSysInterfaceInfo> infoList = interfaceInfoService.queryIFaceListBySysId(map.get("sysId").toString());
            list.addAll(infoList);
        }
        return AjaxResult.success(list);
    }


    /**
     * 导出系统接口信息列表
     */
    @PreAuthorize("@ss.hasPermi('sysinter:sysinter:export')")
    @Log(title = "系统接口信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        List<AesbGSysInterfaceInfo> list = aesbGSysInterfaceInfoService.selectAesbGSysInterfaceInfoList(aesbGSysInterfaceInfo);
        ExcelUtil<AesbGSysInterfaceInfo> util = new ExcelUtil<AesbGSysInterfaceInfo>(AesbGSysInterfaceInfo.class);
        return util.exportExcel(list, "sysinter");
    }

    /**
     * 获取系统接口信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('sysinter:sysinter:query')")
    @GetMapping(value = "/{jkVersionId}")
    public AjaxResult getInfo(@PathVariable("jkVersionId") String jkVersionId)
    {
        return AjaxResult.success(aesbGSysInterfaceInfoService.selectAesbGSysInterfaceInfoById(jkVersionId));
    }

    /**
     * 新增系统接口信息
     */
    @PreAuthorize("@ss.hasPermi('sysinter:sysinter:add')")
    @Log(title = "系统接口信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        return toAjax(aesbGSysInterfaceInfoService.insertAesbGSysInterfaceInfo(aesbGSysInterfaceInfo));
    }

    /**
     * 修改系统接口信息
     */
    @PreAuthorize("@ss.hasPermi('sysinter:sysinter:edit')")
    @Log(title = "系统接口信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AesbGSysInterfaceInfo aesbGSysInterfaceInfo)
    {
        return toAjax(aesbGSysInterfaceInfoService.updateAesbGSysInterfaceInfo(aesbGSysInterfaceInfo));
    }

    /**
     * 删除系统接口信息
     */
    @PreAuthorize("@ss.hasPermi('sysinter:sysinter:remove')")
    @Log(title = "系统接口信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jkVersionIds}")
    public AjaxResult remove(@PathVariable String[] jkVersionIds)
    {
        return toAjax(aesbGSysInterfaceInfoService.deleteAesbGSysInterfaceInfoByIds(jkVersionIds));
    }
}
