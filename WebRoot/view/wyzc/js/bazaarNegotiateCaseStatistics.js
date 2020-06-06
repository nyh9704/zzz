/**
 * 全局变量
 */
var url;
var duty;
/**
 * 预加载事件
 */
$(function(){
	//判断当前登陆用户权限
	//searchUserPartAndDuty();
	//分页查询
	searchBazaarNegotiateCaseStatistics();
	//给增加按钮绑定单击事件
	$("#addBtn").click(function(){
		$("#cResult").textbox('setValue','签单');
		$("#cAddress").textbox('setValue','网签');
		$("#cIsonline").textbox('setValue','是');
		url = '../../saveNegotiateCaseStatistics.json';
		$('#clientInfo_div').dialog({
			title : '新增客户信息',
			closed : false
		});
		searchDepartOfDecider();
	});
	
	//给关闭按钮绑定单击事件
	$("#closeBtn").click(closeBtn);
	//给具体情况窗口里面的关闭按钮绑定单击事件
	$("#conditionCloseBtn").click(closeWin);
	//给补全结果窗口里面的关闭按钮绑定单击事件
	$("#complementCloseBtn").click(closeComplementWin);
	//给提交按钮绑定单击事件
	$("#submitBtn").click(saveClientInfo);
	//给不全结果窗口的提交按钮绑定单击事件
	$("#complementSubmitBtn").click(saveComplementClientInfo);
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(searchKeyWorld);
	//生成下拉列表里面的值
	searchAllGroupLeader();
	//给删除按钮绑定单击事件
	$("#removeBtn").click(deletenegotiateCaseStatistics);
})

/**
 * 关键字查询
 */
function searchKeyWorld(){
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}

/**
 * 权利的游戏之行政部门
 */
function administrativeGameOfThrones(){
	$.ajax({
		url:"../../searchUserPartAndDuty.json",
		type:"post",
		dataType:"json",
		success:function(data){
			console.log(data.leaderPart)
			if(data.part == ""){
				$("#addBtn").css('display','inline-block');
				$("#modifyBtn").css('display','inline-block');
				$("#removeBtn").css('display','inline-block');
				$("#sse").css('display','inline-block');
				searchPersonalData();
			}else if(data.part[0] == '0903' || data.part[0] == ''){
				$("#addBtn").css('display','inline-block');
				$("#modifyBtn").css('display','inline-block');
				$("#removeBtn").css('display','inline-block');
				$("#sse").css('display','inline-block');
				searchPersonalData();
			}else{
				$("#addBtn").css('display','none');
				$("#modifyBtn").css('display','none');
				$("#removeBtn").css('display','none');
				$("#sse").css('display','none');
				searchPersonalData();
			}
		}
	})
}

/**
 * 查所有的主管
 */
function searchAllGroupLeader(){
	$.ajax({
		url:"../../searchAllGroupLeader.json",
		dataType:"json",
		success:function(data){
			$("#selcCounselor").combobox({
				valueField: 'cCounselor',
				textField: 'cCounselor',
				data:data.result,
				panelHeight:'auto',
				editable:false,
				icons:[{
					iconCls:'icon-clear',
					handler: function(e){
						$(e.data.target).combobox('clear');
					}
					}]
			});
			
			$("#cPart").combobox({
				valueField: 'cPart',
				textField: 'cPart',
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
		}
	})
}

/**
 * 查看具体情况
 */
function showcCondition(){
	var data = $("#tt").datagrid('getSelected');
	var arr = data.cCondition.split("\r\n");
	//按照指定格式将数组转成字符串
	var str = arr.join("<br/>");
	$("#condition_p").html(str);
	$("#condition_div").window('open');
}




/**
 * 分页查询
 */
function searchBazaarNegotiateCaseStatistics(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchBazaarNegotiateCaseStatistics.json",
		columns:[[
			{field:'cDate',title:'日期',width:'10%',align:'center',sortable:true},	     
			{field:'cTime',title:'谈单时间',width:'10%',align:'center',
				formatter:function(value,row,index){
					if(row.cTime == null){
						return "——"
					}else{
						return row.cTime.substring(11,16);
					}					
				}
			},			
	        {field:'cName',title:'客户姓名',width:'10%',align:'center'},
	        {field:'cCounselor',title:'客户负责人',width:'10%',align:'center'},
	        {field:'cDecider',title:'谈单人',width:'10%',align:'center'},
	        {field:'cDepart',title:'谈单人所属部门',width:'10%',align:'center'},	       
	        {field:'cAddress',title:'谈单地点',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cAddress == null){ 
	        			return "——";
	        		}else{
	        			return row.cAddress;
	        		}
	        	}
	        },
	        {field:'cResult',title:'谈单结果',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cResult == null){ 
	        			return "——";
	        		}else{
	        			return row.cResult;
	        		}
	        	}
	        },
	        {field:'cCondition',title:'具体情况',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cCondition == null){ 
	        			return "——";
	        		}else{
	        			return "<a style='color:blue;text-decoration:none' href='javascript:showcCondition()'>具体情况</a>";
	        		}
	        	}
	        },  
	        {field:'cIsonline',title:'是否网签',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cIsonline == null){ 
	        			return "——";
	        		}else{
	        			return row.cIsonline;
	        		}
	        	}
	        }  
	    ]]	   
	});
}

