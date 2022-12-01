package com.example.springboot2022novc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot2022novc.entity.ChinaTotal;

public interface ChinaTotalService extends IService<ChinaTotal> {
    Integer maxID();
}