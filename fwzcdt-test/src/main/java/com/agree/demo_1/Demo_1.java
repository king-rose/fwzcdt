package com.agree.demo_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tonny
 * @version 2021.2
 * @date 2022/10/11 10:45
 */
@RequestMapping("demo")
@RestController
public class Demo_1 {



    @GetMapping("info")
    public void demo(){
    }
}
