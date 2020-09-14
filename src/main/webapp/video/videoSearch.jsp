<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {
        $("#searchB").click(function () {
            //清空表格内容
            $("#tableEs").empty();
            //文本框内容
            var content = $("#searchA").val();
            // alert(content);
            //向后台传递数据
            $.ajax({
                url: "${path}/video/queryEs",
                type: "post",
                dataType: "JSON",
                data: {"content": content},
                success: function (data) {
                    $("#tableEs").append("<tr>\n" +
                        "                <td>视频ID</td>\n" +
                        "                <td>视频标题</td>\n" +
                        "                <td>简介</td>\n" +
                        "                <td>视频封面</td>\n" +
                        "                <td>上传时间</td>\n" +
                        "            </tr>");
                    //向表格中填充数据
                    $.each(data, function (index, video) {
                        $("#tableEs").append("<tr>" +
                            "                <td>" + video.id + "</td>\n" +
                            "                <td>" + video.title + "</td>\n" +
                            "                <td>" + video.brief + "</td>\n" +
                            "                <td><img style='width: 200px;height: 100px' src='" + video.cover +
                            "'></img></td>\n" +
                            "                <td>" + video.publishDate + "</td>" + "</tr>");
                    })
                }
            })
        })
    })
</script>

<div align="center">
    <div class="input-group" style="width:450px;height:auto">
        <input id="searchA" type="text" class="form-control" placeholder="视频标题 描述" aria-describedby="basic-addon2">
        <span class="input-group-btn" id="searchB">
            <button class="btn btn-success">点击搜索</button>
        </span>
    </div>
    <hr>
    <br>
    <div class="panel panel-default">
        <div class="panel-heading">搜索结果</div>
        <!-- Table -->
        <table class="table" id="tableEs">
            <tr id="contents"></tr>
        </table>
    </div>

</div>