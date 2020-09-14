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
        valign: center;
    }
</style>

<script>
    $(function () {
        $("#video-show").jqGrid(
            {
                url: '${path}/video/queryPageVideo',   //发送两个参数  page 页数  rows 每页展示数量
                datatype: "json",                //接收  page页数  records总条数   rows展示的数据  total总页数
                editurl: '${path}/video/edit',
                rowNum: 5,
                rowList: [5, 10, 20, 30],
                pager: '#video-page',
                styleUI: "Bootstrap",
                height: "auto",
                autowidth: true,
                viewrecords: true, //是否显示总条数
                colNames: ['ID', '视频标题', '简介', '视频', '上传时间', '所属分类id'],
                colModel: [
                    {name: 'id', width: 60},
                    {name: 'title', editable: true, width: 90, valigin: "center",},
                    {name: 'brief', editable: true, width: 90},
                    {
                        name: 'path',
                        editable: true,
                        width: 180,
                        align: "center",
                        edittype: "file",
                        width: 100,
                        height: 50,
                        formatter: function (cellvalue, options, rowObject) {
                            //return "<img width='100px' height='70px' src='${path}/bootstrap/img/"+cellvalue+"'>";
                            return "<video width='200px' height='150px' src=\"" + cellvalue + "\" controls poster='"
                                + rowObject.cover + "'/>";
                        }
                    },
                    {name: 'publishDate', width: 80, align: "center"},
                    {name: 'categoryId', width: 80, align: "center"}
                ]
            });
        $("#video-show").jqGrid('navGrid', '#video-page',
            {edit: true, add: true, del: true},
            {},
            {
                //关闭添加框
                closeAfterAdd: true,
                //手动进行文件上传
                afterSubmit: function (response) {
                    var id = response.responseJSON.message;
                    //文件异步上传
                    $.ajaxFileUpload({
                        url: "${path}/video/uploadVideo",
                        type: "post",
                        dataType: "text",
                        fileElementId: "path",
                        data: {id: id},
                        success: function () {
                            //刷新页面
                            $("#video-show").trigger("reloadGrid");
                        }
                    })
                    return "123";
                }
            },
            {
                closeAfterDel: true,
                afterSubmit: function (response) {
                    $("#video-show").trigger("reloadGrid");
                    //将信息返回的警号框中
                    $("#showMsg1").html(response.responseJSON.message);
                    //展示出警告框
                    $("#deleteMsg1").show();
                    //自动关闭
                    setTimeout(function () {
                        //关闭警号框
                        $("#deleteMsg1").hide();
                    }, 3000);
                    return "123";
                }
            }
        );
    })
</script>

<%--初始化一个面板--%>
<div class="panel panel-success">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>
    <%--标签页--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">视频信息</a></li>
    </div>
    <div id="deleteMsg1" style="width: 300px;display: none" class="alert alert-warning alert-dismissible" role="alert">
        <span id="showMsg1"/>
    </div>
    <%--表格--%>
    <table id="video-show"></table>
    <%--工具栏--%>
    <div id="video-page"></div>
</div>