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
<c:choose>
<c:when test="${requestScope.ui=='new'}">
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="#" onclick="return false;">新申请回复</a></li>
    </ul>
</div>
<div class="rightinfo">
    <table class="tablelist">
        <thead>
        <tr>
            <th>课程编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>课程名</th>
            <th>教师编号</th>
            <th>教师姓名</th>
            <th>学院名称</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.clist}" var="cl">
            <tr>
                <td>${cl.cid}</td>
                <td>${cl.cname}</td>
                <td>${cl.tid}</td>
                <td>${cl.tname}</td>
                <td>${cl.iname}</td>
                <c:choose>
                    <c:when test="${cl.cintro=='1'}">
                        <td>已通过</td>
                    </c:when>
                    <c:otherwise>
                        <td>已拒绝</td>
                    </c:otherwise>
                </c:choose>
                <td><a href="ms?oper=appNews&suboper=know&pid=${cl.iid}&status=${cl.cintro}" class="tablelink">已读</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">${requestScope.csize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
        <ul class="paginList">
            <li class="paginItem"><a href="ms?oper=appNews&suboper=new&start=${pre}"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="ms?oper=appNews&suboper=new&start=${next}"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
</c:when>
<c:otherwise>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="student/index.jsp">首页</a></li>
            <li><a href="">全部申请</a></li>
        </ul>
    </div>
    <div class="rightinfo">
        <table class="tablelist">
            <thead>
            <tr>
                <th>课程编号<i class="sort"><img src="images/px.gif" /></i></th>
                <th>课程名</th>
                <th>教师编号</th>
                <th>教师姓名</th>
                <th>学院名称</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.clist}" var="cl">
                <tr>
                    <td>${cl.cid}</td>
                    <td>${cl.cname}</td>
                    <td>${cl.tid}</td>
                    <td>${cl.tname}</td>
                    <td>${cl.iname}</td>
                    <c:choose>
                        <c:when test="${cl.cintro=='3'}">
                            <td>已通过</td>
                        </c:when>
                        <c:when test="${cl.cintro=='1'}">
                            <td>已通过</td>
                        </c:when>
                        <c:when test="${cl.cintro=='2'}">
                            <td>已拒绝</td>
                        </c:when>
                        <c:when test="${cl.cintro=='4'}">
                            <td>已拒绝</td>
                        </c:when>
                        <c:otherwise>
                            <td>未审核</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <div class="pagin">
            <div class="message">共<i class="blue">${requestScope.csize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
            <ul class="paginList">
                <li class="paginItem"><a href="ms?oper=appNews&suboper=all&start=${pre}"><span class="pagepre"></span></a></li>
                <li class="paginItem"><a href="ms?oper=appNews&suboper=all&start=${next}"><span class="pagenxt"></span></a></li>
            </ul>
        </div>
</c:otherwise>
</c:choose>

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