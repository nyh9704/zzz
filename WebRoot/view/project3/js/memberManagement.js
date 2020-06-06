/***
 * 成员管理JS
 * 全局变量
 * @returns
 */
var projectid="";//项目编号
var projectname="";//项目名称
var url="";

/***
 * 页面加载
 * @returns
 */
$(function(){
	projectid=sessionStorage.getItem("projectid");//得到项目编号
	projectname=sessionStorage.getItem("projectname");//得到项目名称
	$("#projectname").text(projectname);
	byAjaxName();//通过ajax给角色名称赋值
	byAjaxP();//通过AJAX查询成员
	findAllMenber();//展现
})
/**
 * 刷新数据
 */
function doRefresh(){
	findAllMenber();
}

/***
 * 展现
 * @returns
 */
function findAllMenber(){
	$('#Ptab').datagrid({    
	    url:"../../findAllMenber.json?projectid="+projectid+"",    
	    columns:[[    
	        {field:'username',title:'成员名称',width:100},    
	        {field:'rolename',title:'角色名称',width:100}   
	    ]]    
	});
}

/***
 * 通过AJAX查询角色
 * @returns
 */
function byAjaxName(){
	 url="../../findMangPeople.json";
	 ajax(url,"","#rolename","rolename","rolecode");
}

/***
 * 通过AJAX查询成员
 * @returns
 */
function byAjaxP(){
	 url="../../findMangP.json";
	 ajax(url,"","#username","username","userid");
}

/***
 * 封装AJAX
 * @returns
 */
function ajax(url,others,who,text,value){
	$.ajax({  
		url: url,  
		type: 'post',
		dataType: 'json', 
		data:others,
		success:function(data) {
			$(who).combobox({    
				'data':data.rows,
			    valueField:value,    
			    textField:text   
			});
		}
	});
}

/***
 * 封装AJAX
 * @returns
 */
function addAndRemove(url,others){
	$.ajax({  
		url: url,  
		type: 'post',
		dataType: 'json', 
		data:others,
		success:function(data) {
			if(data.rows>0){
				$.messager.alert('提示','操作成功');  
				findAllMenber();//展现
			}else{
				$.messager.alert('警告','操作失败');  
				findAllMenber();//展现
			}
		}
	});
}

/***
 * 新增按钮点击事件
 * @returns
 */
function addOne(){
	$("#addRootMenuForm").form('clear');
	$("#addPeopleWindows").dialog({"closed":false});//显示表单
}

/***
 * 新增表单保存点击事件
 * @returns
 */
function addOneOk(){
	$("#projectid").val(projectid);
	url="../../addOne.json";
	addAndRemove(url,$("#addRootMenuForm").serializeArray());
	$("#addPeopleWindows").dialog({"closed":true});//隐藏表单
}

/***
 * 删除
 * @returns
 */
function removeOne(){
	url="../../removeOne.json";//修改路径
	var projectids=[];
	var pid=$('#Ptab').datagrid("getSelections");;//获取选择行
	if(pid.length<1){
		$.messager.alert('提示','请选择数据进行删除');  
		return;
	}
	$.each(pid,function(i,e){
		projectids[i]=e.username;//循环添加选择员工的编号
	})
	$.messager.confirm("确定","该操作是无法恢复的，您确认要删除？",function(r){
		if(r){
			addAndRemove(url,{"projectids":projectids,"projectid":projectid});//完成删除
		}else{
			findAllMenber();//展现
		}
	});    
	
	
	   	
}