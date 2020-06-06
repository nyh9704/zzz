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
	searchUserPartAndDuty();
	//给增加按钮绑定单击事件
	$("#addBtn").click(function(){
		//$("#personalData_div").ajaxLoading
		searchLeaderAndPart();
		url = '../../saveClientInfo.json';
		$('#clientInfo_div').dialog({
			title : '新增客户信息',
			closed : false
		});
		
	});
	
	//给关闭按钮绑定单击事件
	$("#closeBtn").click(closeBtn);
	//给提交按钮绑定单击事件
	$("#submitBtn").click(saveClientInfo);
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(searchKeyWorld);
	//生成下拉列表里面的值
	searchAllGroupLeader();
	//给删除按钮绑定单击事件
	$("#removeBtn").click(deleteClientInfo)
})

/**
 * 关键字查询
 */
function searchKeyWorld(){
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}

/**
 * 查询当前登陆用户属于哪个部门
 */
function searchUserPartAndDuty(){
	$.ajax({
		url:"../../searchUserPartAndDuty.json",
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.part == ""){
				$("#addBtn").css('display','inline-block');
				$("#modifyBtn").css('display','inline-block');
				$("#removeBtn").css('display','inline-block');
				$("#sse").css('display','inline-block');
				searchPersonalData();
			}else if(data.part[0] == '0903' || data.part[0] == ''){
				if(data.duty == 1){
					$("#addBtn").css('display','inline-block');
					$("#modifyBtn").css('display','inline-block');					
					$("#sse").css('display','inline-block');
					$("#removeBtn").css('display','none');
				}else{
					$("#addBtn").css('display','inline-block');
					$("#modifyBtn").css('display','inline-block');					
					$("#sse").css('display','inline-block');
					$("#removeBtn").css('display','inline-block');
				}
				
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
 * 分页查询
 */
function searchPersonalData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchClientData.json",
		columns:[[
			{field:'cDate',title:'日期',width:'10%',align:'center',sortable:true},	     
			{field:'cTime',title:'到访时间',width:'10%',align:'center',
				formatter:function(value,row,index){
					if(row.cTime == null){
						return "——"
					}else{
						return row.cTime.substring(11,16);
					}					
				}
			},			
	        {field:'cName',title:'客户姓名',width:'10%',align:'center'},
	        {field:'cTel',title:'客户电话',width:'10%',align:'center'},
	        {field:'cCounselor',title:'客户负责人',width:'10%',align:'center'},
	        {field:'cLeader',title:'上级领导',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cLeader == ""){ 
	        			return "——";
	        		}else{
	        			return row.cLeader;
	        		}
	        	}
	        }, 	       
	        {field:'cPart',title:'所属部门',width:'10%',align:'center'},
	        {field:'cDecider',title:'谈单人',width:'10%',align:'center'},
	        {field:'cAddress',title:'谈单地点',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cAddress == ""){ 
	        			return "——";
	        		}else{
	        			return row.cAddress;
	        		}
	        	}
	        },
	        {field:'cFrequency',title:'到访次数',width:'10%',align:'center'}  
	    ]]	   
	});
}

/**
 * 根据所填的顾问名字生成对应的上级领导和部门
 */
function searchLeaderAndPart(){	
	$("#cCounselor").textbox({
		onChange:function(newValue){
			$.ajax({
				type:'post',
				url:"../../searchLeaderAndPart.json",
				dataType:'json',
				data:{"cCounselor":newValue},
				success:function(data){
					$("#ccPart").textbox('setValue',data.part.wpName);
					if(data.leader == null){
						$("#cLeader").combobox('clear');
						$("#cLeader").combobox('loadData', {});
					}else{
						$("#cLeader").combobox({
							valueField: 'cLeader',
							textField: 'cLeader',
							data:data.leader,
							panelHeight:'auto',
							icons:[{
								iconCls:'icon-clear',
								handler: function(e){
								$(e.data.target).combobox('clear');
								}
								}]
						})
					}
					
				}
			})
		}
	})
}

/**
 * 提交到访客户信息
 */
function saveClientInfo(){
	var obj = $("#clientInfo_form").serializeObject();
	
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		data:$("#clientInfo_form").serializeObject(),
		success:function(data){
			if(data.result == '1'){
				$.messager.alert('提示','操作成功');
				$("#tt").datagrid('reload');
				$("#cLeader").combobox('loadData', {});
				$("#clientInfo_form").form('clear');
				$("#clientInfo_div").window('close');
			}else{
				$.messager.alert('提示','操作成功');
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
		$('#clientInfo_div').dialog({
			title : '修改房源信息',
			closed : false
		});
		//根据所填顾问自动生成该顾问的所在部门和上级领导
		searchLeaderAndPart();
		url = "/training/searchModifyClientInfo.json?cId=" + info[0].cId + "";
		$.ajax({
			url : url,
			dataType : "json",
			success : function(data) {
				$("#clientInfo_form").form("load", data.result);
				$("#cLeader").combobox('setValue',data.result.cLeader);
			}
		});
		url = "/training/modifyClientInfo.json";

	} else{
		$.messager.alert("提示", "请选择一条数据修改");
	}
}


/**
 * 删除客户资料
 */
function deleteClientInfo(){
	var info = $("#tt").datagrid('getSelections');
	if (info.length > 0) {
		url = "../../deleteClientInfo.json?cId=" + info[0].cId + "";
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
 * 清空表单
 */
function clearForm(){
	$("#serchForm").form('clear');
}

