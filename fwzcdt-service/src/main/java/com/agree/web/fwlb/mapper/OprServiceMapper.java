package com.agree.web.fwlb.mapper;

import com.agree.common.core.domain.AjaxResult;
import com.agree.web.fwlb.domain.OprService;
import com.agree.web.fwlb.service.OprSerService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OprServiceMapper {

    public List<OprService> list(OprService oprService);

    public int saveBatch(List<OprService> list);

    public int deleteOprService(String svcId);

    public Map<String,Object> getServiceInfo(String svcId);

    public int updateService(OprService oprService);

    public OprService selectServiceById(String svcCode);

    public List<String> getHeadClassList();

    public List<String> getSmallClassList();

    public int addService(OprService oprService);
}
