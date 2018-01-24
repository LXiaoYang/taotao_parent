package cn.csdn.service.impl;

import cn.csdn.inter.ITestService;
import cn.csdn.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements ITestService {

    @Resource
    private TestMapper testMapper;

    public String getTime(){

        return testMapper.getNow();
    };
}
