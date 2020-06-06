/**
 * 全局变量
 */
var data1 = [];

/**
 * 预加载事件
 * @returns
 */
$(function(){
	loadTreeData()
})

/**
 * 加载数据
 * @returns
 */
function loadTreeData(){
	$("#treeAuth").tree({
		animate:true,
		checkbox:true,//显示复选框
		url:"../../ProjectMangementUrl.json?parentId=0",
		loadFilter:function(data){  
	          //过滤操作  
	       return data.parent;
	    },
	    onCheck:function(node,checked){
	    	if(checked){
	    		$("#authority_div").window('open')
	    	}
	    	
	    }
	});
}






function xx(value,row,index){
	return "<input type='checkbox'/>";
}






function ss(){
	
}