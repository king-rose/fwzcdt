package com.agree.web.fwlb.domain;

import lombok.Data;

import java.util.List;

/**
 * @author Tonny
 * @version 2021.2
 * @date 2022/9/28 17:05
 */
@Data
public class SceneFieldRequest {

    //服务版本ID
    private String svId;

    //输入输出类型
    private String type;

    private List<OprScenarioField> fieldInList;

    private List<OprScenarioField> fieldOutList;
}
