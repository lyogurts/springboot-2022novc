package com.example.springboot2022novc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot2022novc.entity.NocvData;
import com.example.springboot2022novc.service.IndexService;
import com.example.springboot2022novc.vo.DataView;
import com.example.springboot2022novc.vo.NocvDataVo;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ChinaDataAdminController {

    @Autowired
    private IndexService indexService;

    //跳转页面
    @RequestMapping("/toChinaManager")
    public String toChinaManager(){
        return  "admin/chinadatamanager";
    }

    /**
     * 模糊查询,带有分页
     * @return
     */
    @RequestMapping("/listDataByPage")
    @ResponseBody
    public DataView listDataByPage(NocvDataVo nocvDataVo){
        //1.创建分页的对象, 当前页码，每页大小
        IPage<NocvData> page =new Page<>(nocvDataVo.getPage(),nocvDataVo.getLimit() );
        //2.创建模糊查询条件
        QueryWrapper<NocvData> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(nocvDataVo.getName()),"name",nocvDataVo.getName());
        //3.疫情数据确诊最多的排在上面
        queryWrapper.orderByDesc("value");
        //4.查询数据库
        indexService.page(page,queryWrapper);
        //5.返回数据封装
        DataView dataView = new DataView(page.getTotal(),page.getRecords());
        return dataView;
    }

    /**
     *根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping("/china/deleteById")
    @ResponseBody
    public DataView deleteById(Integer id){
        indexService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除中国地图数据成功！");
        return dataView;
    }

    /**
     * nocvData id有值时修改，无值时新增
     * @param nocvData
     * @return
     */
    @RequestMapping("/china/addOrUpdateChina")
@ResponseBody
    public  DataView addChina(NocvData nocvData){
        boolean save = indexService.saveOrUpdate(nocvData);
        DataView dataView = new DataView();
        if (save){
            dataView.setCode(200);
            dataView.setMsg("新增中国地图数据成功");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("新增中国地图数据失败");
        return dataView;
    }

    @RequestMapping({"/excelImportChina"})
    @ResponseBody
    public DataView excelImportChina(@RequestParam("file") MultipartFile file) throws Exception {
        DataView dataView = new DataView();
        if (file.isEmpty()) {
            dataView.setMsg("文件为空，不能上传！");
        }

        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = wb.getSheetAt(0);
        List<NocvData> list = new ArrayList();
        XSSFRow row = null;

        for(int i = 0; i < sheet.getPhysicalNumberOfRows(); ++i) {
            NocvData nocvData = new NocvData();
            row = sheet.getRow(i);
            nocvData.setName(row.getCell(0).getStringCellValue());
            nocvData.setValue((int)row.getCell(1).getNumericCellValue());
            list.add(nocvData);
        }

        this.indexService.saveBatch(list);
        dataView.setCode(200);
        dataView.setMsg("小可爱，excel已经被你插入成功！");
        return dataView;
    }


//    @RequestMapping({"/excelImportChina"})
//    @ResponseBody
//    public DataView excelImportChina(@RequestParam("file") MultipartFile file) throws Exception {
//        DataView dataView = new DataView();
//        if (file.isEmpty()) {
//            dataView.setMsg("文件为空，不能上传！");
//        }
//
//        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
//        XSSFSheet sheet = wb.getSheetAt(0);
//        List<NocvData> list = new ArrayList();
//        XSSFRow row = null;
//
//        for(int i = 0; i < sheet.getPhysicalNumberOfRows(); ++i) {
//            NocvData nocvData = new NocvData();
//            row = sheet.getRow(i);
//            nocvData.setName(row.getCell(0).getStringCellValue());
//            nocvData.setValue((int) row.getCell(1).getNumericCellValue());
//            list.add(nocvData);
//        }
//
//        this.indexService.saveBatch(list);
//        dataView.setCode(200);
//        dataView.setMsg("小可爱，excel已经被你插入成功！");
//        return dataView;
//    }




    @RequestMapping("/excelOutportChina")
    @ResponseBody
    public void excelOutportChina(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        List<NocvData> list = indexService.list();
        XSSFWorkbook wb = new XSSFWorkbook();
        //2-创建sheet页,设置sheet页的名字
        XSSFSheet sheet = wb.createSheet("中国数据表");

        //3-创建标题行
        XSSFRow titleRow = sheet.createRow(0);
        //设置表头信息
        titleRow.createCell(0).setCellValue("城市名称");
        titleRow.createCell(1).setCellValue("确诊数量");
        //4-遍历将数据集合将数据放到对应的列中
        for (NocvData data : list){
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getName());
         dataRow.createCell(1).setCellValue((double)data.getValue());
        }

        // 5.建立输出
        OutputStream os = null;

        try{
            //6-设置Excel的名称
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String("中国疫情数据表".getBytes(),"iso-8859-1") + ".xlsx");
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
        }catch(Exception e){
            e.printStackTrace();

        } finally {
            try {
                if(os != null){
                    os.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
