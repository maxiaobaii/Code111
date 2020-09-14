<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {
        pageInit();
    });

    function pageInit() {
        //初始化表单
        $("#feedTable").jqGrid({
            url: '${path}/Feed/show',
            editurl: "${path}/Feed/edit",
            datatype: "json",
            colNames: ['id', '标题', '内容', '用户id', '反馈时间'],
            rowNum: 3,
            rowList: [0, 2, 4, 16],
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            pager: '#feedPage',//工具栏
            viewrecords: true,//是否展示总条数
            colModel: [
                {name: 'id', width: 55},
                {name: 'title', editable: true, width: 60},
                {name: 'content', editable: true, width: 60},
                {name: 'userId', editable: true, width: 60, align: "center"},
                {
                    name: 'saveDate', editable: true, width: 60, align: "center"
                }
            ]
        });
        //表单的曾删改查操作
        $("#feedTable").jqGrid('navGrid', '#feedPage',
            {
                edit: true,
                add: true,
                del: true,
                search: false,
                addtext: "添加",
                edittext: "修改",
                deltext: "删除"
            },
            {
                closeAfterEdit: true,
            },
            {
                closeAfterAdd: true,
            },
            {
                closeAfterDel: true,
            }
        );
    }
</script>

<%--初始化面板 panel-info是颜色--%>
<div class="panel panel-primary">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>反馈信息</h2>
    </div>

    <%--创建选项卡 active是选中的意思--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="#">反馈管理</a></li>
    </div>

    <%--面板按钮--%>
    <div class="panel panel-body">
        <button class="btn btn-success">导出反馈信息</button>
    </div>

    <!--警告框-->
    <div id="showMessage" class="alert alert-danger" style="width: 300px;height: 50px;display: none">
        <span id="messages"></span>
    </div>

    <!--初始化表单-->
    <table id="feedTable"></table>

    <!--工具栏-->
    <div id="feedPage"/>
</div>