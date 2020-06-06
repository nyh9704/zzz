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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.bo.houseTableBo2;
import com.wyzc.htgl.dao.StopHouseDao;
import com.wyzc.htgl.dao.houseTableDao;

@Service
public class StopHouseService {

    @Autowired
    private StopHouseDao shd;


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
     * 查询返回
     *
     * @param bo
     * @param bo2
     * @return
     */
    public Map<String, Object> showStopHouse(houseTableBo bo, houseTableBo2 bo2) {
        if (bo.getSort() == "shiStartPrice") {
            bo.setSort("shi_start_price");
        } else if (bo.getSort() == "shiAuctionDate") {
            bo.setSort("shi_auction_date");
        } else if (bo.getSort() == "shiBulidDate") {
            bo.setSort("shi_bulid_date");
        } else if (bo.getSort() == "shiPrice") {
            bo.setSort("shi_price");
        } else if (bo.getSort() == "shiSpace") {
            bo.setSort("shi_space");
        } else if (bo.getSort() == "shiAddPrice") {
            bo.setSort("shi_add_price");
        }
        Map<String, Object> map = new HashMap<>();
        if (bo.getSearchMo() != null || bo.getSearchMo() != "" || bo.getShiSpace1() != null || bo.getShiSpace1() != ""
                || bo.getShiSpace2() != null || bo.getShiSpace2() != ""
                || bo.getShiStartPrice1() != null || bo.getShiStartPrice1() != ""
                || bo.getShiStartPrice2() != null || bo.getShiStartPrice2() != ""
                || bo.getShiFinish() != null || bo.getShiFinish() != ""
                || bo.getShiBulidDate() != null || bo.getShiBulidDate() != ""
                || bo.getShiIsElevactor() != null || bo.getShiIsElevactor() != ""
                || bo.getShiArea() != null || bo.getShiArea() != ""
        ) {
            String userId = CommonFunction.getUserFromSession().getUserId();
            //houseTableBo2 bo2 = new houseTableBo2();
            List<houseTableBo> list = this.shd.showStopHouse1(bo);
            bo2.setUserId(userId);
            this.shd.deleteStopSearch(bo2);
            this.shd.deleteStopHouseSearch(bo2);
            for (int i = 0; i < list.size(); i++) {
                bo2.setFilePath(list.get(i).getFilePath());
                bo2.setOrder(list.get(i).getOrder());
                bo2.setShiAddPrice(list.get(i).getShiAddPrice());
                bo2.setShiArea(list.get(i).getShiArea());
                bo2.setShiAuctionDate(list.get(i).getShiAuctionDate());
                bo2.setShiBulidDate(list.get(i).getShiBulidDate());
                bo2.setShiCode(list.get(i).getShiCode());
                bo2.setShiConstruction(list.get(i).getShiConstruction());
                bo2.setShiFinish(list.get(i).getShiFinish());
                bo2.setShiGuaranteePrice(list.get(i).getShiGuaranteePrice());
                bo2.setShiHouseNumber(list.get(i).getShiHouseNumber());
                bo2.setShiIsElevactor(list.get(i).getShiIsElevactor());
                bo2.setShiLc(list.get(i).getShiLc());
                bo2.setShiLocation(list.get(i).getShiLocation());
                bo2.setShiName(list.get(i).getShiName());
                bo2.setShiPrice(list.get(i).getShiPrice());
                bo2.setShiSpace(list.get(i).getShiSpace());
                bo2.setShiSpace1(list.get(i).getShiSpace1());
                bo2.setShiSpace2(list.get(i).getShiSpace2());
                bo2.setShiStartPrice(list.get(i).getShiStartPrice());
                bo2.setShiStartPrice1(list.get(i).getShiStartPrice1());
                bo2.setShiStartPrice2(list.get(i).getShiStartPrice2());
                bo2.setShiTudi(list.get(i).getShiTudi());
                bo2.setShiUrl(list.get(i).getShiUrl());
                bo2.setSort(list.get(i).getSort());
                bo2.setUserId(userId);
                this.shd.addStopHouseCopy(bo2);
            }
            bo2.setUserId(userId);
            bo2.setShiArea(bo.getShiArea());
            bo2.setShiFinish(bo.getShiFinish());
            bo2.setShiIsElevactor(bo.getShiIsElevactor());
            bo2.setShiSpace(bo.getShiSpace1());
            bo2.setShiSpace(bo.getShiSpace2());
            bo2.setShiBulidDate(bo.getShiBulidDate());
            bo2.setShiStartPrice1(bo.getShiStartPrice1());
            bo2.setShiStartPrice2(bo.getShiStartPrice2());

            Page page = new Page(bo2);
            List<houseTableBo2> list3 = this.shd.searchStopFj1(bo2);
            List<houseTableBo2> list2 = this.shd.searchStopFj(page);
            for (int i = 0; i < list3.size(); i++) {
                bo2.setFilePath(list3.get(i).getFilePath());
                bo2.setOrder(list3.get(i).getOrder());
                bo2.setShiAddPrice(list3.get(i).getShiAddPrice());
                bo2.setShiArea(list3.get(i).getShiArea());
                bo2.setShiAuctionDate(list3.get(i).getShiAuctionDate());
                bo2.setShiBulidDate(list3.get(i).getShiBulidDate());
                bo2.setShiCode(list3.get(i).getShiCode());
                bo2.setShiConstruction(list3.get(i).getShiConstruction());
                bo2.setShiFinish(list3.get(i).getShiFinish());
                bo2.setShiGuaranteePrice(list3.get(i).getShiGuaranteePrice());
                bo2.setShiHouseNumber(list3.get(i).getShiHouseNumber());
                bo2.setShiIsElevactor(list3.get(i).getShiIsElevactor());
                bo2.setShiLc(list3.get(i).getShiLc());
                bo2.setShiLocation(list3.get(i).getShiLocation());
                bo2.setShiName(list3.get(i).getShiName());
                bo2.setShiPrice(list3.get(i).getShiPrice());
                bo2.setShiSpace(list3.get(i).getShiSpace());
                bo2.setShiSpace1(list3.get(i).getShiSpace1());
                bo2.setShiSpace2(list3.get(i).getShiSpace2());
                bo2.setShiStartPrice(list3.get(i).getShiStartPrice());
                bo2.setShiStartPrice1(list3.get(i).getShiStartPrice1());
                bo2.setShiStartPrice2(list3.get(i).getShiStartPrice2());
                bo2.setShiTudi(list3.get(i).getShiTudi());
                bo2.setShiUrl(list3.get(i).getShiUrl());
                bo2.setSort(list3.get(i).getSort());
                bo2.setUserId(userId);
                this.shd.addStopHouseCopy1(bo2);
            }
            map.put("rows", list2);
            map.put("total", page.getTotalRecord());
        } else {
            Page page = new Page(bo);
            List<houseTableBo> list = this.shd.showStopHouse(page);
            map.put("rows", list);
            map.put("total", page.getTotalRecord());
        }
        return map;
    }

    /**
     * 导入
     *
     * @param file1
     * @param bo
     * @param request
     * @return
     */
    public Map<String, Object> addStopFyExcel(MultipartFile[] file1, houseTableBo bo, HttpServletRequest request)
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

            Integer add = this.shd.addStopHouse(bo);
            map.put("add", add);
        }
        return map;
    }


    /**
     * 导出
     *
     * @param bo
     * @param response
     * @return
     */
    public Map<String, Object> exportStopHouse(houseTableBo bo, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filePath = "C:\\Users\\Administrator\\Desktop\\预计结束房源表.xls";
        List<houseTableBo> list = this.shd.exportStopHouse(bo);
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
        rows.createCell(10).setCellValue("市场价格");
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
            rows.createCell(10).setCellValue(list.get(i).getShiConstruction()); // 市场价格
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
     * 房源详情
     *
     * @param bo
     * @return
     */
    public Map<String, Object> showStopFyXq(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("showStopFyXq", this.shd.showStopFyXq(bo));
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
    public Map<String, Object> exportStopHouseDq(houseTableBo bo, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filePath = "C:\\Users\\Administrator\\Desktop\\当前结束房源表.xls";
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        List<houseTableBo> list = this.shd.exportStopHouseDq(bo);
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

}
