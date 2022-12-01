package com.example.springboot2022novc.service.imp;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot2022novc.dao.ChinaTotalMapper;
import com.example.springboot2022novc.entity.ChinaTotal;
import com.example.springboot2022novc.service.ChinaTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChinaTotalServiceImpl extends ServiceImpl<ChinaTotalMapper, ChinaTotal> implements ChinaTotalService {
    @Autowired
    private ChinaTotalMapper chinaTotalMapper;


    public Integer maxID() {
        return this.chinaTotalMapper.maxID();
    }
}
