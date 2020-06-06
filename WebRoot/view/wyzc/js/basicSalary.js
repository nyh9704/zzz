var url="";
$(function () {

})



// 模糊查询
function doMoSearch() {
    $("#t").datagrid("load", $("#form_search").serializeObject());
}

// 添加按钮
function doAdd() {
    $("#fbsNumber").next().hide();
    url = "/training/addBasicSalary.json"
    $("#form_save").form('clear');
    $('#dd').dialog({
        title : '增加方案',
        closed : false
    });
}



// 修改按钮
function doModify() {
    $("#fbsNumber").next().show();
    $("#form_save").form('clear');
    var a = $("#t").datagrid('getSelections');
    if (a.length > 0) {
        $('#dd').dialog({
            title : '修改',
            closed : false
        });
        url = "/training/searchModifyBasicSalary.json";

        $.ajax({
            url : url,
            dataType : "json",
            success : function(data) {
                $("#form_save").form("load", (data.searchModifyBasicSalary)[0]);
            }
        });
        url = "/training/modifyBasicSalary.json";

    } else
        ($.messager.alert("提示", "请选择一条数据修改"))
}




// 弹框保存按钮
function doSave() {
    $.messager.confirm('确认', '您确认添加吗？添加过后不能删除请谨慎选择', function(r) {
        if (r) {
            $("#form_save").form('submit', {
                url : url,
                success : function(data) {
                    var data = eval('(' + data + ')');
                    if (data.result != 0 && data.rel != 0) {
                        $("#t").datagrid('reload');
                        $('#dd').dialog({
                            closed : true
                        });
                        $.messager.alert('提示', '操作成功！');
                        $("#form_save").form('clear');
                    }
                }
            })
        } else {
            $("#t").datagrid("reload");
            doAdd();
        }
    });
}

// 弹框取消关闭按钮
function doCancel() {
    $("#form_save").form('clear');
    $('#dd').dialog({
        closed : true
    });
}

// 增加重新填写按钮
function doRest() {
    $("#form_save").form('clear')
}

// 弹框关闭按钮
function closePartichlarsWin() {
    $("#particulars_win").window('close');
}

/**
 * 清空表单
 */
function clearForm(){
    $("#form_search").form('clear');
}