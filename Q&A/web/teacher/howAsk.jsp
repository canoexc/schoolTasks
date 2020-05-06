<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/19
  Time: 0:31
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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jsapi.js"></script>
    <script type="text/javascript" src="js/format+zh_CN,default,corechart.I.js"></script>
    <script type="text/javascript" src="js/jquery.gvChart-1.0.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.ba-resize.min.js"></script>

    <script type="text/javascript">
        gvChartInit();
        jQuery(document).ready(function(){

            jQuery('#myTable5').gvChart({
                chartType: 'PieChart',
                gvSettings: {
                    vAxis: {title: 'No of players'},
                    hAxis: {title: 'Month'},
                    width: 650,
                    height: 250
                }
            });
        });
    </script>
</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="ms?oper=login">首页</a></li>
        <li><a href="ms?oper=askInfo&cid=${requestScope.cid}&cname=${requestScope.cname}&type=cid&var=${requestScope.cid}">课程留言信息</a></li>
        <li><a href="#" onclick="return false;">留言详情</a></li>
    </ul>
</div>


<div class="mainbox">

    <div class="mainleft">


        <div class="leftinfo">
            <div class="listtitle"><a href="ms?oper=showAns&mid=${requestScope.mid}&type=show" class="more1">查看回复</a>课程：${requestScope.cname}</div>

            <div class="maintj" style="text-align: left;font-family: 仿宋;">
                <p style="color: #4b4343"><h1>&emsp;标题：${requestScope.header}&emsp;</h1></p>
                <div class="xline"></div>
                <p style="color: #4b4343"><h1>&emsp;留言人：${requestScope.sname}&emsp;</h1></p>
                <div class="xline"></div>
                <p style="color: #4b4343"><h1>&emsp;留言时间：${requestScope.date}</h1></p>
                <div class="xline"></div>
                <p><h1 >&emsp;内容：${requestScope.question}</h1></div>
            </div>

        </div>
        <!--leftinfo end-->


</div>
    </div>
    <!--mainleft end-->



</div>



</body>
<script type="text/javascript">
    setWidth();
    $(window).resize(function(){
        setWidth();
    });
    function setWidth(){
        var width = ($('.leftinfos').width()-12)/2;
        $('.infoleft,.inforight').width(width);
    }
</script>
</html>

