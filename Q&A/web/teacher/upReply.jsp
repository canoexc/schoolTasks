<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/19
  Time: 21:12
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
            var f=confirm("确定要删除吗？");
            return f
        }
    </script>

</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="teacher/index.jsp">首页</a></li>
        <li><a href="ms?oper=ansCheck">全部回复</a> </li>
        <li><a href="">更新回复</a></li>
    </ul>
</div>
    <div class="mainbox">
    <div class="leftinfo" style="width: 50%">
        <div class="listtitle">课程：${requestScope.cname}</div>

        <div class="maintj" style="text-align: left;font-family: 仿宋;width: 100%">
            <p style="color: #4b4343"><h1>&emsp;标题：${requestScope.header}&emsp;</h1></p>
            <div class="xline"></div>
            <p><h1 >&emsp;内容：${requestScope.question}</h1></div>
    </div>
    <div class="tabson">
        <ul class="forminfo">
            <form action="ms" method="post">
                <input type="hidden" name="oper" value="upAns"/>
                <input type="hidden" name="suboper" value="up"/>
                <input type="hidden" name="rid" value="${requestScope.rid}"/>
                <input type="hidden" name="mid" value="${requestScope.mid}"/>
                <li><label>课程名<b>*</b></label><input name="cname" type="text" class="dfinput" readonly value="${requestScope.cname}"  style="width:200px;"/></li>
                <li><label>教师编号<b>*</b></label><input name="tid" type="text" class="dfinput" readonly value="${sessionScope.teacher.tid}"  style="width:200px;"/></li>
                <li><label>教师姓名<b>*</b></label><input name="tname" type="text" class="dfinput" readonly value="${sessionScope.teacher.tname}"  style="width:200px;"/></li>
                <li><label>回复内容<b>*</b></label>
                    <textarea id="content7" name="answer" style="width:700px;height:250px;visibility:hidden;">${requestScope.answer}</textarea>

                </li>
                <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/>&emsp;&emsp;&emsp;<input name="" type="reset" class="btn" value="清空"/></li>
            </form>
        </ul>
    </div>
</div>
</div>
</body>
