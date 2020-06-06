package com.wyzc.htgl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcPersonnelTestBo;
import com.wyzc.htgl.dao.WyzcPersonnelTestDao;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


@Service
public class WyzcPersonnelTestService {
    @Autowired
    private WyzcPersonnelTestDao wpd;
    private List<WyzcPersonnelTestBo> someList;

    /**
     * 查询所有部门
     *
     * @return
     */
    public Map<String, Object> loadDataToSelect() {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //查询所有部门
        List<WyzcPersonnelTestBo> parts = this.wpd.searchAllPart();
        Iterator<WyzcPersonnelTestBo> it = parts.iterator();
        while (it.hasNext()) {
            WyzcPersonnelTestBo wpb = it.next();
            if (wpb.getWpName().equals("总经办")) {
                it.remove();
            }
        }

        //查询所有岗位等级
        List<WyzcPersonnelTestBo> dutys = this.wpd.searchAllDuty();
        map.put("parts", parts);
        map.put("dutys", dutys);
        return map;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public Map<String, Object> searchPersonnelTestData(WyzcPersonnelTestBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Page page = new Page(bo);
        List<WyzcPersonnelTestBo> list = this.wpd.searchPersonnelTestData(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 增加员工信息
     *
     * @param bo
     * @return
     * @throws ParseException
     */
    public Map<String, Object> savePersonnelTestInfo(WyzcPersonnelTestBo bo) throws ParseException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = 0;
        bo.setTurnOfficialTime(null);
        if (bo.getIsPostpone().equals("")) {
            bo.setIsPostpone("非延期");
        }
        /**
         * 根据所填写的身份证号码，生成对应的出生年月日和年龄
         */
        String identityCard = bo.getIdentityCard();
        String birthday = identityCard.substring(6, 14);//出生年月日截取
        String year = birthday.substring(0, 4);//传入的年
        bo.setBirYear(year);
        String month = birthday.substring(4, 6);//传入的月
        String day = birthday.substring(6, 8);//传入的日
        int yearMonth = Integer.parseInt(month + "" + day);//合成传入身份证的月和日
        bo.setBirMounth(String.valueOf(month));
        String date = new Date().toLocaleString().replaceAll("-", "").substring(0, 8); //获取本地时间并截取年月日替换掉"-"
        int LocationYearMonth = 0;
        if (date.indexOf(8) != -1) {//判断截取长度
            LocationYearMonth = Integer.parseInt((String) date.substring(4, 8));
        } else {
            LocationYearMonth = Integer.parseInt((String) date.substring(4, 7).trim());
        }
        int LocationYear = Integer.parseInt((String) date.substring(0, 4));//当前年
        int yearInt = Integer.parseInt((String) birthday.substring(0, 4));//传入的身份证年份转换
        int age = 0;
        if (LocationYearMonth - yearMonth > 0) {//判断月份是否大小于当前日期
            age = LocationYear - yearInt;
        } else {
            age = LocationYear - yearInt;//小于0就虚岁-1
        }
        bo.setAge(String.valueOf(age));
        /**
         * 计算试岗结束时间
         */

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = bo.getPracticeStart();// 获取试岗开始时间
        Date dt = sdf.parse(str);//将试岗开始时间的值由字符转成时间格式
        Calendar rightNow = Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
        rightNow.setTime(dt); //设置时间
        rightNow.add(Calendar.DAY_OF_YEAR, Integer.valueOf(bo.getPracticeNumber()));//试岗开始时间加上试岗的天数
        Date practiceEnd = rightNow.getTime();//获取试岗结束时间
        String practiceTimeStr = sdf.format(practiceEnd); // 将试岗结束时间转成字符格式
        bo.setPracticeEnd(practiceTimeStr);

        /**
         *  计算试岗延期结束时间
         */
        if (bo.getIsPostpone().equals("延期")) {
            Integer sumDay =
                    Integer.valueOf(bo.getPracticeNumber())
                            + Integer.valueOf(bo.getPostponeNumber());// 获取试岗天数和延期天数的和
            Calendar postponeNow = Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
            postponeNow.setTime(dt);
            postponeNow.add(Calendar.DAY_OF_YEAR, sumDay); //试岗开始日期加上相应的天数
            Date postponeTime = postponeNow.getTime();// 获取延期结束时间
            String postponeTimeStr = sdf.format(postponeTime);
            bo.setPostponeTo(postponeTimeStr);
        }

        /**
         *  计算工龄

         bo.setStartWorkTime("2019-08-23");
         if(bo.getStartWorkTime()!=null || bo.getStartWorkTime()!="") {
         Calendar nowCld = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
         Calendar pastCld = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
         Date nowTime = new Date(); //获取当前时间
         nowCld.setTime(nowTime); // 当前时间
         pastCld.setTime(sdf.parse(bo.getStartWorkTime()));//到岗日期
         int betweenDays = nowCld.get(Calendar.DAY_OF_YEAR)-pastCld.get(Calendar.DAY_OF_YEAR);
         DecimalFormat df = new DecimalFormat("0.0");//格式化小数
         String workAge = df.format((float) betweenDays/365);//返回的是String类型
         bo.setWorkAge(String.valueOf(workAge));
         }
         */
        bo.setBirDate(year + "-" + month + "-" + day);//出生日期
        result = this.wpd.savePersonnelTestInfoNoPostpone(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 根据员工编号查询员工信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchPersonnelByUserId(WyzcPersonnelTestBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        System.out.println(bo.getUserId());
        WyzcPersonnelTestBo wpb = this.wpd.searchPersonnelByUserId(bo);
        map.put("result", wpb);
        return map;
    }

    /**
     * 试岗员工信息修改保存
     *
     * @param bo
     * @return
     * @throws ParseException
     */
    public Map<String, Object> saveModifyTestPersonnel(WyzcPersonnelTestBo bo) throws ParseException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        /**
         * 根据所填写的身份证号码，生成对应的出生年月日和年龄
         */
        if (!bo.getIdentityCard().equals("")) {
            String identityCard = bo.getIdentityCard();
            String birthday = identityCard.substring(6, 14);//出生年月日截取
            String year = birthday.substring(0, 4);//传入的年
            bo.setBirYear(year);
            String month = birthday.substring(4, 6);//传入的月
            String day = birthday.substring(6, 8);//传入的日
            int yearMonth = Integer.parseInt(month + "" + day);//合成传入身份证的月和日
            bo.setBirMounth(String.valueOf(month));
            String date = new Date().toLocaleString().replaceAll("-", "").substring(0, 8); //获取本地时间并截取年月日替换掉"-"
            int LocationYearMonth = 0;
            if (date.indexOf(8) != -1) {//判断截取长度
                LocationYearMonth = Integer.parseInt((String) date.substring(4, 8));
            } else {
                LocationYearMonth = Integer.parseInt((String) date.substring(4, 7).trim());
            }
            int LocationYear = Integer.parseInt((String) date.substring(0, 4));//当前年
            int yearInt = Integer.parseInt((String) birthday.substring(0, 4));//传入的身份证年份转换
            int age = 0;
            if (LocationYearMonth - yearMonth > 0) {//判断月份是否大小于当前日期
                age = LocationYear - yearInt;
            } else {
                age = LocationYear - yearInt;//小于0就虚岁-1
            }
            bo.setAge(String.valueOf(age));
            bo.setBirDate(year + "-" + month + "-" + day);//出生日期
        }

        /**
         * 计算试岗结束时间
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        if (!bo.getPracticeStart().equals("")) {
            String str = bo.getPracticeStart();// 获取试岗开始时间
            dt = sdf.parse(str);//将试岗开始时间的值由字符转成时间格式
            Calendar rightNow = Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
            rightNow.setTime(dt); //设置时间
            rightNow.add(Calendar.DAY_OF_YEAR, Integer.valueOf(bo.getPracticeNumber()));//试岗开始时间加上试岗的天数
            Date practiceEnd = rightNow.getTime();//获取试岗结束时间
            String practiceTimeStr = sdf.format(practiceEnd); // 将试岗结束时间转成字符格式
            bo.setPracticeEnd(practiceTimeStr);
        }


        /**
         *  计算试岗延期结束时间
         */
        if (!bo.getIsPostpone().equals("")) {
            Integer sumDay =
                    Integer.valueOf(bo.getPracticeNumber())
                            + Integer.valueOf(bo.getPostponeNumber());// 获取试岗天数和延期天数的和
            Calendar postponeNow = Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
            postponeNow.setTime(dt);
            postponeNow.add(Calendar.DAY_OF_YEAR, sumDay); //试岗开始日期加上相应的天数
            Date postponeTime = postponeNow.getTime();// 获取延期结束时间
            String postponeTimeStr = sdf.format(postponeTime);
            bo.setPostponeTo(postponeTimeStr);
        }


        Integer result = this.wpd.saveModifyTestPersonnel(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 在职员工修改保存
     *
     * @param bo
     * @return
     */
    public Map<String, Object> saveModiftyOfficialPersonnel(WyzcPersonnelTestBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        bo.setSign("2");
        Integer result = this.wpd.saveModifyTestPersonnel(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 离职员工修改保存
     *
     * @param bo
     * @return
     */
    public Map<String, Object> saveModifyLeavePersonnel(WyzcPersonnelTestBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        bo.setSign("3");
        Integer result = this.wpd.saveModifyLeavePersonnel(bo);
        this.wpd.deletePersonnelByUserId(bo);
        this.wpd.deletePersonnelByUserIdFromRole(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 将数据存到Excel表中
     *
     * @param arr
     * @param re
     * @return
     * @throws RowsExceededException
     * @throws WriteException
     * @throws IOException
     */

    public Map<String, Object> exportSome(String arr, HttpServletResponse re) throws RowsExceededException, WriteException, IOException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        someList = JSON.parseArray(arr, WyzcPersonnelTestBo.class);
        map.put("result", "1");
        return map;
    }

    /**
     * 下载Excel表
     *
     * @param re
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    public void startExportSome(HttpServletResponse re) throws IOException, RowsExceededException, WriteException {
        // TODO Auto-generated method stub
        String filePath = "C:\\Users\\Administrator\\Desktop\\试岗部分员工信息" + System.currentTimeMillis() + ".xls";//设置指定保存位置
        WritableWorkbook workbook = Workbook.createWorkbook(new File(filePath));
        /**
         * 直接下载，不需要保存到指定位置
         *
         1.文件下载响应头
         re.setContentType("application/x-msdownload");
         String excelName = System.currentTimeMillis() + "_"+"试岗员工信息";
         2.响应到浏览器
         re.setHeader
         ("Content-Disposition", "attachment;filename=\""+new String(excelName.getBytes("utf-8"),"ISO-8859-1")+".xls"+"\"");
         3.获取输出流
         OutputStream output=ree.getOutputStream();
         4.创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
         WritableWorkbook wk=Workbook.createWorkbook(output);
         *
         */
        // 创建工作簿sheet
        WritableSheet sheet = workbook.createSheet("sheet01", 0);
        // 3.设置column名
        sheet.addCell(new Label(0, 0, "用户姓名"));//userName;
        sheet.addCell(new Label(1, 0, "身份证号码 "));//identityCard;
        sheet.addCell(new Label(2, 0, "身份证地址")); //;
        sheet.addCell(new Label(3, 0, "所属部门")); //wpId;
        sheet.addCell(new Label(4, 0, "所属职务"));//wdId;
        sheet.addCell(new Label(5, 0, "岗位名称"));//postName
        sheet.addCell(new Label(6, 0, "性别"));// userSex;
        sheet.addCell(new Label(7, 0, "试岗开始时间")); //practiceStart;
        sheet.addCell(new Label(8, 0, "试岗天数")); //practiceNumber;
        sheet.addCell(new Label(9, 0, "试岗结束时间"));// practiceEnd;
        sheet.addCell(new Label(10, 0, "是否延期")); //isPostpone
        sheet.addCell(new Label(11, 0, "延期天数")); //postponeNumber
        sheet.addCell(new Label(12, 0, "试岗延期至"));//postponeTo;
        sheet.addCell(new Label(13, 0, "试岗期"));//postponeTime;
        sheet.addCell(new Label(14, 0, "到岗日期"));//startWorkTime;
        sheet.addCell(new Label(15, 0, "转正日期"));//turnOfficialTime;
        sheet.addCell(new Label(16, 0, "离职日期")); //dimissionTime;
        sheet.addCell(new Label(17, 0, "工龄"));// workAge;
        sheet.addCell(new Label(18, 0, "出生日期"));//birDate;
        sheet.addCell(new Label(19, 0, "出生年份")); //birYear;
        sheet.addCell(new Label(20, 0, "生日月份"));//birMounth;
        sheet.addCell(new Label(21, 0, "年龄"));//age;
        sheet.addCell(new Label(22, 0, "民族")); //nation;
        sheet.addCell(new Label(23, 0, "户口"));//registered;
        sheet.addCell(new Label(24, 0, "学历"));  //education;
        sheet.addCell(new Label(25, 0, "联系电话"));//contactTel;
        sheet.addCell(new Label(26, 0, "备注"));//remark;
        //循环写入信息
        for (int i = 0, j = 1; i < someList.size(); i++, j++) {
            WyzcPersonnelTestBo wpb = someList.get(i);
            sheet.addCell(new Label(0, j, wpb.getUserName()));//userName;
            sheet.addCell(new Label(1, j, wpb.getIdentityCard()));//identityCard;
            sheet.addCell(new Label(2, j, wpb.getUserIdCardAddress()));//身份证地址;
            sheet.addCell(new Label(3, j, wpb.getWpName())); //wpId;
            sheet.addCell(new Label(4, j, wpb.getWdName()));//wdId;
            sheet.addCell(new Label(5, j, wpb.getPostName()));//postName
            sheet.addCell(new Label(6, j, wpb.getUserSex()));// userSex;
            sheet.addCell(new Label(7, j, wpb.getPracticeStart())); //practiceStart;
            sheet.addCell(new Label(8, j, wpb.getPracticeNumber())); //practiceNumber;
            sheet.addCell(new Label(9, j, wpb.getPracticeEnd()));// practiceEnd;
            sheet.addCell(new Label(10, j, wpb.getIsPostpone())); //isPostpone
            sheet.addCell(new Label(11, j, wpb.getPostponeNumber())); //postponeNumber
            sheet.addCell(new Label(12, j, wpb.getPostponeTo()));//postponeTo;
            sheet.addCell(new Label(13, j, wpb.getPostponeTime()));//postponeTime;
            sheet.addCell(new Label(14, j, wpb.getStartWorkTime()));//startWorkTime;
            sheet.addCell(new Label(15, j, wpb.getTurnOfficialTime()));//turnOfficialTime;
            sheet.addCell(new Label(16, j, wpb.getDimissionTime())); //dimissionTime;
            sheet.addCell(new Label(17, j, wpb.getWorkAge()));// workAge;
            sheet.addCell(new Label(18, j, wpb.getBirDate()));//birDate;
            sheet.addCell(new Label(19, j, wpb.getBirYear())); //birYear;
            sheet.addCell(new Label(20, j, wpb.getBirMounth()));//birMounth;
            sheet.addCell(new Label(21, j, wpb.getAge()));//age;
            sheet.addCell(new Label(22, j, wpb.getNation())); //nation;
            sheet.addCell(new Label(23, j, wpb.getRegistered()));//registered;
            sheet.addCell(new Label(24, j, wpb.getEducation()));  //education;
            sheet.addCell(new Label(25, j, wpb.getContactTel()));//contactTel;
            sheet.addCell(new Label(26, j, wpb.getRemark()));//remark;
        }
        // 5.写入数据
        workbook.write();
        // 6.关闭资源
        workbook.close();
        //下载Excel文件
        down(filePath, re);

    }

    /**
     * 导出全部信息
     *
     * @return
     * @throws IOException
     * @throws WriteException
     * @throws RowsExceededException
     */
    public void exportAll(HttpServletResponse re) throws IOException, RowsExceededException, WriteException {
        // TODO Auto-generated method stub
        List<WyzcPersonnelTestBo> list = this.wpd.exportAll();
        String filePath = "C:\\Users\\Administrator\\Desktop\\试岗全部员工信息" + System.currentTimeMillis() + ".xls";//设置指定保存位置
        WritableWorkbook workbook = Workbook.createWorkbook(new File(filePath));
        // 创建工作簿sheet
        WritableSheet sheet = workbook.createSheet("sheet01", 0);
        // 3.设置column名
        sheet.addCell(new Label(0, 0, "用户姓名"));//userName;
        sheet.addCell(new Label(1, 0, "身份证号码 "));//identityCard;
        sheet.addCell(new Label(2, 0, "身份证地址")); //;
        sheet.addCell(new Label(3, 0, "所属部门")); //wpId;
        sheet.addCell(new Label(4, 0, "所属职务"));//wdId;
        sheet.addCell(new Label(5, 0, "岗位名称"));//postName
        sheet.addCell(new Label(6, 0, "性别"));// userSex;
        sheet.addCell(new Label(7, 0, "试岗开始时间")); //practiceStart;
        sheet.addCell(new Label(8, 0, "试岗天数")); //practiceNumber;
        sheet.addCell(new Label(9, 0, "试岗结束时间"));// practiceEnd;
        sheet.addCell(new Label(10, 0, "是否延期")); //isPostpone
        sheet.addCell(new Label(11, 0, "延期天数")); //postponeNumber
        sheet.addCell(new Label(12, 0, "试岗延期至"));//postponeTo;
        sheet.addCell(new Label(13, 0, "试岗期"));//postponeTime;
        sheet.addCell(new Label(14, 0, "到岗日期"));//startWorkTime;
        sheet.addCell(new Label(15, 0, "转正日期"));//turnOfficialTime;
        sheet.addCell(new Label(16, 0, "离职日期")); //dimissionTime;
        sheet.addCell(new Label(17, 0, "工龄"));// workAge;
        sheet.addCell(new Label(18, 0, "出生日期"));//birDate;
        sheet.addCell(new Label(19, 0, "出生年份")); //birYear;
        sheet.addCell(new Label(20, 0, "生日月份"));//birMounth;
        sheet.addCell(new Label(21, 0, "年龄"));//age;
        sheet.addCell(new Label(22, 0, "民族")); //nation;
        sheet.addCell(new Label(23, 0, "户口"));//registered;
        sheet.addCell(new Label(24, 0, "学历"));  //education;
        sheet.addCell(new Label(25, 0, "联系电话"));//contactTel;
        sheet.addCell(new Label(26, 0, "备注"));//remark;
        //循环写入信息
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            WyzcPersonnelTestBo wpb = list.get(i);
            sheet.addCell(new Label(0, j, wpb.getUserName()));//userName;
            sheet.addCell(new Label(1, j, wpb.getIdentityCard()));//identityCard;
            sheet.addCell(new Label(2, j, wpb.getUserIdCardAddress()));//身份证地址;
            sheet.addCell(new Label(3, j, wpb.getWpName())); //wpId;
            sheet.addCell(new Label(4, j, wpb.getWdName()));//wdId;
            sheet.addCell(new Label(5, j, wpb.getPostName()));//postName
            sheet.addCell(new Label(6, j, wpb.getUserSex()));// userSex;
            sheet.addCell(new Label(7, j, wpb.getPracticeStart())); //practiceStart;
            sheet.addCell(new Label(8, j, wpb.getPracticeNumber())); //practiceNumber;
            sheet.addCell(new Label(9, j, wpb.getPracticeEnd()));// practiceEnd;
            sheet.addCell(new Label(10, j, wpb.getIsPostpone())); //isPostpone
            sheet.addCell(new Label(11, j, wpb.getPostponeNumber())); //postponeNumber
            sheet.addCell(new Label(12, j, wpb.getPostponeTo()));//postponeTo;
            sheet.addCell(new Label(13, j, wpb.getPostponeTime()));//postponeTime;
            sheet.addCell(new Label(14, j, wpb.getStartWorkTime()));//startWorkTime;
            sheet.addCell(new Label(15, j, wpb.getTurnOfficialTime()));//turnOfficialTime;
            sheet.addCell(new Label(16, j, wpb.getDimissionTime())); //dimissionTime;
            sheet.addCell(new Label(17, j, wpb.getWorkAge()));// workAge;
            sheet.addCell(new Label(18, j, wpb.getBirDate()));//birDate;
            sheet.addCell(new Label(19, j, wpb.getBirYear())); //birYear;
            sheet.addCell(new Label(20, j, wpb.getBirMounth()));//birMounth;
            sheet.addCell(new Label(21, j, wpb.getAge()));//age;
            sheet.addCell(new Label(22, j, wpb.getNation())); //nation;
            sheet.addCell(new Label(23, j, wpb.getRegistered()));//registered;
            sheet.addCell(new Label(24, j, wpb.getEducation()));  //education;
            sheet.addCell(new Label(25, j, wpb.getContactTel()));//contactTel;
            sheet.addCell(new Label(26, j, wpb.getRemark()));//remark;
        }
        // 5.写入数据
        workbook.write();
        // 6.关闭资源
        workbook.close();
        //下载Excel文件
        down(filePath, re);

    }

    /**
     * 下载Excel文件
     */
    static void down(String url, HttpServletResponse re) {
        File file = new File(url);
        re.setCharacterEncoding("UTF-8");
        re.setContentType("multipart/form-data");
        try {// 设置乱码转字符串file.getName().getBytes("utf-8"),"iso-8859-1")
            re.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(file.getName().getBytes("utf-8"), "iso-8859-1"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            OutputStream out = re.getOutputStream();
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
     * 删除员工信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> removePersonnelByUerId(WyzcPersonnelTestBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.wpd.removePersonnelByUerId(bo);
        this.wpd.deletePersonnelByUserId(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 用户权限
     *
     * @param bo
     * @return
     */
    public Map<String, Object> userJurisdiction(WyzcPersonnelTestBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //获取当前登录用户ID
        String userId = CommonFunction.getUserFromSession().getUserId();
        if (userId.equals("admin")) {
            map.put("admin", "1");
        } else {
            bo.setUserId(userId);
            //查询当前登录用户的所属部门和岗位等级
            WyzcPersonnelTestBo wpb = this.wpd.userJurisdiction(bo);
            map.put("wpb", wpb);
        }
        return map;
    }


}
