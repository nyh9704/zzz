/**
 * 预加载事件
 */
$(function(){
	//加载树数据
	createTreeDataGrid();
	//导出按钮汉化
	exportTheLocalization();
	//查询缺陷状态数据
	searchIssueStateData();
	//加载缺陷状态数据
	loadIssueStateData();
	//给查询按钮绑定单击事件
	$("#searchBtn").click(keyWordSearch);
	//给日期框添加清空按钮
	clearDateButton();
	
})



/**
 * 全局变量
 */
var issueStateData = [];




/**
 * 关键字查询
 */
function keyWordSearch(){
	$.ajax({
		url:"../../searchIssueStateData.json",
		data:$("#serchForm").serializeArray(),
		type:"post",
		dataType:"json",
		success:function(data){
			issueStateData = data.list;
			if(issueStateData.length != 0){
				
				loadIssueStateData();
			}else{
				$.messager.alert('提示','未查到数据,重新加载');
				$("#serchForm").form('clear');
				searchIssueStateData();
				loadIssueStateData();
			}
		}
	});
	
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
			$('#projectName').combotree('loadData', fomatTreeVlaue(data.parent));
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
 * 加载缺陷状态柱状图数据
 */
function loadIssueStateData(){
	var chart = Highcharts.chart('issueStateData', {
		chart: {
			type: 'column'
		},
		title: {
			text: '缺陷状态分布'
		},
		
		xAxis: {
			type: 'category',
			labels: {
				rotation: -45  // 设置轴标签旋转角度
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: '数量 (个)'
			}
		},
		legend: {
			enabled: false
		},
		tooltip: {
			pointFormat: '缺陷状态总量: <b>{point.y:.1f} 个</b>'
		},
		series: [{
			name: '缺陷状态分布',
			data:issueStateData,
			dataLabels: {
				enabled: true,
				rotation: -90,
				color: '#FFFFFF',
				align: 'right',
				format: '{point.y:.1f}', // :.1f 为保留 1 位小数
				y: 10
			}
		}]
	});
	
	
	
}


/**
 * 查询缺陷状态数据
 */
function searchIssueStateData(){
	$.ajax({
		url:'../../searchIssueStateData.json',
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			
			issueStateData = data.list;
			console.log(data.list)
			console.log(issueStateData)
		}
	});
}


/**
 * 导出按钮汉化
 */
function exportTheLocalization(){
	Highcharts.setOptions({
        lang:{
        	printChart:"打印图表",
            downloadJPEG: "下载JPEG 图片" , 
            downloadPDF: "下载PDF文档"  ,
            downloadPNG: "下载PNG 图片"  ,
            downloadSVG: "下载SVG 矢量图" , 
            downloadCSV: '下载 CSV  文件',
    		downloadXLS: '下载 XLS   文件',
            exportButtonTitle: "导出图片" 
        }
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


