package com.agree.web.system.domain;

import lombok.Data;

/**
 * @author Tonny
 * @version 2021.2
 * @date 2022/9/7 11:07
 */
@Data
public class Trend {

    //年份
    private String dealYear;
    private String doneYear;
    
    //季度
    private String dealQuarter;
    private String doneQuarter;

    //月份
    private String dealMonth;
    private String doneMonth;

    //数量
    private String dealNum;
    private String doneNum;
    private String faceNum;

}
