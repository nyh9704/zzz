/**
 * 预加载事件
 */
$(function(){
	
	//加载下拉列表的值
	createOptionValue();
	//分页查询
	searchPersonalData();
	//给提交按钮绑定单击事件
	$("#submitBtn").click(enteringHouseData);
	//给增加按钮绑定单击事件
	$("#addBtn").click(function(){
		//$("#personalData_div").ajaxLoading
		
		url = '../../enteringHouseData.json';
		$('#personalData_div').dialog({
			title : '新增房源信息',
			closed : false
		});
	});
	//给关闭按钮绑定单击事件
	$("#closeBtn").click(closeBtn);
	//给搜索按钮绑定单击事件
	$("#searchBtn").click(searchKeyWorld);
	//给删除按钮绑定单击事件
	$("#removeBtn").click(deletePersonalData);
	$("#sss").click(doTry)
	//给日期下拉列表添加清除按钮
	clearDateButton();
})

/**
 * 全局标量
 */
var title;//标题
var url;//请求地址

/**
 * 加载下拉列表值
 */
function createOptionValue(){
	//区域
	var hidAreaOption = [{    
	    "id":"江北区",    
	    "text":"江北区"   
	},{    
	    "id":"渝中区",    
	    "text":"渝中区"   
	},{    
	    "id":"南岸区",    
	    "text":"南岸区",    
	},{    
	    "id":"九龙坡区",    
	    "text":"九龙坡区"   
	},{    
	    "id":"北碚区",    
	    "text":"北碚区" 
	},{	
		"id":"渝北区",
		"text":"渝北区"		
	},{
		"id":"沙坪坝区",
		"text":"沙坪坝区"	
	},{
		"id":"巴南区",
		"text":"巴南区"	
	},{
		"id":"大渡口区",
		"text":"大渡口区"	
	}];
	
	//给区域下拉列表加载值
	$("#hidArea").combobox({
		valueField: 'id',
		textField: 'id',
		data:hidAreaOption,
		panelHeight:'auto',
		width:200,
		editable:false,
		icons:[{
			iconCls:'icon-clear',
			handler: function(e){
			$(e.data.target).combobox('clear');
			}
			}]
	})
	
	//附带品
	var hidAttachOption = [{
		"id":"车位",
		"text":"车位"	
	},{
		"id":"地下室",
		"text":"地下室"	
	},{
		"id":"花园",
		"text":"花园"	
	}]
	
	//给附带品加载值
	$("#hidAttach").combobox({
		valueField: 'id',
		textField: 'id',
		data:hidAttachOption,
		panelHeight:'auto',
		width:200,
		editable:false,
		icons:[{
			iconCls:'icon-clear',
			handler: function(e){
			$(e.data.target).combobox('clear');
			}
			}]
	})
	
	//装修
	var hidFinishOption = [{
		"id":"清水",
		"text":"清水"	
	},{
		"id":"简装",
		"text":"简装"	
	},{
		"id":"精装",
		"text":"精装"	
	},{
		"id":"豪装",
		"text":"豪装"	
	}];
	
	//给装修加载值
	$("#hidFinish").combobox({
		valueField: 'id',
		textField: 'id',
		data:hidFinishOption,
		panelHeight:'auto',
		width:200,
		editable:false,
		icons:[{
			iconCls:'icon-clear',
			handler: function(e){
			$(e.data.target).combobox('clear');
			}
			}]
	})
	
	//朝向
	var hidOrientationOption = [{
		"id":"东",
		"text":"东"	
	},{
		"id":"南",
		"text":"南"	
	},{
		"id":"西",
		"text":"西"	
	},{
		"id":"北",
		"text":"北"	
	}]
	
	//给朝向加载值
	$("#hidOrientation").combobox({
		valueField: 'id',
		textField: 'id',
		data:hidOrientationOption,
		panelHeight:'auto',
		width:200,
		editable:false,
		icons:[{
			iconCls:'icon-clear',
			handler: function(e){
			$(e.data.target).combobox('clear');
			}
			}]
	})
	//首图类型
	var hidFirstPicTypeOption = [{
		"id":"户外图",
		"text":"户外图"	
	},{
		"id":"户内图",
		"text":"户内图"	
	},{
		"id":"户型图",
		"text":"户型图"	
	}];
	
	//给首图类型加载值
	$("#hidFirstPicType").combobox({
		valueField: 'id',
		textField: 'id',
		data:hidFirstPicTypeOption,
		panelHeight:'auto',
		width:200,
		editable:false,
		icons:[{
			iconCls:'icon-clear',
			handler: function(e){
			$(e.data.target).combobox('clear');
			}
			}]
	})
	
	//文件框
	$('#hidFirstPic').filebox({    
    buttonText: '选择文件', 
    buttonAlign: 'left',
    width:200,
    prompt:'请选择图片文件'
	})
}

/**
 * 关键字查询
 */
function searchKeyWorld(){
	$("#tt").datagrid("load", $("#serchForm").serializeObject());
	$("#serchForm").form('clear');
}


/**
 * 分页查询
 */
