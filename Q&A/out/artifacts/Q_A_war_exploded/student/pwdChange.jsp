<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/8
  Time: 21:43
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
            var cor=${sessionScope.student.pwd};
            var opwd=document.change.opwd.value;
            var pwd=document.change.pwd.value;
            var cpwd=document.change.cpwd.value;
            if(opwd!=cor) {
                alert("旧密码错误！");
                return false;
            }
            if(pwd!=cpwd){
                alert("确认密码与新密码不一致！");
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
        <li><a href="#" onclick="return false;">修改密码</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>修改密码</span></div>

    <ul class="forminfo">
        <form action="ms" method="post" name="change" onsubmit="return ok()">
            <input type="hidden" name="oper" value="pwdChange"/>
            <li><label>请输入旧密码</label><input name="opwd" type="password" class="dfinput" /><i>*</i></li>
            <li><label>请输入新密码</label><input name="pwd" type="password" class="dfinput" /><i>设置6-16位密码</i></li>
            <li><label>请确认新密码</label><input name="cpwd" type="password" class="dfinput" /><i>请确保与新密码一致</i></li>
            <li><label>&nbsp;</label><input type="submit" class="btn" value="提交"/>&emsp;&emsp;<button  type="reset" class="btn" value="重置">重置</button></li>
        </form>
    </ul>


</div>


</body>

</html>
