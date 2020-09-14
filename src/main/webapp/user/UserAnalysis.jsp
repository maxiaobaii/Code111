<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/9/3
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="en">
<script src="${path}/bootstrap/js/jquery.min.js"></script>
<!-- 引入 ECharts 文件 -->
<script src="${path}/echarts/echarts.js"></script>
<script src="${path}/echarts/china.js"></script>
<script src="${path}/goeasy/goeasy-1.0.17.js"></script>
<%--<script src="${path}/bootstrap/js/jquery.min.js"></script>--%>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.get("${path}/user/EchartsCity", function (datas) {
            var serices = [];
            for (var i = 0; i < datas.length; i++) {
                var data = datas[i];
                serices.push(
                    {
                        name: data.sex,
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.citys
                    }
                );
            }
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '省份用户注册量',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['男生', '女生']
                },
                visualMap: {
                    min: 0,
                    max: 50,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: true},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: serices
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }, "JSON");
    });
</script>
<div align="center">
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
</div>