package com.example.springboot2022novc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot2022novc.entity.LineTrend;
import com.example.springboot2022novc.entity.NocvData;

import java.util.List;

public interface IndexService extends IService<NocvData> {
    List<LineTrend> findSevenData();

    List<NocvData> listOrderByIdLimit34();
}
