<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/5
  Time: 15:18
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
            <li><a href="">全部回复</a></li>
        </ul>
    </div>
    <div class="rightinfo">
        <table class="tablelist">
            <thead>
            <tr>
                <th>课程编号<i class="sort"><img src="images/px.gif" /></i></th>
                <th>课程名</th>
                <th>标题</th>
                <th>回复时间</th>
                <th>回复</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.rlist}" var="rl">
                <tr>
                    <td>${rl.cid}</td>
                    <td>${rl.cname}</td>
                    <td>${rl.header}</td>
                    <td>${rl.date}</td>
                    <td>${rl.answer}</td>
                    <td><a href="ms?oper=delAns&rid=${rl.rid}&mid=${rl.mid}" onclick="return ok()" class="tablelink">删除</a>&emsp;
                        <a href="ms?oper=upAns&rid=${rl.rid}&mid=${rl.mid}&cname=${rl.cname}&header=${rl.header}&question=${rl.question}&answer=${rl.answer}"  class="tablelink">修改</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <div class="pagin">
            <div class="message">共<i class="blue">${requestScope.rsize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
            <ul class="paginList">
                <li class="paginItem"><a href="ms?oper=ansCheck&start=${pre}"><span class="pagepre"></span></a></li>
                <li class="paginItem"><a href="ms?oper=ansCheck&start=${next}"><span class="pagenxt"></span></a></li>
            </ul>
        </div>
    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" />&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>
</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>