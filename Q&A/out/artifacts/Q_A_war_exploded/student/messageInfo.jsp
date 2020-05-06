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

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="#" onclick="return false">课程留言信息</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab">
            <ul>
                <li><a href="student/messageInfo.jsp#tab1" class="selected">留言详情</a></li>
                <li><a href="student/messageInfo.jsp#tab2">填写留言</a></li>
            </ul>
        </div>
        <div id="tab1" class="tabson">
            <ul class="seachform">
                <form action="ms" method="post">
                    <input type="hidden" name="oper" value="askInfo"/>
                    <input type="hidden" name="var" value="${requestScope.cid}"/>
                    <input type="hidden" name="cname" value="${requestScope.cname}"/>
                    <li><label>&nbsp;&nbsp;选择查询属性</label>
                        <div class="vocation">
                            <select class="select3" name="type">
                                <option value="cid" selected>显示全部</option>
                                <option value="header">查找标题关键字</option>
                                <option value="question">查找内容关键字</option>
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
            <th>学生编号</th>
            <th>学生姓名</th>
            <th>留言标题</th>
            <th>留言时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.mlist}" var="ml">
            <tr>
                <td>${ml.sid}</td>
                <td>${ml.sname}</td>
                <td>${ml.header}</td>
                <td>${ml.date}</td>
                <c:choose>
                    <c:when test="${ml.status=='0'}">
                        <td>尚无回复</td>
                    </c:when>
                    <c:otherwise>
                        <td>${ml.status}条回复</td>
                    </c:otherwise>
                </c:choose>
                <td><a href="ms?oper=showAsk&mid=${ml.mid}&cid=${ml.cid}&cname=${requestScope.cname}&sid=${ml.sid}&sname=${ml.sname}&header=${ml.header}&question=${ml.question}&status=${ml.status}&date=${ml.date}" class="tablelink">查看内容及回复</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">${requestScope.msize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
        <ul class="paginList">
            <li class="paginItem"><a href="ms?oper=askInfo&cid=${requestScope.cid}&cname=${requestScope.cname}&type=${requestScope.type}&var=${requestScope.cid}&var1=${requestScope.var1}&start=${pre}"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="ms?oper=askInfo$cid=${requestScope.cid}&cname=${requestScope.cname}&type=${requestScope.type}&var=${requestScope.cid}&var1=${requestScope.var1}&start=${next}"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
        </div>
        <div id="tab2" class="tabson">
            <ul class="forminfo">
                <form action="ms?oper=addMessage" method="post" name="up" onsubmit="return ok()">
                <li><label>学生编号<b>*</b></label><input name="sid" type="text" class="dfinput" readonly value="${sessionScope.student.sid}"  style="width:200px;"/></li>

                <li><label>学生姓名<b>*</b></label><input name="sname" type="text" class="dfinput" readonly value="${sessionScope.student.sname}"  style="width:200px;"/></li>

                <li><label>课程编号<b>*</b></label><input name="cid" type="text" class="dfinput" readonly value="${requestScope.cid}"  style="width:200px;"/></li>
                <li><label>课程名称<b>*</b></label><input name="cname" type="text" class="dfinput" readonly value="${requestScope.cname}"  style="width:200px;"/></li>
                <li><label>留言标题<b>*</b></label><input name="header" type="text" class="dfinput" value=""  style="width:200px;"/></li>
                <li><label>留言内容<b>*</b></label>
                    <textarea id="content7" name="question" style="width:700px;height:250px;visibility:hidden;" value=""></textarea>

                </li>
                <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/>&emsp;&emsp;&emsp;<input name="" type="reset" class="btn" value="清空"/></li>
                </form>
            </ul>
        </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
<script type="text/javascript">
    $("#usual1 ul").idTabs();
</script>
</div>
</body>

</html>