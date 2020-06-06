/**
 * 预加载事件
 * 
 */
$(function(){
	
	//生成给下拉列表里面的值
	createComboboxData();
	console.log(new Date().pattern("yyyy-MM-dd EE hh:mm:ss"))
	//隐藏增加面板	
	$("#add_win").window('close');
	//显示增加面板
	$("#addBtn").click(showAddPanel);
	//给提交按钮绑定单击事件
	$('#submitBtn').click(addProjectData);
	//分页查询
	searchData();
	//隐藏详情页面
	$("#particulars_win").window('close');
	//隐藏详情面板中的预览图片
	$("#div_img").hide();
	//给隐藏面板中的关闭按钮绑定单击事件
	$("#particularsCloseBtn").click(closePartichlarsWin);
	//隐藏修改面板中的预览图片
	$("#amend_img").hide();
	//给删除按钮绑定单击事件
	$("#removeBtn").click(removeProjectData);
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(keywordSearch);
	//给增加面板里面的关闭按钮绑定点击事件
	$("#addCloseBtn").click(closeAddWindow);
	//给修改按钮绑定单击事件
	$("#modifBtn").click(amendProjectData);
	//隐藏修改面板
	$("#amend_win").window('close');
	//给修改面板中的提交按钮绑定单击事件
	$("#amendBtn").click(updateProjectData);
	//给修改面板中的关闭按钮绑定单击时间
	$("#amendCloseBtn").click(amendCloseBtn);
	//给日期框添加清空按钮
	clearDateButton();
})

/**
 * 全局变量
 */
var title,url;

/**
 * 分页查询
 * 
 */

function searchData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchProjectData.json",
		columns:[[    
	        {field:'projectName',title:'项目名称',width:'11%',align:'left'},
	        {field:'issueName',title:'缺陷名称',width:'11%',align:'left'}, 
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
	        {field:'issueState',title:'状态',width:'11%',align:'center',
	        	formatter:function(value,row,index){
        			return row.stateDesc;
	        	}
	        }, 
	        {field:'assignee',title:'指派',width:'11%',align:'center',
	        	formatter:function(value,row,index){
        			return row.userName;
	        	}
	        },    
	        {field:'createTime',title:'创建时间',width:'11%',align:'center',sortable:true},    
	        {field:'name',title:'操作',width:'11%',align:'center',
	        	formatter:function(value,row,index){
	        			return '<a style="color:blue"  href="#" onclick="particulars('+row.issueId+','+index+')">'+"详情"+'</a>';
				}  

	        }     
	    ]]	   
	});
}

/*
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

/*
 * 关闭增加面板
 */
function closeAddWindow(){
	$("#add_form").form('clear');
	$("#add_win").window('close');
	
}

/*
 * 关闭修改面板
 */

function amendCloseBtn(){
	$("#amend_win").window('close');
	$("#amend_form").form('clear');
	$("#amendFileName").hide();
}

/*
 * 关闭详情面板
 */
function closePartichlarsWin(){
	$("#particulars_win").window('close');
	$("#div_img").hide();
}


/*
 * 使用easyui自带关闭按钮后执行的操作
 */
function afterCloseDo(){
    $("#add_win").dialog({
    	onClose: function () {
    		$("#add_form").form('clear');
    	}
    });    
}


/**
 * 动态生成下拉列表里面的值
 */
function createComboboxData(){
	
	//生成状态下拉列表里面的值
	$.ajax({
		url:"../../searchState.json",
		dataType:"json",
		success:function(data){
			$('#issueState').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'stateCode',    
			    textField:'stateDesc'   
			});
			

		}
	});
	
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
	});
	
	
	//在修改面板里面给缺陷状态生成数据
	$.ajax({
		url:"../../searchState.json",
		dataType:"json",
		success:function(data){
			$('#amendState').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'stateCode',    
			    textField:'stateDesc'   
			});
		}
	});
	
	//在增加面板里面给缺陷分类生成数据
		$.ajax({
		url:"../../searchIssueType.json",
		success:function(data){
			$('#type').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'typeCode',    
			    textField:'typeDesc'   
			});
		}
	});
	
	//在修改面板里面给缺陷分类生成数据
	$.ajax({
		url:"../../searchIssueType.json",
		success:function(data){
			$('#amendType').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'typeCode',    
			    textField:'typeDesc'   
			});
		}
	});
	
	//在增加面板里面给严重程度生成数据
	$.ajax({
		url:"../../searchIssueSerity.json",
		success:function(data){
			$('#severity').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'severityCode',    
			    textField:'severityDesc' ,
			    
			});
		}
	})
		//在修改面板里面给严重程度生成数据
	
	$.ajax({
		url:"../../searchIssueSerity.json",
		success:function(data){
			$('#amendSeverity').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'severityCode',    
			    textField:'severityDesc' ,
			    
			});
		}
	})
	//在增加面板里面给优先级生成数据
		$.ajax({
		url:"../../searchIssuePriority.json",
		success:function(data){
			$('#priority').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'priorityCode',    
			    textField:'priorityDesc'   
			});
		}
	});
	
	//在修改面板里面给优先级生成数据	
	$.ajax({
		url:"../../searchIssuePriority.json",
		success:function(data){
			$('#amendPriority').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'priorityCode',    
			    textField:'priorityDesc'   
			});
		}
	});
	
	//在增加面板里面给项目名称增加数据
	$.ajax({
		url:"../../createProjectNameByRoleName.json",
		dataType:"json",
		success:function(data){
			$('#proName').combobox({
				data:data.result, 
				panelHeight:'auto',
			    valueField:'projectId',    
			    textField:'projectName'   
			});
		}
	});
	
}

