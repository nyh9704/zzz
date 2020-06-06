/*
 * 全局变量
 */
var url = "../../getJurisdictionr.json";
var data;
/*
 * 预加载事件
 */
$(function(){
	//加载全部人员权限信息
	loadAllData();
	//设置全选复选框状态
	setCheckboxState();
	
})

/*
 * 加载全部人员权限信息
 */

function loadAllData(){
	$("#cAll").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#all").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#all").datagrid({
		url:"../../loadAllData.json?wpId=03",
		singleSelect:true,
		pageList:[100,150,200,220,250,300],
		pageSize:100,		
		columns:[[    
	        {field:'staffName',title:'员工姓名',width:'6%',align:'center'},
	        {field:'postName',title:'岗位名称',width:'6%',align:'center'},
	        {field:'authority01',title:'权限1',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority01 == null || row.authority01 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority01?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority01?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	},
	        	
	        	
	        },
	        {field:'authority02',title:'权限2',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority02 == null || row.authority02 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority02?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority02?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority03',title:'权限3',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority03 == null || row.authority03 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority03?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority03?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority04',title:'权限4',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority04 == null || row.authority04 == '0'){
	        			return "<input   type = 'checkbox' name = 'authority04?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority04?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority05',title:'权限5',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority05 == null || row.authority05 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority05?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority05?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority06',title:'权限6',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority06 == null || row.authority06 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority06?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority06?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority07',title:'权限7',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority07 == null || row.authority07 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority07?"+row.staffName+"' />"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority07?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        		
	        	}
	        },
	        {field:'authority08',title:'权限8',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority08 == null || row.authority08 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority08?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority08?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority09',title:'权限9',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority09 == null || row.authority09 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority09?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority09?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority10',title:'权限10',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority10 == null || row.authority10 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority10?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority10?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	       {field:'authority11',title:'权限11',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority11 == null || row.authority11 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority11?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority11?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority12',title:'权限12',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority12 == null|| row.authority12 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority12?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority12?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority13',title:'权限13',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority13 == null || row.authority13 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority13?"+row.staffName+"' />"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority13?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority14',title:'权限14',width:'6%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority14 == null || row.authority14 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority14?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority14?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        },
	        {field:'authority15',title:'权限15',width:'5%',align:'center',
	        	formatter:function(value,row,index){
	        		if(row.authority15 == null || row.authority15 == '0'){
	        			return "<input  type = 'checkbox' name = 'authority15?"+row.staffName+"'/>"
	        		}else{
	        			return "<input  type = 'checkbox' name = 'authority15?"+row.staffName+"' checked = 'checked'/>"
	        		}
	        	}
	        }
	               
	    ]],
	   
	});
}
	

/**
 * 设置全选复选框状态
 */
function setCheckboxState(){
	$.ajax({
		url:"../../setCheckboxState.json?wpId=03",
		dataType:"json",
		success:function(data){
			if(data.j1 == 1){
				$("#tb input[name = 'authority01']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority01']").removeAttr("checked");
			}
			if(data.j2 == 1){
				$("#tb input[name = 'authority02']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority02']").removeAttr("checked");
			}
			if(data.j3 == 1){
				$("#tb input[name = 'authority03']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority03']").removeAttr("checked");
			}
			if(data.j4 == 1){
				$("#tb input[name = 'authority04']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority04']").removeAttr("checked");
			}
			if(data.j5 == 1){
				$("#tb input[name = 'authority05']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority05']").removeAttr("checked");
			}
			if(data.j6 == 1){
				$("#tb input[name = 'authority06']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority06']").removeAttr("checked");
			}
			if(data.j7 == 1){
				$("#tb input[name = 'authority07']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority07']").removeAttr("checked");
			}
			if(data.j8 == 1){
				$("#tb input[name = 'authority08']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority08']").removeAttr("checked");
			}
			if(data.j9 == 1){
				$("#tb input[name = 'authority09']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority09']").removeAttr("checked");
			}
			if(data.j10 == 1){
				$("#tb input[name = 'authority10']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority10']").removeAttr("checked");
			}
			if(data.j11 == 1){
				$("#tb input[name = 'authority11']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority11']").removeAttr("checked");
			}
			if(data.j12 == 1){
				$("#tb input[name = 'authority12']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority12']").removeAttr("checked");
			}
			if(data.j13 == 1){
				$("#tb input[name = 'authority13']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority13']").removeAttr("checked");
			}
			if(data.j14 == 1){
				$("#tb input[name = 'authority014']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority014']").removeAttr("checked");
			}
			if(data.j15 == 1){
				$("#tb input[name = 'authority15']").prop("checked",true);
			}else{
				$("#tb input[name = 'authority15']").removeAttr("checked");
			}
		}
	})
}






/**
 * 改变权限
 */
function changeState(parameter){
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		traditional:true,
		async:false,
		data:{arrObj:JSON.stringify(parameter)},
		success:function(data){
			$("#all").datagrid('reload');
			setCheckboxState();
			$.messager.alert('提示', '操作成功！');			
		}
	})
	
}

/**
 * 保存
 * @returns
 */
function doSave(){
	var arr = [];	
	var arrObj = [];
	$("input[type = 'checkbox']").each(function(i,e){
		if(e.checked){
			var obj = {};
			arr = e.name.split("?");
			obj.staffName = arr[1];
			obj.state = arr[0];
			obj.code = 1;
			arrObj.push(obj);
		}
		
	})
	 changeState(arrObj);
}

/**
 * 选择每一列的复选框
 * @param e
 * @returns
 */
function selectedColumns(e){
	if(e.checked){
		if(e.name == 'authority01'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority01"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority02'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority02"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority03'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority03"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority04'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority04"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority05'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority05"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority06'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority06"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority07'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority07"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority08'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority08"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority09'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority09"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority10'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority10"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority11'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority11"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority12'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority12"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority13'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority13"){
					$(e).prop("checked",true);
				}
				
			})
		}else if(e.name == 'authority14'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority14"){
					$(e).prop("checked",true);
				}
				
			})
		}else{
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority15"){
					$(e).prop("checked",true);
				}
				
			})
		}
	}else{
		if(e.name == 'authority01'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority01"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority02'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority02"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority03'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority03"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority04'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority04"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority05'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority05"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority06'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority06"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority07'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority07"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority08'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority08"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority09'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority09"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority10'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority10"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority11'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority11"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority12'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority12"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority13'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority13"){
					$(e).removeAttr('checked');
				}
				
			})
		}else if(e.name == 'authority14'){
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority14"){
					$(e).removeAttr('checked');
				}
				
			})
		}else{
			$("input[type = 'checkbox']").each(function(i,e){
				var arr = e.name.split("?");
				if(arr[0] == "authority15"){
					$(e).removeAttr('checked');
				}
				
			});
		}
	}
}




