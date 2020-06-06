//全局变量url
var url = "";

$(function() {
	closePartichlarsWin();
	$("#addBtn").click(function(){
		url = '/training/addChannelHouse.json';
		$('#dd').dialog({
			title : '新增房源信息',
			closed : false
		});
	});
	//给关闭按钮绑定单击事件
	$("#closeBtn").click(closeBtn);
	//给提交按钮绑定单击事件
	 $.ajax({
	    	url:'/training/searchQd.json',
	    	type:'post',
	    	success:function(data){
	    		if(data.searchQd.length>0){
		    		if(data.searchQd[0].wpId == '01' || data.searchQd[0].wpId == '0302' 
		    			||data.searchQd[0].wpId == '0303' || data.searchQd[0].wpId == '06'){
		    			$('#t').datagrid('showColumn','xg');
		    			$('#t').datagrid('showColumn','sc');
		    			$("#deleteBtn").css('display','inline-block');
		    			$("#addBtn").css('display','inline-block');
		    		}else{
		    			$('#t').datagrid('hideColumn','xg');
		    			$('#t').datagrid('hideColumn','sc');
		    			$("#deleteBtn").css('display','none');
		    			$("#addBtn").css('display','none');
		    		}
		    	}else if(data.result =="admin"){
		    		$('#t').datagrid('showColumn','xg');
	    			$('#t').datagrid('showColumn','sc');
	    			$("#deleteBtn").css('display','inline-block');
	    			$("#addBtn").css('display','inline-block');
		    	}else{
		    		$('#t').datagrid('hideColumn','xg');
	    			$('#t').datagrid('hideColumn','sc');
	    			$("#deleteBtn").css('display','none');
	    			$("#addBtn").css('display','none');
		    	}
	    		
	    	}
	    });
})

/*
 * 弹框关闭按钮
 */
function closeBtn(){
	$("#personalData_form").form('clear');
	$('#personalData_div').dialog({
		closed : true
	});
}
//增加重新填写按钮
function doRest() {
	$("#form_save").form('clear')
}




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


//格式化详情列
function details(value, row, index) {
	var a = "修改";
	return "<a href='#' onclick='openPencel(\"" + row.channelCode
			+ "\")'>" + a + "</a>";
}

//格式化详情列
function xq(value, row, index) {
	var a = "其他信息";
	return "<a href='#' onclick='showChannelHouseXq(\"" + row.channelCode
			+ "\")'>" + a + "</a>";
}


// 格式化链接列
function deleteId(value, row, index) {
	var a = "删除";
	return "<a href='#' onclick='deleteCode(\"" + row.channelCode
			+ "\")'  >" + a + "</a>";
}


//详情页面
function showChannelHouseXq(channelCode) {
	url = "/training/showChannelHouseXq.json";
	$.ajax({
		url : url,
		data : {
			"channelCode" : channelCode
		},
		success : function(data) {
			var channelHouseKeeping = data.showChannelHouseXq[0].channelHouseKeeping.split("\r\n");
			var channelHouseKeepingPs = data.showChannelHouseXq[0].channelHouseKeepingPs.split("\r\n");
			//按照指定格式将数组转成字符串
			channelHouseKeeping = channelHouseKeeping.join("<br/>");
			channelHouseKeepingPs = channelHouseKeepingPs.join("<br/>");
			$("#channelHouseKeeping").html(channelHouseKeeping);
			$("#channelHouseKeepingPs").html(channelHouseKeepingPs);
			var dxp = (data.showChannelHouseXq)[0];
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





// 详情页面
function openPencel(channelCode) {
	url = "/training/searchModifyChannelXq.json";
	$.ajax({
		url : url,
		data : {
			"channelCode" : channelCode
		},
		success : function(data) {
			$("#form_save").form("load", (data.searchModifyChannelXq)[0]);
			url="/training/modifyChannelHouse.json";
		}
	});
	$('#dd').dialog({
		title : '修改房源信息',
		closed : false
	});

}

//删除
function deleteCode(channelCode){
	url="/training/deleteCode.json";
	$.messager.confirm('确认', '您确认想要删除记录吗？此操作不可返回请酌情考虑', function(r) {
		if (r) {
			$.ajax({
				url : url,
				data : {
					"channelCode" : channelCode
				},
				beforeSend : function() {
					document.getElementById("bg").style.display = "block";
					document.getElementById("loading").style.display = "block"
				},
				success : function(data) {
					$("#t").datagrid("reload");
					$.messager.alert('提示', '操作成功！');
				}
			});
		} else {
			$("#t").datagrid("reload");
		}
	});
	
	
	
}



// 模糊查询
function doMoSearch() {
	$("#t").datagrid("load", $("#form_search").serializeObject());
}

// 添加按钮
function doAdd() {
	url = "/training/addChannelHouse.json"
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
				beforeSend : function() {
					document.getElementById("bg").style.display = "block";
					document.getElementById("loading").style.display = "block"
				},
				success : function(data) {
					var data = eval('(' + data + ')');
					if (data.result != 0 && data.rel != 0) {
						$("#t").datagrid('reload');
						$('#dd').dialog({
							closed : true
						});
						$.messager.alert('提示', '操作成功！');
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

//批量删除按钮
function doDelete(){
	var a = $("#t").datagrid('getSelections');
	if (a.length > 0) {
		$.messager.confirm('确认', '您确认想要删除记录吗？此操作不可返回请酌情考虑', function(r) {
			if (r) {
				for(var i=0;i<a.length;i++){
					url = "/training/deleteChannelMoreHouse.json?channelCode=" + a[i].channelCode + "";
				$.ajax({
					url : url,
					dataType : "json",
					success : function(data) {
						$("#t").datagrid("reload");
					}
				})
			}
				} else {
				$("#t").datagrid("reload");
			}
		});
		}
	 else {
		$.messager.alert("提示", "请选择一条数据删除！")
	}
}

/**
 * 清空表单
 */
function clearForm(){
	$("#form_search").form('clear');
}