/**
 * 打开修改面板
 */
function amendProjectData(){
	//获取选中行的数据
	var rows = $("#tt").datagrid('getSelections');
	var issueId = rows[0].issueId;
	//查询当前用户是否具有修改的权限
	$.ajax({
		url:"../../judgeUserProjectName.json",
		success:function(data){
			if(data.result == 0){
				$.messager.alert('提示','尚不具备该权限');
			}else{
				//判断用户有没有选中数据				
				if(rows.length == 0){
					$.messager.alert('提示','请选中要修改的数据');
				}else{
					//查询当前用户在当前项目中是否具有修改的权限
					var projectId = rows[0].projectId;
					var projectName = rows[0].projectName;
					
					var parentId = rows[0].parentId;
					$("#hiddenIssueId").val(issueId);
					$.ajax({
						url:"../../DetermineUserProjectRoles.json",
						data:{"projectId":projectId,"projectName":projectName},
						success:function(data){
							if(data.rel == 0){
								$.messager.alert('提示','您在当前项目中尚不具备该权限');
							}else{
								//查询当前项目是否支持修改
								$.ajax({
									url:"../../searchProjectissueState.json",
									data:{"issueId":issueId},
									success:function(data){
										if(data.result.issueState != "001"){
											$.messager.alert('提示','当前项目不支持该操作');
										}else{
											//查询当前项目的父级缺陷
											if(parentId === null){
												$("#amend_form").form('load',rows[0]);
											}else{
												$("#parentCode").val(parentId);
												$.ajax({
													url:"../../searchParentId.json",
													data:{"issueId":issueId,"parentId":parentId},
													success:function(data){
														var parentName = data.bjb.parentName;
														$("#amend_form").form('load',rows[0]);
														$("#amendParentId").textbox('setValue',parentName);

													}
												})
											}
											
											//查询当前选中的信息是否带有附件
											$.ajax({
												url:"../../queryIsFile.json",
												data:{"issueId":issueId},
												success:function(data){
													if(data.result == 0){
														//如果没有附件则隐藏以下下内容
														$("#amendDownload").css("display","none");
														$("#amendPreview").hide();
														$("#amendDelete").hide();
													}else{
														//反之显示
														$("#amendDownload").css("display","block");
														$("#amendDelete").show();
														$("#amendFileName").show();
														//隐藏文件上传框
														$("#filebox_div").hide();
														$("#amendFileName").html("文件名:  "+data.file.attachFile);
														//下载文件
														$('#amendDownloadFile').attr("href","../../downloadFile.json?issueId="+issueId+"");
														//删除文件
														$("#amendDeleteFile").attr("onclick","removeFile("+issueId+")");
													}												
												}
											})
											//打开修改面板
											$("#amend_win").window('open');
											
										}
									}
								})
							}
						}
					})
				}
			}
		}
	})
	//清除选中行
	$("#tt").datagrid('clearSelections');
	
}

/*
 * 在修改面板中删除文件
 */
function removeFile(issueId){	
	//删除成功后隐藏下载，预览，删除按钮
	$("#amendFileName").hide();
	$("#amendDelete").hide();
	$("#amendDownload").hide();
	$("#amendPreview").hide();
	$("#filebox_div").show();
}

/*
 * 提交修改信息
 */
function updateProjectData(){
	
	$("#amend_form").form('submit',{
		url:"../../updateProjectData.json",
		onSubmit: function(){    
	         
	    },    
	    success:function(data){    
	    	var data = eval('(' + data + ')');
	    	if(data.result != 0 && data.rel != 0){
	    		$.messager.alert('提示','修改成功');
	    		$("#tt").datagrid('reload');
	    		$("#amend_form").form('clear');
	    		$("#amend_win").window('close')
	    	}else{
	    		$.messager.alert('提示','修改失败');
	    	}
	    }    

	})
}




