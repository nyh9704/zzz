/**
 * 全局变量
 */
var userPost;
var userId;
var userPs;
var url;
var userCancel;
/**
 * 预加载事件
 */
$(function(){
	//展示数据
	showDueDiligenceDisposeData();
	window.setInterval(refreshWebPage,180000);
	
	//给确认按钮绑定单击事件
	$("#submitDueBtn").click(function(){
		userPost = '进行中';
		amendUserPost(userPost,userPs);
	});
	
	//给驳回按钮绑定单击事件
	$("#cancelDueBtn").click(function(){
		userPost = '已驳回';	
		url = "../../addFinancialRejectRemark.json";
		$("#sp").html("驳回原因");
		$("#due_ps_div").dialog({
			closed:false,
			title:"请添加驳回原因"
		});
		
	});
	
	//关闭订单申请窗口
	$("#closeDueBtn").click(function(){
		$("#due_div").window('close');
	})
	$("#closeSureBtn").click(function(){
		$("#due_sure_div").window('close');
	})	
	$("#closeCancelBtn").click(function(){
		$("#due_cancel_div").window('close');
	})
	$("#closePsBtn").click(function(){
		$("#due_ps_div").window('close');
	})
	//给确认订单完成窗口中的确认按钮绑定单击事件
	$("#submitSureBtn").click(function(){
		userPost = '已完成';
		amendUserPost(userPost,userPs);
	})
	
	//给用户是否申请取消窗口中的确认按钮绑定单击事件
	$("#submitCancelBtn").click(function(){
		userPost = '已取消';
		amendUserPost(userPost,userPs);
	})
	
	//添加备注
	$("#submitPsBtn").click(function(){
		$.ajax({
			url:url,
			data:{userPs:$("#userPs").textbox('getText'),userId:userId,userPost:userPost,userCancel:userCancel},
			success:function(data){
				if(data.result != 0){
					$.messager.alert("提示","添加成功");
					$("#due_ps_div").window('close');
					$("#due_div").window('close');
					
					$("#due_cancel_div").window('close');
					$("#tt").datagrid('reload');
					
				}else{
					$.messager.alert("提示","添加失败");
				}
			}
		})
	})
	
	//驳回取消订单
	$("#rejectCancelBtn").click(function(){
		userCancel = '已驳回';	
		console.log(userPost)
		url = "../../addFinancialRejectRemark.json";
		$("#sp").html("驳回原因");
		$("#due_ps_div").dialog({
			closed:false,
			title:"请添加驳回原因"
		});
	})
})

/**
 * 展示数据
 */
function showDueDiligenceDisposeData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../showFinancialDueDiligenceDisposeData.json",
		columns:[[    
			{field:'userId',title:'订单编号',width:'5%',align:'center'},
	        {field:'userName',title:'用户名',width:'5%',align:'center'},
	        {field:'userChooseHouse',title:'房号',width:'25%',align:'center'},
	        {field:'particularsHouse',title:'房屋详情',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		return "<a href='#' onclick=\"particularsHouse("+row.userId+")\" style='color:blue'>房屋详情</a>"
	        	}
	        },
	        {field:'userPostDate',title:'订单提交时间',width:'10%',align:'center',}, 
	        {field:'userEndDate',title:'订单结束时间',width:'10%',align:'center',}, 	  
	        {field:'userPost',title:'订单状态',width:'10%',align:'center',
	        	formatter:function(value,row,index){
	        		if(value == '进行中'){
	        			return "<span style='color:blue'>"+value+"<span>";
	        		}else if(value == '已驳回'){
	        			return "<span style='color:red'>"+value+"<span>";
	        		}else if(value == '已完成'){
	        			return "<span style='color:green'>"+value+"<span>";
	        		}else{
	        			return "<span>"+value+"<span>";
	        		}
	        	}
	        },
	        {field:'userPs',title:'备注',width:'10%',align:'center'},
	        {field:'userCancel',title:'用户是否申请取消',width:'10%',align:'center'},
	        {field:'userPostFile',title:'附件',width:'5%',align:'center',
	        	formatter:function(value,row,index){
	        		return "<a href='../../financialDownFile.json?userId="+row.userId+"' style='color:blue'>点击下载</a>"
	        	}
	        }
	    ]]	   
	});
}

/**
 * 查询待处理
 */
function pendingOrder(){
	$("#tt").datagrid("load",{pendingOrder:'待处理'});
}

/**
 * 查询已驳回
 */
function rejectOrder(){
	$("#tt").datagrid("load",{rejectOrder:'已驳回'});
}

/**
 * 查询进行中
 */
function underwayOrder(){
	$("#tt").datagrid("load",{underwayOrder:'进行中'});
}

/**
 * 查询已完成
 */
