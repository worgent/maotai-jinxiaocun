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

    <title>搜狐--您的访问出错了，ErrorCode 403</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->


    <script type="text/javascript">
        //您可能没有权限用您提供的凭据查看此目录或网页。
        if (window != top) {
            window.top.location.href = "/errorpage/403.jsp";
        }

        	function out(obj) {
        		var i = obj;
        		if (i == 0)
        			document.location.href = "/index.action";

        		var message = document.getElementById("time");
        		message.innerHTML = i;
        		i--;
        		if (i >= 0) {
        			setTimeout("out(" + i + ")", 1000);
        		}
        	}
    </script>

</head>

<body onload="out(5)">
<br />
<br />
<table>
    <tr>
        <td width="20"></td>
        <td>
            <img src="<%=request.getContextPath()%>/errorpage/images/head.jpg" />
        </td>
    </tr>
</table>



<table style="margin-left: 30px;">
    <tr>
        <td>
            <h3>
                <font>您未被授权权访问该页。</font>
            </h3>
        </td>
    </tr>
    <tr>
        <td>
            <p>您当前的权限不允许访问，请<a href="<%=request.getContextPath()%>/logout">退出</a>当前用户，使用管理员账户登陆！</p>

        </td>
    </tr>
</table>
<div style="margin-left: 30px;">
    <hr width="600" style="margin-left: 0px;" align="left"/>
    请尝试以下操作：
    <br />
    <ul>
        <li>
            如果确信您有权限访问该目录或页面，但问题依然存在，请与网站管理员联系。
        </li>
    </ul>
    <br />
    HTTP 错误 403 - 禁止访问
    <br />
    <hr width="600" style="margin-left: 0px;" align="left" />
    <span id="time"> </span>秒后自动跳转，如不跳转，请点击链接返回:
    <a href="/index.action">登录页
    </a>

</div>

</body>
</html>
