package com.agree.web.fwlb.controller;

import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.core.page.TableDataInfo;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.web.fwlb.domain.OprService;
import com.agree.web.fwlb.service.OprSerService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("oprService")
public class OprServiceController extends BaseController {


    @Autowired
    private OprSerService oprSerService;

    /**
     * 查询服务列表
     */
    @GetMapping("list")
    public TableDataInfo list(OprService oprService){
        startPage();
        List<OprService> list = oprSerService.list(oprService);
        return getDataTable(list);
    }

    /**
     * 服务导入
     */
    @PostMapping("/importData")
    public AjaxResult importExcel(MultipartFile file, boolean updateSupport) throws Exception{
        List<OprService> list = new ArrayList<>();
        ExcelUtil<OprService> util = new ExcelUtil<OprService>(OprService.class);
        List<ReadSheet> readSheets = util.listSheet(file.getInputStream());
        List<OprService> oprServices;
        for (ReadSheet readSheet : readSheets) {
            oprServices = util.importExcel(readSheet.getSheetName(), file.getInputStream(),0);
            list.addAll(oprServices);
        }
        //清除集合中为null的对象
        list.removeIf(Objects::isNull);
        return AjaxResult.success(list);
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<OprService> util = new ExcelUtil<OprService>(OprService.class);
        return util.importTemplateExcel("服务列表数据");
    }


    /**
     * 导入
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        List<Map<String,Object>> objects = null;
        try {
            ExcelReader reader = EasyExcel.read(file.getInputStream()).build();
            List<ReadSheet> sheets = reader.excelExecutor().sheetList();
            for (int i = 0; i < sheets.size(); i++) {
                objects = EasyExcel.read(file.getInputStream()).sheet(i).doReadSync();
            }
        } catch (IOException e) {
            return AjaxResult.error("fail");
        }
        //将map集合放入实体,添加入库
        return AjaxResult.success(oprSerService.saveData(objects));
    }

    //删除
    @DeleteMapping("delete/{svcId}")
    public AjaxResult removeServiceListInfo(@PathVariable String svcId){
        return AjaxResult.success(oprSerService.deleteOprService(svcId));
    }

    //获取详情
    @GetMapping("getServiceInfo/{svcId}")
    public AjaxResult getServiceInfo(@PathVariable String svcId){
        Map<String, Object> map = oprSerService.getServiceInfo(svcId);
        return AjaxResult.success(map);
    }

    //编辑服务信息
    @PostMapping("updateService")
    public AjaxResult updateService(@RequestBody OprService oprService){
        return AjaxResult.success(oprSerService.updateService(oprService));
    }

    //新增服务信息
    @PostMapping("addService")
    public AjaxResult addService(@RequestBody OprService oprService){
        return AjaxResult.success(oprSerService.addService(oprService));
    }


    /**
     * 服务大类
     */
    @GetMapping("headClass")
    public AjaxResult getHeadClassList(){
        return AjaxResult.success(oprSerService.getHeadClassList());
    }

    /**
     * 服务子类
     */
    @GetMapping("smallClass")
    public AjaxResult getSmallClassList(){
        return AjaxResult.success(oprSerService.getSmallClassList());
    }
}
