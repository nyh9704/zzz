//全局变量url
var url = "";

$(function() {
	searchBm()
	
	
	$.ajax({
		url:"/training/searchContractQx.json",
		dataType:'json',
		type:'post',
		success:function(data){
			if(data.searchContractQx.length == 0){
				$("#deleteBtn").linkbutton({
					disabled:true
				});
			}else if(data.searchContractQx[0].wpId == '0902' || data.searchContractQx[0].wpId == '01'){
				if(data.searchContractQx[0].wdId !='1'){
					$("#deleteBtn").linkbutton({
						disabled:false
					});
				}
			}else if(data.searchContractQx[0].wpId =='0903'){
				$("#deleteBtn").linkbutton({
					disabled:true
				});				
				$("#modifyBtn").linkbutton({
					disabled:true
				});
			}else{
				$("#deleteBtn").linkbutton({
					disabled:true
				});
			}
		}
	})
	
	//给时间下拉列表添加清除按钮
	clearDateButton();
	
})


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
    
    $('#startWorkTime1').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
    
    $('#startWorkTime2').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
    
    
}









/**
 * 修改员工资料
 */
function doModify() {
	$("#personnelTest_form").form('clear');
	var a = $("#t").datagrid('getSelections');
	personnelData = a[0];
	if (a.length > 0) {
		$('#personnelTest_mod_div').dialog({
			title:"修改选项",
			closed : false
		});
	} else{
		$.messager.alert("提示", "请选择一条数据修改");
	}
}


/**
 * 修改选项确认
 */
function optionToConfirm(){
	var modForm = $("#personnelTest_mod_form").serializeObject();	
	//根据员工编号查询信息
	$.ajax({
		url:"../../searchPersonnelByUserId.json",
		dataType:'json',
		async:false,
		data:{userId:personnelData.userId},
		type:'post',
		success:function(data){
			newPersonnel = data.result;
		}
		
	})
	
	if(modForm.cho == 0){//显示试岗员工修改界面
		$('#personnelTest_div').dialog({
		title : '修改试岗员工信息',
		closed : false
	});
	//关闭修改选项页面
		$('#personnelTest_mod_div').dialog({
			closed : true
		});
	//表单回显
	$("#personnelTest_form").form("load",newPersonnel);
	url = "../../saveModifyTestPersonnel.json";
	
	}else if(modForm.cho == 1){//显示正式员工修改界面
		$('#personnel_official_div').dialog({
			title:'修改在职员工信息',
			closed : false
		});
		
		//表单回显
		$("#personnel_official_form").form("load",newPersonnel);	
				
		//关闭修改选项页面
		$('#personnelTest_mod_div').dialog({
			closed : true
		});
	}else if(modForm.cho == 2){//显示离职员工修改界面
		$.messager.confirm('确认', '您确认将该员工调整为离职员工吗吗？此操作不可返回请酌情考虑', function(r) {
			if (r) {
				$('#personnel_leave_div').dialog({
					title:'修改离职员工信息',
					closed : false
				});
				//关闭修改选项页面
				$('#personnelTest_mod_div').dialog({
					closed : true
				});
				
				$.ajax({
					url : url,
					dataType : "json",
					success : function(data) {
						if(data.result != 0){
							$("#tt").datagrid("reload");
							$.messager.alert("提示", "删除成功！");
						}else{
							$.messager.alert("提示", "删除失败！");
						}
						
					}
				})
			} else {
				$("#tt").datagrid("reload");
			}
		});
		
		
		
		console.log(personnelData)
	}
}

/**
 * 提交在职员工修改信息
 */
function saveModiftyOfficialPersonnel(){
	$.ajax({
		url:'../../saveModiftyOfficialPersonnel.json',
		type:'post',
		dataType:'json',
		data:$("#personnel_official_form").serializeObject(),
		success:function(data){
			if(data.result == '1'){
				$.messager.alert('提示','操作成功');
				$("#tt").datagrid('reload');
				$("#personnel_official_form").form('clear');
				$("#personnel_official_div").window('close');
			}else{
				$.messager.alert('提示','操作失败');
			}
		}
	})
}

/**
 * 弹框关闭按钮
 */
function closeBtn(){
	$("#personnelTest_form").form('clear');
	$('#personnelTest_div').dialog({
		closed : true
	});
}

/**
 * 修改选项关闭
 * @returns
 */
function closeModBtn(){
	$("#personnelTest_mod_form").form('clear');
	$('#personnelTest_mod_div').dialog({
		closed : true
	});
}

/**
 * 在职员工修改页面关闭
 */
function closeOfficialBtn(){
	$("#personnel_official_form").form('clear');
	$('#personnel_official_div').dialog({
		closed : true
	});
}


//回显部门
function searchBm(){
	$.ajax({
		url:"/training/searchBm.json",
		type:'post',
		dataType:'json',
		success:function(data){
			$("#bm").combobox({
				valueField: 'wpId',
				textField: 'wpName',
				data:data.searchBm,
				panelHeight:'auto',
				editable:false
			})
			$("#bm1").combobox({
				valueField: 'wpId',
				textField: 'wpName',
				data:data.searchBm,
				panelHeight:'auto',
				editable:false
			})
		}
	})
	
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


function doModify(){
	url="/training/modifyContract.json";
	var ids=[];
	var aa = $("#t").datagrid('getSelections');
	ids=aa;
	if(aa.length>1){
		$.messager.alert('警告',"请勿选择多个修改！");
	}else if(aa.length==0){
		$.messager.alert('警告',"请选择一条数据修改！");
	}else{
	$("#form_save").form("load",ids[0]);
	$('#dd').dialog({
		title:'修改合同',
		closed:false
	});
	
	}
}

//增加重新填写按钮
function doRest() {
	$("#form_save").form('clear')
}

//弹框保存按钮
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
						$.messager.alert('提示', '操作成功！');
						$("#form_save").form('clear');
					}
				}
			})
		} else {
			$("#t").datagrid("reload");
		}
	});
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