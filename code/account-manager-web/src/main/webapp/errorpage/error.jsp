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

    <title>搜狐--您的访问的页面暂时无法访问，请稍后再试</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
     <link rel="stylesheet" type="text/css" href="styles.css">
     -->


    <script type="text/javascript">
        if (window != top) {
            window.top.location.href = "/index.action";
        }

        function out(obj) {
            var i = obj;
            if (i == 0)
                window.location.href = "/index.action";
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
<br/><br/>
<table>
    <tr>
        <td width="350"></td>
        <td>
            <img
                    src="<%=request.getContextPath()%>/errorpage/images/head.jpg"/>
        </td>
    </tr>
</table>
<center>


    <table align="center">
        <tr>
            <td><h3>您访问的页面，现在无法访问，请稍后再试 。</h3></td>
        </tr>
        <tr>
            <td>
                <h3>
                    Web 服务器不能执行此请求。请稍后重试此请求。</h3></td>
        </tr>
        <tr>
            <td>
                <h3> 如果问题依然存在，请与 Web服务器的管理员联系。</h3>
            </td>
        </tr>
    </table>

    <h3>
        <span id="time"> </span>秒后自动跳转，如不跳转，请点击下列连接
    </h3>
</center>
<br>
<table>
    <tr>
        <td width="500"></td>
        <td><h3><a href="/index.action">
            登录页面
        </a></h3></td>
        <td>
            <img
                    src="<%=request.getContextPath()%>/errorpage/images/500.jpg"/>
        </td>
    </tr>
</table>
</body>
</html>
