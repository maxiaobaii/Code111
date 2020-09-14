<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/9/3
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="${path}/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${path}/goeasy/goeasy-1.0.17.js"></script>
<script src="${path}/echarts/echarts.js"></script>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        $.get("${path}/user/queryUserNumByMonth", function (data) {
            console.log(data);
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户各月份注册分析图',
                    link: "${path}/main/main.jsp",
                    subtext: "纯属虚构"
                },
                tooltip: {},//鼠标提示
                legend: {
                    data: ['男生', '女生']//选项卡
                },
                xAxis: {
                    data: data.month
                },
                yAxis: {},//自适应
                series: [{
                    name: '男生',//跟选项卡是一一对应的
                    type: 'bar',
                    data: data.boys
                }, {
                    name: '女生',//跟选项卡是一一对应的
                    type: 'bar',
                    data: data.girls
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }, "json")
    })
</script>
<div align="center">
    <div id="main" style="width: 800px;height:550px;"></div>
</div>