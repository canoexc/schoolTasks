<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/5
  Time: 21:26
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
            var tname=document.up.tname.value;
            var pwd=document.up.pwd.value;
            if(pwd==""){
                alert("密码不能为空！");
                return false;
            }
            if(tname==""){
                alert("教师姓名不能为空！");
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
        <li><a href="#" onclick="return false;">添加教师信息</a></li>
    </ul>
</div>

<div class="formbody" >

    <div class="formtitle"><span>添加教师信息</span></div>

    <ul class="forminfo">
        <form action="ms" method="post" name="up" onsubmit="return ok()">
        <input type="hidden" name="oper" value="addTeacher"/>
        <li><label>教师姓名</label><input name="tname" type="text" class="dfinput" /><i>请输入真实姓名</i></li>
        <li><label>密码</label><input name="pwd" type="text" class="dfinput" /><i>设置6-16位密码</i></li>
        <li><label>教师职称</label>
            <select name="title" style="width: 50px;height: 20px">
                <option value="教授" selected>教授</option>
                <option value="副教授" >副教授</option>
                <option value="讲师" >讲师</option>
                <option value="职员" >职员</option>
            </select>
        </li>
        <li><label>教师简介</label><textarea name="intro" cols="" rows="" class="textinput"></textarea></li>
        <li><label>&nbsp;</label><input  type="submit" class="btn" value="添加"/>&emsp;&emsp;<input  type="reset" class="btn" value="重置"/></li>
        </form>
    </ul>


</div>


</body>

</html>


