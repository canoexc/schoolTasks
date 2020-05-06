<%--
  Created by IntelliJ IDEA.
  User: canoexc
  Date: 2019/12/4
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script language="JavaScript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson .header").click(function(){
                var $parent = $(this).parent();
                $(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();

                $parent.addClass("active");
                if(!!$(this).next('.sub-menus').size()){
                    if($parent.hasClass("open")){
                        $parent.removeClass("open").find('.sub-menus').hide();
                    }else{
                        $parent.addClass("open").find('.sub-menus').show();
                    }


                }
            });

            // 三级菜单点击
            $('.sub-menus li').click(function(e) {
                $(".sub-menus li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('.menuson').slideUp();
                if($ul.is(':visible')){
                    $(this).next('.menuson').slideUp();
                }else{
                    $(this).next('.menuson').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#fff3e1;">
<div class="lefttop"><span></span>功能页</div>

<dl class="leftmenu">

    <dd>
        <div class="title">
            <span><img src="images/leftico01.png" /></span>账号信息
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="student/personalInfo.jsp" target="rightFrame">查看账号信息</a><i></i></li>
            <li><cite></cite><a href="student/pwdChange.jsp" target="rightFrame">修改密码</a><i></i></li>
        </ul>
    </dd>


    <dd>
        <div class="title">
            <span><img src="images/leftico02.png" /></span>课程信息
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="ms?oper=courseInfo&suboper=mine&type=all&ui=mine" target="rightFrame">我的课程</a><i></i></li>
            <li><cite></cite><a href="ms?oper=courseInfo&suboper=other&type=all&ui=other" target="rightFrame">申请课程</a><i></i></li>
        </ul>
    </dd>


    <dd><div class="title"><span><img src="images/leftico03.png" /></span>我的消息</div>
        <ul class="menuson">
            <li>
                <div class="header">
                    <cite></cite>
                    申请消息
                    <i></i>
                </div>
                <ul class="sub-menus">
                    <li><a href="ms?oper=appNews&suboper=new" target="rightFrame">新申请回复</a></li>
                    <li><a href="ms?oper=appNews&suboper=all" target="rightFrame">全部申请</a></li>
                </ul>
            </li>

            <li><cite></cite><a href="ms?oper=askCheck" target="rightFrame">我的提问</a><i></i></li>
            <li>
                <div class="header">
                    <cite></cite>
                    回复消息
                    <i></i>
                </div>
                <ul class="sub-menus">
                    <li><a href="ms?oper=ansCheck&type=new" target="rightFrame">新回复</a></li>
                    <li><a href="ms?oper=ansCheck&type=all" target="rightFrame">全部回复</a></li>
                </ul>
            </li>
        </ul>
    </dd>


</dl>

</body>
</html>


