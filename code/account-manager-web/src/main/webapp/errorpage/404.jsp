<%@ page import="java.util.Random" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html lang="zh-cn">
	<head>
		<base href="<%=basePath%>">

		<title>搜狐--您的访问出错了</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 
<script type="text/javascript">
    if (window != top){ 
			window.top.location.href ="/index.action"; }
	function out(obj) {
		var i = obj;
		if (i == 0)
			document.location.href = "/index.action";

		var message = document.getElementById("time");
		message.innerHTML = i;
		i--;
		if (i >= 0) {
			setTimeout("out("+i+")",1000);
}
}
</script>
	</head>

	<body onload="out(5)">
	<br/><br/>
		<table>
			<tr>
				<td width="300"></td>
				<td>
					<img 
					src="<%=request.getContextPath()%>/errorpage/images/head.jpg" />
				</td>
			</tr>
		</table>
		<center>
		
			<H2>
				对不起!您所访问的页面不存在，或已删除。
			</H2>
			<h2>
				<span id="time"> </span>秒后自动跳转，如不跳转，请点击下列连接
			</h2>
		</center>
		<br>
		<center>
			<a href="/index.action"><h2>
					登录页
				</h2>
			</a>
		</center>
		<table>
			<tr>
				<td width="300"></td>
				<td>
                    <%! Random random = new Random();   %>
					<img 
					src="<%=request.getContextPath()%>/errorpage/images/<%=random.nextInt(10)%>.jpg" />
				</td>
			</tr>
		</table>
	</body>
</html>
