<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="error_page">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>您的访问的页面出错，请稍后再试</title>
		<!-- Bootstrap framework -->
            <link rel="stylesheet" href="<%=basePath %>main/css/bootstrap.min.css" />
		<!-- main styles -->
            <link rel="stylesheet" href="<%=basePath %>main/css/style.css" />
			
            <!-- <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Jockey+One" /> -->
            
	</head>
	<body>

		<div class="error_box">
			<h1>页面发生错误</h1>
			<p>
				<% 
					exception.printStackTrace(response.getWriter()); 
				%>
			</p>
			<a href="<%=basePath %>" class="back_link btn btn-default btn-sm">返回首页</a>
		</div>
	</body>
</html>