<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ include file="../../index/base.jsp" %>

    <title>主页</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <style>
        table th{
            text-align: center;
        }
    </style>

    <script type="text/x-dot-template" id="order_list_view">
        <div class="ibox-content" style="height: 100%;">

            <h2>订单管理</h2>

            <div class="vertical-center">
                <div class="input-group"
                     style="margin-bottom:6px;display: inline-block;width: 50%;min-width:400px;padding-top:12px;">
                    <%--<div class="btn-group pull-left" style="margin-right: 10px;">--%>
                    <%--<select class="btn btn-white btn-sm" id="member_level">--%>
                    <%--<option value="">全部会员</option>--%>
                    <%--<option value="3">钻石会员</option>--%>
                    <%--<option value="2">金牌会员</option>--%>
                    <%--<option value="1">银牌会员</option>--%>
                    <%--<option value="0">--%>
                    <%--注册会员--%>
                    <%--</option>--%>
                    <%--</select>--%>
                    <%--</div>--%>

                    <div class="pull-left" style="margin-right: 10px;">
                        <input style="height: 30px;width: 180px;" id="start_date"
                               class="laydate-icon form-control layer-date"
                               value="" placeholder="开始时间">
                    </div>
                    <div class="pull-left" style="margin-right: 10px;">
                        <input style="height: 30px;width: 180px;" id="end_date"
                               class="laydate-icon form-control layer-date"
                               value="" placeholder="结束时间">
                    </div>
                </div>
                <div class="input-group vertical-center" style="display: inline-block;width: 44%;text-align: right">

                    <input id="keywords" type="text" placeholder="" value="" class="input form-control"
                           style="height:30px;position: relative">
                    <span class="input-group-btn" style="position: absolute;top:0;right:0">
                            <button type="button" class="btn btn btn-primary" onclick="search('keywords')"><i
                                    class="fa fa-search"></i> 搜索</button>
                        </span>
                </div>
            </div>

            <br>
            <div class="pager">
                <div class="btn-group pull-right">
                    <!-- 上一页 -->
                    <span class="btn btn-white btn-sm" onclick="search('prev')" title="上一页"><i
                            class="fa fa-arrow-left"></i></span>
                    <input id="page" class="btn btn-sm btn-white" onchange="search(this.value)" type="text" value="1"
                           placeholder=""
                           style="width: 46px;">
                    <!-- 下一页 -->
                    <span class="btn btn-white btn-sm" onclick="search('last')" title="下一页"><i
                            class="fa fa-arrow-right"></i></span>
                    <input id="last" type="hidden" value="1">
                    <input id="prev" type="hidden" value="2">
                </div>
                <div class="btn-group pull-right" style="margin-right: 10px;">
                    <span class="btn btn-white btn-sm">共 0 页</span>
                    <span class="btn btn-white btn-sm">每页显示</span>
                    <input id="limit" class="btn btn-sm btn-white" onchange="search('row',this.value)" type="text"
                           value="10"
                           placeholder="" style="width: 46px;">
                    <span class="btn btn-white btn-sm">条</span>
                </div>
            </div>

            <div class="clients-list" style="position: absolute;top:200px;left:20px;right: 20px;bottom: 20px;">
                <%--<ul class="nav nav-tabs" style="visibility: hidden">--%>
                <%--<!--<span class="pull-right small text-muted">10 个合伙人</span>-->--%>

                <%--<li class="active"><a href="http://localhost:8082/admin/partner"><i class="fa fa-user"></i> 合伙人</a>--%>
                <%--</li>--%>
                <%--<li class=""><a href="http://localhost:8082/admin/card"><i class="fa fa-credit-card-alt"></i>--%>
                <%--信用卡</a>--%>
                <%--</li>--%>
                <%--<li class=""><a href="http://localhost:8082/admin/loan"><i class="fa fa-money"></i> 小额贷</a></li>--%>

                <%--</ul>--%>
                <div class="tab-content" style="height: 100%">
                    <div id="tab-1" class="tab-pane active" style="height: 100%">
                        <div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
                            <div class="full-height-scroll"
                                 style="border:1px solid #cfdadd;width: auto; height: 100%;overflow-y: auto">
                                <div class="table-responsive">
                                    <table class="table table-striped table-hover text-center">
                                        <thead>
                                        <tr>
                                            <th>num</th>
                                            <th>email</th>
                                            <th>Type of paper</th>
                                            <th>Topic</th>
                                            <th>subject</th>
                                            <th>Number of pages</th>
                                            <th>Deadline</th>
                                            <th>Type of service</th>
                                            <th>Writer quality</th>
                                            <th>language level</th>
                                            <th>cited resources</th>
                                            <th>Format of citation</th>
                                            <th>wechat</th>
                                            <th>Paper instructions</th>
                                            <th>Paper instructions 2</th>
                                            <%--<th>Writers</th>--%>
                                            <th>Create Time</th>
                                            <th>country</th>
                                        </tr>
                                        </thead>
                                        <tbody class="list_content">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="slimScrollBar"
                                 style="background: rgb(0, 0, 0); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 600px;"></div>
                            <div class="slimScrollRail"
                                 style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </script>

    <%--list item--%>
    <script type="text/x-dot-template" id="order_list_item">
        <tr class="member_info_2516 check-tr">
            <td>
                {{=it.num}}
            </td>
            <td><i class="fa fa-email"> </i> {{=it.email}}</td>
            <td>
                {{=paperTypes[it.type - 1]||("Other("+it.type+")")||""}}
            </td>
            <td>
                {{=it.topic}}
            </td>
            <td>
                {{=subjectData[it.subject - 1]||("Other("+it.subject+")")||""}}
            </td>
            <td>
                {{=it.pageNum}}
            </td>
            <td>
                {{=it.deadLine}}
            </td>
            <td>{{=it.serviceType}}</td>
            <td>
                {{=it.quality}}
            </td>
            <td>
                {{=it.languageLevel}}
            </td>
            <td>
                {{=it.citeNum}}
            </td>
            <td>
                {{=citationData[it.citation - 1]||("Other("+it.citation+")")||""}}
            </td>
            <td>
                {{=it.contact||""}}
            </td>
            <td>
                {{=it.instructions}}
            </td>
            <td>
                {{=it.instructions_2}}
            </td>
            <%--<td>--%>
                <%--{{=it.writers}}--%>
            <%--</td>--%>
            <td>
                {{=utils.formatTimestamp(it.createTime.time)}}
            </td>
            <td>
                {{=it.region}}
            </td>
        </tr>
    </script>

    <%--pager--%>
    <script type="text/x-dot-template" id="order_list_pager">
        <div class="btn-group pull-right">
            <!-- 上一页 -->
            <span class="btn btn-white btn-sm" onclick="search('prev')" title="上一页"><i
                    class="fa fa-arrow-left"></i></span>
            <input id="page" class="btn btn-sm btn-white" onchange="search(this.value)" type="text" value="{{=it.page}}"
                   placeholder=""
                   style="width: 46px;">
            <!-- 下一页 -->
            <span class="btn btn-white btn-sm" onclick="search('last')" title="下一页"><i
                    class="fa fa-arrow-right"></i></span>
            <input id="last" type="hidden" value="1">
            <input id="prev" type="hidden" value="2">
        </div>
        <div class="btn-group pull-right" style="margin-right: 10px;">
            <span class="btn btn-white btn-sm">共 {{=it.total}} 页</span>
            <span class="btn btn-white btn-sm">每页显示</span>
            <input id="limit" class="btn btn-sm btn-white" onchange="search('row',this.value)" type="text"
                   value="{{=it.rows}}"
                   placeholder="" style="width: 46px;">
            <span class="btn btn-white btn-sm">条</span>
        </div>
    </script>

</head>
<body style="overflow:hidden">

<div class="ibox">

</div>


<%@ include file="../../index/foot.jsp" %>

<!-- 自定义js -->
<script type="text/javascript" src="<%=basePath%>/static/lib/layer/laydate/laydate.js?time=<%=time%>"></script>
<script type="text/javascript"
        src="<%=basePath%>/static/scripts/common/hAdmin.js?time=<%=time%>"></script>

<!-- 第三方插件 -->
<script src="<%=basePath%>/static/lib/pace/pace.min.js"></script>

<script src="<%=basePath%>/static/scripts/view/OrderList.js?time=<%=time%>"></script>

</body>
</html>