/**
 * 给增加按钮绑定事件
 */
function showAddPanel(){
	$("#add_win").dialog({
		title:'新增缺陷'
	})
	url = '../../judgeUserProjectName.json';
	//查询当前用户是否具有增加缺陷的权限
	$.ajax({
		url:url,
		dataType:'json',
		success:function(data){
			if(data.result == 0){
				$.messager.alert('提示','没有该权限');
			}else{
				//给表单控件设置禁用
				setInputDisabled();
				//选中项目之后解除禁用
				$("#proName").combobox({
					onSelect: function () {
						//给表单控件设置启用
						setInputUse();
						//给表单控件设置必填
						setInputRequired();
						  var projectId = $("#proName").combobox('getValue');
				            var projectName = $("#proName").combobox('getText');
				            //查询当前用户在当前项目中是否具有增加的权限
				            $.ajax({
				            	url:"../../DetermineUserProjectRoles.json",
				            	data:{"projectId":projectId,"projectName":projectName},
				            	success:function(data){
				            		if(data.rel == 0){
				            			$.messager.alert('提示','您在当前项目中尚不具备该权限');
				            			$("#add_form").form('clear');
				            			//设置表单禁用
				            			setInputDisabled();
				            		}else{
				            			//根据项目名生成对应的父级缺陷
				            			$.ajax({
				            				url:"../../DetermineUserProjectRoles.json",
				            				data:{"projectId":projectId,"projectName":projectName},
				            				success:function(data){
					            					$('#parentId').combobox({
					    								data:data.res, 						
					    								panelHeight:'auto',
					    							    valueField:'parentId',    
					    							    textField:'parentName'   
					    							});
				            					}
				            			})
				            			//给增加面板里面的缺陷状态增加数据
				            			$.ajax({
												url:"../../searchState.json",
												dataType:"json",
												success:function(data){
													
													//过滤函数，n:表示数组的单个实体，i:表示数组的索引。
													data.result=$.grep(data.result,function(n,i){
														
														return i == 0;
														});
													console.log(data.result)
													$('#state').combobox({
														data:data.result, 
														panelHeight:'auto',
													    valueField:'stateCode',    
													    textField:'stateDesc'   
													});
												}
											});
				            			
				            		}
				            	}
				            })
					}
				})
				//打开新增面板
				$('#add_win').window('open')
				//点击框架自带的关闭按钮后执行的操作
				afterCloseDo();
				//打开新增面板时改变url
				url = "../../addProjectData.json";
			}
		}
	})
	
}

/**
 * 提交增加数据
 */

function addProjectData(){
	//表单验证
	if($("#add_form").form('validate')){
		//提交新增数据
		$("#add_form").form('submit',{
			url:url,
			onSubmit: function(){    
		        
		    },    
		    success:function(data){    
		    	var data = eval('(' + data + ')');
		    	if(data.result != 0 && data.rel != 0){
		    		$.messager.alert('提示','增加成功');
		    		$("#tt").datagrid('reload');
		    		$("#add_form").form('clear');
		    		$("#add_win").window('close')
		    	}else{
		    		$.messager.alert('提示','增加失败');
		    	}
		    }    

		})
	}else{
		$.messager.alert('提示','输入不正确');
	}
	
}

/**
 * 详情页面
 */

function particulars(issueId,index){
	//查询该条信息是否有附带的文件
	$.ajax({
		url:"../../queryIsFile.json",
		data:{"issueId":issueId},
		success:function(data){
			if(data.result == 0){
				$("#download").css("display","none");
				$("#preview").hide();
				$("#fileName").hide();
			}else{
				$("#download").css("display","block");
				$("#fileName").html("文件名:  "+data.file.attachFile);
				$('#downloadFile').attr("href","../../downloadFile.json?issueId="+issueId+"");
				$('#previewPicture').attr("onclick","previewPicture("+issueId+");"); 
				if(data.file.isPic == 1){
					$("#preview").show();
					
				}else{
					$("#preview").hide();
				}
			}
			
			
		}
	})
	title = '缺陷详情';
	//查询缺陷信息
	$.ajax({
		url:"../../searchProjectDataByIssueId.json",
		data:{"issueId":issueId},
		success:function(data){
			var projectData = data.bo;
			console.log(projectData.doneCondition)
			var doneCondition = projectData.doneCondition.split("\r\n");
			var issueDesc = projectData.issueDesc.split("\r\n");
			//按照指定格式将数组转成字符串
			doneCondition = doneCondition.join("<br/>");
			issueDesc = issueDesc.join("<br/>");
			//console.log(doneCondition)
			//单独给详情页面里面的表单控件赋值
			$("#issueDescHide").html(issueDesc);
			$("#userName").html(projectData.userName);
			$("#doneConditionHide").html(doneCondition);
			
			//给该项目的上级缺陷赋值
			var parentId = projectData.parentId;
			if(parentId === null){
				$("#particulars_form").form('load',projectData);
			}else{
				$.ajax({
					url:"../../searchParentId.json",
					data:{"issueId":issueId,"parentId":parentId},
					success:function(data){
						$("#particulars_form").form('load',projectData);
						$("#particularsParentId").val(data.bjb.parentName);

					}
				})
			}
			if( data.bo.doneRatio != null){	
				$("#doneRatio").html( projectData.doneRatio);
			}else{
				$("#doneRatio").html("——");
			}
			//打开详情面板
			$("#addBtn").click(showAddPanel);
		}
	})
	//关闭面板时清空表单
	$("#particulars_win").dialog({
		title:title,
		onClose:function(){
			$("#particulars_form").form('clear');
			$("#fileName").html("");
			$("#div_img").hide();
			$("#showHeadImg").attr("src","");
		}
	})
}