function accomplishOrder(){
	$("#tt").datagrid("load",{accomplishOrder:'已完成'});
}

/**
 * 查询被取消订单
 */
function cancelOrder(){
	$("#tt").datagrid("load",{cancelOrder:'是'});
}

/**
 * 查询取消申请中
 */
function applyCancelOrder(){
	$("#tt").datagrid("load",{applyCancelOrder:'否'});
}

/**
 * 查询全部
 */
function searchAllOrderr(){
	$("#tt").datagrid("load",{});
}

/**
 * 接受订单
 */
function acceptingOrder(){
	var list = $("#tt").datagrid('getSelections');
	if(list.length > 0){
		if(list[0].userPost != '已取消'){
			if(list[0].userPost != '待处理'){
				$.messager.alert("提示","请选择状态为：‘未处理’的数据");
			}else{
				$("#due_div").window('open');
				$(".userPost:eq(0)").html(list[0].userPost);
				userId = list[0].userId;
			}
		}else{
			$.messager.alert("提示","请选择其他状态的饿数据");
		}
		
	}else{
		$.messager.alert("提示","请选择一条数据");
	}
}

/**
 *确认订单完成
 */
function confirmOrder(){
	var list = $("#tt").datagrid('getSelections');
	if(list.length > 0){
		if(list[0].userPost != '已结束'){
			if(list[0].userPost != '进行中'){
				$.messager.alert("提示","请选择状态为：‘进行中’的数据");
			}else{
				$("#due_sure_div").window('open');
				$(".userPost:eq(1)").html(list[0].userPost);
				userId = list[0].userId;
			}
		}else{
			$.messager.alert("提示","请选择其他状态的数据");
		}
		
	}else{
		$.messager.alert("提示","请选择一条数据");
	}
}

/**
 * 同意取消订单
 */
function agreedCancelOrder(){
	var list = $("#tt").datagrid('getSelections');
	if(list.length > 0){
		if(list[0].userPost != '已结束'){
			if(list[0].userCancel != '是'){
				$.messager.alert("提示","请选择用户是否申请取消状态为：‘是’的数据");
			}else{
				$("#due_cancel_div").window('open');
				$(".userPost:eq(2)").html(list[0].userCancel);
				userPost = list[0].userPost;
				userId = list[0].userId;
			}
		}else{
			$.messager.alert("提示","请选择其他状态的数据");
		}
		
	}else{
		$.messager.alert("提示","请选择一条数据");
	}
}

/**
 * 添加备注
 */
function addRemark(){
	var list = $("#tt").datagrid('getSelections');
	if(list.length > 0){
		if(list[0].userPost != '已结束'){
			url = "../../addFinancialRemark.json";
			$("#due_ps_div").window('open');
			userId = list[0].userId;
		}else{
			$.messager.alert("提示","请选择其他状态的数据");
		}
		
	}else{
		$.messager.alert("提示","请选择一条数据");
	}
}

/**
 * 改变数据状态
 */
function amendUserPost(userPost,userPs){
	$.ajax({
		url:"../../amendFinancialUserPost.json",
		data:{userPost:userPost,userId:userId},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result != 0){
				$.messager.alert("提示","修改成功");
				$("#due_div").window('close');
				$("#due_sure_div").window('close');
				$("#due_cancel_div").window('close');
				$("#tt").datagrid('reload');
			}else{
				$.messager.alert("提示","修改失败");
			}
		}
	})
}


/**
 * 
 */


/**
 * 附件下载
 */
function downuserPostFile(fileName){
	alert(1)
}


/**
 *查询所选择房屋的链接
 */
function particularsHouse(chooseHouse){
	$.ajax({
		url:"../../searchFinancialHouseUrlByChooseHouse.json",
		data:{userId:chooseHouse},
		type:"post",
		dataType:"json",
		success:function(data){
			openPencel(data.result);
		}
	})
}

/**
 * 关闭详情页
 */
function closePartichlarsWin(){
	$("#particulars_win").window('close');
}



/**
 * 详情页面
 * @param shiCode
 * @returns
 */
function openPencel(shiCode) {
	url = "/training/showFyXq.json";
	$.ajax({
		url : url,
		data : {
			"shiUrl" : shiCode
		},
		success : function(data) {
			var dxp = (data.showFyXq)[0];
			if (dxp == null || data.length == 0) {
				return;
			}
			for (attr in dxp) {
				console.log(attr)
				$("span[name=" + attr + "]").html(dxp[attr]);
			}
		}
	});
	$("#particulars_win").window('open');

}

/**
 * 刷新网页
 */
function refreshWebPage(){
	var strUrl = window.location.href;	
	if(strUrl.split('/')[7] == 'financialDataAnalyze.html'){
		searchAllOrderr();
	}	
     
}
