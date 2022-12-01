package com.example.springboot2022novc.tengxunapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot2022novc.entity.ChinaTotal;
import com.example.springboot2022novc.entity.NocvData;
import com.example.springboot2022novc.service.ChinaTotalService;
import com.example.springboot2022novc.service.IndexService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChinaTotalScheduleTask {
    @Autowired
    private ChinaTotalService chinaTotalService;
    @Autowired
    private IndexService indexService;

    /**
     * 每小时更新一次全国数据统计信息
     * alibaba格式java程序可读
     * @throws Exception
     */
@Scheduled(fixedDelay = 1000000000)
//    @Scheduled(cron ="0 0 8,9,10,11,12,13,18,20 * * ?")
    public void updateChinaTotalToDB() throws Exception {
        HttpUtils httpUtils = new HttpUtils();
        String string = httpUtils.getData();
        System.out.println(string);
        //所有（string类型）数据的alibaba格式
        JSONObject jsonObject = JSONObject.parseObject(string);
        //获取data
        Object data = jsonObject.get("data");
        System.out.println(data);
        //把获取到的data转换为alibaba格式
        JSONObject jsonObjectData = JSONObject.parseObject(data.toString());

        //获取data下的chinaTotal和下面的tree一个级别的
        Object chinaTotal = jsonObjectData.get("chinaTotal");
        //获取data下的overseaLastUpdateTime(最后更新时间)
        Object lastUpdateTime = jsonObjectData.get("overseaLastUpdateTime");


        //把获取到的chinaTotal转换为alibaba格式
        JSONObject jsonObjectTotal = JSONObject.parseObject(chinaTotal.toString());
        //获取chinaTotal下的total total(全国整体疫情数据)
        Object total = jsonObjectTotal.get("total");
//        System.out.println("total:" + total);

        //把获取到的total转换为alibaba格式
        JSONObject totalData = JSONObject.parseObject(total.toString());
        //获取total下的数据 治愈，死亡，健康。。。
        Object confirm = totalData.get("confirm");
        Object input = totalData.get("input");
        Object severe = totalData.get("severe");
        Object heal = totalData.get("heal");
        Object dead = totalData.get("dead");
        Object suspect = totalData.get("suspect");
        System.out.println("死亡");
//  创建ChinaTotal对象并为属性confirm,input,severe,heal,dead,suspect，lastUpdateTime赋值
      //程序实体赋值
        ChinaTotal dataEntity = new ChinaTotal();
        dataEntity.setConfirm((Integer)confirm);
        dataEntity.setInput((Integer)input);
        dataEntity.setSevere((Integer)severe);
        dataEntity.setHeal((Integer)heal);
        dataEntity.setDead((Integer)dead);
        dataEntity.setSuspect((Integer)suspect);

        //把类型日期转换成xxx类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dataEntity.setUpdateTime(format.parse(String.valueOf(lastUpdateTime)));

        //插入数据库,更新操作
        this.chinaTotalService.save(dataEntity);

      //上面41，42行已经转换过alibab格式了，areaTree和上面chinaTotal(44,45)一个级别的
        JSONArray areaTree = jsonObjectData.getJSONArray("areaTree");
        Object[] objects = areaTree.toArray();
        //把obeject[2](中国)转换成alibaba格式。
        JSONObject jsonObject1 = JSONObject.parseObject(objects[2].toString());
        //获取的是中国的省份信息
        JSONArray children = jsonObject1.getJSONArray("children");
        //转换成java数组
        Object[] objects1 = children.toArray();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<NocvData> list = new ArrayList();

        for(int i = 0; i < objects1.length; ++i) {
            NocvData nocvData = new NocvData();
            //objects1里面的每一个数据都转换成alibab格式
            JSONObject jsonObject2 = JSONObject.parseObject(objects1[i].toString());
            //拿到省份的名字
            Object name = jsonObject2.get("name");
            //最后更新时间
            Object timePro = jsonObject2.get("lastUpdateTime");
           //这个total是省份下的total,确诊，治愈，死亡。。。
            Object total1 = jsonObject2.get("total");
            //转换成阿里巴巴格式
            JSONObject jsonObject3 = JSONObject.parseObject(total1.toString());
          //得到确诊，死亡，治愈信息。
            Object confirm1 = jsonObject3.get("confirm");
            Object heal1 = jsonObject3.get("heal");
            Object dead1 = jsonObject3.get("dead");

            int xianConfirm = Integer.parseInt(confirm1.toString()) - Integer.parseInt(heal1.toString()) - Integer.parseInt(dead1.toString());
//            int xianConfirm = Integer.parseInt(confirm1.toString());
            nocvData.setName(name.toString());
            nocvData.setValue(xianConfirm);
            if (timePro == null) {
                nocvData.setUpdateTime(new Date());
            } else {
                nocvData.setUpdateTime(format1.parse(String.valueOf(timePro)));
            }

            list.add(nocvData);
        }
        indexService.saveBatch(list);
    }




}

