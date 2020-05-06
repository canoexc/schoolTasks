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
</head>


<body>
<c:choose>
<c:when test="${requestScope.ui=='mine'}">
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="#" onclick="return false;">我的课程</a></li>
    </ul>
</div>

<div id="tab2" class="tabson">


    <ul class="seachform">
        <form action="ms" method="post">
            <input type="hidden" name="oper" value="courseInfo"/>
            <input type="hidden" name="suboper" value="mine"/>
            <li><label>&nbsp;&nbsp;选择查询属性</label>
                <div class="vocation">
                    <select class="select3" name="type">
                        <option value="all" selected>显示全部</option>
                        <option value="cid">课程编号</option>
                        <option value="cname">课程名</option>
                        <option value="tid">教师编号</option>
                        <option value="tname">教师姓名</option>
                        <option value="iid">学院编号</option>
                        <option value="iname">学院名称</option>
                    </select>
                </div>
            </li>
            <li><label>输入属性值</label><input name="var" type="text" class="scinput" /></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="查询"/></li>

        </form>
    </ul>

</div>
<div class="rightinfo">
    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>课程编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>课程名</th>
            <th>教师编号</th>
            <th>教师姓名</th>
            <th>学院编号</th>
            <th>学院名称</th>
            <th>课程简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.clist}" var="cl">
            <tr>
                <td><input name="" type="checkbox" value="" /></td>
                <td>${cl.cid}</td>
                <td>${cl.cname}</td>
                <td>${cl.tid}</td>
                <td>${cl.tname}</td>
                <td>${cl.iid}</td>
                <td>${cl.iname}</td>
                <td>${cl.cintro}</td>
                <td><a href="ms?oper=askInfo&cid=${cl.cid}&cname=${cl.cname}&type=cid&var=${cl.cid}" class="tablelink">查看留言</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">${requestScope.csize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
        <ul class="paginList">
            <li class="paginItem"><a href="ms?oper=courseInfo&suboper=mine&type=${requestScope.type}&ui=mine&start=${pre}"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="ms?oper=courseInfo&suboper=mine&type=${requestScope.type}&ui=mine&start=${next}"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
</c:when>
<c:otherwise>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="admin/index.jsp">首页</a></li>
            <li><a href="">申请课程</a></li>
        </ul>
    </div>

    <div id="tab2" class="tabson">


        <ul class="seachform">
            <form action="ms" method="post">
                <input type="hidden" name="oper" value="courseInfo"/>
                <input type="hidden" name="suboper" value="other"/>
                <li><label>&nbsp;&nbsp;选择查询属性</label>
                    <div class="vocation">
                        <select class="select3" name="type">
                            <option value="all" selected>显示全部</option>
                            <option value="cid">课程编号</option>
                            <option value="cname">课程名</option>
                            <option value="tid">教师编号</option>
                            <option value="tname">教师姓名</option>
                            <option value="iid">学院编号</option>
                            <option value="iname">学院名称</option>
                        </select>
                    </div>
                </li>
                <li><label>输入属性值</label><input name="var" type="text" class="scinput" /></li>
                <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="查询"/></li>

            </form>
        </ul>

    </div>
    <div class="rightinfo">
        <table class="tablelist">
            <thead>
            <tr>
                <th><input name="" type="checkbox" value="" checked="checked"/></th>
                <th>课程编号<i class="sort"><img src="images/px.gif" /></i></th>
                <th>课程名</th>
                <th>教师编号</th>
                <th>教师姓名</th>
                <th>学院编号</th>
                <th>学院名称</th>
                <th>课程简介</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.clist}" var="cl">
                <tr>
                    <td><input name="" type="checkbox" value="" /></td>
                    <td>${cl.cid}</td>
                    <td>${cl.cname}</td>
                    <td>${cl.tid}</td>
                    <td>${cl.tname}</td>
                    <td>${cl.iid}</td>
                    <td>${cl.iname}</td>
                    <td>${cl.cintro}</td>
                    <td><a href="ms?oper=applying&cid=${cl.cid}&cname=${cl.cname}&tid=${cl.tid}&tname=${cl.tname}" class="tablelink">申请权限</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <div class="pagin">
            <div class="message">共<i class="blue">${requestScope.csize}</i>条记录&nbsp;<i class="blue">&nbsp;</i></div>
            <ul class="paginList">
                <li class="paginItem"><a href="ms?oper=courseInfo&suboper=other&type=${requestScope.type}&ui=other&start=${pre}"><span class="pagepre"></span></a></li>
                <li class="paginItem"><a href="ms?oper=courseInfo&suboper=other&type=${requestScope.type}&ui=other&start=${next}"><span class="pagenxt"></span></a></li>
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