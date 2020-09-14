<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<style>
    th {
        text-align: center;
        background-color: #eaf5f7;
    }

    td {
        text-align: center;
    }
</style>

<script>
    /*展示用户数据*/
    $(function () {
        $("#userTable").jqGrid(
            {
                url: "${path}/user/allUser",//发送请求 传递什么参数？  page页码    rows每页展示多L少条
                editurl: "${path}/user/edit", //oper  三个值  edit add del
                datatype: "json",    //响应  拿到的返回值？page页码   rows当前页的数据  total总页数    records总条数
                colNames: ['Id', '用户名', '手机号', '微信', '头像', '签名', '状态', '注册时间'],
                colModel: [
                    {
                        name: 'id', width: 55
                    },
                    {
                        name: 'username', align: "center", editable: true, width: 90
                    },
                    {
                        name: 'phone', align: "center", editable: true, width: 100
                    },
                    {
                        name: 'wechat', align: "center", editable: true, align: "center"
                    },
                    {
                        name: 'headImg', align: "center", editable: true, edittype: "file", align: "center",
                        formatter: function (cellvalue, options, rowObject) {
                            //三个参数  列的值 ，操作 ，行对象
                            return "<img width='120px' height='80px' src='" + cellvalue + "'/>";
                        }
                    },
                    {
                        name: 'sign', editable: true, align: "center"
                    },
                    {
                        name: 'status', align: "center", width: 150, sortable: false,
                        formatter: function (cellvalue, options, rowObject) {
                            //三个参数  列的值 ，操作 ，行对象
                            if (cellvalue == "正常") {
                                return "<a onclick='updateStstus(\"" + rowObject.id + "\",\"" + cellvalue + "\")' class='btn btn-success'>正常</a>"
                            } else {
                                return "<a onclick='updateStstus(\"" + rowObject.id + "\",\"" + cellvalue + "\")' class='btn btn-danger'>冻结</a>"
                            }
                        }
                    },
                    {
                        name: 'createDate', align: "center", align: "center"
                    },
                ],
                pager: '#userPage',
                rowList: [5, 10, 15, 20],
                rowNum: 5,//初始化每页条数
                viewrecords: true,//展示总条数
                page: 1,//初始化页码
                styleUI: "Bootstrap",
                height: "auto",
                autowidth: true,//表格自适应
            });
        $("#userTable").jqGrid('navGrid', '#userPage', {
                edit: false,
                add: true,
                del: false,
                search: true,
                refresh: true,
                search: true
            },
            {},//操作修改之后的额外操作
            {
                //操作添加之后的额外操作
                //关闭文本框
                closeAfterAdd: true,
                //手动文件上传
                afterSubmit: function (response) {
                    var id = response.responseJSON.id;
                    //异步文件上传
                    $.ajaxFileUpload({
                        url: "${path}/user/uploadFile",
                        type: "post",
                        dataType: "text",
                        fileElementId: "headImg",//上传的文件域id
                        data: {id: id},
                        success: function () {
                            //刷新表单
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    //必须要有返回值
                    return "123";
                }
            },
            {});
    });

    //修改用户状态
    function updateStstus(id, status) {
        if (status == "正常") {
            $.ajax({
                url: "${path}/user/edit",
                type: "post",
                data: {"id": id, "status": "冻结", "oper": "edit"},
                success: function (data) {
                    $("#userTable").trigger("reloadGrid");
                }
            })
        } else {
            $.ajax({
                url: "${path}/user/edit",
                type: "post",
                data: {"id": id, "status": "正常", "oper": "edit"},
                success: function (data) {
                    $("#userTable").trigger("reloadGrid");
                }
            })
        }
    }

    //发送短信验证码
    function sendCode() {
        var phone = $("#phoneCode").val();
        // alert(phone)
        //2 发送验证码
        $.post("${path}/user/getAliSendPhone", {"number": phone}, function (data) {
            //展示提示信息
            $("#messages").html(data.message)

            $("#showMessage").show();

            setTimeout(function () {
                //关闭警告框
                $("#showMessage").hide();
            }, 3000);
        }, "JSON")
    }

    //导出用户数据
    $("#easyPoiWrite").click(function () {
        $.post("${path}/user/easyPoiWrite", function (data) {
            $("#messages").html(data.message)
            $("#showMessage").show();
            setTimeout(function () {
                //关闭警告框
                $("#showMessage").hide();
            }, 3000);
        }, "JSON")
    })

    //导入用户数据
    $("#easyPoiRead").click(function () {
        $.post("${path}/user/easyPoiRead", function (data) {
            $("#messages").html(data.message)
            $("#showMessage").show();
            setTimeout(function () {
                //关闭警告框
                $("#showMessage").hide();
            }, 3000);
        }, "JSON")
    })

</script>

<%--初始化面板--%>
<div class="panel panel-info">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>
    <%--标签页--%>
    <div class="nav nav-tabs">
        <li class="active"><a>用户信息</a></li>
    </div>
    <%--按钮--%>
    <div class="panel panel-body">
        <a class="btn btn-success" id="easyPoiWrite">导出用户数据</a>
        <a class="btn btn-info" id="easyPoiRead">导入用户</a>
        <a class="btn btn-warning">测试按钮</a>
        <div class="input-group" style="width: 300px;height: auto;float: right">
            <input type="text" id="phoneCode" class="form-control" placeholder="请输入手机号" aria-describedby="basic-addon2">
            <span class="input-group-addon" id="sendMsg" onclick="sendCode()">发送短信验证码</span>
        </div>
    </div>
    <!--警告框-->
    <div id="showMessage" class="alert alert-danger" style="width: 300px;height: 50px;display: none">
        <span id="messages"></span>
    </div>
    <table id="userTable"></table>
    <div id="userPage"></div>
</div>