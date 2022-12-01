package com.example.springboot2022novc.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot2022novc.dao.HealthClockMapper;
import com.example.springboot2022novc.entity.HealthClock;
import com.example.springboot2022novc.service.HealthClockService;
import org.springframework.stereotype.Service;

@Service
public class HealthClockServiceImpl extends ServiceImpl<HealthClockMapper, HealthClock>implements HealthClockService {
}
