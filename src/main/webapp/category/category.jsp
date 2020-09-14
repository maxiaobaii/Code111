<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    th {
        text-align: center;
    }

    td {
        text-align: center;
    }
</style>
<script>
    $(function () {
        //父表格
        $("#categoryTable").jqGrid(
            {
                url: "${path}/category/one",//父表格提供数据
                editurl: "${path}/category/edit",
                datatype: "json",
                height: "auto",
                styleUI: "Bootstrap",
                autowidth: true,
                rowNum: 5,
                rowList: [5, 10, 20, 30],
                pager: '#categoryPage',
                viewrecords: true,
                subGrid: true,//添加subgird支持
                caption: "展示类别信息",
                colNames: ['id', '类别名', '级别'],
                colModel: [
                    {
                        name: 'id', align: "center", editable: true, width: 55
                    },
                    {
                        name: 'cateName', align: "center", editable: true, width: 90
                    },
                    {
                        name: 'levels', align: "center", width: 100
                    },
                ],
                //子表格
                subGridRowExpanded: function (subgrid_id, row_id) {
                    //第一个参数 子容器的id  第二个参数  当前行id
                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;
                    //在子容器中，创建了一个子表格和子表格的分页工具
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    //对子表格进行初始化
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url: "${path}/category/second?oneId=" + row_id,//子表格提供数据
                            editurl: "${path}/category/edit?parentId=" + row_id,
                            datatype: "json",
                            rowNum: 5,
                            rowList: [5, 10, 20, 30],
                            pager: pager_id,
                            styleUI: "Bootstrap",
                            viewrecords: true,
                            autowidth: true,
                            height: '100%',
                            colNames: ['id', '类别名', '级别', '父类id'],
                            colModel: [
                                {
                                    name: 'id', align: "center", width: 55, editable: true
                                },
                                {
                                    name: 'cateName', align: "center", editable: true, width: 90
                                },
                                {
                                    name: 'levels', align: "center", width: 100
                                },
                                {
                                    name: 'parentId', align: "center", width: 80
                                },
                            ]
                        });
                    $("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            add: true,
                            edit: true,
                            del: true,
                            search: false,
                            addtext: "添加",
                            edittext: "修改",
                            deltext: "删除"
                        }, {
                            closeAfterEdit: true,
                        },
                        {
                            closeAfterAdd: true,
                        },
                        {
                            closeAfterDel: true,
                        });
                },
            });
        $("#categoryTable").jqGrid('navGrid', '#categoryPage', {
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
            }, {
                closeAfterAdd: true,
            },
            {
                closeAfterDel: true,
                afterSubmit: function (reponse) {
                    /* alert(reponse.responseJSON.message);*/
                    $("#messages").html(reponse.responseJSON.message)
                    $("#showMessage").show();
                    setTimeout(function () {
                        //关闭警告框
                        $("#showMessage").hide();
                    }, 3000);
                    return "hello";
                }
            },
        );
    });
</script>
<%--初始化面板--%>
<div class="panel panel-warning">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>
    <%--标签页--%>
    <div class="nav nav-tabs">
        <li class="active"><a>类别信息</a></li>
    </div>
    <!--警告框-->
    <div id="showMessage" class="alert alert-danger" style="width: 300px;height: 50px;display: none">
        <span id="messages"></span>
    </div>
    <table id="categoryTable"></table>
    <div id="categoryPage"></div>
</div>