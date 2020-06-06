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
	
})

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
		url:"../../searchnegotiateRecord.json",
		columns:[[
			{field:'cDate',title:'日期',width:'12%',align:'center',sortable:true},	     
			{field:'cTime',title:'谈单时间',width:'12%',align:'center',
				formatter:function(value,row,index){
					if(row.cTime == null){
						return "——"
					}else{
						return row.cTime.substring(11,16);
					}					
				}
			},			
	        {field:'cName',title:'客户姓名',width:'12%',align:'center'},
	        {field:'cCounselor',title:'客户负责人',width:'12%',align:'center'},
	        {field:'cDecider',title:'谈单人',width:'12%',align:'center'},
	        {field:'cDepart',title:'谈单人所属部门',width:'12%',align:'center'},
	        {field:'cAddress',title:'谈单地点',width:'12%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.cAddress == ""){ 
	        			return "——";
	        		}else{
	        			return row.cAddress;
	        		}
	        	}
	        },
	        {field:'cResult',title:'谈单结果',width:'12%',align:'center'}  
	    ]]	   
	});
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
 * 今日查询
 */
function todayVisit(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load",{todayVisit:"1"});
}

/**
 * 昨日查询
 */
function yestodayVisit(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load",{yestodayVisit:"2"});
}

/**
 * 三日内查询
 */
function threeDaysVisit(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load",{threeDaysVisit:"3"});
}

/**
 * 一周内查询
 */
function oneWeekVisit(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load",{oneWeekVisit:"4"});
}

/**
 * 一月内查询
 */
function oneMonthVisit(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load",{oneMonthVisit:"5"});
}






/**
 * 重置数据+清空表单
 */
function resetData(){
	$("#serchForm").form('clear');
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
}

