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
	//时间下拉框-起始时间
	showYearAndMounth("startTime");
	//时间下拉框-截止时间
	showYearAndMounth("endTime");
	//给日期框增加清空按钮
	clearDateButton();
})

/**
 * 关键字查询
 */
function keywordSearch(){
	var startTime = $("#startTime").datebox('getValue');
	var endTime = $("#endTime").datebox('getValue');
	console.log(startTime);
	console.log(endTime);
	var obj=$("#serchForm").serializeObject();
	if(startTime != null && startTime != ""){
		obj.startTime = obj.startTime+"-01 00:00:00";
		
	}
	
	if(endTime != null && endTime != ""){
		obj.endTime = obj.endTime+"-31 23:59:59";
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
		url:"../../searchMounthData.json",
		columns:[[    
	        {field:'projectName',title:'项目名称',width:'25%',align:'center'},
	        {field:'workCreateBy',title:'登记人',width:'25%',align:'center'}, 
	        {field:'workCreateTime',title:'登记时间',width:'25%',align:'center'},
	        {field:'workHours',title:'登记工时',width:'25%',align:'center'}   
	    ]]	   
	});
}

/*
 * 日期下拉框只显示年月
 */
function showYearAndMounth(world){
	$("#"+world).datebox({
        height: 26,
        width: 130,
        editable: false,
        onShowPanel: function () {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); // 触发click事件弹出月份层
            if (!tds)
                setTimeout(function() { //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function(e) {
                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                        var year = /\d{4}/.exec(span.html())[0] //得到年份
                            ,month = parseInt($(this).attr('abbr'), 10); //月份 之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1;
                        $("#"+world).datebox('hidePanel') //隐藏日期对象
                            .datebox('setValue', year + '-' + month); //设置日期的值
                    });
                }, 0);
        },
        parser: function (s) {//配置parser，返回选择的日期
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) {
            if (d.getMonth() == 0) {
                return d.getFullYear() - 1 + '-' + 12;
            } else {
                var tmp_month = d.getMonth()+1;
                if (tmp_month < 10) {
                    tmp_month = '0' + tmp_month;
                }
                return d.getFullYear() + '-' + tmp_month;
            }
        },// 配置formatter，只返回年月
        onChange: function (newDate, oldDate) {
 
            var arr = newDate.split('-');
            var tmp_year = parseInt(arr[0], 10);
            var tmp_month = parseInt(arr[1], 10);
            if (tmp_month < 10) {
                tmp_month = '0' + tmp_month;
            }
            m_date = tmp_year + '-' + tmp_month;
        },
    });
    var p = $("#"+world).datebox('panel'), // 日期选择对象
    tds = false, // 日期选择对象中月份
    span = p.find('span.calendar-text'); // 显示月份层的触发控件
    $("#"+world).datebox("setValue",getYear()+"-"+getMonth());
    function getYear(){
        var myDate = new Date();
        var year = managerNum(myDate.getFullYear());    //获取完整的年份(4位,1970-????)
        return year;
    }
    function getMonth(){
        var myDate = new Date();
        var month = managerNum(myDate.getMonth()+1);       //获取当前月份(0-11,0代表1月)
        return month;
    }
    function managerNum(data){
        if(parseInt(data)<10){
            return "0"+data;
        }else{
            return "" +data;
        }
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





