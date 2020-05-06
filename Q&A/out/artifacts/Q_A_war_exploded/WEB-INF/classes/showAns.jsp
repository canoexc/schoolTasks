
<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/20
  Time: 8:26
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
    <script type="text/javascript">
        KE.show({
            id : 'content7',
            cssPath : './index.css'
        });
    </script>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="ms?oper=askInfo&cid=${l.cid}&cname=${l.cname}&type=cid&var=${l.cid}">课程留言信息</a></li>
        <li><a href="ms?oper=showAsk&mid=${l.mid}&cid=${l.cid}&cname=${l.cname}&sid=${l.sid}&sname=${l.sname}&header=${l.header}&question=${l.question}&status=${l.status}&date=${l.date}">留言详情</a></li>
        <li><a href="#" onclick="return false;">查看回复</a></li>
    </ul>
</div>
<ul class="seachform">
    <form action="ms" method="post">
        <input type="hidden" name="oper" value="showAns"/>
        <input type="hidden" name="mid" value="${requestScope.mid}"/>
        <li><label>&nbsp;&nbsp;选择查询属性</label>
            <div class="vocation">
                <select class="select3" name="type">
                    <option value="show" selected>显示全部</option>
                    <option value="key">查找关键字</option>
                </select>
            </div>
        </li>
        <li><label>输入属性值</label><input name="var1" type="text" class="scinput" /></li>
        <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="查询"/></li>
    </form>
</ul>

<table class="tablelist">
    <thead>
    <tr>
        <th>教师编号</th>
        <th>教师姓名</th>
        <th>回复内容</th>
        <th>回复时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.rlist}" var="rl">
        <tr>
            <td>${rl.tid}</td>
            <td>${rl.tname}</td>
            <td>${rl.answer}</td>
            <td>${rl.date}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="pagin">
    <div class="message">共<i class="blue">${requestScope.rsize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
    <ul class="paginList">
        <li class="paginItem"><a href="ms?oper=showAns&mid=${rl.mid}&type=${requestScope.type}&var1=${requestScope.var1}&start=${pre}"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="ms?oper=showAns$mid=${rl.mid}&type=${requestScope.type}&var1=${requestScope.var1}&start=${next}"><span class="pagenxt"></span></a></li>
    </ul>
</div>
</body>
