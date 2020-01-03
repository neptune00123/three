<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <%--jquery依赖--%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <%--echarts依赖--%>
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <%--中国地图依赖--%>
    <script src="${pageContext.request.contextPath}/echarts/china.js"></script>

    <%--内容--%>
    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            var option = {
                title : {
                    text: '用户地理分布图',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:["用户"]
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '用户',
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

                    },

                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            $.ajax({
                url:"${pageContext.request.contextPath}/echarts/getdata",
                datatype:"json",
                success:function (data) {
                    myChart.setOption({
                        series:[{
                            data:data
                        }]
                    })
                }
            })
        })
        
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>