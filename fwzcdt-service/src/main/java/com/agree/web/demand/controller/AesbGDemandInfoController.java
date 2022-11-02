package com.agree.web.demand.controller;

import java.util.ArrayList;
import java.util.List;

import com.agree.web.demand.domain.AesbGDemandInfo;
import com.agree.web.demand.domain.AesbSystemInterfaceDemand;
import com.agree.web.demand.domain.AssociationRequest;
import com.agree.web.demand.service.IAesbGDemandInfoService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.agree.common.annotation.Log;
import com.agree.common.core.controller.BaseController;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.enums.BusinessType;
import com.agree.common.utils.poi.ExcelUtil;
import com.agree.common.core.page.TableDataInfo;

import javax.annotation.Resource;

import static com.agree.common.core.domain.AjaxResult.success;


/**
 * 需求信息Controller
 *
 * @author ruoyi
 * @date 2022-08-15
 */
@RestController
@RequestMapping(value = "/demand")
public class AesbGDemandInfoController extends BaseController {
    @Resource
    private IAesbGDemandInfoService aesbGDemandInfoService;

    /**
     * 查询需求信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AesbGDemandInfo aesbGDemandInfo) {
        startPage();
        List<AesbGDemandInfo> list = aesbGDemandInfoService.selectAesbGDemandInfoList(aesbGDemandInfo);
        return getDataTable(list);
    }

    /**
     * 查询需求审批列表
     */
    @GetMapping("/acceptList")
    public TableDataInfo acceptList(AesbGDemandInfo aesbGDemandInfo) {
        startPage();
        List<AesbGDemandInfo> list = aesbGDemandInfoService.selectDemandAcceptList(aesbGDemandInfo);
        return getDataTable(list);
    }



    /**
     * 查询需求审批列表
     */
    @PostMapping("/test")
    public String test() {
        return "123456";
    }


    /**
     * 导出需求信息列表
     */
    @PreAuthorize("@ss.hasPermi('demand:info:export')")
    @Log(title = "需求信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AesbGDemandInfo aesbGDemandInfo) {
        List<AesbGDemandInfo> list = aesbGDemandInfoService.selectAesbGDemandInfoList(aesbGDemandInfo);
        ExcelUtil<AesbGDemandInfo> util = new ExcelUtil<>(AesbGDemandInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取需求信息详细信息
     */
    @GetMapping(value = "/{demandId}")
    public AjaxResult getInfo(@PathVariable("demandId") String demandId) {
        return success(aesbGDemandInfoService.selectAesbGDemandInfoById(demandId));
    }

    /**
     * 新增需求信息
     */
    @Log(title = "需求信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AesbGDemandInfo aesbGDemandInfo) {
        AesbGDemandInfo demandInfo = aesbGDemandInfoService.selectAesbGDemandInfoById(aesbGDemandInfo.getDemandId());
        if (ObjectUtils.isEmpty(demandInfo)) {
            return toAjax(aesbGDemandInfoService.insertAesbGDemandInfo(aesbGDemandInfo));
        }
        return toAjax(aesbGDemandInfoService.updateAesbGDemandInfo(aesbGDemandInfo));
    }

    /**
     * 修改需求信息
     */
    @Log(title = "需求信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AesbGDemandInfo aesbGDemandInfo) {
        return toAjax(aesbGDemandInfoService.updateAesbGDemandInfo(aesbGDemandInfo));
    }

    /**
     * 修改需求接口
     */
    @PutMapping("updateFace")
    public AjaxResult edit(@RequestBody AesbSystemInterfaceDemand aesbSystemInterfaceDemand) {
        return toAjax(aesbGDemandInfoService.updateSystemInterFaceInfo(aesbSystemInterfaceDemand));
    }
    /**
     * 驳回 更改需求接口状态
     */
    @PutMapping("downFace")
    public AjaxResult downFace(@RequestBody AesbSystemInterfaceDemand aesbSystemInterfaceDemand) {
        return toAjax(aesbGDemandInfoService.downSystemInterFaceInfo(aesbSystemInterfaceDemand));
    }

    /**
     * 删除需求信息
     */
    @PreAuthorize("@ss.hasPermi('demand:info:remove')")
    @Log(title = "需求信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{demandId}")
    public AjaxResult remove(@PathVariable String demandId) {
        return toAjax(aesbGDemandInfoService.deleteAesbGDemandInfoByIds(demandId));
    }


    /**
     * 关联接口
     */
    @GetMapping("asso")
    @Transient
    public AjaxResult toAssociation(@RequestParam("IFaceDatas") String IFaceDatas, @RequestParam("sysDatas") String sysDatas) {
        JSONArray IFaceArray = JSONArray.parseArray(IFaceDatas);
        JSONArray SysArray = JSONArray.parseArray(sysDatas);

        List<AesbSystemInterfaceDemand> datas = new ArrayList<>();

        List<AssociationRequest> faceList = IFaceArray.toJavaList(AssociationRequest.class);
        List<AssociationRequest> sysList = SysArray.toJavaList(AssociationRequest.class);

        for (AssociationRequest system : sysList) {
            for (AssociationRequest face : faceList) {
                AesbSystemInterfaceDemand sid = new AesbSystemInterfaceDemand();
                sid.setDemandId(face.getDemandId());
                sid.setSysId(system.getSysId());
                sid.setPrincipal(face.getPrincipal());
                sid.setSysName(face.getSysName());
                if (face.getSysName().equals(system.getLabel())) {
                    sid.setJkNameCn(face.getJkNameCn());
                    sid.setJkNameEn(face.getJkNameEn());
                    sid.setJkVersionId(face.getJkVersionId());
                }
                datas.add(sid);
            }
        }
        //修改状态
        AesbGDemandInfo demandInfo = new AesbGDemandInfo();
        demandInfo.setDemandId(faceList.get(0).getDemandId());
        demandInfo.setDemandState("1");
        aesbGDemandInfoService.updateAesbGDemandInfo(demandInfo);
        //删除原关联数据
        aesbGDemandInfoService.deleteAssociationDemand(faceList.get(0).getDemandId());
        //更新关联表数据
        for (AesbSystemInterfaceDemand sid : datas) {
            aesbGDemandInfoService.insertAssociationDemand(sid);
        }
        return success("操作成功");
    }

    /**
     * 我的任务单
     * @param pageNum 页码
     * @param pageSize 页面大小
     */
    @GetMapping("taskList")
    public PageInfo<AesbGDemandInfo> myTaskList(AesbGDemandInfo demandInfo, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return aesbGDemandInfoService.selectMyTaskList(demandInfo, pageNum, pageSize);
    }


    /**
     * 任务进度统计(top5)
     */
    @GetMapping("process")
    public AjaxResult taskProcess(AesbGDemandInfo demandInfo, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return success(aesbGDemandInfoService.getTaskProcess(demandInfo, pageNum, pageSize));
    }

    /*
      提交申请
     */
  /*  @PostMapping("submitApply/{id}")
    public AjaxResult submit(@PathVariable("id") String demandId){

        try {
            AesbGDemandInfo demandInfo = aesbGDemandInfoService.selectAesbGDemandInfoById(demandId);
            processService.submitApply(demandInfo, "demand");
            //修改需求状态
            demandInfo.setDemandState("1");
            aesbGDemandInfoService.updateAesbGDemandInfo(demandInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return error("提交申请出错:"+e.getMessage());
        }
        return success("提交成功!");
    }*/
}
