<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/8
  Time: 14:26
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
        <li><a href="ms?oper=showCourse">修改课程信息</a></li>
        <li><a href="#" onclick="return false;">更新课程信息</a></li>
    </ul>
</div>

<div class="formbody" >

    <div class="formtitle"><span>更新课程信息</span></div>

    <ul class="forminfo">
        <form action="ms" method="post">
            <input type="hidden" name="oper" value="upCourse"/>
            <li><label>课程编号</label><input name="cid" type="text" class="dfinput" readonly value="${requestScope.cid}" /><i>不可更改</i></li>
            <li><label>课程名</label><input name="cname" type="text" class="dfinput" value="${requestScope.cname}"/><i>*</i></li>
            <li><label>教师编号</label><input name="tid" type="text" class="dfinput" value="${requestScope.tid}"/><i>*</i></li>
            <li><label>教师姓名</label><input name="tname" type="text" class="dfinput" value="${requestScope.tname}"/><i>*</i></li>
            <li><label>学院编号</label><input name="iid" type="text" class="dfinput" value="${requestScope.iid}"/><i>*</i></li>
            <li><label>学院名称</label><input name="iname" type="text" class="dfinput" value="${requestScope.iname}"/><i>*</i></li>
            <li><label>课程简介</label><textarea name="cintro" cols="" rows="" class="textinput" >${requestScope.cintro}</textarea></li>
            <li><label>&nbsp;</label><input  type="submit" class="btn" value="更新"/></li>
        </form>
    </ul>


</div>


</body>

</html>

