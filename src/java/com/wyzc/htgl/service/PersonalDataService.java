package com.wyzc.htgl.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.PersonalDataBo;
import com.wyzc.htgl.dao.PersonalDataDao;

/**
 * 个人资料服务层
 *
 * @author Administrator
 */
@Service
public class PersonalDataService {
    @Autowired
    private PersonalDataDao pdd;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchPersonalData(PersonalDataBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //获取登陆id
        bo.setUserId(CommonFunction.getUserFromSession().getUserId());
        Page page = new Page(bo);
        List<PersonalDataBo> list = this.pdd.searchPersonalData(page);
        for (PersonalDataBo pdb : list) {
            pdb.setHouseFloor(pdb.getHidFloorNow() + "/" + pdb.getHidFloorAll());
            pdb.setHouseType(pdb.getHidRoom() + "室" + pdb.getHidHall() + "厅" + pdb.getHidKitchen() + "厨" + pdb.getHidToilet() + "卫");
        }
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }


    /**
     * 录入房源
     *
     * @param bo
     * @param hidFirstPic
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public Map<String, Object> enteringHouseData(PersonalDataBo bo, @RequestParam MultipartFile[] fileImg,
                                                 HttpServletRequest re) throws IllegalStateException, IOException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //获取登陆id
        bo.setUserId(CommonFunction.getUserFromSession().getUserId());
        String strType = fileImg[0].getContentType();
        System.out.print(strType);
        if (strType.equals("png") || strType.equals("jpg") || strType.equals("gif") || strType.equals("jpeg") ||
                strType.equals("image/jpeg") || strType.equals("image/png")) {
            String upPath = re.getServletContext().getRealPath("/") + "view\\wyzc\\img\\" + new Date().getTime() + fileImg[0].getOriginalFilename();
            fileImg[0].transferTo(new File(upPath));
            bo.setHidFirstPic(upPath);
            //将数据增加进主表中
            Integer result = this.pdd.enteringHouseData(bo);
            //将数据增加近备份表中
            this.pdd.enteringHouseDataToBackup(bo);
            map.put("result", result);
        } else {
            map.put("result", "pic");
        }

        return map;
    }


    /**
     * 获取图片地址
     *
     * @param bo
     * @return
     */
    public Map<String, Object> getImgUrl(PersonalDataBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String imgUrl = this.pdd.getImgUrl(bo);
        String[] str = imgUrl.split("\\\\");
        map.put("imgUrl", str[6] + "/" + str[7]);
        return map;
    }


    /**
     * 查单条数据
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchModifyPersonal(PersonalDataBo bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchModifyPersonal", this.pdd.searchModifyPersonal(bo));
        return map;
    }


    /**
     * 提交修改信息
     *
     * @param bo
     * @param fileImg
     * @param re
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public Map<String, Object> modifyPersonal(PersonalDataBo bo, MultipartFile[] fileImg, HttpServletRequest re) throws IllegalStateException, IOException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        bo.setUserId(CommonFunction.getUserFromSession().getUserId());
        String strType = fileImg[0].getContentType();
        if (strType.equals("png") || strType.equals("jpg") || strType.equals("gif") || strType.equals("jpeg") ||
                strType.equals("image/jpeg") || strType.equals("image/png")) {
            String upPath = re.getServletContext().getRealPath("/") + "view\\wyzc\\img\\" + new Date().getTime() + fileImg[0].getOriginalFilename();
            fileImg[0].transferTo(new File(upPath));
            bo.setHidFirstPic(upPath);
            Integer result = this.pdd.modifyPersonal(bo);
            map.put("result", result);
        } else {
            map.put("result", "pic");
        }

        return map;
    }


    /**
     * 删除信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> deletePersonalData(PersonalDataBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.pdd.deletePersonalData(bo);
        map.put("result", result);
        return map;
    }


    public Map<String, Object> doTry(HttpServletRequest re) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        System.out.println(re.getSession().getAttribute("sss"));
        if (re.getSession().getAttribute("sss") == null) {
            System.out.print("第一次");
            HttpSession session = re.getSession();
            session.setAttribute("sss", 0);
            session.setMaxInactiveInterval(5);
            //session.invalidate();
            map.put("result", "1");
        } else {
            System.out.print("第n次");
            map.put("result", "n");
        }
        return map;
    }


}
