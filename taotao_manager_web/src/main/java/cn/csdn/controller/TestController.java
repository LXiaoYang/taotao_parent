package cn.csdn.controller;

import cn.csdn.inter.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping("/test")
    @ResponseBody
    public String getTime(){
        return testService.getTime();
    }
}