/**
 * 根据所填的谈单人姓名生成对应的部门
 */
function searchDepartOfDecider(){	
	$("#cDecider").textbox({
		onChange:function(newValue){
			$.ajax({
				type:'post',
				url:"../../searchDepartOfDecider.json",
				dataType:'json',
				data:{"cDecider":newValue},
				success:function(data){
					$("#cDepart").textbox('setValue',data.rsult);					
				}
			})
		}
	})
}

/**
 * 提交到访客户信息
 */
function saveClientInfo(){
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		data:$("#clientInfo_form").serializeObject(),
		success:function(data){
			if(data.result == '1'){
				$.messager.alert('提示','操作成功');
				$("#tt").datagrid('reload');
				$("#clientInfo_form").form('clear');
				$("#clientInfo_div").window('close');
			}else{
				$.messager.alert('提示','操作失败');
			}
		}
	})
}


/**
 * 提交到访客户信息
 */
function saveComplementClientInfo(){
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		data:$("#complement_form").serializeObject(),
		success:function(data){
			if(data.result == '1'){
				$.messager.alert('提示','操作成功');
				$("#tt").datagrid('reload');
				$("#complement_form").form('clear');
				$("#complement_div").window('close');
			}else{
				$.messager.alert('提示','操作失败');
			}
		}
	})
}


/**
 * 修改客户资料
 */
function doModify() {
	$("#clientInfo_form").form('clear');
	var info = $("#tt").datagrid('getSelections');
	if (info.length > 0) {
		if(info[0].cIsonline == '是'){
			$.messager.alert("提示", "该单子为网签，不支持补全结果");
		}else{
			$('#complement_div').dialog({
				title : '修改谈单情况',
				closed : false
			});
			//根据编号查询信息
			url = "/training/searchNegotiateCaseStatisticsById.json?cId=" + info[0].cId + "";
			$.ajax({
				url : url,
				dataType : "json",
				success : function(data) {
					$("#complement_form").form("load", data.result);
				}
			});
			url = "/training/modifyBazaarNegotiateCaseStatisticsById.json";
		}
				
	} else{
		$.messager.alert("提示", "请选择一条数据修改");
	}
}


/**
 * 删除客户资料
 */
function deletenegotiateCaseStatistics(){
	var info = $("#tt").datagrid('getSelections');
	if (info.length > 0) {
		url = "../../deletenegotiateCaseStatistics.json?cId=" + info[0].cId + "";
		$.messager.confirm('确认', '您确认想要删除记录吗？此操作不可返回请酌情考虑', function(r) {
			if (r) {
				$.ajax({
					url : url,
					dataType : "json",
					success : function(data) {
						if(data.result != 0){
							$("#tt").datagrid("reload");
							$.messager.alert("提示", "删除成功！");
						}else{
							$.messager.alert("提示", "删除失败！");
						}
						
					}
				})
			} else {
				$("#tt").datagrid("reload");
			}
		});

	} else {
		$.messager.alert("提示", "请选择一条数据删除！")
	}
}

/**
 * 弹框关闭按钮
 */
function closeBtn(){
	$("#clientInfo_form").form('clear');
	$('#clientInfo_div').dialog({
		closed : true
	});
	
	$("#cLeader").combobox('clear');
}

/**
 * 关闭补全结果窗口
 */
function closeComplementWin(){
	$("#complement_form").form('clear');
	$('#complement_div').dialog({
		closed : true
	});
}



/**
*今日查询
*/
function todayVisit(){
	var data = $("#serchForm").serializeObject();
	$("#tt").datagrid("load",
			{visitData:"1",
			keyWorld:data.keyWorld,
			counselor:data.counselor,
			cCounselor:data.cCounselor,
			startTime:data.startTime,
			endTime:data.endTime
			});
}

/**
* 昨日查询
*/
function yestodayVisit(){
	var data = $("#serchForm").serializeObject();
	$("#tt").datagrid("load",
			{visitData:"2",
			keyWorld:data.keyWorld,
			counselor:data.counselor,
			cCounselor:data.cCounselor,
			startTime:data.startTime,
			endTime:data.endTime
			});
}

/**
* 三日内查询
*/
function threeDaysVisit(){
	var data = $("#serchForm").serializeObject();
	$("#tt").datagrid("load",
			{visitData:"3",
			keyWorld:data.keyWorld,
			counselor:data.counselor,
			cCounselor:data.cCounselor,
			startTime:data.startTime,
			endTime:data.endTime
			});
}

/**
* 一周内查询
*/
function oneWeekVisit(){
	var data = $("#serchForm").serializeObject();
	$("#tt").datagrid("load",
			{visitData:"4",
			keyWorld:data.keyWorld,
			counselor:data.counselor,
			cCounselor:data.cCounselor,
			startTime:data.startTime,
			endTime:data.endTime
			});
}

/**
* 一月内查询
*/
function oneMonthVisit(){
	var data = $("#serchForm").serializeObject();
	$("#tt").datagrid("load",
			{visitData:"5",
			keyWorld:data.keyWorld,
			counselor:data.counselor,
			cCounselor:data.cCounselor,
			startTime:data.startTime,
			endTime:data.endTime
			});
}

/**
 * 关闭具体情况窗口
 */
function closeWin(){
	$("#condition_div").window('close');
}



/**
 * 重置数据+清空表单
 */
function resetData(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}
