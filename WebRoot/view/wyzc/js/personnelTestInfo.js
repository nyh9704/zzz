/**
 * 全局变量
 */
var url;
var personnelData;
var newPersonnel;
/**
 * 预加载事件
 */
$(function(){
	//用户权限
	userJurisdiction();
	//给是否延期复选框绑定单击事件
	$("#aa").click(function(){
		$("#testPostponeNumber").numberbox('setValue','');
	})
	//加载下拉列表里面的数据
	loadDataToSelect();
	//分页查询
	//searchPersonalData();
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(searchKeyWorld);
	//给增加按钮绑定单击事件
	$("#addBtn").click(function(){
		url = '../../savePersonnelTestInfo.json';
		$('#personnelTest_div').dialog({
			title : '新增员工信息',
			closed : false
		});
		
	});
	//给关闭按钮绑定单击事件
	$("#closeBtn").click(closeBtn);
	//给修改选项关闭按钮绑定单击事件
	$("#mod_closeBtn").click(closeModBtn);
	//给修改选项确认按钮绑定单击事件
	$("#mod_submitBtn").click(optionToConfirm);
	//在职员工修改页面中的关闭按钮绑定单击事件
	$("#closeOfficialBtn").click(closeOfficialBtn);
	//在职员工修改页面确认按钮绑定单击事件
	$("#submitOfficialBtn").click(saveModiftyOfficialPersonnel)
	//离职员工修改页面中的关闭按钮绑定单击事件
	$("#closeLeaveBtn").click(closeLeaveBtn);
	//离职员工修改页面中的确认按钮绑定单击事件
	$("#submitLeaveBtn").click(saveModifyLeavePersonnel);
	//给提交按钮绑定单击事件
	$("#submitBtn").click(savePersonnelTestInfo);
	//给导出当前页按钮绑定单击事件
	$("#exportSome").click(exportSome);
	//给导出全部按钮绑定单击事件
	$("#exportAll").click(exportAll);
	//给删除按钮绑定单击事件
	$("#removeBtn").click(removePersonnelByUerId);
	//给日期添加清楚按钮
	clearDateButton();
})


/**
 * 用户权限
 */
function userJurisdiction(){
	$.ajax({
		url:"../../userJurisdiction.json",
		success:function(data){
			if(data.admin == "1"){
				searchPersonalData();
			}else if(data.wpb.wpName == "人事部" || data.wpb.wpName == "产品研发" 
				|| data.wpb.wpName == "总经办" || data.wpb.wpName == "设计中心" ){
				searchPersonalData();
				if(data.wpb.wdName == "专员"){
					$("#span").css('display','none');
				}else{
					$("#span").css('display','inline-block');
				}
			}else if(data.wpb.wpName == "行政部" || data.wpb.wpName == "客服/售后部" || data.wpb.wpName == "培训部"){
				searchPersonalData();
				$("#span").css('display','none');
				$("#ssp").css('display','none');
			}else{
				$("table").hide();
				$.messager.alert('提示','尚不具备该权限');			
			}
		}
	})
}






/**
 * 加载下拉列表里面的数据
 * @returns
 */
