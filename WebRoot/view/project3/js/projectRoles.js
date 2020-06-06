/**
 * 预加载事件
 */
$(function(){
	//给增加按钮绑定单击事件  
	$("#addBtn").click(showAddDiv)
	//隐藏增加面板 
	$("#operation_div").window("close");
	//隐藏修改面板
	$("#cut_div").window("close");
	//显示数据
	searchData();
	//给增加按钮绑定单击事件
	$("#okBtn").click(addProjectRole);
	//给修改按钮绑定单击事件
	$("#cutBtn").click(showCutDiv);
	//给删除按钮绑定单击事件
	$("#removeBtn").click(removeProjectRole);
	//给修改保存按钮绑定单击事件
	$("#okCutBtn").click(modifyState);
	
	  $('#form_tab').css('height',$(window).height()*0.6);
	
})

/*
 * 全局变量
 */
var result;
var url;

/*
 * 显示修改面板
 */
function showCutDiv(){
	var rows = $("#tt").datagrid('getSelections');
	if(rows.length == 0){
		$.messager.alert('提示','没有要修改的信息');
	}else{
		var roleCode = rows[0].roleCode;
		$('#cutForm').form('load',rows[0])
		title = "修改项目角色";
		$('#cut_div').window({    
		    width:'auto',    
		    height:'auto',    
		    modal:true,
		    title:title,
		    closed:false
		});
		$.ajax({
			type:"post",
			url:"../../searchState.json",
			dataType:"json",
			async:false,
			success:function(data){
				data.result.pop();
				console.log(data.result)
				var str = "";
				var num = 1;
				$.each(data.result,function(index,eml){
					
					if(num%2 != 0){
						str += "<input type='checkbox' value='"+eml.stateCode+"' name='list["+index+"].stateCode'/><span>"+eml.stateDesc+"</span>&nbsp;&nbsp";
					}else{
						str += "<input type='checkbox' value='"+eml.stateCode+"' name='list["+index+"].stateCode'/><span>"+eml.stateDesc+"</span><br/><br/>";
					}
					num++;
				})				
				$("#form_taba").html(str);
		    }
		});
		//根据当前项目角色的信息使相应的复选框处于选中状态
		var operate = rows[0].operate.split(',');		
		$("input[type=checkbox]").each(function(index,ele){
			for(var i = 0;i<operate.length;i++){
				if($(this).next().text() == operate[i]){
					$(this).prop("checked",true);
				}
			}
		})
		
	}
	formVerification("modify");
}


/**
 * 显示增加面板
 * 
 */
function showAddDiv(){
	title = "新增项目角色";
	$('#operation_div').window({    
	    width:'auto',    
	    height:'auto',    
	    modal:true,
	    title:title,
	    closed:false
	});
	
	$.ajax({
		type:"post",
		url:"../../searchState.json",
		dataType:"json",
		async:false,
		success:function(data){
			data.result.pop()
			var str = "";
			var num = 1;
			var cssstr = "";
			$.each(data.result,function(index,eml){
					str += "<input type='checkbox' value='"+eml.stateCode+"' name='list["+index+"].stateCode'/>"+eml.stateDesc+"&nbsp;&nbsp";
				
				var px = data.result.length;
				cssstr =px*20+20 +"px";
			})
			
			$("#form_tab").html(str);
			$("#form_d").css("height",cssstr);
		},		
	})
	formVerification("add");
	$("#addForm").form('clear');
}

/*
 * 分页查询
 */
function searchData(){
	$("#tt").datagrid({
		title:"数据列表",
		url:"../../searchData.json",
		columns:[[    
	        {field:'roleCode',title:'编号',width:'20%',align:'center'},    
	        {field:'roleName',title:'项目角色名',width:'20%',align:'center'},      
	        {field:'operate',title:'可操作状态',width:'20%',align:'center'}, 
	        {field:'moduleState',title:'状态',width:'20%',align:'center'},    
	        {field:'name',title:'操作',width:'20%',align:'center', 
	        	formatter:function(value,row,index){
	        		if(row.moduleState == "有效"){
	        			result = "1";
	        			return '<a style="color:blue"  href="javascript:void(0);" onclick="closeState('+row.roleCode+','+result+')">'+"禁用"+'</a>';
	        		}else{
	        			result = "0";
	        			return '<a style="color:blue"  href="javascript:void(0);" onclick="openState('+row.roleCode+','+result+')">'+"开启"+'</a>';
	        		}
	        		 
	        	}
	        }     
	    ]]	   
	});
}

