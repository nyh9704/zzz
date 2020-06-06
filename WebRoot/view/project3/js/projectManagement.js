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
	//为validatebox添加blur事件
    //$('#pnum').bind('blur',addxuhao);//序号自动填入
});

/**
 * 功能：格式化状态列
 * @param value
 * @param row
 * @param index
 */
function isSysManageFormatter(value, row, index) {
	if(value=="01"){
		return  "<img src='../../css/themes/icons/ok.png'/>";
	} 
	else if(value=="00"){
		return  "—";
	}
}

/**
 * 功能：格式化操作列
 * @param value
 * @param row
 * @param index
 */
function isUsedFormatter(value, row, index){
		if(row.projectstate=="01"){
			return  "<a href='#' onclick='closeStatu(\""+row.projectid+"\",\""+row.projectstate+"\")' style='color: black;text-decoration:none'>关闭</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onclick='details(\""+row.projectid+"\")' style='color: black;text-decoration:none'>详情</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onclick='mangerPeople(\""+row.projectid+"\",\""+row.projectname+"\");' style='color: black;text-decoration:none'>成员管理</a>";
		}else{
			return  "<a href='#' onclick='openStatu(\""+row.projectid+"\",\""+row.projectstate+"\")' style='color: black;text-decoration:none'>打开</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onclick='details(\""+row.projectid+"\")' style='color: black;text-decoration:none'>详情</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onclick='mangerPeople(\""+row.projectid+"\",\""+row.projectname+"\");' style='color: black;text-decoration:none'>成员管理</a>";
		}
}

/**
 * 是否显示无效菜单
 */
function showNoUsed(){
	loadDatagrid();
}

/**
 * 
 * 操作列的打开功能
 */
function openStatu(id,projectstate){
	url="../../openProjrct.json";
	$.ajax({  
		url: url,  
		type: 'post',
		dataType: 'json', 
		data:{"projectid":id,"projectstate":projectstate},
		success:function(data) {
				if(data.rows==1){
					$.messager.alert('消息','操作成功');  
					loadDatagrid();//展现
				}else{
					$.messager.alert('友情小提示','请先打开父级项目的有效状态');  
					loadDatagrid();//展现
				}
		}
	});
	
}

/**
 * 操作列的关闭功能
 */
function closeStatu(id,projectstate){
	url="../../closeProjrct.json";
	$.ajax({  
		url: url,  
		type: 'post',
		dataType: 'json', 
		data:{"projectid":id,"projectstate":projectstate},
		success:function(data) {
				if(data.rows==1){
					$.messager.alert('消息','操作成功');  
					loadDatagrid();//展现
				}
		}
	});
}

/**
 * 操作列的成员管理功能
 */
function mangerPeople(projectid,projectname){
	sessionStorage.setItem("projectid",projectid);
	sessionStorage.setItem("projectname",projectname);
	window.location.href="memberManagement.html";
	
}

/***
 * 操作列的详情功能
 * id：点击行的项目编号
 */
function details(id){
	var re= new RegExp("\n","g");//g,表示全部替换。
	//获取TD节点
	var tds=$("#showOneTable span");
	//通过员工编号取得该员工的所以数据
	$.ajax({
			"url":"../../findOnwProjrct.json",
		     type:"post",
		     dataType:"json",
		     data:{"projectid":id},
		     success:function(data){
		     var thisdata=data.rows[0];
		        for(one in thisdata){
		        	for(var i=0;i<tds.length;i++){
		        		if(one==tds[i].attributes.name.value){
		        			if(one=="projectdesc"){
		        				thisdata[one]=thisdata[one].replace(re,"<br/>");
		        				$("span[name="+tds[i].attributes.name.value+"]").html(thisdata[one]);
		        			}else if(one=="createtime"||one=="modifytime"){
		        				thisdata[one]=thisdata[one].substring(0, thisdata[one].length-2);
		        				$("span[name="+tds[i].attributes.name.value+"]").html(thisdata[one]);
		        			}else if(one=="projectstate"){
		        				if(thisdata[one]=="00"){
		        					$("span[name="+tds[i].attributes.name.value+"]").html("无效");
		        				}else{
		        					$("span[name="+tds[i].attributes.name.value+"]").html("有效");
		        				}	
		        			}else if(one=="pprojectid"&&thisdata[one]=="%root%"){
		        					$("span[name="+tds[i].attributes.name.value+"]").html("无");
		        			}else{
		        				$("span[name="+tds[i].attributes.name.value+"]").html(thisdata[one]);
		        			}
		        		}
		        	}
		        }
		        $("#showpanel").dialog({
		        	"closed":false,//详情信息窗口打开
		        });
		      }
		})
}