function loadDataToSelect(){
	//查询下拉列表需要加载的数据
	$.ajax({
		url:"../../loadDataToSelect.json",
		dataType:"json",
		success:function(data){
			//加载查询功能区的部门信息
			$("#wpId").combobox({
				valueField: 'wpId',
				textField: 'wpName',
				data:data.parts,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
			
			//加载试岗员工增加页面的部门信息
			$("#addWpId").combobox({
				valueField: 'wpId',
				textField: 'wpName',
				data:data.parts,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
			
			//加载在职员工增加页面的部门信息
			$("#wpIdTest").combobox({
				valueField: 'wpId',
				textField: 'wpName',
				data:data.parts,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
			
			//加载查询功能区岗位等级信息
			$("#wdId").combobox({
				valueField: 'wdId',
				textField: 'wdName',
				data:data.dutys,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
			
			//加载试岗增加页面的岗位等级信息
			$("#addWdId").combobox({
				valueField: 'wdId',
				textField: 'wdName',
				data:data.dutys,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
			
			//加载在职员工增加页面岗位等级信息
			$("#wdIdTest").combobox({
				valueField: 'wdId',
				textField: 'wdName',
				data:data.dutys,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
		}
	})
}


/**
 * 关键字查询
 */
function searchKeyWorld(){
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}



/**
 * 分页查询
 */
function searchPersonalData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchPersonnelTestData.json",
		columns:[[    
	        {field:'wpName',title:'所属部门',width:'5%',align:'center'},
	        {field:'postName',title:'岗位名称',width:'5%',align:'center'},
	        {field:'wdName',title:'岗位级别',width:'4%',align:'center'},
	        {field:'userName',title:'姓名',width:'5%',align:'center',}, 	       
	        {field:'userSex',title:'性别',width:'3%',align:'center'},
	        {field:'identityCard',title:'身份证号',width:'8%',align:'center'},
	        {field:'practiceStart',title:'试岗开始',width:'6%',align:'center',sortable:true},
	        {field:'practiceEnd',title:'试岗结束',width:'6%',align:'center',sortable:true},
	        {field:'isPostpone',title:'是否延期',width:'6%',align:'center'},
	        {field:'postponeTo',title:'试岗延期至',width:'6%',align:'center',sortable:true},
	        {field:'birDate',title:'出生日期',width:'6%',align:'center',sortable:true},
	        {field:'birYear',title:'出生年份',width:'4%',align:'center',sortable:true},	       
	        {field:'birMounth',title:'生日月份',width:'4%',align:'center',sortable:true},
	        {field:'age',title:'年龄',width:'3%',align:'center',sortable:true},
	        {field:'nation',title:'民族',width:'3%',align:'center',},
	        {field:'registered',title:'户口',width:'3%',align:'center'},	       
	        {field:'education',title:'学历',width:'3%',align:'center'},
	        {field:'contactTel',title:'联系电话',width:'6%',align:'center'},
	        {field:'userIdCardAddress',title:'身份证地址',width:'10%',align:'center',},
	        {field:'remark',title:'备注',width:'5%',align:'center'}	       
	               
	    ]]	   
	});
}


/**
 * 提交员工信息
 */
function savePersonnelTestInfo(){
	var personnelTestObj =  $("#personnelTest_form").serializeObject();
	if(personnelTestObj.userName ==""){
		$.messager.alert('提示','请完善信息');		
	}else{
		$.ajax({
			type:'post',
			url:url,
			dataType:'json',
			data:$("#personnelTest_form").serializeObject(),
			success:function(data){
				if(data.result == '1'){
					$.messager.alert('提示','操作成功');
					$("#tt").datagrid('reload');
					$("#personnelTest_form").form('clear');
					$("#personnelTest_div").window('close');
				}else{
					$.messager.alert('提示','操作失败');
				}
			}
		})
	}
}

/**
 * 修改员工资料
 */
function doModify() {
	$("#personnelTest_form").form('clear');
	var info = $("#tt").datagrid('getSelections');
	personnelData = info[0];
	if (info.length > 0) {
		$('#personnelTest_mod_div').dialog({
			title:"修改选项",
			closed : false
		});
	} else{
		$.messager.alert("提示", "请选择一条数据修改");
	}
}

/**
 * 修改选项确认
 */
function optionToConfirm(){
	var modForm = $("#personnelTest_mod_form").serializeObject();	
	//根据员工编号查询信息
	$.ajax({
		url:"../../searchPersonnelByUserId.json",
		dataType:'json',
		async:false,
		data:{userId:personnelData.userId},
		type:'post',
		success:function(data){
			newPersonnel = data.result;
		}
		
	})
	
	if(modForm.cho == 0){//显示试岗员工修改界面
		$('#personnelTest_div').dialog({
		title : '修改试岗员工信息',
		closed : false
	});
	//关闭修改选项页面
		$('#personnelTest_mod_div').dialog({
			closed : true
		});
	//表单回显
	$("#personnelTest_form").form("load",newPersonnel);
	url = "../../saveModifyTestPersonnel.json";
	
	}else if(modForm.cho == 1){//显示正式员工修改界面
		$('#personnel_official_div').dialog({
			title:'修改在职员工信息',
			closed : false
		});
		
		//表单回显
		$("#personnel_official_form").form("load",newPersonnel);	
				
		//关闭修改选项页面
		$('#personnelTest_mod_div').dialog({
			closed : true
		});
	}else if(modForm.cho == 2){//显示离职员工修改界面
		$.messager.confirm('确认', '您确认将该员工调整为离职员工吗吗？此操作不可返回请酌情考虑', function(r) {
			if (r) {
				$('#personnel_leave_div').dialog({
					title:'修改离职员工信息',
					closed : false
				});
				//关闭修改选项页面
				$('#personnelTest_mod_div').dialog({
					closed : true
				});
				//表单回显
				$("#personnel_leave_div").form("load",newPersonnel);	
				
			}else{
				$('#personnelTest_mod_div').dialog({
					title:"修改选项",
					closed : false
				});
			}
		});
	}
}

/**
 * 提交在职员工修改信息
 */
function saveModiftyOfficialPersonnel(){
	$.ajax({
		url:'../../saveModiftyOfficialPersonnel.json',
		type:'post',
		dataType:'json',
		data:$("#personnel_official_form").serializeObject(),
		success:function(data){
			if(data.result == '1'){
				$.messager.alert('提示','操作成功');
				$("#tt").datagrid('reload');
				$("#personnel_official_form").form('clear');
				$("#personnel_official_div").window('close');
			}else{
				$.messager.alert('提示','操作失败');
			}
		}
	})
}

/**
 * 提交离职员工信息
 */
function saveModifyLeavePersonnel(){
	$.ajax({
		url:'../../saveModifyLeavePersonnel.json',
		type:'post',
		dataType:'json',
		data:$("#personnel_leave_form").serializeObject(),
		success:function(data){
			if(data.result == '1' || data.res == '1'){
				$.messager.alert('提示','操作成功');
				$("#tt").datagrid('reload');
				$("#personnel_leave_form").form('clear');
				$("#personnel_leave_div").window('close');
			}else{
				$.messager.alert('提示','操作失败');
			}
		}
	})
}

/**
 * 删除员工信息
 */
function removePersonnelByUerId(){
	$.messager.confirm('确认', '您确认将该员工调整为离职员工吗？此操作不可返回请酌情考虑', function(r) {
		if (r) {
			var info = $("#tt").datagrid('getSelections');
			if (info.length > 0) {
				$.ajax({
					url:"../../removePersonnelByUerId.json",
					dataType:"json",
					type:"post",
					data:{"userId":info[0].userId},
					success:function(data){
						if(data.result == "1"){
							$.messager.alert("提示", "删除成功");
							$("#tt").datagrid("reload");
						}
					}
				})
			} else{
				$.messager.alert("提示", "请选择一条数据删除");
			}			
		}else{
			$("#tt").datagrid("reload");
		}
	});
	
	
}


/**
 * 导出当前页
 */
function exportSome(){
	//获取当前页的数据
	var arr = $("#tt").datagrid('getRows');
	//console.log(someInfo)
	//发送到后台
	$.ajax({
		url:"../../exportSome.json",
		type:"post",
		dataType:"json",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		traditional:true,
		async:false,
		data:{arr:JSON.stringify(arr)},
		success:function(data){
			if(data.result == '1'){
				$("#exportSome").attr('href','../../startExportSome.json')
			}
			
		}
	})
	
}












/**
 * 弹框关闭按钮
 */
function closeBtn(){
	$("#personnelTest_form").form('clear');
	$('#personnelTest_div').dialog({
		closed : true
	});
}

/**
 * 修改选项关闭
 * @returns
 */
function closeModBtn(){
	$("#personnelTest_mod_form").form('clear');
	$('#personnelTest_mod_div').dialog({
		closed : true
	});
}

/**
 * 在职员工修改页面关闭
 */
function closeOfficialBtn(){
	$("#personnel_official_form").form('clear');
	$('#personnel_official_div').dialog({
		closed : true
	});
}

/**
 * 离职员工修改页面关闭
 */
function closeLeaveBtn(){
	$("#personnel_leave_form").form('clear');
	$('#personnel_leave_div').dialog({
		closed : true
	});
}


/**
 * 给日期框添加清空按钮
 */
function clearDateButton(){
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
    buttons.splice(1, 0, {
		text: '清除',
		handler: function(target){
                    //清除日期信息
		    $(target).datebox("setValue","");
		    //关闭日期选择框
		     $(target).datebox('hidePanel');
		}
	});
    
    $('#practiceStartSmall').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
    
    $('#practiceStartBig').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
    
    $('#practiceEndSmall').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
    
    $('#practiceEndBig').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
}


/**
 * 清空表单
 */
function clearForm(){
	$("#serchForm").form('clear');
}

/**
 * 
 */
