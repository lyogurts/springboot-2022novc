package com.example.springboot2022novc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot2022novc.entity.HealthClock;
import com.example.springboot2022novc.service.HealthClockService;
import com.example.springboot2022novc.vo.DataView;
import com.example.springboot2022novc.vo.HealthClockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HealthClockController {
    @Autowired
    private HealthClockService healthClockService;


    @RequestMapping({"/toHealthClock"})
    public String toHealthClock() {
        return "admin/healthclock";
    }

    @RequestMapping({"/listHealthClock"})
    @ResponseBody
    public DataView listHealthClock(HealthClockVo healthClockVo) {
        IPage<HealthClock> page = new Page((long)healthClockVo.getPage(), (long)healthClockVo.getLimit());
        QueryWrapper<HealthClock> queryWrapper = new QueryWrapper();
        queryWrapper.like(healthClockVo.getUsername() != null, "username", healthClockVo.getUsername());
        queryWrapper.eq(healthClockVo.getPhone() != null, "phone", healthClockVo.getPhone());
        this.healthClockService.page(page, queryWrapper);
        return new DataView(page.getTotal(), page.getRecords());
    }

    @RequestMapping({"/addHealthClock"})
    @ResponseBody
    public DataView addHealthClock(HealthClock healthClock) {
       boolean b = this.healthClockService.saveOrUpdate(healthClock);
        DataView dataView = new DataView();
        if (b) {
            dataView.setCode(200);
            dataView.setMsg("添加成功！");
            return dataView;
        }
            dataView.setCode(100);
            dataView.setMsg("添加失败！");
            return dataView;

    }

    @RequestMapping({"/deleteHealthClockById"})
    @ResponseBody
    public DataView deleteHealthClockById(Integer id) {
        this.healthClockService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除数据成功！");
        return dataView;
    }
}





