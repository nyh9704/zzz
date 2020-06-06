/**
 * 全局变量
 */
var url;
var duty;
/**
 * 预加载事件
 */
$(function(){		
	//给搜索按钮绑定单击事件
	$("#searchFree").click(searchKeyWorld);
	//分页查询
	searchPersonalData();
	searchAllGroupLeader();
	gGameOfThrones();
})

/**
 * 关键字查询
 */
function searchKeyWorld(){
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}

/**
 * 查询组长
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
			
		}
	})
}


/**
 * 权利的游戏
 */
function gGameOfThrones(){
	$.ajax({
		url:"../../gGameOfThrones.json",
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.duty == "4" || data.duty == '1'){
				$("#game").css('display','none');
			}else{
				$("#game").css('display','inline-block');
			}
		}
		
	})
}




/**
 * 分页查询
 */
function searchPersonalData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchInviteCaseStatistics.json",
		columns:[[
			{field:'cDate',title:'日期',width:'14%',align:'center',sortable:true},	     
	        {field:'cName',title:'客户姓名',width:'14%',align:'center'},
	        {field:'cTel',title:'客户电话',width:'14%',align:'center'},
	        {field:'cCounselor',title:'客户负责人',width:'14%',align:'center'},
	        {field:'cLeader',title:'上级领导',width:'14%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cLeader == ""){ 
	        			return "——";
	        		}else{
	        			return row.cLeader;
	        		}
	        	}
	        }, 	       
	        {field:'cPart',title:'所属部门',width:'14%',align:'center'},
	        {field:'cFrequency',title:'到访次数',width:'14%',align:'center'}  
	    ]]	   
	});
}


/**
 * 今日查询
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
 * 重置数据+清空表单
 */
function resetData(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}

