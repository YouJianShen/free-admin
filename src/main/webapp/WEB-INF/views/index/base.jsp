<%@page import="com.suit.util.JsonUtil" %>
<%@page import="com.suit.core.util.SystemUtil" %>
<%@page import="com.suit.shiro.ShiroUser" %>
<%@page import="com.suit.util.JsonUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    String time = String.valueOf(new Date().getTime());
    String userInfo = JsonUtil.getJSONSerializer(new String[]{""},
            new String[]{"salt", "password", "", "", ""},
            "yyyy-MM-dd HH:mm:ss").serialize(
            request.getAttribute("user"));
%>
<!DOCTYPE html>
<html style="background:#F4F4F4;height: 100%; ">
<head>
    <link href="<%=basePath%>/static/lib/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath%>/static/styles/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>/static/styles/animate.css" rel="stylesheet">
    <link href="<%=basePath%>/static/styles/style.css?v=4.1.0" rel="stylesheet">
    <link href="<%=basePath%>/static/styles/login.css" rel="stylesheet">
    <script type="text/javascript"
            src="<%=basePath%>/static/lib/jquery/jquery.min.js?time=<%=time%>"></script>
    <script type="text/javascript"
            src="<%=basePath%>/static/scripts/common/doT.min.js?time=<%=time%>"></script>
    <script type="text/javascript"
            src="<%=basePath%>/static/scripts/common/utils.js?time=<%=time%>"></script>
    <script type="text/javascript">
        var _ctxPath = "<%=basePath%>";
        var userInfo = JSON.parse('<%=userInfo%>');

        $(document).ready(function () {
            $(document).bind("keypress", function (event) {
                if (event.keyCode == 8) {
                    if (event.target.tagName.toLowerCase() == "body") {
                        return false;
                    }
                }
            });
        });
    </script>
