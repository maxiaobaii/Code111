<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<style>
    th, td {
        text-align: center;
    }

</style>

<script>
    $(function () {
        pageInit();
    });

    function pageInit() {
        //初始化表单
        $("#logTable").jqGrid({
            url: '${path}/Log/show',
            editurl: "${path}/Log/edit",
            datatype: "json",
            colNames: ['id', '管理员名', '时间', '操作的方法', '状态'],
            rowNum: 5,
            rowList: [5, 10, 20, 30],
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            pager: '#logPage',//工具栏
            viewrecords: true,//是否展示总条数
            colModel: [
                {name: 'id', width: 55},
                {name: 'name', editable: true, width: 90},
                {name: 'date', editable: true, width: 100},
                {name: 'manipulate', editable: true, width: 80, align: "center"},
                {
                    name: 'status', editable: true, width: 80, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        //三个参数  列的值 ，操作 ，行对象
                        if (cellvalue == "success") {
                            return "<strong><span style='color: green' >" + cellvalue + "</span></strong>";
                        } else {
                            return "<strong><span style='color: red' >" + cellvalue + "</span></strong>";
                        }
                    }
                }
            ]
        });
        //表单的曾删改查操作
        $("#logTable").jqGrid('navGrid', '#logPage',
            {edit: false, add: false, del: true, deltext: "删除"},
            {},
            {},
            {closeAfterDel: true,}
        );
    }
</script>

<%--初始化面板 panel-info是颜色--%>
<div class="panel panel-danger">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>日志信息</h2>
    </div>

    <%--创建选项卡 active是选中的意思--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="#">日志管理</a></li>
    </div>

    <%--面板按钮--%>
    <div class="panel panel-body">
        <button class="btn btn-info">导出日志信息</button>
    </div>

    <!--警告框-->
    <div id="showMessage" class="alert alert-danger" style="width: 300px;height: 50px;display: none">
        <span id="messages"></span>
    </div>

    <!--初始化表单-->
    <table id="logTable"/>

    <!--工具栏-->
    <div id="logPage"/>
</div>