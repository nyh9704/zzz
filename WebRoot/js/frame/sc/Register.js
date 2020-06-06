var basePath = getWebAppRootPath();
var code = true;
$(function () {
    /**
     * 加载一级部门
     */
    $.ajax({
        url: basePath + "/searchParentPart.lyh",
        dataType: 'json',
        async: false,
        success: function (data) {
            for (var i = 0; i < data.result.length; i++) {
                $("#wpId").append("<option value=" + data.result[i].wpId + ">" + data.result[i].wpName + "</option>");
            }
            $("#wpId").append("<option value='root'>请选择部门</option>");
        }
    })

    /**
     * 加载子部门
     */
    $("#wpId").change(function () {
        var value = $("#wpId").val();
        if ($("#wpId").val() == "root") {
            $("#wpIdChild").css('display', 'none');
            $("#wdId").empty();
        } else {
            $.ajax({
                url: basePath + "/searchChildPart.lyh",
                dataType: 'json',
                data: {"parentId": $("#wpId").val()},
                success: function (data) {
                    if (data.result.length != 0) {
                        $("#wpIdChild").empty();
                        $("#wdId").empty();
                        for (var i = 0; i < data.result.length; i++) {
                            $("#wpIdChild").append("<option value=" + data.result[i].wpId + ">" + data.result[i].wpName + "</option>");
                        }
                        $("#wpIdChild").append("<option value='root'>请选择部门</option>");
                        $("#wpIdChild option[value='root']").prop("selected", 'selected');
                        $("#wpIdChild").css('display', 'inline-block');
                    } else {
                        $("#wpIdChild").css('display', 'none');
                        $("#wdId").empty();
                        if ($("#wpId").val() == "05") {
                            $("#wdId").append("<option value='0'>请选择职位</option>");
                            $("#wdId").append("<option value='4'>顾问</option>");
                            $("#wdId").append("<option value='2'>主管</option>");
                            $("#wdId").append("<option value='3'>经理</option>");
                        } else {
                            $("#wpIdChild").css('display', 'none');
                            $("#wdId").empty();
                            $("#wdId").append("<option value='0'>请选择职位</option>");
                            $("#wdId").append("<option value='1'>专员</option>");
                            $("#wdId").append("<option value='2'>主管</option>");
                            $("#wdId").append("<option value='3'>经理</option>");
                        }
                    }
                }

            })

        }


    })
    //加载职位
    $("#wpIdChild").change(function () {
        if ($("#wpIdChild").val() == "root") {
            $("#wdId").empty();
        } else {
            $("#wdId").empty();
            $("#wdId").append("<option value='0'>请选择职位</option>");
            $("#wdId").append("<option value='1'>专员</option>");
            $("#wdId").append("<option value='2'>主管</option>");
            $("#wdId").append("<option value='3'>经理</option>");
        }

    })

    /**
     * 设置下拉列表值默认
     */
    $("#wpId option:last").prop("selected", 'selected');
})


/**
 * 表单序列化
 * @param form
 * @returns
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
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


/**
 * 提交注册表单
 */

function registerAccount() {
    var obj = $('#register_form').serializeObject();
    if (obj.userId == '' && obj.identityCard == ''
        && obj.userName == '' && obj.userPwd == '' && obj.wpId == 'root') {
        alert("请完善信息");
    } else if (obj.userId == '' && obj.identityCard == '' && wpIdChild == 'root'
        && obj.userName == '' && obj.userPwd == '') {
        alert("请完善信息");
    } else if (obj.userId == '' && obj.identityCard == '' && wpIdChild == 'root'
        && obj.userName == '' && obj.userPwd == '') {
        alert("请完善信息");
    } else if (code == false) {
        alert("请完善信息");
    } else if (obj.userId == '' || obj.identityCard == '' || obj.wpId == '' ||
        obj.userName == '' || obj.userPwd == '' || obj.wpId == '') {
        alert("请完善信息");
    } else if (obj.userId == '' && obj.identityCard == '' && wpIdChild == 'root'
        && obj.userName == '' && obj.userPwd == '') {
        alert("请完善信息");
    } else if (obj.wpId == 'root') {
        alert("请完善信息");
    } else if (obj.userPwd.length < 8) {
        alert("请输入8位以上密码");
    } else {
        $.ajax({
            url: basePath + "/registerUser.lyh",
            type: "post",
            data: $('#register_form').serializeObject(),
            dataType: 'json',
            async: false,
            success: function (data) {
                if (data.result == "1") {
                    alert('注册成功！');
                    $("#register_form")[0].reset();
                    window.location.href = "http://localhost:8080/training/#";

                } else {
                    alert('注册失败，如各项信息均属无误，请联系技术部！');
                }
            }
        })
    }
}


/**
 * 获取web应用程序根路径
 */
function getWebAppRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
    } else {
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }
}


/**
 * 关闭注册页面并显示登陆页面
 */
function closeWindow() {

    window.location.href = "http://localhost:8080/training/#";

}