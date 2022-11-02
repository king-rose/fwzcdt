package com.agree.web.fwlb.service.impl;

import com.agree.common.core.domain.AjaxResult;
import com.agree.common.utils.DateUtils;
import com.agree.common.utils.SecurityUtils;
import com.agree.common.utils.uuid.IdUtils;
import com.agree.web.fwlb.domain.OprService;
import com.agree.web.fwlb.mapper.OprServiceMapper;
import com.agree.web.fwlb.service.OprSerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OprSerServiceImpl implements OprSerService {

    @Resource
    private OprServiceMapper oprServiceMapper;

    public List<OprService> list(OprService oprService) {
        return oprServiceMapper.list(oprService);
    }

    /**
     * 获取list批量新增
     * @param list
     * @return
     */
    public AjaxResult saveData(List<Map<String,Object>> list) {

        List<OprService> oprServices = new ArrayList<>();
        list.removeIf(Objects::isNull);
        for (Map map : list) {
            map.forEach((key, value)->{
                if (value==null) {
                    map.put(key, "");
                }
            });
            OprService oprService = new OprService();
            oprService.setSvcName(map.get(0).toString());
            oprService.setSvcDes(map.get(1).toString());
            oprService.setServiceHeadClass(map.get(2).toString());
            oprService.setServiceSmallClass(map.get(3).toString());
            oprService.setSvcCode("OpenDepositAcct");
            oprService.setSvcSgId("C0-N");
            oprService.setSvcId(IdUtils.fastUUID());
            oprService.setSvcCreateTime(DateUtils.getNowDate());
            String username = SecurityUtils.getUsername();
            oprService.setSvcCreateUser(username);
            oprServices.add(oprService);
        }
        int row=0;
        if (oprServices.size()>0){
            row = oprServiceMapper.saveBatch(oprServices);
            return AjaxResult.success(row);
        }
        return AjaxResult.error(row,"批量导入失败");
    }

    public AjaxResult deleteOprService(String svcId) {
        if (oprServiceMapper.deleteOprService(svcId)>0){
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    public Map<String,Object> getServiceInfo(String svcId) {
        return  oprServiceMapper.getServiceInfo(svcId);
    }

    public int updateService(OprService oprService) {
        oprService.setSvcUpdateTime(DateUtils.getNowDate());
        oprService.setSvcUpdateUser(SecurityUtils.getUsername());
        return oprServiceMapper.updateService(oprService);
    }

    public OprService selectServiceById(String svcCode) {
        return oprServiceMapper.selectServiceById(svcCode);
    }

    public List<String> getHeadClassList() {
        return oprServiceMapper.getHeadClassList();
    }

    public List<String> getSmallClassList() {
        return oprServiceMapper.getSmallClassList();
    }

    public int addService(OprService oprService) {
        oprService.setSvcId(DateUtils.dateTimeNow());
        oprService.setSvcCreateUser(SecurityUtils.getUsername());
        oprService.setSvcCreateTime(DateUtils.getNowDate());
        oprService.setSvcSgId(oprService.getServiceHeadClass().substring(0, 2)+"-"+oprService.getServiceSmallClass().substring(0,1));
        return oprServiceMapper.addService(oprService);
    }

}
