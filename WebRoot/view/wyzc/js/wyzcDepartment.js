/***
 * 全局变量
 */
var url="";//全局地址

/***
 * 项目管理JS
 */

/**
 * 功能：初始化,加载初始数据
 */
$(function (){
	loadDatagrid();//展现树形表格
});


/**
 * 加载数据网格内容
 */
function loadDatagrid(){
	//设置treegrid初始属性及方法
	$("#menuTreeGrid").treegrid({
		idField: 'wpId',
		treeField: 'wpName',
		parentField : 'parentId',
		animate: true,
		striped:true,
	});
	
	//加载treegrid数据
	$.ajax({  
		url: "../../ProjectMangementUrl.json",  
		type: 'post',
		dataType: 'json', 
		data:{parentId:"0"},
		success:function(data) {
			$("#menuTreeGrid").treegrid('loadData',data.parent);
		},
		error : function() {
			$.messager.alert('错误','服务器内部错误!','error');
		}
	});
}

/***
 * 新增第一级菜单
 * 
 */
function addRootMenu(){
	$("#addRootMenuForm").form('clear');//表单清空
	//$("#projectstate_yes").prop("checked",true);//默认复选框为有效状态
	url="../../addProjectonr.json";
	$("#addRootMenu").window('open');
	$("#pname").validatebox({
		missingMessage:"部门名称不能为空",
	});
	$("#pnum").validatebox({
		missingMessage:"部门编号不能为空",
		invalidMessage:"部门编号已经存在",
		validType:"remoteValid['../../checkedOnlyOne.json','wpId']",
	});
	
}

/**
 * 功能：新增下级菜单时，排序号自动填写
 */
/*function addxuhao(){
	$("#soreNum").textbox("setText",$("#pnum").val());
}*/

function xx(value,row,index){
	return "权限";
}

/***
 * 新增一级菜单保存按钮
 * @returns
 */
function addProject(){
	allUsedOfAjax(url,$("#addRootMenuForm").serializeArray(),"#addRootMenu");//使用封装的AJAX方法
}

/***
 * 封装AJAX方法，根据URL执行对应处理程序
 * url：访问地址
 * others：附带数据
 * whatFrom：窗口ID
 * whatFromC：表单ID
 * @returns
 */
function allUsedOfAjax(url,others,whatFrom){
	$.ajax({  
		url: url,  
		type: 'post',
		dataType: 'json', 
		data:others,
		success:function(data) {
				if(data.rows==1){
					$(whatFrom).window('close');
					$.messager.alert('消息','操作成功');  
					loadDatagrid();//展现
				}else{
					$(whatFrom).window('close');
					$.messager.alert('警告','操作失败');  
					loadDatagrid();//展现
				}		
		}
	});
}

/**
 * 功能：打开新增下级菜单对话框
 */
function addNextMenu(){
	$("#addNextMenuForm").form('clear');//表单清空
	var one=$('#menuTreeGrid').datagrid('getSelected');//得到选中行
	if(one==null){
		$.messager.alert('友情提示','请选中上级部门');  
	}else{
		$("#upProjectid").html(one.wpId+".");//将父级项目ID写入子级前面
		$("#addNextMenu").window('open');
		
		$("#nextPname").validatebox({
			missingMessage:"部门名称不能为空",
		});
		$("#nextPnum").validatebox({
			missingMessage:"部门编号不能为空",
			invalidMessage:"部门编号已经存在",
			validType:"remoteValid['../../checkedOnlyOne.json?upProjectid="+$("#upProjectid").html()+"','wpId']",
		});
	}
	url="../../addNextProjectonr.json?pid="+$("#upProjectid").html()+"";
}

/***
 * 功能：下级菜单保存
 */
function addNextProject(){
	allUsedOfAjax(url,$("#addNextMenuForm").serializeArray(),"#addNextMenu");//使用封装的AJAX方法
}

/***
 * 修改按钮
 */
function modifyMenu(){
	url="../../findOnwProjrct.json";
	$("#mPname").validatebox({
		missingMessage:"部门名称不能为空",
	});
	showDate();//将要修改的数据展现到修改表单上并展现

}

/***
 * 将选中的行数据展现在修改表单上面
 */
function showDate(){
	var one=$('#menuTreeGrid').datagrid('getSelected');//得到选中行
	if(one==null){
		$.messager.alert('友情提示','请选中某个部门进行修改');  
	}else{//将选中的数据传入修改表单
		$("#projectid").html(one.projectid);//获取不可更改的项目编号
		
		$.ajax({  
		url: url,  
		type: 'post',
		dataType: 'json', 
		data:{"projectid":one.projectid},
		success:function(data) {
			
			$('#modifyMenuForm').form('load',data.rows[0]);// 读取记录填充表单
			$("#modifyMenu").window('open');//展现修改窗口
		}
	});
		
	}
}

/***
 * 修改表单的保存按钮
 * @returns
 */
function modifyProject(){
	url="../../modifyProjrct.json?projectid="+$("#projectid").html()+"";
	allUsedOfAjax(url,$('#modifyMenuForm').serializeArray(),"#modifyMenu");
}














