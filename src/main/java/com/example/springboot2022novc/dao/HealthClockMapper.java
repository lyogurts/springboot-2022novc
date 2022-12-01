package com.example.springboot2022novc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot2022novc.entity.HealthClock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HealthClockMapper extends BaseMapper<HealthClock>{
}
