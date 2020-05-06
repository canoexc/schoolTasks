<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/8
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <li><a href="#" onclick="return false;">更新学院信息</a></li>
    </ul>
</div>

<div class="formbody" >

    <div class="formtitle"><span>更新学院信息</span></div>

    <ul class="forminfo">
        <form action="ms" method="post">
            <input type="hidden" name="oper" value="upInstitute"/>
            <li><label>学院编号</label><input name="iid" type="text" class="dfinput" readonly value="${requestScope.iid}" /><i>不可更改</i></li>
            <li><label>学院名称</label><input name="iname" type="text" class="dfinput" value="${requestScope.iname}"/><i>*</i></li>
            <li><label>学院简介</label><textarea name="iintro" cols="" rows="" class="textinput" >${requestScope.iintro}</textarea></li>
            <li><label>&nbsp;</label><input  type="submit" class="btn" value="添加"/>&emsp;&emsp;<input  type="reset" class="btn" value="重置"/></li>
        </form>
    </ul>


</div>


</body>

</html>