/**
 * 刷新数据
 */
function doRefresh(){
	loadDatagrid();
}

/**
 * 加载数据网格内容
 */
function loadDatagrid(){
	//设置treegrid初始属性及方法
	$("#menuTreeGrid").treegrid({
		idField: 'projectid',
		treeField: 'projectname',
		parentField : 'pprojectid',
		animate: true,
		striped:true,
	});
	
	//加载treegrid数据
	$.ajax({  
		url: "../../ProjectMangementUrl.json",  
		type: 'post',
		dataType: 'json', 
		data:{projectstate: $("#searchForm").find(":checkbox").is(':checked')?'':'01', pprojectid:"%root%"},
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
	$("#projectstate_yes").prop("checked",true);//默认复选框为有效状态
	url="../../addProjectonr.json";
	$("#addRootMenu").window('open');
	$("#pname").validatebox({
		missingMessage:"项目名称不能为空",
	});
	$("#pnum").validatebox({
		missingMessage:"项目编号不能为空",
		invalidMessage:"项目编号已经存在",
		validType:"remoteValid['../../checkedOnlyOne.json','projectid']",
	});
	
}

/**
 * 功能：新增下级菜单时，排序号自动填写
 */
/*function addxuhao(){
	$("#soreNum").textbox("setText",$("#pnum").val());
}*/

/***
 * 新增一级菜单复选框单选
 * 
 */
function yes(){
	var yes=$("#projectstate_yes");
	var no=$("#projectstate_no");
	
	if(yes[0].checked){
		no.prop("checked",false);
	}
	
}
function no(){
	var yes=$("#projectstate_yes");
	var no=$("#projectstate_no");
	
	if(no[0].checked){
		yes.prop("checked",false);
	}
	
}
function yes1(){
	var yes=$("#projectstate_yes1");
	var no=$("#projectstate_no1");
	
	if(yes[0].checked){
		no.prop("checked",false);
	}
	
}
function no1(){
	var yes=$("#projectstate_yes1");
	var no=$("#projectstate_no1");
	
	if(no[0].checked){
		yes.prop("checked",false);
	}
	
}
function yes2(){
	var yes=$("#projectstate_yes2");
	var no=$("#projectstate_no2");
	
	if(yes[0].checked){
		no.prop("checked",false);
	}
	
}
function no2(){
	var yes=$("#projectstate_yes2");
	var no=$("#projectstate_no2");
	
	if(no[0].checked){
		yes.prop("checked",false);
	}
	
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
	$("#projectstate_yes2").prop("checked",true);//默认复选框为有效状态
	var one=$('#menuTreeGrid').datagrid('getSelected');//得到选中行
	if(one==null){
		$.messager.alert('友情提示','请选中父级');  
	}else{
		$("#upProjectid").html(one.projectid+".");//将父级项目ID写入子级前面
		$("#addNextMenu").window('open');
		
		$("#nextPname").validatebox({
			missingMessage:"项目名称不能为空",
		});
		$("#nextPnum").validatebox({
			missingMessage:"项目编号不能为空",
			invalidMessage:"项目编号已经存在",
			validType:"remoteValid['../../checkedOnlyOne.json?upProjectid="+$("#upProjectid").html()+"','projectid']",
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
		missingMessage:"项目名称不能为空",
	});
	showDate();//将要修改的数据展现到修改表单上并展现

}

/***
 * 将选中的行数据展现在修改表单上面
 */
function showDate(){
	var one=$('#menuTreeGrid').datagrid('getSelected');//得到选中行
	if(one==null){
		$.messager.alert('友情提示','请选中某个项目进行修改');  
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














