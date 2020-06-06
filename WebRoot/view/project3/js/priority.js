//全局变量
var title = "";


//页面加载时间
$(function(){
	$("#addBtn").click(addWin);//形成增加框
	$("#submitBtn").click(addSubmit);
	$("#add_win").dialog({
		closed:true
	});
	$("#upd_win").dialog({
		closed:true
	});
	$('#dg').datagrid({    
	    url:'../../getAllPriority.json',    
	    columns:[[    
	        {field:'priorityCode',title:'编号',width:'25%',align:'center'},    
	        {field:'priorityDesc',title:'优先级',width:'25%',align:'center'},
	        {field:'moduleState',title:'状态',width:'25%',align:'center'},
	        {field:'zzzzzzz',title:'操作',width:'25%',align:'center',
	        	formatter: function(value,row,index){
	                if(row.moduleState == "有效"){
	        			state = "1";
	        			return '<a style="color:blue" href='+"#"+' onclick='+"updseve("+row.priorityCode+")"+'>'+"修改"+'</a>'+" | "+
	        			'<a style="color:blue" href='+"#"+' onclick='+"delseve("+row.priorityCode+")"+'>'+"删除"+'</a>'+" | "+
	        			'<a style="color:blue" href='+"#"+' onclick='+"changeState1("+state+','+row.priorityCode+")"+'>'+"关闭"+'</a>';
	        		}else{
	        			state = "0";
	        			return '<a style="color:blue" href='+"#"+' onclick='+"updseve("+row.priorityCode+")"+'>'+"修改"+'</a>'+" | "+
	        			'<a style="color:blue" href='+"#"+' onclick='+"delseve("+row.priorityCode+")"+'>'+"删除"+'</a>'+" | "+
		                '<a style="color:blue" href='+"#"+' onclick='+"changeState("+state+','+row.priorityCode+")"+'>'+"开启"+'</a>';
	        		}
	            }
	        },
	        
	    ]]    
	});
	$("#submitUpdBtn").click(updAllSeverity);//修改表单数据
	$("#addCloseBtn").click(addCloseWin);//增加框关闭事件
	$("#updCloseBtn").click(updCloseWin);//修改框关闭事件
});

/**
 * 功能性函数
 */ 
function addWin(){
	title = "添加缺陷状态";
	$("#add_win").dialog({
	    width:300,
	    height:200,
	    modal:true,
	    title:title,
	    closed:false
	})
}


function updWin(){
	title = "修改数据";
	$("#upd_win").dialog({
	    width:300,
	    height:200,
	    modal:true,
	    title:title,
	    closed:false
	})
}

//关闭增加框
function addCloseWin(){
	$("#add_win").dialog({
	    width:300,
	    height:200,
	    modal:true,
	    title:title,
	    closed:true
	});
}
//关闭修改框
function updCloseWin(){
	$("#upd_win").dialog({
	    width:300,
	    height:200,
	    modal:true,
	    title:title,
	    closed:true
	});
}
/**
 * 添加数据
 * @returns
 */
function addSubmit(){
	$('#add_form').form({    
	    url:"../../addpriority.json",    
	    onSubmit: function(){    
	        // do some check    
	        // return false to prevent submit;    
	    },    
	    success:function(data){    
	    	if(data.res != "0"){
	    	   $.messager.alert('提示','操作成功');
	    	   $("#add_win").dialog({
		    		  closed:true
		    	  });
	    	   
		       $("#dg").datagrid("reload");
		       $("#add_form").form("clear");
	       }else{
	    	   $.messager.alert('提示','操作失败'); 
	       }
	    }    
	});    
	// submit the form    
	$('#add_form').submit();  
}


//删除数据
function delseve(id){
	console.log(id)
	$.post(
		"../../delPriority.json",
		{"priorityCode":id},
		function(data){
			if(data.res == "1"){
		    	  $.messager.alert("提示","删除成功");
		    	  $("#dg").datagrid("reload");
		      }else{
		    	  $.messager.alert("提示","删除失败");
		    	  $("#dg").datagrid("reload");
		      }
			
		}
		
	)
}

//修改状态(无效状态)
function changeState(state,id){
	console.log(state == "1")
	console.log(state);
	console.log(id);
	if(state == "1"){
		$.messager.alert("提示","目前是有效状态，不能开启!");
	}else{
		$.post(
				"../../updPriority.json",
				{"moduleState":"有效","priorityCode":id},
				function(data){
					if(data.res == "1"){
				    	  $.messager.alert("提示","操作成功");
				    	  $("#dg").datagrid("reload");
				      }else{
				    	  $.messager.alert("提示","操作失败");
				    	  $("#dg").datagrid("reload");
				      }
					
				}
				
			)
	}
}

//修改状态(有效状态)
function changeState1(state,id){
	if(state == "1"){
		$.post(
				"../../updPriority1.json",
				{"moduleState":"禁用","priorityCode":id},
				function(data){
					if(data.res == "1"){
				    	  $.messager.alert("提示","操作成功");
				    	  $("#dg").datagrid("reload");
				      }else{
				    	  $.messager.alert("提示","操作失败");
				    	  $("#dg").datagrid("reload");
				      }
					
				}
				
			)
		
	}else{
		$.messager.alert("提示","目前是无效状态，不能关闭!");
	}
}

//展现数据
function updseve(code){
	$.post(
			"../../selePriority.json",
			{"priorityCode":code},
			function(data){
				var priorityCode = data.rows[0].priorityCode;
				var priorityDesc = data.rows[0].priorityDesc;
				var moduleState = data.rows[0].moduleState;
				$("#a").textbox("setValue",priorityCode);
				$("#b").textbox("setValue",priorityDesc);
				$("#c").textbox("setValue",moduleState);
				$("#a").textbox("readonly");
				updWin();
			}
	);
}


//修改所有的数据
function updAllSeverity(){
	var priorityCode = $("#a").textbox("getValue");
	var priorityDesc = $("#b").textbox("getValue");
	var moduleState = $("#c").textbox("getValue");
	
	$.post(
			"../../updAllPriority.json",
			{"priorityCode":priorityCode,"priorityDesc":priorityDesc,"moduleState":moduleState},
			function(data){
				if(data.res != "1"){
					$.messager.alert("提示","修改失败！");
				}else{
					$.messager.alert("提示","修改成功！");
					$("#upd_win").dialog({
			    		  closed:true
			    	  });
					$("#upd_form").dialog("clear");
					$("#dg").datagrid("reload");
				}
			}
	);
}















































