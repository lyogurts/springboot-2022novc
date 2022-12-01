package com.example.springboot2022novc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@TableName("health_clock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthClock {
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;
    private  String  username;
    private  String  sex;
    private  Integer age;
    private  String  phone;
    private String  morningTemp;
    private  String afternoonTemp;
    private  String  nightTemp;
    private String feverAndCough;
    private  String  recentHome;
    private String  riskZone;
    private  String recentZone;
    private String  healthStatus;
    private Date createTime;
//    private Integer id;
//    private String username;
//    private String sex;
//    private Integer age;
//    private String phone;
//    private String morningTemp;
//    private String afternoonTemp;
//    private String nightTemp;
//    private String feverAndCough;
//    private String recentHome;
//    private String riskZone;
//    private String recentZone;
//    private String healthStatus;
//    private Date createTime;

}
