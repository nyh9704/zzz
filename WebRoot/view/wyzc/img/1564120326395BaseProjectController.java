package com.huibo.issue.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bn.javax.dao.Page;
import com.huibo.issue.bo.BaseProjectBo;
import com.huibo.issue.bo.IssueWorkLogBo;
import com.huibo.issue.po.IssueAttach;
import com.huibo.issue.po.IssueWorkLogPo;
import com.huibo.issue.service.BaseProjectService;
/**
 * 
 * @author Administrator
 *
 */
@Controller
public class BaseProjectController {
	@Autowired
	private BaseProjectService baseProjectService;
	
	/*
	 * 分页查询
	 */
	@RequestMapping("/searchProjectData")
	public Map<String, Object> searchProjectData(String code,String exploit,String strStatus,String issueState,BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			bo.setCode(code);
			bo.setIssueState(issueState);
			bo.setExploit(exploit);
			map = this.baseProjectService.searchProjectData(bo); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 根据缺陷编号查询缺陷信息
	 */
	@RequestMapping("/searchProjectDataByIssueId")
	public Map<String, Object> searchProjectDataByIssueId(String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchProjectDataByIssueId(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	/*
	 * 查询项目名称
	 */
	@RequestMapping("/searchProjectNameData")
	public Map<String,Object> searchProjectNameData(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchProjectNameData(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 查询缺陷分类
	 */
	
	@RequestMapping("/searchIssueType")
	public Map<String, Object> searchIssueType(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchIssueType(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 查询严重程度
	 */
	
	@RequestMapping("/searchIssueSerity")
	public Map<String, Object> searchIssueSerity(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchIssueSerity(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 查询优先级
	 */
	
	@RequestMapping("/searchIssuePriority")
	public Map<String, Object> searchIssuePriority(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchIssuePriority(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 按照条件生成相应的项目名
	 */
	@RequestMapping("/createProjectNameByRoleName")
	public Map<String, Object> createProjectNameByRoleName(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.createProjectNameByRoleName(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	/*
	 * 根据用户的项目角色来生成对应的分配人
	 */
	@RequestMapping("/DetermineUserProjectRoles")
	public Map<String, Object> DetermineUserProjectRoles(String projectName,String projectId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.DetermineUserProjectRoles(projectName,projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 根据缺陷编号来查询该条数据是否有文件
	 */
	@RequestMapping("/queryIsFile")
	public Map<String, Object> queryIsFile(@RequestParam("issueId") String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.queryIsFile(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 下载文件
	 */
	@RequestMapping("/downloadFile")
	public void downFile1(String issueId,BaseProjectBo bo,HttpServletResponse response) throws IOException{
			IssueAttach attach =  this.baseProjectService.searchAttach(issueId);
			File file = new File("D:/filebox/"+attach.getAttachFile());
			response.setContentType("application/x-msdownload;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""+new String(file.getName().getBytes("utf-8"))+"\"");
			InputStream is = new FileInputStream(file);
			OutputStream os = response.getOutputStream();   
			byte[] by = new byte[1024];
			int len = 0;
			while((len = is.read(by)) != -1) {
				os.write(by,0,len);
			}
			os.flush();
			if(is != null) {
				is.close();
			}
	}

	/*
	 * 图片预览
	 */
	@RequestMapping("/previewPicture")
	public Map<String, Object> previewPicture(String issueId,BaseProjectBo bo,HttpServletResponse response) throws IOException{
		Map<String, Object> map = new HashMap<>();
		IssueAttach attach =  this.baseProjectService.searchAttach(issueId);
		File file = new File("/filebox/"+attach.getAttachFile());
		response.setContentType("application/json");
		map.put("url", file);
		return map;
}
	
	
	/*
	 * 查询用户的项目角色
	 */
	@RequestMapping("/judgeUserProjectName")
	public Map<String, Object> judgeUserProjectName(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.judgeUserProjectName(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 提交增加数据
	 */
	@RequestMapping("/addProjectData")
	public Map<String, Object> addProjectData(BaseProjectBo bo,MultipartFile filebox){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.addProjectData(bo,filebox);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 详情信息
	 */
	@RequestMapping("/searchDataByIssueId")
	public Map<String, Object> searchDataByIssueId(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchDataByIssueId(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 根据当前项目名判断用户是否具有管理人员的项目角色
	 */
	@RequestMapping("/judgeUserAdminByProjectName")
	public Map<String, Object> judgeUserAdminByProjectName(String issueId,String projectId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.judgeUserAdminByProjectName(issueId,projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 根据当前项目判断用户是否具有测试人员项目角色
	 */
	@RequestMapping("/judgeUserExploitByProjectName")
	public Map<String, Object> judgeUserExploitByProjectName(String issueId,String projectId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.judgeUserExploitByProjectName(issueId,projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	/*
	 * 删除项目信息
	 */
	@RequestMapping("/removeProjectData")
	public Map<String, Object> removeProjectData(String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.removeProjectData(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 在修改面板中删除文件
	 */
	@RequestMapping("/removeFile")
	public Map<String, Object> removeFile(String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.removeFile(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 查询当前项目的缺陷状态
	 */
	@RequestMapping("/searchProjectissueState")
	public Map<String, Object> searchProjectissueState(String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchProjectissueState(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 提交修改信息
	 */
	@RequestMapping("/updateProjectData")
	public Map<String, Object> updateProjectData(BaseProjectBo bo,MultipartFile filebox){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.updateProjectData(bo,filebox);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	}
	
	/*
	 * 提交分配数据
	 */
	@RequestMapping("/saveAllocationData")
	public Map<String, Object> saveAllocationData(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.saveAllocationData(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	}
	
	/*
	 * 提交反馈数据
	 */
	@RequestMapping("/feedbackData")
	public Map<String, Object> feedbackData(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.feedbackData(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 根据缺陷id查询缺陷信息
	 */
	@RequestMapping("/searchCommentData")
	public Map<String, Object> searchCommentData(String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchCommentData(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 根据缺陷id查询反馈信息
	 */
	@RequestMapping("/searchCommentDataByIssueId")
	public Map<String, Object> searchCommentDataByIssueId(String issueId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchCommentDataByIssueId(issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 提交处理数据
	 */
	@RequestMapping("/saveAllDisposeData")
	public Map<String, Object> saveAllDisposeData(BaseProjectBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.saveAllDisposeData(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 查询当前项目的父级缺陷
	 */
	@RequestMapping("/searchParentId")
	public Map<String, Object> searchParentId(String issueId,String parentId){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchParentId(issueId,parentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/*
	 * 查询日报数据
	 */
	@RequestMapping("/searchDailyData")
	public Map<String, Object> searchDailyData(IssueWorkLogBo bo){
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchDailyData(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
    /*
     * 下载文件
     */
    @RequestMapping("/downExcel")
    public void downExcel(HttpServletResponse response,IssueWorkLogBo bo,String code) throws UnsupportedEncodingException {
    	UUID num = UUID.randomUUID();
    	this.baseProjectService.exportExcel(response,bo,num,code);
    	response.setContentType("application/x-msdownload;charset=utf-8");
    	//response.setContentType("application/octet-stream");
    	String name = "";
    	String fileName = "";
    	if(code.equals("month")) {
    		name = "工时记录月报";
    		fileName = new String(name.getBytes("GB2312"), "ISO_8859_1"); 
    	}else {
    		name = "工时记录日报";
    		fileName = new String(name.getBytes("GB2312"), "ISO_8859_1"); 
    	}
    	 response.setHeader("Content-disposition", "attachment; filename="+fileName);
       
        try {
        	if(code.equals("month")) {
        		FileUtils.copyFile(new File("D:/filebox/工时记录月报"+num+".xls"), response.getOutputStream());
        	}else {
        		FileUtils.copyFile(new File("D:/filebox/工时记录日报"+num+".xls"), response.getOutputStream());
        	}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /*
     * 查询月报信息
     */
    @RequestMapping("/searchMounthData")
    public Map<String, Object> searchMounthData(IssueWorkLogBo bo){
    	Map<String, Object> map = new HashMap<>();
		try {
			map = this.baseProjectService.searchMounthData(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
    }
}
