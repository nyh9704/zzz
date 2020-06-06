/**
 * 预加载事件
 */
$(function(){
	//加载树数据
	createTreeDataGrid();
	//导出按钮汉化
	exportTheLocalization();
	//查询缺陷严重程度环形图数据
	searchIssueSeverityData();
	//加载缺陷严重程度环形图图数据
	loadIssueSeverityData();
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(keyWordSearch);
	//给日期框添加清空按钮
	clearDateButton();
})


/**
 * 全局变量
 */
var issueSeverityData = [];


/**
 * 关键字查询
 */
function keyWordSearch(){
	$.ajax({
		url:"../../searchIssueSeverityData.json",
		data:$("#serchForm").serializeArray(),
		type:"post",
		dataType:"json",
		success:function(data){
			issueSeverityData = data.list;
			if(issueSeverityData.length != 0){
				alert(1)
				loadIssueSeverityData();
			}else{
				$.messager.alert('提示','未查到数据,重新加载');
				$("#serchForm").form('clear');
				searchIssueSeverityData();
				loadIssueSeverityData();
			}
		}
	})
	
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
 * 加载缺陷严重程度环形图数据
 */
function loadIssueSeverityData(){
	var chart = Highcharts.chart('issueSeverityData', {
		chart: {
			spacing : [40, 0 , 40, 0]
		},
		title: {
			floating:true,
			text: '缺陷严重程度'
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		exporting: {

			//enabled:true,默认为可用，当设置为false时，图表的打印及导出功能失效

			buttons: { //配置按钮选项

				printButton: { //配置打印按钮

					width: 50,

					symbolSize: 20,

					borderWidth: 2,

					borderRadius: 0,

					hoverBorderColor: 'red',

					height: 30,

					symbolX: 25,

					symbolY: 15,

					x: -200,

					y: 20

				},

				exportButton: { //配置导出按钮

					width: 50,

					symbolSize: 20,

					borderWidth: 2,

					borderRadius: 0,

					hoverBorderColor: 'red',

					height: 30,

					symbolX: 25,

					symbolY: 15,

					x: -150,

					y: 20

				},

			},

			filename: '52wulian.org', //导出的文件名

			type: 'image/png', //导出的文件类型

			width: 800 //导出的文件宽度

		},
		
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.percentage:.1f} %',
					style: {
						color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					}
				},
				point: {
					events: {
						mouseOver: function(e) {  // 鼠标滑过时动态更新标题
							// 标题更新函数，API 地址：https://api.hcharts.cn/highcharts#Chart.setTitle
							chart.setTitle({
								text: e.target.name+ '\t'+ e.target.y + ' 个'
							});
						}
						//, 
						// click: function(e) { // 同样的可以在点击事件里处理
						//     chart.setTitle({
						//         text: e.point.name+ '\t'+ e.point.y + ' %'
						//     });
						// }
					}
				},
			}
		},
		series: [{
			type: 'pie',
			innerSize: '80%',
			name: '缺陷严重程度',
			data: issueSeverityData
		}]
	}, function(c) { // 图表初始化完毕后的会掉函数
		// 环形图圆心
		var centerY = c.series[0].center[1],
			titleHeight = parseInt(c.title.styles.fontSize);
		// 动态设置标题位置
		c.setTitle({
			y:centerY + titleHeight/2
		});
	});


}


/**
 * 查询缺陷严重程度分类数据
 */
function searchIssueSeverityData(){
	$.ajax({
		url:'../../searchIssueSeverityData.json',
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			issueSeverityData = data.list;
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
            downloadCSV: '下载 CSV 文件',
    		downloadXLS: '下载 XLS 文件',
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
