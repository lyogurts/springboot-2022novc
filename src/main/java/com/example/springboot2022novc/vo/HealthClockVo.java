package com.example.springboot2022novc.vo;

import com.example.springboot2022novc.entity.HealthClock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HealthClockVo extends HealthClock {
    private Integer page;
    private  Integer limit;
}
