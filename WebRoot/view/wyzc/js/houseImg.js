//全局变量url
var url = "";

$(function() {
	
	
})


// 格式化链接列
function href(value, row, index) {
	var a = "房屋图片链接";
	return "<a href='" + row.imgUrl + "' style='color: blue;'>" + a + "</a>";
}

// 模糊查询
function doMoSearch() {
	$("#t").datagrid("load", $("#form_search").serializeObject());
}

// 添加按钮
function doAdd() {
	url = "/training/AddHouseImg.json"
	$("#form_save").form('clear');
	$('#dd').dialog({
		title : '新增房源',
		closed : false
	});
}


// 弹框保存按钮
function doSave() {
	$.messager.confirm('确认', '您确认添加记录吗？添加过后不能删除', function(r) {
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
/*function doModify() {
	$("#form_save").form('clear');
	var a = $("#t").datagrid('getSelections');
	if (a.length > 0) {
		$('#dd').dialog({
			title : '修改房源信息',
			closed : false
		});
		url = "/training/showFyXq.json?shiCode=" + a[0].shiCode + "";

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
}*/



/**
 * 清空表单
 */
function clearForm(){
	$("#form_search").form('clear');
}