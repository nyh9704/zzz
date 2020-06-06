 var code = true;
$(function(){
	
})

function domodiyf(){
	//获取当前页面网址
	var url = window.location.href;
	//将该字符串转成数组
	var arr = url.split("/");
	//新建一个数组并将该数组里面的值拼成字符串
	var str=[];
	for(var i=0;i<5;i++){
		str[i]=arr[i]
	}
	var newUrl = str.join("/");
	//该条消息属于什么类型的消息
	var messageType = "houseTable";
	//跳转的网址
	var turnUrl = newUrl+"/"+"wyzc/"+messageType+".html";
	var dataId = arr[6]
	var str = '<a href="javascript:;" class="active menuTab" data-id="">'+code+'<i class="fa fa-times-circle"></i></a>';
    $('.menuTab').removeClass('active');
    var str1 = '<iframe class="LRADMS_iframe" id="" name=""  width="100%" height="100%" src="' + turnUrl + '" frameborder="0" data-id="" seamless></iframe>';
    $('.mainContent').find('iframe.LRADMS_iframe').hide();    
    var strArr = [];
    $('.menuTabs .page-tabs-content').find("a").each(function(index,element){
    	strArr.push(element.innerText);
    })  
    for(var i = 0; i < strArr.length; i++){
    	if(strArr[i] != messageType){
    		code = true;
    		
    	}else{
    		code = false;   		
    	}
    }
    if(code){
    	$('.menuTabs .page-tabs-content').append(str);
    	$('.mainContent').append(str1);
    }

}

function dos(){
	$("#if").attr('src','img.html');
}

