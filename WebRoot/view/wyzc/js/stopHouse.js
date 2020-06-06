//全局变量url
var url = "";

$(function() {
	
	$("#particulars_win").window('close');
    $.ajax({
    	url:'/training/searchId.json',
    	type:'post',
    	success:function(data){
    		console.log('111')
    		if(data.searchId!='客服'){
    			$("#mb").menu({
    				disabled:true
    			});
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

// 导出按钮
/*function doExport() {
	url = "/training/exportHouse.json";
	$.messager.confirm('确认', '您确认导出为excel吗', function(r) {
		if (r) {
			$.ajax({
				url : url,
				dataType : "json",
				type:"post",
				beforeSend : function() {
					document.getElementById("bg").style.display = "block";
					document.getElementById("loading").style.display = "block"
				},
				success : function(data) {
					document.getElementById("bg").style.display = 'none';
					document.getElementById("loading").style.display = 'none';
					$.messager.alert('提示', '导出成功');
				}
			});
		} else {
			doMoSearch();
		}
	});

}*/

// 导入excel按钮
function doUpFile() {
	$("#upLoadFile").form('clear');
	$('#filePath').filebox({
		buttonText : '选择文件',
		buttonAlign : 'left'
	})
	$('#upfileload').dialog({
		title : '请选择上传类型',
		closed : false
	});
}




// 导入弹框_关闭按钮
function doGb() {
	$("#upLoadFile").form('clear');
	$('#upfileload').dialog({
		closed : true
	});
}

// 导入弹框_导入新增数据
function doAddFy() {
	$.messager.confirm('确认', '您确认选择正确吗', function(r) {
		if (r) {
			url = "/training/addStopFyExcel.json";
				  $.ajax({
				         type:'post',
				         'url':url,
				         data:new FormData($("#upLoadFile")[0]),
				         contentType:false,
				         processData:false,//这个很有必要，不然不行
				         dataType:"json",
				        // mimeType:"multipart/form-data",
				         beforeSend : function() {
				        	 doGb();
								document.getElementById("bg").style.display = "block";
								document.getElementById("loading").style.display = "block"
							},
				         success:function(data){
				        	 	document.getElementById("bg").style.display = 'none';
				        	 	document.getElementById("loading").style.display = 'none';
								$("#upLoadFile").form('clear');
								$("#t").datagrid("reload");
								$.messager.alert('提示', '上传成功');
								
				            }
				     });
		} else {

		}
	})
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

function onpenNewWindow(shiUrl){
	window.open(shiUrl);
}



// 详情页面
function openPencel(shiCode) {
	url = "/training/showStopFyXq.json";
	$.ajax({
		url : url,
		data : {
			"shiUrl" : shiCode
		},
		success : function(data) {
			var dxp = (data.showStopFyXq)[0];
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

// 弹框关闭按钮
function closePartichlarsWin() {
	$("#particulars_win").window('close');
}


/**
 * 清空表单
 */
function clearForm(){
	$("#form_search").form('clear');
}