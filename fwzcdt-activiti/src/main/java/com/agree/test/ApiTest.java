package com.agree.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testApi")
/*
 * data:2022-04-24
 * author:luoxu
 * description:测试apiPost工具是否正常查询接口数据
 */
public class ApiTest {

    /**
     * GET请求:返回字符串
     * @return
     */
    @GetMapping("/one")
    public String demo(){
        return "hello world";
    }

    /**
     *  POST请求
     */
    @PostMapping("/query/01")
    public void demo01(){

    }
}
