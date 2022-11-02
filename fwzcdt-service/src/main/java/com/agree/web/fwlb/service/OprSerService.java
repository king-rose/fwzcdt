package com.agree.web.fwlb.service;

import com.agree.common.core.domain.AjaxResult;
import com.agree.web.fwlb.domain.OprService;

import java.util.List;
import java.util.Map;

public interface OprSerService {

    public List<OprService> list(OprService oprService);

    public AjaxResult saveData(List<Map<String,Object>> list);

    public AjaxResult deleteOprService(String svcId);

    public Map<String,Object> getServiceInfo(String svcId);

    public int updateService(OprService oprService);

    public OprService selectServiceById(String svcCode);

    public List<String> getHeadClassList();

    public List<String> getSmallClassList();

    public int addService(OprService oprService);
}
