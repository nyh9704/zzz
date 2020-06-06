//全局变量url
var url = "";
var terminationRecall = "";
$(function() {
	
	$("#particulars_win").window('close');
	 $('#todayXin').window('close'); 
	    $('#todayKai').window('close'); 
	    $('#todayChe').window('close'); 
	    $.ajax({
	    	url:'/training/gameOfThrones.json',
	    	type:'post',
	    	success:function(data){
	    		if(data.searchId != null || data.searchId != ''){
	    			if(data.searchId[0] =='0903' || data.searchId[0] == '01' || data.searchId[0] == '0302' ||
	    	    			data.searchId[0] == '0303' || data.result == 'admin'){
	    	    			$('#modifyBtn').linkbutton('enable');
	    	    		}else{
	    	    			$('#modifyBtn').linkbutton('disable');
	    	    		}
	    			if(data.searchId =='0903' || data.searchId == '01' || data.searchId == '0302' ||
	    	    			data.searchId == '0303' || data.result == 'admin'){
	    	    			$('#modifyBtn').linkbutton('enable');
	    	    		}else{
	    	    			$('#modifyBtn').linkbutton('disable');
	    	    		}
	    		}
	    		
	    	}
	    });
})




// 显示等待中函数
function showdiv() {
	document.getElementById("bg").style.display = "block";
	document.getElementById("show").style.display = "block";
}

// 隐藏等待中函数
function hidediv() {
	document.getElementById("bg").style.display = 'none';
	document.getElementById("show").style.display = 'none';
}






// 格式化详情列
function details(value, row, index) {
	var a = "房屋详情";
	return "<a href='#' onclick='openPencel(\"" + row.shiUrl
			+ "\")' style='color: blue;'>" + a + "</a>";
}

// 格式化链接列
function href(value, row, index) {
	var a = "链接";
	return "<a href='#' onclick='onpenNewWindow(\"" + row.shiUrl
			+ "\")'  style='color: blue;'>" + a + "</a>";
}

//格式化附件列
function shiAppendix(value, row, index) {
	var a = "链接";
	return "<a href='#' onclick='onpenNewWindow(\"" + row.shiAppendix
			+ "\")'  style='color: blue;'>" + a + "</a>";
}
function onpenNewWindow(shiUrl){
	window.open(shiUrl);
}



// 详情页面
function openPencel(shiCode) {
	url = "/training/showFyXq.json";
	$.ajax({
		url : url,
		data : {
			"shiUrl" : shiCode
		},
		success : function(data) {
			var dxp = (data.showFyXq)[0];
			if (dxp == null || data.length == 0) {
				return;
			}
			for (attr in dxp) {
				$("span[name=" + attr + "]").html(dxp[attr]);
			}
		}
	});
	$("#particulars_win").window('open');

}

// 模糊查询
function doMoSearch() {
	$("#t").datagrid("load", $("#form_search").serializeObject());
}

// 添加按钮
function doAdd() {
	url = "/training/addHouse.json"
	$("#form_save").form('clear');
	$('#dd').dialog({
		title : '新增房源',
		closed : false
	});
}

function sorterorder(a,b){
    a=parseFloat(a);
    b=parseFloat(b);
    return (a>b?1:-1);
}

// 弹框保存按钮
function doSave() {
	$.messager.confirm('确认', '您确认添加记录吗？此操作不可逆', function(r) {
		if (r) {
			$("#form_save").form('submit', {
				url : url,
				success : function(data) {
					var data = eval('(' + data + ')');
					if (data.result != 0 && data.rel != 0) {
						$("#t").datagrid('reload');
						$('#dd').dialog({
							closed : true
						});
						$.messager.alert('提示', '添加成功！');
						$("#form_save").form('clear');
					}
				}
			})
		} else {
			$("#t").datagrid("reload");
			doAdd();
		}
	});
}

// 弹框取消关闭按钮
function doCancel() {
	$("#form_save").form('clear');
	$('#dd').dialog({
		closed : true
	});
}

// 增加重新填写按钮
function doRest() {
	$("#form_save").form('clear')
}

// 弹框关闭按钮
function closePartichlarsWin() {
	$("#particulars_win").window('close');
}



// 修改按钮
function doModify() {
	$("#form_save").form('clear');
	var a = $("#t").datagrid('getSelections');
	if (a.length > 0) {
		$('#dd').dialog({
			title : '修改房源信息',
			closed : false
		});
		url = "/training/showFyXq.json?shiUrl=" + a[0].shiUrl + "";

		$.ajax({
			url : url,
			dataType : "json",
			success : function(data) {
				$("#form_save").form("load", (data.showFyXq)[0]);
			}
		});
		url = "../../modifyAuctionEnd.json";

	} else
		($.messager.alert("提示", "请选择一条数据修改"))
}


/**
 * 查询今日撤回
 */
function terminationRecallToday(){
	terminationRecall = new Date().toLocaleDateString().replace(/\//g,"-");
	var str = terminationRecall.split("-");
	for(var i = 0; i<str.length; i++){
		if(str[1].length == 1){
			str[1] = "0"+str[1];
		}
	}
	terminationRecall = str.join("-");
	$("#t").datagrid("load",{nowTerminationDate:terminationRecall});
}


/**
 * 清空表单
 */
function clearForm(){
	$("#form_search").form('clear');
}












/**
 * 
 */


 
