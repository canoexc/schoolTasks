<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/2
  Time: 22:52
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
  <script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="ms?oper=login">首页</a></li>
  </ul>
</div>

<div class="mainindex">


  <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b>${sessionScope.teacher.tname}老师，你好，欢迎使用答疑系统</b>
  </div>


  <div class="xline"></div>

  <ul class="umlist">
    <c:if test="${sessionScope.nasize!=0}">
      <p></p>
      <p><b><li>你有${sessionScope.nasize}条新申请，点击<a href="ms?oper=appNews&suboper=new" class="tablelink">处理</a></li></b></p>
    </c:if>
    <c:if test="${sessionScope.msize!=0}">
      <p></p>
      <p><b><li>你有${sessionScope.msize}条新提问，点击<a href="ms?oper=askCheck&type=new" class="tablelink">回复</a></li></b></p>
    </c:if>
  </ul>

</div>



</body>

</html>

