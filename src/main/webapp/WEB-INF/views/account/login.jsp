<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page
        import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException" %>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    String username = (String) request.getAttribute("userName");
    if (username == null)
        username = "";
    String error = (String) request
            .getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
    String msg = "";
    if (error != null) {
        if ("org.apache.shiro.authc.IncorrectCredentialsException"
                .equals(error)) {
            msg = "用户名或密码输入有误";
        } else if ("com.suit.shiro.CaptchaException".equals(error)) {
            msg = "验证码输入有误";
        } else if ("org.apache.shiro.authc.UnknownAccountException"
                .equals(error)) {
            msg = "用户名或者密码输入有误";
        }
    }
    String time = String.valueOf(new Date().getTime());
%>
<!DOCTYPE html>
<html>
<head>
    <title>登录界面</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/suit/css/base.css?time=<%=time%>>"
          rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="http://localhost:8082/assets/admin/default/favicon.ico">
    <link href="${ctx}/static/lib/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/static/styles/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/styles/animate.css" rel="stylesheet">
    <link href="${ctx}/static/styles/style.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx}/static/styles/Login.css" rel="stylesheet">
    <style>
        .vertical-center > * {
            display: inline-block;
            vertical-align: middle;
            white-space: nowrap;
        }
    </style>
</head>
<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-12">
            <form aaction='${ctx}/login' method="post" accept-charset="utf-8">
                <input type="hidden" name="csrf_token_name" value="6b4f3cdf4b0ee972af3ee992611713dd"/>
                <h4 class="no-margins">登陆系统 : </h4>
                <p class="m-t-md">欢迎进入DinoEdu</p>
                <input name='username' type='text' value="<%=username%>" type="text" class="form-control uname" placeholder="请输入登陆账号"/>
                <input name="password" type="password" class="form-control pword m-b" placeholder="请输入登陆密码"/>
                <div class="vertical-center">
                    <input class='neet_submit' name='rememberMe' style="display: none" value=true/>
                    <input name='captcha' type='text' type="text" class="form-control valid-code" placeholder="请输入验证码"
                           data-fv-field="validCode" style="margin: 0;">
                    <a class="verifyBox" href="javascript:;">
                        <img class='login_mainbody_form_captchaImg' src="${ctx}/captcha/code" title="点击更换验证码">
                    </a>
                </div>
                <div class="clearBox"></div>
                <div class="rememberBox">
                    <%--<div class="floatLeft">--%>
                        <%--<input name="remember" type="checkbox" placeholder=""/><span--%>
                            <%--class="stay_logged_in">记住密码</span>--%>
                    <%--</div>--%>
                    <%--<div class="floatRight">--%>
                        <%--<a href="">忘记密码</a>--%>
                    <%--</div>--%>
                </div>
                <button class="btn btn-success btn-block">登陆</button>
                <p class="red-msg"><%=msg%></p>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; XXX
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${ctx}/static/suit/js/jquery.min.js?time=<%=time%>"></script>
<script type="text/javascript"
        src="${ctx}/static/suit/js/base.unmin.js?time=<%=time%>"></script>
<script type="text/javascript">
    $("input[name=accountName]").val();
    suit.matchWindow($(".match_window"));

    suit.apply(suit.bindEvent, {
        login: function () {
            var data = {};
            var check = suit.getSubmitValue(data, "neet_submit");
            if (check)
                $("#form").submit();
        },
        regist: function () {
            var data = {};
            if (suit.getSubmitValue(data, "neet_submit2")) {
                if (data.password !== data.password_2) {
                    suit.alertMsg({
                        msg: "两次输入的密码不一致"
                    });
                    return;
                }
                loadMask.show();
                suit.http.ajax({
                    url: "${ctx}/system/user/regist",
                    type: "post",
                    data: data,
                    success: function (status) {
                        var res = JSON.parse(status);
                        suit.alertMsg(res);
                        if (res.success) {
                            $("input[name=username]").val(data.account);
                            $("input[name=password]").val(data.password);
                            $("#form").submit();
                        }
                        loadMask.hide();
                    },
                    failure: function (status) {
                        var res = JSON.parse(status);
                        suit.alertMsg(res);
                        loadMask.hide();
                    }
                });
            }
        }
    });
    suit.attachEvent();
    var ready = true;
    addEvent();
    function addEvent() {
        $("input.neet_submit[name=password]").on("keydown", function (e) {
            if (e.keyCode === 13) {
                suit.bindEvent.login();
            }
        });
        $("input.neet_submit2[name=password_2]").on("keydown", function (e) {
            if (e.keyCode === 13) {
                suit.bindEvent.regist();
            }
        });
        $(".login_mainbody_form_captchaImg").bind("click", function () {
            $("input[name='captcha']").val("");
            $(this).attr("src", "${ctx}/captcha/code?" + Math.random());
        });
        $(".other_page").on("click", function () {
            clear();
            $("#reg_form").removeClass("hide");
        });
        $(".return_back").on("click", function () {
            clear();
            $("#form").removeClass("hide");
        });
    }
    console.log("<%=error%>");
    function clear() {
        ready = false;
        var dom = $(".panel_item").not(".hide");
        dom.addClass("hide");
    }
</script>
</body>
</html>