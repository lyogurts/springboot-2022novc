package com.example.springboot2022novc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot2022novc.entity.ChinaTotal;
import com.example.springboot2022novc.entity.LineTrend;
import com.example.springboot2022novc.entity.NocvData;
import com.example.springboot2022novc.service.ChinaTotalService;
import com.example.springboot2022novc.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;
    @Autowired
    private ChinaTotalService chinaTotalService;
//    @RequestMapping("/")
//public  String  index(Model model) throws ParseException {
////        找到id最大的那一条数据
//        Integer id = chinaTotalService.maxID();
////        2.根据ID进行查找数据
//        ChinaTotal chinaTotal = chinaTotalService.getById(id);
//        model.addAttribute(" chinaTotal", chinaTotal);
//        return "index";
//    }
@RequestMapping({"/"})
public String index(Model model) throws ParseException {
    Integer id = chinaTotalService.maxID();
    ChinaTotal chinaTotal = chinaTotalService.getById(id);
    model.addAttribute("chinaTotal", chinaTotal);
    return "index";
}
    @RequestMapping("/query")
    @ResponseBody
    //从后台数据库拿数据 ，前台用ajax解析list
    public List<NocvData> queryData(){
        List<NocvData> list =  indexService.listOrderByIdLimit34();
        return list;
    }

    //跳转pie页面
    @RequestMapping("/topie")
    public  String  topie(){
        return "pie";
    }


    //从后台数据库拿数据
    @RequestMapping("/querypie")
    @ResponseBody
    public List<NocvData> queryPieData(){
        List<NocvData> list =  indexService.listOrderByIdLimit34();

        return list;
    }

    //跳转bar页面
    @RequestMapping("/tobar")
    public String  tobar(){
        return "bar";
    }

    //从后台数据库拿数据
    @RequestMapping("/querybar")
    @ResponseBody
    public Map<String ,List<Object>> queryBarData(){
       //所有数据(城市，数值)
        List<NocvData> list =  indexService.listOrderByIdLimit34();

        //所有城市数据
        /*
        思路：通过foreach把城市和城市确诊人数分开装载到集合
              把这两个集合装载到一个map集合中返回。
         */
        List<String >cityList = new ArrayList<>();
        for (NocvData data :
                list) {
            cityList.add(data.getName());
        }
        //所有城市疫情数值
        List<Integer >dataList = new ArrayList<>();
        for (NocvData data :
                list) {
            dataList.add(data.getValue());
        }

        HashMap map = new HashMap<>();
        map.put("cityList",cityList);
        map.put("dataList",dataList);
        return map;
    }


/*
* 跳转柱状图页面
* */
    @RequestMapping("/toLine")
    public String toLine(){
        return "line";
    }

    //从后台数据库拿数据
    @RequestMapping("/queryline")
    @ResponseBody
    public Map<String ,List<Object>> queryLine(){
        //查询近七天所有的数据
        List<LineTrend> list7 = indexService.findSevenData();

        //封装所有的确诊人数
        List<Integer> confirmList = new ArrayList<>();
        for (LineTrend data :
                list7) {
            confirmList.add(data.getConfirm());
        }

        //封装所有的隔离人数
        List<Integer> isolationList = new ArrayList<>();
        for (LineTrend data :
                list7) {
            isolationList.add(data.getIsolation());
        }

        //封装所有的治愈人数
        List<Integer> cureList = new ArrayList<>();
        for (LineTrend data :
                list7) {
            cureList.add(data.getCure());
        }

        //封装所有的死亡人数
        List<Integer> deadList = new ArrayList<>();
        for (LineTrend data :
                list7) {
            deadList.add(data.getDead());
        }

        //封装所有的疑似人数
        List<Integer> similarList = new ArrayList<>();
        for (LineTrend data :
                list7) {
            similarList.add(data.getSimilar());
        }


        //返回map。。。
        Map map=new HashMap();
        map.put("confirmList",confirmList);
        map.put("isolationList",isolationList );
        map.put(" cureList",cureList);
        map.put("deadList",deadList);
        map.put("similarList",similarList);
        return map;
    }
}
