/**
 * 预加载事件
 */
$(function(){
	//判断当前用户是否具有管理员权限
	judgeUserIsAdmin();
	//隐藏详情页面
	$("#allocation_win").window('close');
	//生成项目下拉列表里面的值
	createProjectName();
	//给分配面板中的保存按钮绑定单击事件
	$("#allocationBtn").click(saveAllocationData);
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(keywordSearch);
	//给日期框添加清空按钮
	clearDateButton();
	
})

/**
 * 生成项目下拉列表里面的值
 */
function createProjectName(){
	//生成项目下拉列表里面的值
		$.ajax({
		url:"../../searchProjectNameData.json",		
		success:function(data){
			$('#projectName').combobox({
				data:data.result, 			
				panelHeight:'auto',
			    valueField:'projectId',    
			    textField:'projectName',			    
			});
			
		}
	})
}

/**
 * 关键字查询
 */
function keywordSearch(){
	var startTime = $("#startTime").datebox('getValue');
	var endTime = $("#endTime").datebox('getValue');
	var obj=$("#serchForm").serializeObject();
	if(startTime != null && startTime != ""){
		obj.startTime = obj.startTime+" 00:00:00";
		
	}
	
	if(endTime != null && endTime != ""){
		obj.endTime = obj.endTime+" 23:59:59";
	}
	
	$("#tt").datagrid('load',obj);
}


/**
 * 判断当前用户是否具有管理员权限
 */
function judgeUserIsAdmin(){
	$.ajax({
		url:"../../judgeUserProjectName.json",
		success:function(data){
			if(data.rel == 0){
				$("table").hide();
				$.messager.alert('提示','尚不具备该权限');
			}else{				
				searchData();
				
			}
		}
	})
}

/**
 * 分页查询
 */
function searchData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchProjectData.json?issueState="+'001'+"",
		columns:[[    
	        {field:'projectName',title:'项目名称',width:'11%',align:'center'},
	        {field:'issueName',title:'缺陷名称',width:'11%',align:'center'}, 
	        {field:'issuePriority',title:'优先级',width:'11%',align:'center',
	        	formatter:function(value,row,index){
        			return row.priorityDesc;
	        	}
	        },
	        {field:'issueSeverity',title:'严重程度',width:'11%',align:'center',
	        	formatter:function(value,row,index){
        			return row.severityDesc;
	        	}
	        },    
	        {field:'issueType',title:'缺陷分类',width:'11%',align:'center',
	        	formatter:function(value,row,index){
        			return row.typeDesc;
	        	}
	        },      
	        {field:'createTime',title:'创建时间',width:'11%',align:'center',sortable:true}, 
	        {field:'assignee',title:'指派',width:'11%',align:'center',
	        	formatter:function(value,row,index){
        			return row.userName;
	        	}
	        },    
	        {field:'planStartTime',title:'计划完成时间',width:'11%',align:'center'},    
	        {field:'name',title:'操作',width:'11%',align:'center', 
	        	formatter:function(value,row,index){
	        			return '<a style="color:blue"  href="#" onclick="allocation('+row.issueId+','+index+')">'+"分配"+'</a>';
	        	}
	        }     
	    ]]	   
	});
}

/**
 * 点击分配面板自带的关闭按钮后执行的操作
 */
function afterCloseDo(){
    $("#allocation_win").dialog({
    	onClose: function () {
    		$("#allocation_form").form('clear');
    	}
    });
    
    
}



/**
 * 分配页面
 */
function allocation(issueId,index){
	$.ajax({
		url:"../../searchProjectDataByIssueId.json",
		data:{"issueId":issueId},
		success:function(data){
			var projectData = data.bo;
			//查询该用户在当前项目中是否具有管理员权限
			var projectId = projectData.projectId;
			var projectName = projectData.projectName;
			$.ajax({
				url:"../../judgeUserAdminByProjectName.json",
				data:{"projectId":projectId,"issueId":issueId},
				success:function(data){
					if(data.result == 0){
						$.messager.alert('提示','在当前项目中不具备该权限');
					}else{
						projectData.userName = "";
						//按照指定格式将字符串转成数组
						var doneCondition = projectData.doneCondition.split("\r\n");
						var issueDesc = projectData.issueDesc.split("\r\n");
						//按照指定格式将数组转成字符串
						doneCondition = doneCondition.join("<br/>");
						issueDesc = issueDesc.join("<br/>");
						//单独给分配页面里面的表单控件赋值
						$("#issueDescHide").html(issueDesc);
						$("#doneConditionHide").html(doneCondition);
						$("#issueName").html(projectData.issueName);
						//if(row.doneRatio != null){	
							$("#doneRatio").html(projectData.doneRatio);
						//}else{
							//$("#doneRatio").html("——");
						//}
						
						//给该项目的上级缺陷赋值
						var parentId = projectData.parentId;
						if(parentId == null){
							$("#allocation_form").form('load',projectData);
						}else{
							$.ajax({
								url:"../../searchParentId.json",
								data:{"issueId":issueId,"parentId":parentId},
								success:function(data){
									$("#allocation_form").form('load',projectData);
									$("#parentId").val(data.bjb.parentName);

								}
							})
						}
						//给分配面板里面的派发人生成数据
						$.ajax({
							url:"../../DetermineUserProjectRoles.json",
							data:{"projectId":projectId,"projectName":projectName},
							success:function(data){
								$("#assignee").combobox({
									data:data.result, 
									panelHeight:'auto',
									valueField:'userId',    
									textField:'userName'   
								})
							}
						})
						
						//打开分配面板
						$("#allocation_win").window('open');
						//关闭面板时清空表单
						$("#allocation_win").dialog({
							onClose:function(){
								$("#allocation_form").form('clear');
							}
						})
					}
				}
			})
		}
	})
	

	
}

/**
 * 提交分配数据
 */
function saveAllocationData(){
	if($("#allocation_form").form('validate')){
		$("#allocation_form").form('submit',{
			url:"../../saveAllocationData.json",
			onSubmit: function(){    
		         
		    },    
		    success:function(data){    
		    	var data = eval('(' + data + ')');
		    	if(data.result != 0){
		    		$.messager.alert('提示','分配成功');
		    		$("#tt").datagrid('reload');
		    		$("#allocation_form").form('clear');
		    		$("#allocation_win").window('close');
		    	}else{
		    		$.messager.alert('提示','分配失败');
		    	}
		    }    

		})
	}else{
		$.messager.alert('提示','操作错误');
	}
	

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
    
    $('#startTime').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
    
    $('#endTime').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
}











