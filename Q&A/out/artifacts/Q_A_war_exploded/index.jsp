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
    <script language="javascript">
        function ok() {
            var pwd=document.up.pwd.value;
            var name=document.up.name.value;
            if(pwd==""||name=="") {
                alert("编号或密码为空！");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
<div class="login-form">
    <form action="ms" method="post" name="up" onsubmit="return ok()">
        <h1>Welcome</h1>
        <c:if test="${'false'.equals(requestScope.lf)}">
            <script language="JavaScript">
                alert("编号或密码错误！");
            </script>
        </c:if>
        <div align="left" style="color: #6d6d6d">
            &emsp;登录身份&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            <select name="status" style="color: #6d6d6d">
                <option value="admin" selected>管理员</option>
                <option value="teacher">教师</option>
                <option value="student">学生</option>
            </select>
        </div>
        <div class="form-group ">

            <input type="text" class="form-control" placeholder="编号" id="UserName" name="name">
            <input type="hidden" name="oper" value="login">
            <i class="fa fa-user"></i>
        </div>
        <div class="form-group log-status">
            <input type="password" class="form-control" placeholder="密码" id="Password" name="pwd">
            <i class="fa fa-lock"></i>
        </div>
        <%--<span class="alert">Invalid Credentials</span> --%>
        <a class="link" href="student/signup.jsp">新学生用户请注册</a>
        <input type="submit" class="log-btn" name="Login" value="登录">

    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<%--<script  src="js/index.js"></script>--%>
</body>

</html>