/**
 * 预览图片
 */
function previewPicture(issueId){
	$.ajax({
		url:"../../previewPicture.json",
		data:{"issueId":issueId},
		success:function(data){
			var url = data.url.substring(data.url.indexOf("\\"));
			$("#div_img").show();
			$("#showHeadImg").attr("src",url);
		}
	})
}


/**
 * 删除数据
 */
function removeProjectData(){
	//获取当前选中行数据
	var rows = $("#tt").datagrid('getSelections');	
	//判断有无选中的数据
	if(rows.length == 1){
		var issueId = rows[0].issueId;
		var projectId = rows[0].projectId;
		//判断当前用户有无删除的权限
		$.ajax({
			url:"../../judgeUserProjectName.json",
			data:{"issueId":issueId},
			success:function(data){
				if(data.result == 0){
					$.messager.alert('提示','尚不具备该权限');
				}else{
					//判断该项目是否支持删除
					$.ajax({
						url:"../../searchProjectissueState.json",
						data:{"issueId":issueId},
						success:function(data){
							if(data.result.issueState != "001"){
								$.messager.alert('提示','当前项目不支持该操作');
							}else{
								//判断当前用户在当前项目中有无删除的权限
								$.ajax({
									url:"../../DetermineUserProjectRoles.json",
									data:{"issueId":issueId,"projectId":projectId},
									success:function(data){
										if(data.rel == 0){
											$.messager.alert('提示','您在当前项目中不具备该权限');
										}else{
											$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
											    if (r){    
											    	$.ajax({
														url:"../../removeProjectData.json",
														data:{"issueId":issueId},
														success:function(data){
															if(data.rel != 0 ){
																$.messager.alert('提示','删除成功');
																$("#tt").datagrid('reload');
															}else{
																$.messager.alert('提示','删除失败');
															}											
														}
													})
											    }    
											});							
										}
									}
								})
							}
						}
					})
			
				}
			}
		})
	}else{
		$.messager.alert('提示','请选择要删除的数据');
	}	
}

/*
 * 给增加面板里面的表单空间设置禁用
 */
function setInputDisabled(){
	$("#parentId").textbox({disabled:true});
	$("#issueName").textbox({disabled:true});
	$("#issueDesc").textbox({disabled:true});
	$("#doneConditionId").textbox({disabled:true});
	$("#type").textbox({disabled:true});
	$("#severity").textbox({disabled:true});
	$("#priority").textbox({disabled:true});
	$("#state").textbox({disabled:true});
	$("#planWorkHours").textbox({disabled:true});
	$("#start").textbox({disabled:true});
	$("#end").textbox({disabled:true});
	$("#filebox").filebox({disabled:true});
}

/*
 * 给增加面板里面的表单控件设置启用
 */
function setInputUse(){
	$("#parentId").textbox({disabled:false});
	$("#issueName").textbox({disabled:false});
	$("#issueDesc").textbox({disabled:false});
	$("#doneConditionId").textbox({disabled:false});
	$("#type").textbox({disabled:false});
	$("#severity").textbox({disabled:false});
	$("#priority").textbox({disabled:false});
	$("#state").textbox({disabled:false});
	$("#planWorkHours").textbox({disabled:false});
	$("#start").textbox({disabled:false});
	$("#end").textbox({disabled:false});
	$("#filebox").filebox({disabled:false});
}

/*
 * 给增加面板里面的表单控件设置必填
 */
function setInputRequired(){
	$("#parentId").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});

	$("#issueName").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#issueDesc").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#doneConditionId").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#type").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#severity").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#priority").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});

	$("#state").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#planWorkHours").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#start").textbox({
		required:true,
		missingMessage:"该项不能为空",
	});
	
	$("#end").textbox({
		required:true,
		missingMessage:"该项不能为空",
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

