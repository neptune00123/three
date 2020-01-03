<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
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
                xAxis: {
                    type: 'category',
                    name:"month",
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                },
                yAxis: {

                    type: 'value'
                },
                series: [{
                    type: 'line'
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            $.ajax({
                url:"${pageContext.request.contextPath}/echarts/month",
                datatype:"json",
                success:function (data) {
                    console.log(data);
                    console.log(data);

                        myChart.setOption({
                            series:[{
                                name:"month",
                                data:data
                            }]

                    })


                }
            })
        })

    </script>
</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
