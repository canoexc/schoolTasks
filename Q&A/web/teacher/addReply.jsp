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
            var answer=document.up.answer.value;
            if(answer==""){
                alert("回复不可为空！");
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
        <li><a href="ms?oper=askInfo&cid=${requestScope.cid}&cname=${requestScope.cname}&type=cid&var=${requestScope.cid}">课程留言信息</a></li>
        <li><a href="#" onclick="return false;">回复留言</a></li>
    </ul>
</div>
    <div class="mainbox">
    <div class="leftinfo" style="width: 50%">
        <div class="listtitle"><c:if test="${requestScope.status!='0'}"><a href="ms?oper=showAsk&mid=${requestScope.mid}&type=show" class="more1">查看回复</a></c:if>课程：${requestScope.cname}</div>

        <div class="maintj" style="text-align: left;font-family: 仿宋;width: 100%">
            <p style="color: #4b4343"><h1>&emsp;标题：${requestScope.header}&emsp;</h1></p>
            <div class="xline"></div>
            <p style="color: #4b4343"><h1>&emsp;留言人：${requestScope.sname}&emsp;</h1></p>
            <div class="xline"></div>
            <p style="color: #4b4343"><h1>&emsp;留言时间：${requestScope.date}</h1></p>
            <div class="xline"></div>
            <p><h1 >&emsp;内容：${requestScope.question}</h1></div>
    </div>
    <div class="tabson">
        <ul class="forminfo">
            <form action="ms?oper=addReply&suboper=add" method="post" name="up" onsubmit="return ok()">
                <input type="hidden" name="suboper" value="add"/>
                <input type="hidden" name="sub" value="${requestScope.sub}"/>
                <input type="hidden" name="mid" value="${requestScope.mid}"/>
                <input type="hidden" name="cid" value="${requestScope.cid}"/>
                <input type="hidden" name="cname" value="${requestScope.cname}"/>
                <input type="hidden" name="header" value="${requestScope.header}"/>
                <input type="hidden" name="question" value="${requestScope.question}"/>
                <input type="hidden" name="sid" value="${requestScope.sid}"/>
                <input type="hidden" name="sname" value="${requestScope.sname}"/>
                <input type="hidden" name="date" value="${requestScope.date}"/>
                <li><label>课程名<b>*</b></label><input name="cname" type="text" class="dfinput" readonly value="${requestScope.cname}"  style="width:200px;"/></li>
                <li><label>教师编号<b>*</b></label><input name="tid" type="text" class="dfinput" readonly value="${sessionScope.teacher.tid}"  style="width:200px;"/></li>
                <li><label>教师姓名<b>*</b></label><input name="tname" type="text" class="dfinput" readonly value="${sessionScope.teacher.tname}"  style="width:200px;"/></li>
                <li><label>回复内容<b>*</b></label>
                    <textarea id="content7" name="answer" style="width:700px;height:250px;visibility:hidden;"></textarea>

                </li>
                <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/>&emsp;&emsp;&emsp;<input name="" type="reset" class="btn" value="清空"/></li>
            </form>
        </ul>
    </div>
</div>
</div>
</body>
