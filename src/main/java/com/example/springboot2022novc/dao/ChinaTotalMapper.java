package com.example.springboot2022novc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot2022novc.entity.ChinaTotal;
import org.apache.ibatis.annotations.Select;

public interface ChinaTotalMapper extends BaseMapper<ChinaTotal> {
    @Select({"select max(id) from china_total"})
    Integer maxID();
}
