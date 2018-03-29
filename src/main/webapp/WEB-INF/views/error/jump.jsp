<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
	<title>错误</title>
</head>
<script type='text/javascript'>
	if(parent.homeView){
		parent.location.href = parent.baseurl + "/logout";
	}
</script>
<body>
	<h2>登录账号有误，跳转中</h2>
	<p><a href="<c:url value="/"/>">返回首页</a></p>
</body>
</html>