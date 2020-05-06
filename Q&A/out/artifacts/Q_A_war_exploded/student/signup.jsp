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
    <script language="JavaScript">
        function ok() {
            var pwd=document.reg.pwd.value;
            var cpwd=document.reg.cpwd.value;
            var name=document.reg.sname.value;
            if(pwd!=cpwd){
                alert("两次输入密码不一致！");
                return false;
            }
            if(pwd==""||cpwd==""){
                alert("密码不可为空！");
            }
            if(name==""){
                alert("姓名不能为空！");
            }
            return true;
        }
    </script>
</head>

<body>
<div class="login-form">
    <form action="ms" method="post" name="reg" onsubmit="return ok()">
        <h1>Sign Up</h1>
        <div align="left" style="color: #6d6d6d">
        </div>
        <div class="form-group ">

            <input type="text" class="form-control" placeholder="学生姓名" id="UserName" name="sname">
            <input type="hidden" name="oper" value="signup">
            <i class="fa fa-user"></i>
        </div>
        <div class="form-group log-status">
            <input type="password" class="form-control" placeholder="密码(设置6-16位)" id="Password" name="pwd">
            <i class="fa fa-lock"></i>
        </div>
        <div class="form-group log-status">
            <input type="password" class="form-control" placeholder="确认密码" id="cPassword" name="cpwd">
            <i class="fa fa-lock"></i>
        </div>
        <%--<span class="alert">Invalid Credentials</span> --%>
        <input type="submit" class="log-btn" name="Login" value="注册" >

    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<%--<script  src="js/index.js"></script>--%>
</body>

</html>

