<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/4
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" >

<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="css/style1.css">
</head>

<body>
<div class="login-form">
    <form action="index.jsp" method="post">
        <h1>注册成功！</h1>
        <div align="left" style="color: #6d6d6d">
        </div>
        <div class="form-group ">

            学生编号为：${requestScope.sid}
        </div>
        <div class="form-group log-status">
            ***请使用学生编号登录***
        </div>
        <div class="form-group log-status">
        </div>
        <%--<span class="alert">Invalid Credentials</span> --%>
        <input type="submit" class="log-btn" name="Login" value="确认">

    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<%--<script  src="js/index.js"></script>--%>
</body>

</html>

