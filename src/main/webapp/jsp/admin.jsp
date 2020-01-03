<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>

    </title>
    <%-- bootstrap的核心css --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/css/bootstrap.css">
    <%-- jqGrid --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

    <%-- jquery.js --%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <%-- bootstrap.js --%>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <%-- 国际化 --%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%-- jqGrid核心js --%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <%--富文本编辑器--%>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="" class="navbar-brand">
                持法明州后台管理系统
            </a>
            <button class="navbar-toggle collapsed" data-toggle="collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="">欢迎：<strong>${sessionScope.user.username}</strong></a></li>
                <li><a href="">
                    退出登录
                    <span class="glyphicon glyphicon-log-in"></span>
                </a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <%--导航菜单--%>
        <div class="col-sm-2">
            <div>
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                             aria-labelledby="headingOne">
                            <div class="panel-body">
                                <a href="javascript:void(0)" onclick="$('#myContent').load('second.jsp')"
                                   class="list-group-item list-group-item-danger">用户查看</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    上师管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingTwo">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    文章管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingThree">
                            <div class="panel-body">
                                <a class="btn btn-default btn-lg" href="javascript:$('#body').load('article.jsp')">文章详情</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    专辑管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingFour">
                            <div class="panel-body">
                                <a class="btn btn-default btn-lg" href="javascript:$('#body').load('album.jsp')">专辑详情</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    轮播图管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingFive">
                            <div class="panel-body">
                                <a class="btn btn-default btn-lg" href="javascript:$('#body').load('banner.jsp')">轮播图查看</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-10" id="body">

            <%--巨幕--%>
            <div class="jumbotron" style="height:180px">
                <div class="text-left">
                    <h2 style="color: #8c8c8c">欢迎来到持名法州后台管理系统</h2>
                </div>
            </div>
            <%--轮播图--%>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height: 550px">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="../img1/shouye.jpg" width="1500px" style="height: 550px" alt="...">
                        <%--   <div class="carousel-caption">
                               ...
                           </div>--%>
                    </div>
                    <div class="item">
                        <img src="../img1/A2.jpg" width="1500px" style="height: 550px" alt="...">
                        <%-- <div class="carousel-caption">
                             ...
                         </div>--%>
                    </div>
                    <div class="item">
                        <img src="../img1/shouye.jpg" width="1500px" style="height: 550px" alt="...">
                        <%-- <div class="carousel-caption">
                             ...
                         </div>--%>
                    </div>
                    <%-- ...--%>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default">
    <div class="container">
        <div align="center" style="margin-top: 15px">
            <p>@百知教育baizhi@zparkhr.com.cn</p>
        </div>
    </div>
</nav>
</body>
</html>