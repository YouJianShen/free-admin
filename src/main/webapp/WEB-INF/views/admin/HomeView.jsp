<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ include file="../index/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper" class="">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
            <div class="sidebar-collapse" style="width: auto; height: 100%;">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear">
                            <span class="block m-t-xs" style="font-size:18px;">
                                <!--<i class="fa fa-area-chart"></i>-->
                                <strong class="font-bold">DinoEdu</strong>
                            </span>
                        </span>
                            </a>
                        </div>
                        <div class="logo-element">DinoEdu</div>
                    </li>

                    <!--     左侧菜单开始       -->
                    <li class="line dk"></li>

                    <li>
                        <a class="J_menuItem" href="javascript:void(0)">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>

                    <li class="line dk"></li>

                    <li>
                        <a class="J_menuItem" href="javascript:void(0)" data-href="<%=basePath%>/admin/order">
                            <i class="fa fa-money"></i>
                            <span class="nav-label">订单查询</span>
                        </a>
                    </li>

                    <li class="line dk"></li>

                    <li>
                        <a class="J_menuItem" href="javascript:void(0)">
                            <i class="fa fa-th-large"></i>
                            <span class="nav-label">名片管理</span>
                        </a>
                    </li>

                    <li class="line dk"></li>
                    <%--<li>--%>
                        <%--<a href="#">--%>
                            <%--<i class="fa fa-th-large"></i>--%>
                            <%--<span class="nav-label">管理</span>--%>
                            <%--<span class="fa arrow"></span>--%>
                        <%--</a>--%>
                        <%--<ul class="nav nav-second-level collapse">--%>
                            <%--<li>--%>
                                <%--<a class="J_menuItem" href="http://localhost:8082/admin/partner">合伙人</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a class="J_menuItem" href="http://localhost:8082/admin/card">信用卡</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a class="J_menuItem" href="http://localhost:8082/admin/loan">小额贷</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a class="J_menuItem" href="http://localhost:8082/admin/pos">POS机</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>

                    <%--<li class="line dk"></li>--%>


                    <%--<li>--%>
                        <%--<a class="J_menuItem" href="http://localhost:8082/admin/cash">--%>
                            <%--<i class="fa fa-money"></i>--%>
                            <%--<span class="nav-label">提现</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>

                    <%--<li class="line dk"></li>--%>


                    <%--<li>--%>
                        <%--<a class="J_menuItem" href="http://localhost:8082/admin/card_bank">--%>
                            <%--<i class="fa fa-credit-card-alt"></i>--%>
                            <%--<span class="nav-label">信用卡</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>

                    <%--<li class="line dk"></li>--%>


                    <%--<li>--%>
                        <%--<a class="J_menuItem" href="http://localhost:8082/admin/news">--%>
                            <%--<i class="fa fa-newspaper-o"></i>--%>
                            <%--<span class="nav-label">资讯</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>

                    <%--<li class="line dk"></li>--%>


                    <%--<li>--%>
                        <%--<a class="J_menuItem" href="http://localhost:8082/admin/excel">--%>
                            <%--<i class="fa "></i>--%>
                            <%--<span class="nav-label">办卡记录</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>

                    <%--<li class="line dk"></li>--%>

                    <!--     左侧菜单结束       -->

                </ul>
            </div>
            <div class="slimScrollBar"
                 style="background: rgb(0, 0, 0); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 973px;"></div>
            <div class="slimScrollRail"
                 style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.9; z-index: 90; right: 1px;"></div>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1" style="overflow: hidden">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <!--<form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>-->
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-user-circle"></i> <!--<span class="label label-primary"></span>-->
                        </a>
                        <ul class="dropdown-menu" style="width: 180px;">
                            <li>
                                <a href="javascript:;">
                                    <div class="link-block">
                                        账号：<?=$this->_manager->username?>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="javascript:;">
                                    <div class="link-block">
                                        角色：<?=$this->_manager->nickname?>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="J_menuItem" href="<?=url('login/resetting_password','site')?>">
                                    <div class="link-block">
                                        <i class="fa fa-link"></i>：
                                        修改密码
                                        <i class="fa fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="<?=url('login/logout','site')?>">
                                    <div class="link-block">
                                        <i class="fa fa-sign-out"></i>：退出
                                    </div>
                                </a>
                            </li>

                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <div class="wrapper wrapper-content fadeInRight" style="height: 100%">
                <iframe id="J_iframe" width="100%" height="100%" src="<%=basePath%>/admin/order" frameborder="0"
                        data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<%@ include file="../index/foot.jsp" %>
<!-- 自定义js -->
<script type="text/javascript"
        src="<%=basePath%>/static/scripts/common/hAdmin.js?time=<%=time%>"></script>

<!-- 第三方插件 -->
<script src="<%=basePath%>/static/lib/pace/pace.min.js"></script>

</body>
</html>