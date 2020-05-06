<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/20
  Time: 23:35
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
    <link href="css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
    <script type="text/javascript">
        KE.show({
            id : 'content7',
            cssPath : './index.css'
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });
            $(".select2").uedSelect({
                width : 167
            });
            $(".select3").uedSelect({
                width : 100
            });
        });
    </script>
    <script type="text/javascript">
        $(function(){
            $("#del").click(function(){
                var flag=window.confirm("确认删除吗？");
                if(flag){
                    $.session.set('f','0');
                    window.location.href="ms?oper=delTeacher&amp;tid=${tl.tid}";
                }
            })
        })
    </script>
    <script language="javascript">
        function ok() {
            var header=document.up.header.value;
            var question=document.up.question.value;
            if(header==""){
                alert("标题不能为空！");
                return false;
            }
            if(question==""){
                alert("内容不能为空！");
                return false;
            }
            return true;
        }
    </script>
    <script type="text/javascript">
        KE.show({
            id : 'content7',
            cssPath : './index.css'
        });
    </script>
</head>
<body>
<div class="formbody">
<div id="tab2" class="tabson">
    <ul class="forminfo">
        <form action="ms" method="post" name="up" onsubmit="return ok()">
            <input type="hidden" name="oper" value="upAsk"/>
            <input type="hidden" name="suboper" value="up"/>
            <input type="hidden" name="mid" value="${requestScope.mid}"/>
            <li><label>学生编号<b>*</b></label><input name="sid" type="text" class="dfinput" readonly value="${sessionScope.student.sid}"  style="width:200px;"/></li>

            <li><label>学生姓名<b>*</b></label><input name="sname" type="text" class="dfinput" readonly value="${sessionScope.student.sname}"  style="width:200px;"/></li>

            <li><label>课程编号<b>*</b></label><input name="cid" type="text" class="dfinput" readonly value="${requestScope.cid}"  style="width:200px;"/></li>
            <li><label>课程名称<b>*</b></label><input name="cname" type="text" class="dfinput" readonly value="${requestScope.cname}"  style="width:200px;"/></li>
            <li><label>留言标题<b>*</b></label><input name="header" type="text" class="dfinput" value="${requestScope.header}"  style="width:200px;"/></li>
            <li><label>留言内容<b>*</b></label>
                <textarea id="content7" name="question" style="width:700px;height:250px;visibility:hidden;">${requestScope.question}</textarea>

            </li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/>&emsp;&emsp;&emsp;<input name="" type="reset" class="btn" value="清空"/></li>
        </form>
    </ul>
</div>
</div>
</body>
</html>
