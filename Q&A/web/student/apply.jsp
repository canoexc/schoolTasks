<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/8
  Time: 14:18
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
        <li><a href="ms?oper=courseInfo&suboper=other&type=all&ui=other">申请课程</a></li>
        <li><a href="#" onclick="return false;">填写申请信息</a></li>
    </ul>
</div>

<div class="formbody" >

    <div class="formtitle"><span>添加课程信息</span></div>

    <ul class="forminfo">
        <form action="ms" method="post">
            <input type="hidden" name="oper" value="apply"/>
            <li><label>学生编号</label><input name="sid" type="text" class="dfinput" readonly value="${sessionScope.student.sid}"/><i>*</i></li>
            <li><label>学生姓名</label><input name="sname" type="text" class="dfinput" readonly value="${sessionScope.student.sname}"/><i>*</i></li>
            <li><label>课程编号</label><input name="cid" type="text" class="dfinput" readonly value="${requestScope.cid}"/><i>*</i></li>
            <li><label>课程名称</label><input name="cname" type="text" class="dfinput" readonly value="${requestScope.cname}"/><i>*</i></li>
            <li><label>教师编号</label><input name="tid" type="text" class="dfinput" readonly value="${requestScope.tid}"/><i>*</i></li>
            <li><label>教师姓名</label><input name="tname" type="text" class="dfinput" readonly value="${requestScope.tname}"/><i>*</i></li>
            <li><label>填写备注</label><textarea name="ps" cols="" rows="" class="textinput" placeholder="(选填)"></textarea></li>
            <li><label>&nbsp;</label><input  type="submit" class="btn" value="申请"/>&emsp;&emsp;<input  type="reset" class="btn" value="重置"/></li>
        </form>
    </ul>


</div>


</body>

</html>


