package com.wyzc.htgl.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcUserBo;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.bo.houseTableBo2;
import com.wyzc.htgl.dao.houseTableDao;

/**
 * 救救孩子吧
 *
 * @author 二娃
 */
@Service
public class houseTableService {
    @Autowired
    private houseTableDao htd;
    private List<houseTableBo> exportNow;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */

    public Map<String, Object> showHouse(houseTableBo bo, houseTableBo2 bo2) {

        Map<String, Object> map = new HashMap<>();

        Page page = new Page(bo);

        List<houseTableBo> list = this.htd.showHouse(page);
        exportNow = this.htd.exportNow(bo);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 房源添加
     *
     * @param bo
     * @return
     */
    public Map<String, Object> addHouse(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        if (bo.getShiBulidDate() == null && bo.getShiBulidDate() == "") {
            String date = new Date().toLocaleString();
            bo.setShiBulidDate(date);
        }
        Integer add = this.htd.addHouse(bo);
        map.put("add", add);
        return map;
    }

    /**
     * 详情
     *
     * @param bo
     * @return
     */
    public Map<String, Object> showFyXq(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("showFyXq", this.htd.showFyXq(bo));
        return map;
    }

    /**
     * 修改房源信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> modifyHouse(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        //设置结束时间
        //1.将字符串格式的开拍时间转为时间格式
        LocalDate ld = LocalDate.parse(bo.getShiAuctionDate());
        //2.判断该条数据里面是否有‘变卖’字样
        String endDate = "";
        if (bo.getShiHouseNumber().indexOf("变卖") != -1) {
            //3.如果有则结束时间为开拍时间加上60天
            LocalDate re = ld.plusDays(60);
            endDate = re.format(DateTimeFormatter.ISO_DATE);
            bo.setShiEndDate(endDate);
        } else {
            //4.没有则结束时间为开拍时间加上1天
            LocalDate re = ld.plusDays(1);
            endDate = re.format(DateTimeFormatter.ISO_DATE);
            bo.setShiEndDate(endDate);
        }
        map.put("modifyHouse", this.htd.modifyHouse(bo));
        return map;
    }

    /**
     * 删除房源信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> deleteHouse(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("deleteHouse", this.htd.deleteHouse(bo));
        return map;
    }

    /**
     * 导出所有数据
     *
     * @param bo
     * @return
     * @throws IOException
     */
    public Map<String, Object> exportHouse(houseTableBo bo, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filePath = "C:\\Users\\Administrator\\Desktop\\房源表.xls";
        List<houseTableBo> list = this.htd.exportHouse(bo);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet();
        HSSFRow rows = sheet1.createRow(0);
        // rows.createCell(0).setCellValue("优质指数");
        rows.createCell(1).setCellValue("区域");
        rows.createCell(2).setCellValue("楼盘名称");
        rows.createCell(3).setCellValue("房号");
        rows.createCell(4).setCellValue("面积㎡");
        rows.createCell(5).setCellValue("装修");
        rows.createCell(6).setCellValue("室内结构");
        rows.createCell(7).setCellValue("有无电梯");
        rows.createCell(8).setCellValue("楼层");
        rows.createCell(9).setCellValue("起拍价");
        rows.createCell(10).setCellValue("市场价");
        rows.createCell(11).setCellValue("保证金");
        rows.createCell(12).setCellValue("加价幅度");
        rows.createCell(13).setCellValue("竞拍日期");
        rows.createCell(14).setCellValue("建成时间");
        rows.createCell(15).setCellValue("位置");
        rows.createCell(16).setCellValue("土地类型");
        rows.createCell(17).setCellValue("网址");
        for (int i = 0; i < list.size(); i++) {
            rows = sheet1.createRow(i + 1);
            // rows.createCell(0).setCellValue(list.get(i).ge);//优质指数
            rows.createCell(1).setCellValue(list.get(i).getShiArea()); // 区域
            rows.createCell(2).setCellValue(list.get(i).getShiName()); // 楼盘名称
            rows.createCell(3).setCellValue(list.get(i).getShiHouseNumber()); // 房号
            rows.createCell(4).setCellValue(list.get(i).getShiSpace());// 面积
            rows.createCell(5).setCellValue(list.get(i).getShiFinish());// 装修
            rows.createCell(6).setCellValue(list.get(i).getShiConstruction());// 室内结构
            rows.createCell(7).setCellValue(list.get(i).getShiIsElevactor());// 有无电梯
            rows.createCell(8).setCellValue(list.get(i).getShiLc());// 楼层
            rows.createCell(9).setCellValue(list.get(i).getShiStartPrice());// 起拍价
            rows.createCell(10).setCellValue(list.get(i).getShiPrice()); // 市场价格
            rows.createCell(11).setCellValue(list.get(i).getShiGuaranteePrice());// 保证金
            rows.createCell(12).setCellValue(list.get(i).getShiAddPrice());// 加价幅度
            rows.createCell(13).setCellValue(list.get(i).getShiAuctionDate());// 竞拍日期
            rows.createCell(14).setCellValue(list.get(i).getShiBulidDate());// 建成日期
            rows.createCell(15).setCellValue(list.get(i).getShiLocation());// 位置
            rows.createCell(16).setCellValue(list.get(i).getShiTudi());// 土地类型
            rows.createCell(17).setCellValue(list.get(i).getShiUrl());// 网址

            FileOutputStream out = new FileOutputStream(filePath);
            wb.write(out);
            out.close();
        }
        down(filePath, response);// 调用方法
        map.put("row", list);
        return map;
    }

    /**
     * 下载文件
     *
     * @param url
     * @param response
     */
    static void down(String url, HttpServletResponse response) {
        File file = new File(url);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        try {// 设置乱码转字符串file.getName().getBytes("utf-8"),"iso-8859-1")
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(file.getName().getBytes("utf-8"), "iso-8859-1"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 上传excel文件
     *
     * @param request
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Map<String, Object> addFyExcel(MultipartFile[] file1, houseTableBo bo, HttpServletRequest request)
            throws FileNotFoundException, IOException {
        // TODO Auto-generated method stub

        String realpath = "";
        // 获取文件名
        String name = "";
        // 判断文件个数
        if (file1.length == 1) {
            name = fileup(file1[0], request);
            ;

        } else {// 多个文件是多个名字你要在这个遍历循环里， 如果只有单个文件上传就可以了你直接拿name
            for (MultipartFile multipartFile : file1) {
                name = multipartFile.getOriginalFilename();
                fileup(multipartFile, request);
            }
        }

        Map<String, Object> map = new HashMap<>();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(name));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= lastRowNum; i++) {

            HSSFRow row = sheet.getRow(i);

            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                bo.setShiArea(row.getCell(0).getStringCellValue());// 区域
            } else
                bo.setShiArea("无");

            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                bo.setShiName(row.getCell(1).getStringCellValue());// 楼盘名称
            } else
                bo.setShiName("无");

            if (row.getCell(2) != null) {
                row.getCell(2).setCellType(CellType.STRING);
                bo.setShiHouseNumber(row.getCell(2).getStringCellValue());// 房号
            } else
                bo.setShiHouseNumber("无");

            if (row.getCell(3) != null) {
                row.getCell(3).setCellType(CellType.STRING);
                bo.setShiSpace(row.getCell(3).getStringCellValue().trim());// 面积
            } else
                bo.setShiSpace("无");

            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(CellType.STRING);
                bo.setShiFinish(row.getCell(4).getStringCellValue());// 装修
            } else
                bo.setShiFinish("无");

            if (row.getCell(5) != null) {
                row.getCell(5).setCellType(CellType.STRING);
                bo.setShiConstruction(row.getCell(5).getStringCellValue());// 室内结构
            } else
                bo.setShiConstruction("无");

            if (row.getCell(6) != null) {
                row.getCell(6).setCellType(CellType.STRING);
                bo.setShiIsElevactor(row.getCell(6).getStringCellValue());// 有无电梯
            } else
                bo.setShiIsElevactor("无");

            if (row.getCell(7) != null) {
                row.getCell(7).setCellType(CellType.STRING);
                bo.setShiLc(row.getCell(7).getStringCellValue());// 楼层
            } else
                bo.setShiLc("无");

            if (row.getCell(8) != null) {
                row.getCell(8).setCellType(CellType.STRING);
                bo.setShiStartPrice(row.getCell(8).getStringCellValue().trim());// 起拍价
            } else
                bo.setShiStartPrice("无");

            if (row.getCell(9) != null) {
                row.getCell(9).setCellType(CellType.STRING);
                bo.setShiPrice(row.getCell(9).getStringCellValue().trim());// 市场价格
            } else
                bo.setShiPrice("无");

            if (row.getCell(10) != null) {
                row.getCell(10).setCellType(CellType.STRING);
                bo.setShiGuaranteePrice(row.getCell(10).getStringCellValue().trim());// 保证金
            } else
                bo.setShiGuaranteePrice("无");

            if (row.getCell(11) != null) {
                row.getCell(11).setCellType(CellType.STRING);
                bo.setShiAddPrice(row.getCell(11).getStringCellValue().trim());// 加价幅度
            } else
                bo.setShiAddPrice("无");

            if (row.getCell(12) != null) {

                bo.setShiAuctionDate(sdf.format(row.getCell(12).getDateCellValue()));// 竞拍日期
            } else
                bo.setShiAuctionDate("无");

            if (row.getCell(13) != null) {
                row.getCell(13).setCellType(CellType.STRING);
                bo.setShiBulidDate(row.getCell(13).getStringCellValue().toString());// 建成时间
            } else
                bo.setShiBulidDate("无");

            if (row.getCell(14) != null) {
                row.getCell(14).setCellType(CellType.STRING);
                bo.setShiLocation(row.getCell(14).getStringCellValue());// 位置
            } else
                bo.setShiLocation("无");

            if (row.getCell(15) != null) {
                row.getCell(15).setCellType(CellType.STRING);
                bo.setShiTudi(row.getCell(15).getStringCellValue());// 土地类型
            } else
                bo.setShiTudi("无");

            if (row.getCell(16) != null) {
                row.getCell(16).setCellType(CellType.STRING);
                bo.setShiUrl(row.getCell(16).getStringCellValue());// 网址
            } else
                bo.setShiUrl("无");
            //获取当前系统时间
            LocalDateTime ldt = LocalDateTime.now();
            //将时间格式转为字符串格式
            String upload = ldt.format(DateTimeFormatter.ISO_DATE);
            bo.setShiUploadDate(upload);
            bo.setShiSign("1");
            //设置结束时间
            //1.将字符串格式的开拍时间转为时间格式
            LocalDate ld = LocalDate.parse(bo.getShiAuctionDate());
            //2.判断该条数据里面是否有‘变卖’字样
            String endDate = "";
            if (bo.getShiHouseNumber().indexOf("变卖") != -1) {
                //3.如果有则结束时间为开拍时间加上60天
                LocalDate re = ld.plusDays(60);
                endDate = re.format(DateTimeFormatter.ISO_DATE);
                bo.setShiEndDate(endDate);
            } else {
                //4.没有则结束时间为开拍时间加上1天
                LocalDate re = ld.plusDays(1);
                endDate = re.format(DateTimeFormatter.ISO_DATE);
                bo.setShiEndDate(endDate);
            }
            Integer add = this.htd.addHouse(bo);
            map.put("add", add);
        }
        return map;
    }

    /**
     * 当日新增
     *
     * @param file1
     * @param bo
     * @param request
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public Map<String, Object> toDayXz(MultipartFile[] file1, houseTableBo bo, HttpServletRequest request)
            throws IllegalStateException, IOException {
        // TODO Auto-generated method stub
        this.htd.deleteTodyXz(bo);
        String realpath = "";
        // 获取文件名
        String name = "";
        // 判断文件个数
        if (file1.length == 1) {
            name = fileup(file1[0], request);
        } else {// 多个文件是多个名字你要在这个遍历循环里， 如果只有单个文件上传就可以了你直接拿name
            for (MultipartFile multipartFile : file1) {
                name = multipartFile.getOriginalFilename();
                fileup(multipartFile, request);
            }
        }
        Map<String, Object> map = new HashMap<>();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(name));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 1; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                bo.setShiArea(row.getCell(0).getStringCellValue());// 区域
            } else
                bo.setShiArea("无");

            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                bo.setShiName(row.getCell(1).getStringCellValue());// 楼盘名称
            } else
                bo.setShiName("无");

            if (row.getCell(2) != null) {
                row.getCell(2).setCellType(CellType.STRING);
                bo.setShiHouseNumber(row.getCell(2).getStringCellValue());// 房号
            } else
                bo.setShiHouseNumber("无");

            if (row.getCell(3) != null) {
                row.getCell(3).setCellType(CellType.STRING);
                bo.setShiSpace(row.getCell(3).getStringCellValue());// 面积
            } else
                bo.setShiSpace("无");

            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(CellType.STRING);
                bo.setShiFinish(row.getCell(4).getStringCellValue());// 装修
            } else
                bo.setShiFinish("无");

            if (row.getCell(5) != null) {
                row.getCell(5).setCellType(CellType.STRING);
                bo.setShiConstruction(row.getCell(5).getStringCellValue());// 室内结构
            } else
                bo.setShiConstruction("无");

            if (row.getCell(6) != null) {
                row.getCell(6).setCellType(CellType.STRING);
                bo.setShiIsElevactor(row.getCell(6).getStringCellValue());// 有无电梯
            } else
                bo.setShiIsElevactor("无");

            if (row.getCell(7) != null) {
                row.getCell(7).setCellType(CellType.STRING);
                bo.setShiLc(row.getCell(7).getStringCellValue());// 楼层
            } else
                bo.setShiLc("无");

            if (row.getCell(8) != null) {
                row.getCell(8).setCellType(CellType.STRING);
                bo.setShiStartPrice(row.getCell(8).getStringCellValue());// 起拍价
            } else
                bo.setShiStartPrice("无");

            if (row.getCell(9) != null) {
                row.getCell(9).setCellType(CellType.STRING);
                bo.setShiPrice(row.getCell(9).getStringCellValue());// 市场价格
            } else
                bo.setShiPrice("无");

            if (row.getCell(10) != null) {
                row.getCell(10).setCellType(CellType.STRING);
                bo.setShiGuaranteePrice(row.getCell(10).getStringCellValue());// 保证金
            } else
                bo.setShiGuaranteePrice("无");

            if (row.getCell(11) != null) {
                row.getCell(11).setCellType(CellType.STRING);
                bo.setShiAddPrice(row.getCell(11).getStringCellValue());// 加价幅度
            } else
                bo.setShiAddPrice("无");

            if (row.getCell(12) != null) {
                bo.setShiAuctionDate(sdf.format(row.getCell(12).getDateCellValue()));// 竞拍日期
            } else
                bo.setShiAuctionDate("无");

            if (row.getCell(13) != null) {
                row.getCell(13).setCellType(CellType.STRING);
                bo.setShiBulidDate(row.getCell(13).getStringCellValue().toString());// 建成时间
            } else
                bo.setShiBulidDate("无");

            if (row.getCell(14) != null) {
                row.getCell(14).setCellType(CellType.STRING);
                bo.setShiLocation(row.getCell(14).getStringCellValue());// 位置
            } else
                bo.setShiLocation("无");

            if (row.getCell(15) != null) {
                row.getCell(15).setCellType(CellType.STRING);
                bo.setShiTudi(row.getCell(15).getStringCellValue());// 土地类型
            } else
                bo.setShiTudi("无");

            if (row.getCell(16) != null) {
                row.getCell(16).setCellType(CellType.STRING);
                bo.setShiUrl(row.getCell(16).getStringCellValue());// 网址
            } else
                bo.setShiUrl("无");
            Integer add = this.htd.toDayXz(bo);
            map.put("toDayXz", add);
        }
        return map;
    }

    /**
     * 当日开拍
     *
     * @param file1
     * @param bo
     * @param request
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public Map<String, Object> toDayKp(MultipartFile[] file1, houseTableBo bo, HttpServletRequest request)
            throws IllegalStateException, IOException {
        // TODO Auto-generated method stub
        this.htd.deleteTodyKp(bo);
        String realpath = "";
        // 获取文件名
        String name = "";
        // 判断文件个数
        Map<String, Object> map = new HashMap<>();
        if (file1.length == 1) {
            name = fileup(file1[0], request);
            ;

        } else {// 多个文件是多个名字你要在这个遍历循环里， 如果只有单个文件上传就可以了你直接拿name
            for (MultipartFile multipartFile : file1) {
                name = multipartFile.getOriginalFilename();
                fileup(multipartFile, request);
            }
        }


        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(name));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i <= lastRowNum; i++) {

            HSSFRow row = sheet.getRow(i);

            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                bo.setShiHouseNumber(row.getCell(0).getStringCellValue());// 房号
            } else
                bo.setShiHouseNumber("无");
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                bo.setShiUrl(row.getCell(1).getStringCellValue());// 网址
            } else
                bo.setShiUrl("无");
            Integer add = this.htd.toDayKp(bo);
            map.put("add", add);
        }
        return map;
    }

    /**
     * 撤回
     *
     * @param file1
     * @param bo
     * @param request
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public Map<String, Object> toDayCh(MultipartFile[] file1, houseTableBo bo, HttpServletRequest request)
            throws IllegalStateException, IOException {
        List<houseTableBo> recallList = new ArrayList<>();
        // TODO Auto-generated method stub
        this.htd.deleteTodyCh(bo);
        //String realpath = "";
        // 获取文件名
        String name = "";
        // 判断文件个数
        if (file1.length == 1) {
            name = fileup(file1[0], request);
            ;

        } else {// 多个文件是多个名字你要在这个遍历循环里， 如果只有单个文件上传就可以了你直接拿name
            for (MultipartFile multipartFile : file1) {
                name = multipartFile.getOriginalFilename();
                fileup(multipartFile, request);
            }
        }

        Map<String, Object> map = new HashMap<>();
        //HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(name));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 1; i <= lastRowNum; i++) {
            houseTableBo testBo = new houseTableBo();
            HSSFRow row = sheet.getRow(i);
            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                testBo.setShiHouseNumber(row.getCell(0).getStringCellValue());// 房号
            }
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                testBo.setShiUrl(row.getCell(1).getStringCellValue());// 网址
            }
            recallList.add(testBo);
        }

        //查询房源表中的数据
        //List<houseTableBo> list = this.htd.searchFromShowHouseInfo();
        //获取当前系统时间
        LocalDateTime ldt = LocalDateTime.now();
        //将时间格式转为字符串格式
        String recallDate = ldt.format(DateTimeFormatter.ISO_DATE);
        for (houseTableBo rb : recallList) {
            rb.setShiRecallDate(recallDate);
            rb.setShiSign("3");
            System.out.println(rb.getShiHouseNumber() + "\n" + rb.getShiUrl());
            Integer add = this.htd.addRecallDateToShi(rb);
            map.put("add", add);
        }
        hssfWorkbook.close();
        return map;
    }

    /****
     * 文件上传
     *
     * @param file
     * @param re
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    static String fileup(MultipartFile file, HttpServletRequest re) throws IllegalStateException, IOException {
        String a = re.getServletContext().getRealPath("/") + "view\\wyzc\\upload\\" + new Date().getTime()
                + file.getOriginalFilename();
        file.transferTo(new File(a));
        return a;
    }

    /**
     * 当日新增
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchTodyXz(houseTableBo bo) {
        Page page = new Page(bo);
        Map<String, Object> map = new HashMap<>();
        List<houseTableBo> list = this.htd.searchTodyXz(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 当日开拍
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchTodyKp(houseTableBo bo) {
        Page page = new Page(bo);
        Map<String, Object> map = new HashMap<>();
        List<houseTableBo> list = this.htd.searchTodyKp(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 当日撤回
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchTodyCh(houseTableBo bo) {
        Page page = new Page(bo);
        Map<String, Object> map = new HashMap<>();
        List<houseTableBo> list = this.htd.searchTodyCh(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 导入过期数据
     *
     * @param file1
     * @param bo
     * @param request
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public Map<String, Object> doGqFy(MultipartFile[] file1, houseTableBo bo, HttpServletRequest request)
            throws IllegalStateException, IOException {
        // 删除过期表中的数据
        this.htd.deletePastHouse(bo);
        String realpath = "";
        // 获取文件名
        String name = "";
        // 判断文件个数
        if (file1.length == 1) {
            name = fileup(file1[0], request);
            ;

        } else {// 多个文件是多个名字你要在这个遍历循环里， 如果只有单个文件上传就可以了你直接拿name
            for (MultipartFile multipartFile : file1) {
                name = multipartFile.getOriginalFilename();
                fileup(multipartFile, request);
            }
        }

        Map<String, Object> map = new HashMap<>();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(name));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                bo.setShiHouseNumber(row.getCell(0).getStringCellValue());// 房号
            } else {
                bo.setShiHouseNumber("无");
            }
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                bo.setShiUrl(row.getCell(1).getStringCellValue());// 网址
            } else {
                bo.setShiUrl("无");
            }
            Integer add = this.htd.addPastHouse(bo);
            map.put("add", add);
        }

        // 查询房源展示表中的数据
        List<houseTableBo> showList = this.htd.exportHouse(bo);
        // 查询过期房源表中的数据
        List<houseTableBo> pastList = this.htd.searchPastHouse();
        // 删除重复的数据
        for (houseTableBo pastBo : pastList) {
            for (houseTableBo showBo : showList) {
                if (pastBo.getShiUrl().equals(showBo.getShiUrl())) {
                    Integer result = this.htd.deleteHouse(showBo);
                    map.put("result", result);
                }
            }
        }

        return map;
    }

    /**
     * 权限
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchId(WyzcUserBo bo) {
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        if (userId.equals("admin")) {
            map.put("result", "admin");
            map.put("searchId", "");
        } else {
            map.put("searchId", this.htd.searchId(bo));
        }
        return map;
    }

    /**
     * 导出当前数据
     *
     * @param bo
     * @param response
     * @return
     * @throws IOException
     */
    public Map<String, Object> exportHouseDq(houseTableBo bo, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filePath = "C:\\Users\\Administrator\\Desktop\\当前房源表.xls";
        //String userId = CommonFunction.getUserFromSession().getUserId();
        //bo.setUserId(userId);
        //List<houseTableBo> list = this.htd.exportHouseDq(bo);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet();
        HSSFRow rows = sheet1.createRow(0);
        // rows.createCell(0).setCellValue("优质指数");
        rows.createCell(1).setCellValue("区域");
        rows.createCell(2).setCellValue("楼盘名称");
        rows.createCell(3).setCellValue("房号");
        rows.createCell(4).setCellValue("面积㎡");
        rows.createCell(5).setCellValue("装修");
        rows.createCell(6).setCellValue("室内结构");
        rows.createCell(7).setCellValue("有无电梯");
        rows.createCell(8).setCellValue("楼层");
        rows.createCell(9).setCellValue("起拍价");
        rows.createCell(10).setCellValue("市场价");
        rows.createCell(11).setCellValue("保证金");
        rows.createCell(12).setCellValue("加价幅度");
        rows.createCell(13).setCellValue("竞拍日期");
        rows.createCell(14).setCellValue("建成时间");
        rows.createCell(15).setCellValue("位置");
        rows.createCell(16).setCellValue("土地类型");
        rows.createCell(17).setCellValue("网址");
        for (int i = 0; i < exportNow.size(); i++) {
            rows = sheet1.createRow(i + 1);
            // rows.createCell(0).setCellValue(list.get(i).ge);//优质指数
            rows.createCell(1).setCellValue(exportNow.get(i).getShiArea()); // 区域
            rows.createCell(2).setCellValue(exportNow.get(i).getShiName()); // 楼盘名称
            rows.createCell(3).setCellValue(exportNow.get(i).getShiHouseNumber()); // 房号
            rows.createCell(4).setCellValue(exportNow.get(i).getShiSpace());// 面积
            rows.createCell(5).setCellValue(exportNow.get(i).getShiFinish());// 装修
            rows.createCell(6).setCellValue(exportNow.get(i).getShiConstruction());// 室内结构
            rows.createCell(7).setCellValue(exportNow.get(i).getShiIsElevactor());// 有无电梯
            rows.createCell(8).setCellValue(exportNow.get(i).getShiLc());// 楼层
            rows.createCell(9).setCellValue(exportNow.get(i).getShiStartPrice());// 起拍价
            rows.createCell(10).setCellValue(exportNow.get(i).getShiPrice()); // 市场价格
            rows.createCell(11).setCellValue(exportNow.get(i).getShiGuaranteePrice());// 保证金
            rows.createCell(12).setCellValue(exportNow.get(i).getShiAddPrice());// 加价幅度
            rows.createCell(13).setCellValue(exportNow.get(i).getShiAuctionDate());// 竞拍日期
            rows.createCell(14).setCellValue(exportNow.get(i).getShiBulidDate());// 建成日期
            rows.createCell(15).setCellValue(exportNow.get(i).getShiLocation());// 位置
            rows.createCell(16).setCellValue(exportNow.get(i).getShiTudi());// 土地类型
            rows.createCell(17).setCellValue(exportNow.get(i).getShiUrl());// 网址

            FileOutputStream out = new FileOutputStream(filePath);
            wb.write(out);
            out.close();
        }
        down(filePath, response);// 调用方法
        map.put("row", exportNow);
        return map;
    }

}
