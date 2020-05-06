<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/6
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="#" onclick="return false;">我的个人信息</a></li>
    </ul>
</div>

<div class="formbody" >

    <div class="formtitle"><span>我的个人信息</span></div>

    <ul class="forminfo">
            <li><label>学生编号</label><input name="tid" type="text" class="dfinput" readonly value="${(sessionScope.student).sid}" /><i>不可更改</i></li>
            <li><label>学生姓名</label><input name="tname" type="text" class="dfinput" readonly value="${(sessionScope.student).sname}"/></li>
    </ul>


</div>


</body>

</html>
