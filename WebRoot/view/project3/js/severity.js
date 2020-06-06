/*
 * 全局变量
 */
var state = "0";//代表状态呈无效状态
var desc = "2"//普通

/*
 * 页面加载事件
 */
//?severityCode='+ row.severityCode +'
$(function(){
	$("#addBtn").click(addWin);//给增加按钮添加点击事件
	$("#submitBtn").click(addSeverity);//给增加框确认按钮添加点击事件
	$("#add_win").dialog({
		closed:true
	});//默认关闭增加框
	$("#upd_win").dialog({
		closed:true
	});//默认关闭修改框
	$('#dg').datagrid({    
	    url:'../../getAllSeverity.json',    
	    columns:[[    
	        {field:'severityCode',title:'编号',width:'25%',align:'center'},    
	        {field:'severityDesc',title:'严重程度',width:'25%',align:'center'},
	        {field:'moduleState',title:'状态',width:'25%',align:'center'},
	        {field:'zzzzzzz',title:'操作',width:'25%',align:'center',
	        	formatter: function(value,row,index){
	                if(row.moduleState == "有效"){
	        			state = "1";
	        				return '<a style="color:blue" href='+"#"+' onclick='+"updseve("+row.severityCode+")"+'>'+"修改"+'</a>'+" | "+
		        			'<a style="color:blue" href='+"#"+' onclick='+"delseve("+row.severityCode+")"+'>'+"删除"+'</a>'+" | "+
		        			'<a style="color:blue" href='+"#"+' onclick='+"changeState1("+state+','+row.severityCode+")"+'>'+"关闭"+'</a>';
	        		}else{
	        			state = "0";
	        			return '<a style="color:blue" href='+"#"+' onclick='+"updseve("+row.severityCode+")"+'>'+"修改"+'</a>'+" | "+
	        			'<a style="color:blue" href='+"#"+' onclick='+"delseve("+row.severityCode+")"+'>'+"删除"+'</a>'+" | "+
		                '<a style="color:blue" href='+"#"+' onclick='+"changeState("+state+','+row.severityCode+")"+'>'+"开启"+'</a>';
	        		}
	            }
	        },
	        
	    ]]    
	});
	$("#submitUpdBtn").click(updAllSeverity);//修改表单数据
	$("#addCloseBtn").click(addCloseWin);//增加框关闭事件
	$("#updCloseBtn").click(updCloseWin);//修改框关闭事件
})


/*
 * 功能性函数
 */
//定义增加框的宽高
function addWin(){
	title = "添加数据";
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

/*
 * 增删改函数
 */
//增加严重状态函数
function addSeverity(){
	$('#add_form').form({    
	    url:"../../addseverity.json",    
	    onSubmit: function(){    
	        // do some check    
	        // return false to prevent submit;    
	    },    
	    success:function(data){ 
	      if(data.substring(7,8) == "1"){
	    	  $.messager.alert("提示","添加成功");
	    	  $("#add_win").dialog({
	    		  closed:true
	    	  });
	    	  $("#dg").datagrid("reload");
	      }else{
	    	  $.messager.alert("提示","添加失败");
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
		"../../delSeverity.json",
		{"severityCode":id},
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
				"../../updSeverity.json",
				{"moduleState":"有效","severityCode":id},
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
				"../../updSeverity1.json",
				{"moduleState":"禁用","severityCode":id},
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
			"../../seleSeverity.json",
			{"severityCode":code},
			function(data){
				var severityCode = data.rows[0].severityCode;
				var severityDesc = data.rows[0].severityDesc;
				var moduleState = data.rows[0].moduleState;
				$("#a").textbox("setValue",severityCode);
				$("#b").textbox("setValue",severityDesc);
				$("#c").textbox("setValue",moduleState);
				$("#a").textbox("readonly");
				updWin();
			}
	);
}

//修改所有的数据
function updAllSeverity(){
	var severityCode = $("#a").textbox("getValue");
	var severityDesc = $("#b").textbox("getValue");
	var moduleState = $("#c").textbox("getValue");
	
	$.post(
			"../../updAllSeverity.json",
			{"severityCode":severityCode,"severityDesc":severityDesc,"moduleState":moduleState},
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