/**
 * 预加载项目
 */
$(function(){
	//加载树数据
	createTreeDataGrid();
	//分页查询
	searchData();
	//关键字查询
	$("#searchBtn").click(keywordSearch);
	//给时间框添加清空按钮
	clearDateButton();
})

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




/*
 * 加载树
 */
function createTreeDataGrid(){
	$.ajax({  
		url: "../../ProjectMangementUrl.json",  
		type: 'post',
		dataType: 'json', 
		data:{projectstate:'01', pprojectid:"%root%"},
		success:function(data) {
			var one="{projectid:'',projectname:'全部'}";
			var obj=eval('('+one+')');
			data.parent.unshift(obj);//将新增的对象添加到对象数组的第一个
			$('#cc').combotree('loadData', fomatTreeVlaue(data.parent));
		}
	});
}


/*
 * 转换成固定格式
 */
function fomatTreeVlaue(data){
	for(var i=0;i<data.length;i++){
		data[i].id=data[i].projectid;
		data[i].text=data[i].projectname;
		if(data[i].children !=null && data[i].children.length>0){
			fomatTreeVlaue(data[i].children)
		}	
	}
	return data
}

/**
 * 分页查询
 */
function searchData(){
	$("#tt").datagrid({
		url:"../../searchDailyData.json",
		columns:[[    
	        {field:'projectName',title:'项目名称',width:'25%',align:'center'},
	        {field:'workCreateBy',title:'登记人',width:'25%',align:'center'}, 
	        {field:'workCreateTime',title:'登记时间',width:'25%',align:'center'},
	        {field:'workHours',title:'登记工时',width:'25%',align:'center'}   
	    ]]	   
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
