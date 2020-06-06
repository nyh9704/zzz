/**
 * 
 *典当流程任务节点JS
 */

//form表单弹窗属性
var options={
	width:'60%',
	height:'80%',
	title:'商品出售',
	buttons:"#sale_form"
};


//当前登录用户信息
var user;

//付款方式数据集合（数据字典）
var payTypeObj;

//当前审核的收货业务
var auditBusi;

//业务关联的商品信息
var goodsInfo;

/**
 * 初始化
 * @param row 典当的商品数据
 */
function initPage(){
	//获取当前登录用户信息并赋值给全局变量user
	$.ajax({
		url:basePath + "/frame_sysrole_finduser.json",
		type:"post",
		async:false,
		success:function(data){
			//得到用户
			user = data.user;
		}
	});
	
	//从任务信息中取出业务主键作为业务id，查询信息
	var agreementCode = _taskData.businessKey;
	
	$.ajax({
		url:basePath + "/businesslogic.json",
		type:"post",
		async:false,
		data:{logicId:"06.03.05.02",agreementCode:agreementCode},
		success:function(data){
			
			//典当业务信息
			var consignSaleInfo=data.consignSaleInfo;
			
			//全局变量赋值
			auditBusi=consignSaleInfo;
			
			consignSaleInfo.bottomPrice=consignSaleInfo.bottomPrice+"元";
			consignSaleInfo.serviceCharge=consignSaleInfo.serviceCharge+"%";
			
			
			
			//收货协议信息回显
			$("#consignSaleAuditForm").form("load",consignSaleInfo);
			
			
			//去除input边框
			$("#consignSaleAuditForm input").css({"border":"none",backgroundColor:"#fafafa"});
			
			//不可编辑
			$("#consignSaleAuditForm input").attr("readonly",true);
			
			
			//会员信息
			var memberInfo=data.memberInfo;
			
			$("#consignSaleAuditMemberInfo_form").form("load",memberInfo);
			//去除input边框
			$("#consignSaleAuditMemberInfo_form input").css({"border":"none",backgroundColor:"#fafafa"});
			
			//不可编辑
			$("#consignSaleAuditMemberInfo_form input").attr("readonly",true);
			
			
			//商品信息
			goodsInfo=data.goodsInfo;
			
			//页面商品数据回显
			$("#consignSaleAuditgoodsIdPP").text(goodsInfo.goodsId);
			$("#consignSaleAuditgoodsNamePP").text(goodsInfo.goodsName);
			$("#consignSaleAuditofficialPricePP").text(goodsInfo.officialPrice);
			$("#consignSaleAuditvaluationPricePP").text(goodsInfo.valuationPrice);
			$("#consignSalesellingPricePP").text(goodsInfo.pawnPrice);
			$("#consignSaleAuditfirstPricePP").text(goodsInfo.firstPrice);
		}
	});
	
	//操作记录
	$("#consignSaleShowhistoryLogTable").datagrid({
		url:basePath+"/dataaccess.json",
		method:"post",
		fitColumns:true,
		queryParams:{accessId:"63206",busiId:auditBusi.agreementCode,busiType:"04"},
		columns:[[
			         {field:'busiLogId',title:'记录编号',width:'15%',sortable:true,alias:'busi_log_id',halign:'center',align:'left'},
			         {field:'busiId',title:'业务编号',hidden:true},
			         {field:'empId',title:'操作人',width:"20%",sortable:false,halign:'center',align:'left'},
			         {field:'operation',title:'操作类型',width:'20%',sortable:false,alias:'',halign:'center',align:'center'},
			         {field:'operateTime',title:'操作时间',width:'35%',sortable:false,alias:'',halign:'center',align:'center'},
			     ]]
	});
};

/**
 *放款方式格式化 
 */
function payTypeFormatter(value){
	var result="";
	
	//从数据字典获取数据
	$.ajax({
		url:basePath+"/dataaccess.json",
		data:{typeCode:"PC_PAWN_PAY_TYPE",accessId:3301},
		method:"post",
		async:false,
		success:function(data){
			payTypeObj=data.rows;
		}
	});
	
	//返回格式化后的数据
	for(var i=0;i<payTypeObj.length;i++){
		if(payTypeObj[i].dictCode==value){
			result=payTypeObj[i].dictDesc;
			break;
		}
	}
	
	return result;
}

/**
 *审核操作 
 *@param audit 审核结果(1.通过 0.不通过)
 */
function doSale(){
	//构造参数
	var auditParams={};
	auditParams.logicId="03.06.03";
	
	auditParams.consignSaleId=auditBusi.consignSaleId;
	auditParams.consignSaleStat="04";
	
	//操作记录
	auditParams.busiType="04";
	auditParams.busiId=auditBusi.agreementCode;
	auditParams.empId=user.userId;
	auditParams.operation="寄卖商品售出";
	
	//销售人
	auditParams.payEmpId=user.userId;
	
	//商品信息
	auditParams.goodsId=goodsInfo.goodsId;

	//流程实例ID
	auditParams.taskId=_taskData.id;
	
	$.messager.confirm("确认信息","是否确认销售,并结束本商品寄卖流程",function(r){
		if(r){
			//调用业务逻辑完成审核
			$.ajax({
				url:basePath+"/businesslogic.json",
				data:auditParams,
				method:"post",
				async:false,
				success:function(data){
					if(data.resultCode==1){
						//刷新列表并关闭弹窗
						refresh();
						
						$.messager.alert("提示信息","办理完成","info");
					}else{
						$.messager.alert("错误信息","办理失败，请重试或联系管理员","error");
					}
				}
			});
		}
	});
	
}


/** 
 * 还款日期 
 * startDate:起始日期 
 * valueTime:有效天数 
 */  
function transferCouponValueTime(startDate,valueTime){  
 var date = new Date(startDate);
 
 var newDate = new Date(date.getFullYear(),date.getMonth(),date.getDate()+valueTime,date.getHours(),date.getMinutes(),date.getSeconds());  
 var year1 = date.getFullYear();  
 var month1 = date.getMonth()+1;  
 var day1 = date.getDate();  
 var year2 = newDate.getFullYear();  
 var month2 = newDate.getMonth()+1;  
 var day2 = newDate.getDate();
 //year1+"-"+month1+"-"+day1+"-"+
 
 return year2+"-"+month2+"-"+day2+" "+newDate.getHours()+":"+newDate.getMinutes()+":"+newDate.getSeconds();  
   
}