/*
 * 提交修改信息
 */
function modifyState(){
	if($("#cutForm").form('validate')){
		var roleCode = $("#roleCodes").textbox('getValue');
		$('#cutForm').form('submit', {    
			url:"../../modifyState.json",      
		    onSubmit: function(parm){    
		        parm.roleCode = roleCode
		    },    
		    success:function(data){    
		    	data = eval( '('+data+')' );
		    	if(data.result != 0 && data.rel != 0 && data.res != 0){
		    		$.messager.alert('提示','修改成功');
		    		 $("#tt").datagrid("reload");  
		    	}else{
		    		$.messager.alert('提示','修改失败');
		    	}
		    	 
		    }    
		});  

		$("#cut_div").window('close');
	}else{
		$.messager.alert('提示','输入不正确');
	}
	
	
	
}

/*
 * 增加项目角色信息
 */
function addProjectRole(){
	//formVerification("add);
	if($("#addForm").form('validate')){
		url = "../../addProjectRole.json";
		$('#addForm').form({    
		    url:url,    
		    onSubmit: function(){    
		    },    
		    success:function(data){
		    	data = eval( '('+data+')' );//将后台响应的参数转成对象
		        if(data.result != 0 && data.rel != 0){
		        	$.messager.alert('提示','增加成功！！！');
		    		  $("#tt").datagrid("reload");  
		        }else{
		        	$.messager.alert('提示','增加失败！！！');
		        }		    	
		    }    
		});
		formVerification("add");
    	$("#operation_div").window('close');
    	$('#addForm').submit();  
	}else{
		$.messager.alert('提示','输入不正确');
	}
		
	

}

/*
 * 删除项目角色
 */
 
function removeProjectRole(){
	
	var rows = $("#tt").datagrid('getSelections');
	var ids = [];
	for(var i = 0; i<rows.length;i++){
		ids.push(rows[i].roleCode);
	}
	if(rows.length == 0){
		$.messager.alert('提示','没有要删除的用户');    
	}else{
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	$('#removeProjectRole').form('submit', {    
				    url:"../../removeProjectRole.json",    
				    onSubmit: function(parm){    
				        parm.ids = ids;
				    },    
				    success:function(data){    
				        data = eval( '('+data+')' );
				        if(data.result != 0 && data.res != 0){
				        	$.messager.alert('提示','删除成功');
				    		$("#tt").datagrid("reload");  
				        }else{
				        	$.messager.alert('提示','删除失败');
				        }
				    }    
				}); 
				$("#tt").datagrid('clearSelections');
		    }    
		});  
		
	}
		
}

/*
 * 修改状态为禁用
 */
function closeState(roleCode){
	$.ajax({
		type:"post",
		url:"../../changeState.json",
		dataType:"json",
		data:{"roleCode":roleCode},
		async:false,
		success:function(data){
	        if(data.result != 0 ){
	        	$.messager.alert('提示','修改成功');
	        	$("#tt").datagrid("reload");  
	        }else{
	        	$.messager.alert('提示','修改失败');
	        }
		}
		
	})
		
}

/*
 * 修改状态为开启
 */

function openState(roleCode){
	$.ajax({
		type:"post",
		url:"../../changeState.json",
		dataType:"json",
		data:{"roleCode":roleCode},
		async:false,
		success:function(data){
	        if(data.result != 0 ){
	        	$.messager.alert('提示','修改成功');
	        	$("#tt").datagrid("reload");  
	        }else{
	        	$.messager.alert('提示','修改失败');
	        }
		}
		
	})
}


/*
 * 表单验证
 */  
function formVerification(r){

	if(r == "add"){// 新增验证
		$("#role_Code").textbox({
			required:true,
			missingMessage:"用户编号不能为空",
			invalidMessage:"用户编号已经存在",
			validType:"remoteValid['../../judgeRoleCode.json','roleCode']"
		});
		$("#role_Name").textbox({
			required:true,
			missingMessage:"角色不能为空",
			invalidMessage:"角色已经存在",	
			validType:"remoteValid['../../judgeAddRoleCode.json','roleName']"
		});
	}else{// 修改验证
		$("#roleNames").textbox({
			
			required:true,
			missingMessage:"角色不能为空",
			invalidMessage:"角色已经存在",	
			validType:"remoteValid['../../judgeRoleName.json','roleName',{roleCode:'"+$("#roleCodes").val()+"'}]"

		});
		
	}


}
 
 
 