function searchPersonalData(){
	$("#tt").datagrid(Common.createDatagridOptionsParams("","",135,null))
	$("#tt").datagrid({
		url:"../../searchPersonalData.json",
		columns:[[    
	        {field:'hidArea',title:'区域',width:'12%',align:'center'},
	        {field:'hidName',title:'小区名称',width:'15%',align:'center'},
	        {field:'hidAddress',title:'具体地址',width:'15%',align:'center'},
	        {field:'hidTitle',title:'标题',width:'8%',align:'center'}, 	       
	        {field:'houseType',title:'户型',width:'12%',align:'center'},
	        {field:'hidAttach',title:'附带品',width:'15%',align:'center'},
	        {field:'hidSpace',title:'面积',width:'15%',align:'center'},
	        {field:'hidPrice',title:'价格',width:'8%',align:'center',sortable:true}, 
	        {field:'hidOrientation',title:'朝向',width:'12%',align:'center'},
	        {field:'houseFloor',title:'楼层',width:'15%',align:'center'},
	        {field:'hidFinish',title:'装修',width:'15%',align:'center'},
	        {field:'hidHouseAge',title:'房龄',width:'8%',align:'center'}, 
	        {field:'hidFirstPicType',title:'首图类型',width:'12%',align:'center'},
	        {field:'hidFirstPic',title:'首图',width:'15%',align:'center',
	        	formatter:function(value,row,index){
	        		return '<a id="a" style="color:blue" href="#" onclick="showPic('+row.hidId+')">'+"点击这里查看图片"+'<a/>'
	        	}
	        },
	        {field:'hidIssueDate',title:'发布时间',width:'15%',align:'center'},
	        {field:'hidClickRateDay',title:'日点击量',width:'8%',align:'center'}, 
	        {field:'hidConsultClientDay',title:'当日咨询客户非中介',width:'5%',align:'center'},
	        {field:'hidConsult',title:'到访客户',width:'5%',align:'center'},
	        {field:'hidCClient',title:'C类客户',width:'5%',align:'center'},
	        {field:'hidBClient',title:'B类客户',width:'5%',align:'center'}, 
	        {field:'hidAClient',title:'A类客户',width:'5%',align:'center'},
	        {field:'hidSuccessClient',title:'签单客户',width:'5%',align:'center'}
	    ]]	   
	});
}



/**
 * 录入房源
 */
function enteringHouseData(){
	
	
	var formData = $("#personalData_form").serializeObject();
	console.log(formData);
	if(formData.hidArea != "" && formData.hidName != "" && formData.hidAddress != "" 
			&& formData.hidTitle != "" 
			&& formData.hidPrice != ""){
		$.ajax({
			type:'post',
			url:url,
			data:new FormData($("#personalData_form")[0]),
			dataType:'json',
			processData:false,
			contentType:false,
			success:function(data){
				console.log(data.result)
				if(data.result == "pic"){
					$.messager.alert('提示','请选择图片');
				}else if(data.result == 1){					
					$.messager.alert('提示','操作成功');
					$("#tt").datagrid('reload');
					$("#personalData_form").form('clear');
					$("#personalData_div").window('close');
				}else{
					$.messager.alert('提示','操作失败');
				}
			}
		})
	}else{
		$.messager.alert('提示','无效的提交');
	}
	
	
}


/**
 * 图片预览
 */
function showPic(value){
	var newImg = new Image();
	$.ajax({
		type:'post',
		url:'../../getImgUrl.json',
		data:{'hidId':value},
		dataType:'json',
		async:false,
		success:function(data){
			$("#xxx").picturePreview([{"url":data.imgUrl},{"url":data.imgUrl},{"url":data.imgUrl}],"xxx");
			/*newImg.src = data.imgUrl;
			$("#img").attr("src",newImg.src);
			newImg.onload = function(){
				$("#picture_win").dialog({
					closed:false,
					title:"首图",
					width:newImg.width+18,
					height:newImg.height+40,
					onClose:function(){
						newImg.src = "";
					}
				});
			}*/
			
			
		}
	})
	
	
}

/**
 * 修改按钮
 */
function doModify() {
	$("#personalData_form").form('clear');
	var a = $("#tt").datagrid('getSelections');
	console.log(a)
	if (a.length > 0) {
		$('#personalData_div').dialog({
			title : '修改房源信息',
			closed : false
		});
		url = "/training/searchModifyPersonal.json?hidId=" + a[0].hidId + "";
		$.ajax({
			url : url,
			dataType : "json",
			success : function(data) {
				$("#personalData_form").form("load", (data.searchModifyPersonal)[0]);
				$("#hidFirstPic").textbox('setValue',(data.searchModifyPersonal)[0].hidFirstPic);
			}
		});
		url = "/training/modifyPersonal.json";

	} else{
		$.messager.alert("提示", "请选择一条数据修改");
	}
}


/**
 * 弹框关闭按钮
 */
function closeBtn(){
	$("#personalData_form").form('clear');
	$('#personalData_div').dialog({
		closed : true
	});
}

/**
 * 删除数据
 */
function deletePersonalData(){
	var personalData = $("#tt").datagrid('getSelections');
	if (personalData.length > 0) {
		url = "../../deletePersonalData.json?hidId=" + personalData[0].hidId + "";
		$.messager.confirm('确认', '您确认想要删除记录吗？此操作不可返回请酌情考虑', function(r) {
			if (r) {
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

	} else {
		$.messager.alert("提示", "请选择一条数据删除！")
	}
}

/**
 * 提交日报
 */
function doTry(){
	$.ajax({
		url:'../../doTry.json',
		type:'post',
		dataType:'json',
		success:function(data){
			console.log(data.result)
		}
	})	
}

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
    
    $('#hidIssueDate').datebox({
    	editable:false,
    	buttons: buttons,//增加清除按钮
    	onSelect:function(date){
    	//TODO：
    	}
    });
}



/**
 * 清空表单
 */
function clearForm(){
	$("#form_search").form('clear');
}

/**
 * 
 */
