/**
 * 定义全局变量
 */
var url="";

/**
 * 格式化性别列
 * @param value
 * @param row
 * @param index
 * @returns
 */
function genderId(value,row,index){
	if(value=="O"){
		return "保密";
	}else if(value=="M"){
		return "男";
	}else if(value=="F"){
		return "女";
	}
}


/**
 * 模糊查询
 */
function doUserSearch(){
	 $.ajax({
			type:"post",
			url:"http://localhost:8080/training/searchMoUser.json",
			data:{"searchMo":$("#searchMo").val()},
			dataType:"json",
			success:function(data){
				if($("#searchMo").val()!=null){
					$("#t").datagrid("loadData",data.serchM);
				}else{
					$("#t").datagrid("reload");
					alert("查询不能为空！");
				}
			}
		});
}



/**
 * 页面预加载
 * @returns
 */
$(function(){
	searchAllUser();
	 $.fn.serializeObject = function()
	 {
	 	var o = {};
	 	var a = this.serializeArray();
	 	$.each(a, function() {
	 		if (o[this.name] !== undefined) {
	 			if (!o[this.name].push) {
	 				o[this.name] = [o[this.name]];
	 			}
	 			o[this.name].push(this.value || '');
	 		} else {
	 			o[this.name] = this.value || '';
	 		}
	 	});
	 	return o;
	 };
	 
});



/**
 * 点击添加学生按钮弹框
 */
function doAdd(){
	url="http://localhost:8080/training/addUser.json";
	$("#form_save").form('clear');
	$('#dd').dialog({
		title:'添加用户',
		closed:false
	});
	
}

/**
* 点击修改学生按钮弹框
*/
function doModify(){
	
	url="http://localhost:8080/training/modifyUser.json";
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
		title:'修改学生',
		closed:false
	});
	
	}
}

/**
 * 删除用户
 */
function doDelete(){  
	var ids=[];
	var aa = $("#t").datagrid('getSelections');
	console.log(aa);
	if(aa.length==1){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){ 
		    	$.each(aa,function(index,ele){
		    		 ids[ids.length]=ele.userId;
		    	});
				$.post("http://localhost:8080/training/removeUser.json",
						{"ids":ids},
						function(data){
							alert("删除成功");
							$("#t").datagrid("reload");
						},"json");  
		    }else{
		    	$("#t").datagrid("reload");
		    }
		});  
	}else if(aa.length>1){
		alert("请勿选择删除多条！");
	}else{
		alert("请选择要删除的数据！");
	}
}

/**
 * 页面加载用户信息
 */
function searchAllUser(){
	$.ajax({
		dataType:"json",
		success:function(data){
			$("#t").datagrid("loadData",data.rows);
		}
	});
}


$.extend($.fn.validatebox.defaults.rules, {   
	userId:{ 
		validator: function(value, param){
			return /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,10}$/.test(value);
		},   
		 message: '登陆名请以大小写字母和数字组成并且长度保持在3~10位，谢谢合作'  
		},
	userPd:{
		validator: function(value, param){
		return /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,10}$/.test(value);
	},   
	 message: '密码请以大小写字母和数字组成并且长度保持在3~10位，谢谢合作'  
	}
});
/**
 * 保存按钮提交信息
 */
function doSave(){
	$.ajax({
		type:"post",
		url:url,
		data:$("#form_save").serializeObject(),
		dataType:"json",
		success:function(data){
			$("#t").datagrid("reload");
			$('#dd').dialog({
				title:'修改学生',
				closed:true
			});
			$("#form_save").form('clear');
			alert("操作成功");
		}
	});
}


/**
 * 取消按钮关闭弹窗
 */
function doCancel(){
	$("#form_save").validate().resetForm();
	$('#dd').dialog({
		closed:true
	}) 
	$("#form_save").form('clear');
}



