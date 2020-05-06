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
    <script language="javascript">
        function ok() {
            var cname=document.up.cname.value;
            var tid=document.up.tid.value;
            var tname=document.up.tname.value;
            var iid=document.up.iid.value;
            var iname=document.up.iname.value;
            if(cname==""){
                alert("课程名不能为空！");
                return false;
            }
            if(tid==""){
                alert("教师编号不能为空！");
                return false;
            }
            if(tname==""){
                alert("教师姓名不能为空！");
                return false;
            }
            if(iid==""){
                alert("学院编号不能为空！");
                return false;
            }
            if(iname==""){
                alert("学院名不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="#" onclick="return false">添加课程信息</a></li>
    </ul>
</div>

<div class="formbody" >

    <div class="formtitle"><span>添加课程信息</span></div>

    <ul class="forminfo">
        <form action="ms" method="post" name="up" onsubmit="return ok()">
            <input type="hidden" name="oper" value="addCourse"/>
            <li><label>课程名</label><input name="cname" type="text" class="dfinput" /><i>*</i></li>
            <li><label>教师编号</label><input name="tid" type="text" class="dfinput" /><i>*</i></li>
            <li><label>教师姓名</label><input name="tname" type="text" class="dfinput" /><i>*</i></li>
            <li><label>学院编号</label><input name="iid" type="text" class="dfinput" /><i>*</i></li>
            <li><label>学院名称</label><input name="iname" type="text" class="dfinput" /><i>*</i></li>
            <li><label>课程简介</label><textarea name="cintro" cols="" rows="" class="textinput" placeholder="(选填)"></textarea></li>
            <li><label>&nbsp;</label><input  type="submit" class="btn" value="添加"/>&emsp;&emsp;<input  type="reset" class="btn" value="重置"/></li>
        </form>
    </ul>


</div>


</body>

</html>


