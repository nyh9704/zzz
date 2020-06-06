//全局变量url
var url = "";
var nowEndDate = "";
var nowUploadDate = "";
var nowAuctionDate = "";
$(function() {
	
	$("#particulars_win").window('close');
	 $('#todayXin').window('close'); 
	    $('#todayKai').window('close'); 
	    $('#todayChe').window('close'); 
    $.ajax({
    	url:'/training/searchId.json',
    	type:'post',
    	success:function(data){
    		if(data.searchId != null || data.searchId != ''){
    			if(data.searchId[0] =='0903' || data.searchId[0] == '01' || data.searchId[0] == '0302' ||
    	    			data.searchId[0] == '0303' || data.result == 'admin'){
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

//当日新增导入按钮
function toDayAdd() {
	$("#addXX").form('clear');
	$('#filePath').filebox({
		buttonText : '选择文件',
		buttonAlign : 'left'
	})
	$('#addXz').dialog({
		title : '当日新增导入',
		closed : false
	});
}



//当日开拍导入按钮
function toDayKKp() {
	$("#addLKpp").form('clear');
	$('#filePath').filebox({
		buttonText : '选择文件',
		buttonAlign : 'left'
	})
	$('#addLKp').dialog({
		title : '当日开拍导入',
		closed : false
	});
}

//当日撤回导入按钮
function toDayCCh() {
	$("#addCzz").form('clear');
	$('#filePath').filebox({
		buttonText : '选择文件',
		buttonAlign : 'left'
	})
	$('#addCh').dialog({
		title : '当日撤回导入',
		closed : false
	});
}



// 导入弹框_关闭按钮
function doGb() {
	$("#upLoadFile").form('clear');
	$('#upfileload').dialog({
		closed : true
	});
	$("#addXX").form('clear');
	$('#addXz').dialog({
		closed : true
	});
	$("#addLKpp").form('clear');
	$('#addLKp').dialog({
		closed : true
	});
	$("#addCzz").form('clear');
	$('#addCh').dialog({
		closed : true
	});
}

// 导入弹框_导入新增数据
function doAddFy() {
	$.messager.confirm('确认', '您确认选择正确吗', function(r) {
		if (r) {
			url = "/training/addFyExcel.json";
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



//导入弹框_当日新增数据
function toDayXz() {
	$.messager.confirm('确认', '您确认选择正确吗', function(r) {
		if (r) {
			url = "/training/toDayXz.json";
				  $.ajax({
				         type:'post',
				         'url':url,
				         data:new FormData($("#addXX")[0]),
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
								$("#todayXinZ").datagrid("reload");
								$.messager.alert('提示', '上传成功');
								
				            }
				     });
		} else {

		}
	})
}





//导入弹框_当日开拍数据
function toDayKp() {
	$.messager.confirm('确认', '您确认选择正确吗', function(r) {
		if (r) {
			url = "/training/toDayKp.json";
				  $.ajax({
				         type:'post',
				         url:url,
				         data:new FormData($("#addLKpp")[0]),
				         contentType:false,
				         processData:false,//这个很有必要，不然不行
				         dataType:"json",
				        // mimeType:"multipart/form-data",
				         beforeSend : function() {
				        	 doGb();
				        	 document.getElementById("bg").style.display = "block";
							 document.getElementById("loading").style.display = "block";
							},
				         success:function(data){
				               	document.getElementById("bg").style.display = 'none';
								document.getElementById("loading").style.display = 'none';
								$("#upLoadFile").form('clear');
								$("#todayKaiP").datagrid("reload");
								
								$.messager.alert('提示', '上传成功');
								
				            }
				     });
		} else {

		}
	})
}



//导入弹框_当日撤回数据
function toDayCh() {
	$.messager.confirm('确认', '您确认选择正确吗', function(r) {
		if (r) {
			url = "/training/toDayCh.json";
				  $.ajax({
				         type:'post',
				         'url':url,
				         data:new FormData($("#addCzz")[0]),
				         contentType:false,
				         processData:false,//这个很有必要，不然不行
				         dataType:"json",
				        // mimeType:"multipart/form-data",
				         beforeSend : function() {
				        	 doGb();
				        	 document.getElementById("bg").style.display = "block";
							 document.getElementById("loading").style.display = "block";
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


//查询当日新增
function searchToDayXz(){
	$("#todayXinZ").datagrid("reload");
	$('#todayXin').window('open'); 
}

//查询当日开拍
function searchToDayKp(){
	$("#todayKaiP").datagrid("reload");
	$('#todayKai').window('open'); 
}

//查询当日撤回
function searchToDayCh(){
	$("#todayCheH").datagrid("reload");
	$('#todayChe').window('open'); 
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
				console.log(attr)
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

function doExportNow(){
    var objArr = $('#t').datagrid('getData');
    console.log(objArr)
}







/**
 * 导入过期数据
 * @param value
 * @param row
 * @param index
 * @returns
 */
function doGqFy(){
	$.messager.confirm('确认', '您确认选择正确吗', function(r) {
		if (r) {
			url = "../../doGqFy.json";
				  $.ajax({
				         type:'post',
				         url:url,
				         data:new FormData($("#upLoadFile")[0]),
				         contentType:false,
				         processData:false,//这个很有必要，不然不行
				         dataType:"json",
				        // mimeType:"multipart/form-data",
				         success:function(data){
				                if(data.result != 0){
				                	$.messager.alert('提示','上传成功');
				                	$("#upLoadFile").form('clear');
				                }else{
				                	$.messager.alert('提示','上传失败');
				                }
				            }
				     });
		} else {
			$.messager.alert('提示','当前项目不支持该操作');
		}
	})
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
		url = "../../modifyHouse.json";

	} else
		($.messager.alert("提示", "请选择一条数据修改"))
}

// 删除按钮
function doDelete() {
	var a = $("#t").datagrid('getSelections');
	if (a.length > 0) {
		url = "../../deleteHouse.json?shiCode=" + a[0].shiCode + "";
		$.messager.confirm('确认', '您确认想要删除记录吗？此操作不可返回请酌情考虑', function(r) {
			if (r) {
				$.ajax({
					url : url,
					dataType : "json",
					success : function(data) {
						$("#t").datagrid("reload");
						$.messager.alert("提示", "删除成功！");
					}
				})
			} else {
				$("#t").datagrid("reload");
			}
		});

	} else {
		$.messager.alert("提示", "请选择一条数据删除！")
	}
}


/**
 * 查询今日新增
 */
function searchUploadToday(){
	nowUploadDate = new Date().toLocaleDateString().replace(/\//g,"-");
	var str = nowUploadDate.split("-");
	console.log(str)
	for(var i = 0; i<str.length; i++){
		if(str[1].length == 1){
			str[1] = "0"+str[1];
		}
		if(str[2].length == 1){
			str[2] = "0"+str[2];
		}
	}
	nowUploadDate = str.join("-");
	console.log(str)
	$("#t").datagrid("load",{nowUploadDate:nowUploadDate});
	
}

/**
 * 查询今日开拍 nowAuctionDate
 */
function searchAuctionToday(){
	nowAuctionDate = new Date().toLocaleDateString().replace(/\//g,"-");
	var str = nowAuctionDate.split("-");
	console.log(str)
	for(var i = 0; i<str.length; i++){
		if(str[1].length == 1){
			str[1] = "0"+str[1];
		}
		if(str[2].length == 1){
			str[2] = "0"+str[2];
		}
	}
	nowAuctionDate = str.join("-");
	console.log(str)
	$("#t").datagrid("load",{nowAuctionDate:nowAuctionDate});
}

/**
 * 查询今日结束
 */
function searchEndToday(){
	nowEndDate = new Date().toLocaleDateString().replace(/\//g,"-");
	var str = nowEndDate.split("-");
	console.log(str)
	for(var i = 0; i<str.length; i++){
		if(str[1].length == 1){
			str[1] = "0"+str[1];
		}
		if(str[2].length == 1){
			str[2] = "0"+str[2];
		}
	}
	nowEndDate = str.join("-");
	console.log(str)
	$("#t").datagrid("load",{nowEndDate:nowEndDate});
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


 
