package com.wyzc.htgl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo2;
import com.wyzc.htgl.bo.WyzcPersonnelTestBo;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.dao.WyzcPersonnelDao;

@Service
public class WyzcPersonnelService {
    @Autowired
    private WyzcPersonnelDao wpd;

    public Map<String, Object> showDepartureData(WyzcPersonnelBo bo, WyzcPersonnelBo2 bo2) {
        if (bo.getSort() == "postponeTime") {
            bo.setSort("postpone_time");
        } else if (bo.getSort() == "startWorkTime") {
            bo.setSort("start_work_time");
        } else if (bo.getSort() == "dimissionTime") {
            bo.setSort("dimission_time");
        } else if (bo.getSort() == "workAge") {
            bo.setSort("work_age");
        } else if (bo.getSort() == "birDate") {
            bo.setSort("bir_date");
        } else if (bo.getSort() == "age") {
            bo.setSort("age");
        }
        Map<String, Object> map = new HashMap<>();
        if (bo.getSearchMo() != null || bo.getSearchMo() != ""
                || bo.getAge1() != null || bo.getAge1() != ""
                || bo.getAge2() != null || bo.getAge2() != ""
                || bo.getWorkAge1() != null || bo.getWorkAge1() != ""
                || bo.getWorkAge2() != null || bo.getWorkAge2() != ""
                || bo.getPostponeTime1() != null || bo.getPostponeTime1() != ""
                || bo.getPostponeTime2() != null || bo.getPostponeTime2() != ""
                || bo.getStartWorkTime1() != null || bo.getStartWorkTime1() != ""
                || bo.getStartWorkTime2() != null || bo.getStartWorkTime2() != ""
                || bo.getNation() != null || bo.getNation() != ""
                || bo.getRegistered() != null || bo.getRegistered() != ""
                || bo.getEducation() != null || bo.getEducation() != ""
                || bo.getUserName() != null || bo.getUserName() != ""
                || bo.getWpName() != null || bo.getWpName() != ""
                || bo.getPostName() != null || bo.getPostName() != ""
                || bo.getWdName() != null || bo.getWdName() != ""
                || bo.getIsOfficial() != null || bo.getIsOfficial() != ""
                || bo.getContractType() != null || bo.getContractType() != "") {
            String userId = CommonFunction.getUserFromSession().getUserId();//获取当前用户名
            List<WyzcPersonnelBo> list = this.wpd.showDepartureData1(bo);
            bo2.setUserId(userId);
            this.wpd.deleteDepartureDataSerche(bo2);
            this.wpd.deleteDepartureDataExportHouse(bo2);
            for (int i = 0; i < list.size(); i++) {
                bo2.setAge(list.get(i).getAge());
                bo2.setAge1(list.get(i).getAge1());
                bo2.setAge2(list.get(i).getAge2());
                bo2.setBirDate(list.get(i).getBirDate());
                bo2.setBirMounth(list.get(i).getBirMounth());
                bo2.setBirYear(list.get(i).getBirYear());
                bo2.setContactTel(list.get(i).getContactTel());
                bo2.setContractType(list.get(i).getContractType());
                bo2.setDimissionTime(list.get(i).getDimissionTime());
                bo2.setEducation(list.get(i).getEducation());
                bo2.setIdentityCard(list.get(i).getIdentityCard());
                bo2.setIsOfficial(list.get(i).getIsOfficial());
                bo2.setIsPostpone(list.get(i).getIsPostpone());
                bo2.setNation(list.get(i).getNation());
                bo2.setOrder(list.get(i).getOrder());
                bo2.setPage(list.get(i).getPage());
                bo2.setFileNumber(list.get(i).getFileNumber());
                bo2.setPostName(list.get(i).getPostName());
                bo2.setPostponeNumber(list.get(i).getPostponeNumber());
                bo2.setPostponeTime(list.get(i).getPostponeTime());
                bo2.setPostponeTime1(list.get(i).getPostponeTime1());
                bo2.setPostponeTime2(list.get(i).getPostponeTime2());
                bo2.setPostponeTo(list.get(i).getPostponeTo());
                bo2.setPracticeEnd(list.get(i).getPracticeEnd());
                bo2.setPracticeNumber(list.get(i).getPracticeNumber());
                bo2.setPracticeStart(list.get(i).getPracticeStart());
                bo2.setRegistered(list.get(i).getRegistered());
                bo2.setRemark(list.get(i).getRemark());
                bo2.setRows(list.get(i).getRows());
                bo2.setSearchMo(list.get(i).getSearchMo());
                bo2.setSign(list.get(i).getSign());
                bo2.setSort(list.get(i).getSort());
                bo2.setStartWorkTime(list.get(i).getStartWorkTime());
                bo2.setStartWorkTime1(list.get(i).getStartWorkTime1());
                bo2.setStartWorkTime2(list.get(i).getStartWorkTime2());
                bo2.setTurnOfficialTime(list.get(i).getTurnOfficialTime());
                bo2.setUserIdCardAddress(list.get(i).getUserIdCardAddress());
                bo2.setUserName(list.get(i).getUserName());
                bo2.setUserSex(list.get(i).getUserSex());
                bo2.setWdId(list.get(i).getWdId());
                bo2.setWdName(list.get(i).getWdName());
                bo2.setWorkAge(list.get(i).getWorkAge());
                bo2.setWorkAge1(list.get(i).getWorkAge1());
                bo2.setWorkAge2(list.get(i).getWorkAge2());
                bo2.setWpId(list.get(i).getWpId());
                bo2.setWpName(list.get(i).getWpName());
                bo2.setUserId(userId);
                this.wpd.addDepartureData(bo2);
            }
            bo2.setUserId(userId);
            bo2.setPostponeTime1(bo.getPostponeTime1());
            bo2.setPostponeTime2(bo.getPostponeTime2());
            bo2.setStartWorkTime1(bo.getStartWorkTime1());
            bo2.setStartWorkTime2(bo.getStartWorkTime2());
            bo2.setWorkAge1(bo.getWorkAge1());
            bo2.setWorkAge2(bo.getWorkAge2());
            bo2.setAge1(bo.getAge1());
            bo2.setAge2(bo.getAge2());
            bo2.setNation1(bo.getNation());//民族
            bo2.setRegistered1(bo.getRegistered());//户口
            bo2.setEducation1(bo.getEducation());
            bo2.setWpName1(bo.getWpName());
            bo2.setEducation1(bo.getEducation());
            bo2.setUserName1(bo.getUserName());
            bo2.setPostName1(bo.getPostName());
            bo2.setWdName1(bo.getWdName());
            bo2.setIsOfficial1(bo.getIsOfficial());
            bo2.setContractType1(bo.getContractType());
            Page page = new Page(bo2);
            List<WyzcPersonnelBo2> list3 = this.wpd.searchDepartureData1(bo2);
            List<WyzcPersonnelBo2> list2 = this.wpd.searchDepartureData(bo2);
            for (int i = 0; i < list3.size(); i++) {
                bo2.setFileNumber(list3.get(i).getFileNumber());
                bo2.setAge(list3.get(i).getAge());
                bo2.setAge1(list3.get(i).getAge1());
                bo2.setAge2(list3.get(i).getAge2());
                bo2.setBirDate(list3.get(i).getBirDate());
                bo2.setBirMounth(list3.get(i).getBirMounth());
                bo2.setBirYear(list3.get(i).getBirYear());
                bo2.setContactTel(list3.get(i).getContactTel());
                bo2.setContractType(list3.get(i).getContractType());
                bo2.setDimissionTime(list3.get(i).getDimissionTime());
                bo2.setEducation(list3.get(i).getEducation());
                bo2.setIdentityCard(list3.get(i).getIdentityCard());
                bo2.setIsOfficial(list3.get(i).getIsOfficial());
                bo2.setIsPostpone(list3.get(i).getIsPostpone());
                bo2.setNation(list3.get(i).getNation());
                bo2.setOrder(list3.get(i).getOrder());
                bo2.setPage(list3.get(i).getPage());
                bo2.setPostName(list3.get(i).getPostName());
                bo2.setPostponeNumber(list3.get(i).getPostponeNumber());
                bo2.setPostponeTime(list3.get(i).getPostponeTime());
                bo2.setPostponeTime1(list3.get(i).getPostponeTime1());
                bo2.setPostponeTime2(list3.get(i).getPostponeTime2());
                bo2.setPostponeTo(list3.get(i).getPostponeTo());
                bo2.setPracticeEnd(list3.get(i).getPracticeEnd());
                bo2.setPracticeNumber(list3.get(i).getPracticeNumber());
                bo2.setPracticeStart(list3.get(i).getPracticeStart());
                bo2.setRegistered(list3.get(i).getRegistered());
                bo2.setRemark(list3.get(i).getRemark());
                bo2.setRows(list3.get(i).getRows());
                bo2.setSearchMo(list3.get(i).getSearchMo());
                bo2.setSign(list3.get(i).getSign());
                bo2.setSort(list3.get(i).getSort());
                bo2.setStartWorkTime(list3.get(i).getStartWorkTime());
                bo2.setStartWorkTime1(list3.get(i).getStartWorkTime1());
                bo2.setStartWorkTime2(list3.get(i).getStartWorkTime2());
                bo2.setTurnOfficialTime(list3.get(i).getTurnOfficialTime());
                bo2.setUserIdCardAddress(list3.get(i).getUserIdCardAddress());
                bo2.setUserName(list3.get(i).getUserName());
                bo2.setUserSex(list3.get(i).getUserSex());
                bo2.setWdId(list3.get(i).getWdId());
                bo2.setWdName(list3.get(i).getWdName());
                bo2.setWorkAge(list3.get(i).getWorkAge());
                bo2.setWorkAge1(list3.get(i).getWorkAge1());
                bo2.setWorkAge2(list3.get(i).getWorkAge2());
                bo2.setWpId(list3.get(i).getWpId());
                bo2.setWpName(list3.get(i).getWpName());
                bo2.setUserId(userId);
                bo2.setSort(list3.get(i).getSort());
                this.wpd.addExportDepartureData(bo2);
            }
            map.put("rows", list2);
            map.put("total", page.getTotalRecord());
        } else {
            Page page = new Page(bo);
            List<WyzcPersonnelBo> list = this.wpd.showDepartureData(page);
            map.put("rows", list);
            map.put("total", page.getTotalRecord());
        }
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
     * 部门
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchBm(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        List<WyzcPersonnelBo> list = this.wpd.searchBm(bo);
        Iterator<WyzcPersonnelBo> it = list.iterator();
        while (it.hasNext()) {
            WyzcPersonnelBo wpb = it.next();
            if (wpb.getWpId().equals("01")) {
                it.remove();
            }
        }
        map.put("searchBm", list);
        return map;
    }


    public Map<String, Object> exportPersonDq(WyzcPersonnelBo bo, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filePath = "C:\\Users\\Administrator\\Desktop\\当前离职员工表.xls";
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        List<WyzcPersonnelBo> list = this.wpd.exportPersonDq(bo);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet();
        HSSFRow rows = sheet1.createRow(0);
        // rows.createCell(0).setCellValue("优质指数");
        rows.createCell(1).setCellValue("所属部门");
        rows.createCell(2).setCellValue("岗位名称");
        rows.createCell(3).setCellValue("岗位级别");
        rows.createCell(4).setCellValue("姓名");
        rows.createCell(5).setCellValue("性别");
        rows.createCell(6).setCellValue("身份证号");
        rows.createCell(7).setCellValue("试岗期");
        rows.createCell(8).setCellValue("到岗日期");
        rows.createCell(9).setCellValue("离职日期");
        rows.createCell(10).setCellValue("工龄");
        rows.createCell(11).setCellValue("出生日期");
        rows.createCell(12).setCellValue("年龄");
        rows.createCell(13).setCellValue("民族");
        rows.createCell(14).setCellValue("户口");
        rows.createCell(15).setCellValue("学历");
        rows.createCell(16).setCellValue("联系电话");
        rows.createCell(17).setCellValue("正式/试用");
        rows.createCell(18).setCellValue("合同类别");
        rows.createCell(19).setCellValue("身份证地址");
        rows.createCell(20).setCellValue("备注");
        for (int i = 0; i < list.size(); i++) {
            rows = sheet1.createRow(i + 1);
            rows.createCell(1).setCellValue(list.get(i).getWpName()); //所属部门
            rows.createCell(2).setCellValue(list.get(i).getPostName()); //岗位名称
            rows.createCell(3).setCellValue(list.get(i).getWdName()); //岗位级别
            rows.createCell(4).setCellValue(list.get(i).getUserName());//姓名
            rows.createCell(5).setCellValue(list.get(i).getUserSex());//性别
            rows.createCell(6).setCellValue(list.get(i).getIdentityCard());// 身份证号
            rows.createCell(7).setCellValue(list.get(i).getPostponeTime());// 试岗期
            rows.createCell(8).setCellValue(list.get(i).getStartWorkTime());// 到岗日期
            rows.createCell(9).setCellValue(list.get(i).getDimissionTime());// 离职日期
            rows.createCell(10).setCellValue(list.get(i).getWorkAge()); // 工龄
            rows.createCell(11).setCellValue(list.get(i).getBirDate());// 出生日期
            rows.createCell(12).setCellValue(list.get(i).getAge());// 年龄
            rows.createCell(13).setCellValue(list.get(i).getNation());// 民族
            rows.createCell(14).setCellValue(list.get(i).getRegistered());//户口 
            rows.createCell(15).setCellValue(list.get(i).getEducation());//学历
            rows.createCell(16).setCellValue(list.get(i).getContactTel());// 联系电话
            rows.createCell(17).setCellValue(list.get(i).getIsOfficial());// 正式/试用
            rows.createCell(18).setCellValue(list.get(i).getContractType());// 合同类别
            rows.createCell(19).setCellValue(list.get(i).getUserIdCardAddress());// 身份证地址
            rows.createCell(20).setCellValue(list.get(i).getRemark());// 备注
            FileOutputStream out = new FileOutputStream(filePath);
            wb.write(out);
            out.close();
        }
        down(filePath, response);// 调用方法
        map.put("row", list);
        return map;
    }


    public Map<String, Object> exportPerson(WyzcPersonnelBo bo, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filePath = "C:\\Users\\Administrator\\Desktop\\当前离职员工表.xls";
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        List<WyzcPersonnelBo> list = this.wpd.exportPerson(bo);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet();
        HSSFRow rows = sheet1.createRow(0);
        rows.createCell(1).setCellValue("所属部门");
        rows.createCell(2).setCellValue("岗位名称");
        rows.createCell(3).setCellValue("岗位级别");
        rows.createCell(4).setCellValue("姓名");
        rows.createCell(5).setCellValue("性别");
        rows.createCell(6).setCellValue("身份证号");
        rows.createCell(7).setCellValue("试岗期");
        rows.createCell(8).setCellValue("到岗日期");
        rows.createCell(9).setCellValue("离职日期");
        rows.createCell(10).setCellValue("工龄");
        rows.createCell(11).setCellValue("出生日期");
        rows.createCell(12).setCellValue("年龄");
        rows.createCell(13).setCellValue("民族");
        rows.createCell(14).setCellValue("户口");
        rows.createCell(15).setCellValue("学历");
        rows.createCell(16).setCellValue("联系电话");
        rows.createCell(17).setCellValue("正式/试用");
        rows.createCell(18).setCellValue("合同类别");
        rows.createCell(19).setCellValue("身份证地址");
        rows.createCell(20).setCellValue("备注");
        for (int i = 0; i < list.size(); i++) {
            rows = sheet1.createRow(i + 1);
            rows.createCell(1).setCellValue(list.get(i).getWpName()); //所属部门
            rows.createCell(2).setCellValue(list.get(i).getPostName()); //岗位名称
            rows.createCell(3).setCellValue(list.get(i).getWdName()); //岗位级别
            rows.createCell(4).setCellValue(list.get(i).getUserName());//姓名
            rows.createCell(5).setCellValue(list.get(i).getUserSex());//性别
            rows.createCell(6).setCellValue(list.get(i).getIdentityCard());// 身份证号
            rows.createCell(7).setCellValue(list.get(i).getPostponeTime());// 试岗期
            rows.createCell(8).setCellValue(list.get(i).getStartWorkTime());// 到岗日期
            rows.createCell(9).setCellValue(list.get(i).getDimissionTime());// 离职日期
            rows.createCell(10).setCellValue(list.get(i).getWorkAge()); // 工龄
            rows.createCell(11).setCellValue(list.get(i).getBirDate());// 出生日期
            rows.createCell(12).setCellValue(list.get(i).getAge());// 年龄
            rows.createCell(13).setCellValue(list.get(i).getNation());// 民族
            rows.createCell(14).setCellValue(list.get(i).getRegistered());//户口 
            rows.createCell(15).setCellValue(list.get(i).getEducation());//学历
            rows.createCell(16).setCellValue(list.get(i).getContactTel());// 联系电话
            rows.createCell(17).setCellValue(list.get(i).getIsOfficial());// 正式/试用
            rows.createCell(18).setCellValue(list.get(i).getContractType());// 合同类别
            rows.createCell(19).setCellValue(list.get(i).getUserIdCardAddress());// 身份证地址
            rows.createCell(20).setCellValue(list.get(i).getRemark());// 备注
            FileOutputStream out = new FileOutputStream(filePath);
            wb.write(out);
            out.close();
        }
        down(filePath, response);// 调用方法
        map.put("row", list);
        return map;
    }


    public Map<String, Object> deletePerson(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("deletePerson", this.wpd.deletePerson(bo));
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


    public Map<String, Object> searchDepartureQx(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        List<WyzcPersonnelBo> list = this.wpd.searchDepartureQx(bo);
        map.put("searchContractQx", list);
        return map;
    }
}
