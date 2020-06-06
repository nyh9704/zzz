/**
 * 预加载事件
 */
$(function(){
	//判断当前用户是否具有开发人员权限
	judgeUserIsExploit();
	//隐藏详情页面
	$("#allocation_win").window('close');
	//生成项目下拉列表里面的值
	createProjectName();
	//给处理面板中的保存按钮绑定单击事件
	$("#amendBtn").click(saveAllocationData);
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
 * 判断当前用户是否具有开发人员权限
 */
function judgeUserIsExploit(){
	$.ajax({
		url:"../../judgeUserProjectName.json",
		success:function(data){
			if(data.res == 0){
				$("table").hide();
				$.messager.alert('提示','尚不具备该权限');
				
			}else{
				TreatmentsearchData();
			}
		}
	})
}


/**
 * 分页查询
 */
function TreatmentsearchData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		title:"数据列表",
		url:"../../searchProjectData.json?exploit="+1+"&code=1",
		columns:[[    
	        {field:'projectName',title:'项目名称',width:'10%',align:'center'},
	        {field:'issueName',title:'缺陷名称',width:'10%',align:'center'}, 
	        {field:'issuePriority',title:'优先级',width:'10%',align:'center',
	        	formatter:function(value,row,index){
        			return row.priorityDesc;
	        	}
	        },
	        {field:'issueSeverity',title:'严重程度',width:'10%',align:'center',
	        	formatter:function(value,row,index){
        			return row.severityDesc;
	        	}
	        },    
	        {field:'issueType',title:'缺陷分类',width:'10%',align:'center',
	        	formatter:function(value,row,index){
        			return row.typeDesc;
	        	}
	        },      
	        {field:'issueState',title:'状态',width:'10%',align:'center',
	        	formatter:function(value,row,index){
        			return row.stateDesc;
	        	}
	        }, 
	        {field:'assignee',title:'指派',width:'10%',align:'center',
	        	formatter:function(value,row,index){
        			return row.userName;
	        	}
	        },    
	        {field:'planStartTime',title:'计划完成时间',width:'10%',align:'center'},  
	        {field:'modifyTime',title:'更新完成时间',width:'10%',align:'center'}, 
	        {field:'name',title:'操作',width:'11%',align:'center', 
	        	formatter:function(value,row,index){
	        			return '<a style="color:blue"  href="#" onclick="allocation('+row.issueId+','+index+')">'+"处理"+'</a>';
	        	}
	        }     
	    ]]	   
	});
}


/**
 * 处理页面
 */
function allocation(issueId,index){
	//查询缺陷信息
	$.ajax({
		url:"../../searchProjectDataByIssueId.json",
		data:{"issueId":issueId},
		success:function(data){
			var projectData = data.bo;			
			//查询该用户在当前项目中是否具有开发人员权限
			var projectId = projectData.projectId;
			var projectName = projectData.projectName;
			var parentId = projectData.parentId;
			$.ajax({
				url:"../../judgeUserExploitByProjectName.json",
				data:{"projectId":projectId,"issueId":issueId},
				success:function(data){
					if(data.result == 0){
						$.messager.alert('提示','在当前项目中不具备该权限');
					}else{
						
						
						//按照指定格式将字符串转成数组
						var doneCondition = projectData.doneCondition.split("\r\n");
						var issueDesc = projectData.issueDesc.split("\r\n");
						//按照指定格式将数组转成字符串
						doneCondition = doneCondition.join("<br/>");
						issueDesc = issueDesc.join("<br/>");
						//单独给分配页面里面的表单控件赋值
						$("#issueDescHide").html(issueDesc);
						$("#userName").html(projectData.userName);
						$("#doneConditionHide").html(doneCondition);
						$("#issueName").html(projectData.issueName);
						//if(projectData.doneRatio != null){	
							$("#doneRatio").html(projectData.doneRatio);
						//}else{
							//$("#doneRatio").html("——");
						//}
						//查询父级缺陷
						if(parentId === null){
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
						//给分配面板里面的状态生成数据
						$.ajax({
							url:"../../searchState.json",
							data:{"projectId":projectId,"projectName":projectName},
							success:function(data){
								//删除末尾数据
								data.result.pop();
								//过滤函数，n:表示数组的单个实体，i:表示数组的索引。
								data.result=$.grep(data.result,function(n,i){
									if(i != 0 && i != 3){
										return i;
									}
									
									});
								$("#issueState").combobox({
									data:data.result, 
									panelHeight:'auto',
								    valueField:'stateCode',    
								    textField:'stateDesc'   
								})
							}
						})
						//根据缺陷id查询有无反馈信息
						$.ajax({
							url:"../../searchCommentData.json",
							data:{"issueId":issueId},
							success:function(data){
								if(data.result == 0){
									$("#comment_div").hide();
								}else{
									$('#commentTab').datagrid({    
									    url:'../../searchCommentDataByIssueId.json?issueId='+issueId+'',
									    columns:[[    
									        {field:'logId',title:'缺陷编号',width:'17%'},    
									        {field:'comment',title:'评论内容',width:'48%'},    
									        {field:'modifyTime',title:'修改时间',width:'35%'}    
									    ]]    
									});
									
									$("#comment_div").show();
									
									
								}
							}
						});
						
						//打开处理面板
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
 * 提交处理数据
 */
function saveAllocationData(){
	if($("#allocation_form").form('validate')){
		$("#allocation_form").form('submit',{
			url:"../../saveAllDisposeData.json",
			onSubmit: function(){    
		         
		    },    
		    success:function(data){    
		    	var data = eval('(' + data + ')');
		    	if(data.result != 0 && data.rel != 0){
		    		$.messager.alert('提示','处理成功');
		    		$("#tt").datagrid('reload');
		    		$("#allocation_form").form('clear');
		    		$("#allocation_win").window('close')
		    	}else{
		    		$.messager.alert('提示','处理失败');
		    	}
		    }    

		})
	}else{
		$.messager.alert('提示','操作失败');
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


















































