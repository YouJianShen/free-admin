<%@page import="com.suit.util.JsonUtil" %>
<%@page import="com.suit.core.util.SystemUtil" %>
<%@page import="com.suit.shiro.ShiroUser" %>
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
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>free-admin</title>
    <link href=/free-admin/static/css/app.197dd023a60122498661bf9b0d83b65f.css rel=stylesheet />
</head>
<body>
<div id='app'>
</div>
<script type=text/javascript src=/free-admin/static/js/manifest.cb06b82ee31f3ae656e9.js></script>
<script type=text/javascript src=/free-admin/static/js/vendor.1c1d7f6e03fd77ad54b0.js></script>
<script type=text/javascript src=/free-admin/static/js/app.bccd1b59b4cfeb621b0d.js></script>
</body>
</html>