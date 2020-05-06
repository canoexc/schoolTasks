package servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpServer;
import pojo.*;
import service.*;
import service.impl.*;

@WebServlet(name="admitServlet",urlPatterns ="/as")
public class adminServlet extends HttpServlet {
    Logger logger=Logger.getLogger(adminServlet.class);
    teacherService ts=new teacherServiceImpl();
    courseService cs=new courseServiceImpl();
    instituteService is=new instituteServiceImpl();
    messageService ms=new messageServiceImpl();
    replyService rs=new replyServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String oper=req.getParameter("oper");
        if("login".equals(oper)){
            //调用登录功能
            checkAdminLogin(req,resp);
        }else if("out".equals(oper)){
            adminOut(req,resp);
        }else if("showTeacher".equals(oper)){
            showTeacher(req,resp);
        }else if("addTeacher".equals(oper)){
            addTeacher(req,resp);
        }else if("delTeacher".equals(oper)){
            delTeacher(req,resp);
        }else if("upTeacher".equals(oper)){
            upTeacher(req,resp);
        }else if("upMidT".equals(oper)){
            upMidT(req,resp);
        }else if("addCourse".equals(oper)){
            addCourse(req,resp);
        }else if("delCourse".equals(oper)){
            delCourse(req,resp);
        }else if("upCourse".equals(oper)){
            upCourse(req,resp);
        }else if("showCourse".equals(oper)){
            showCourse(req,resp);
        }else if("upMidC".equals(oper)){
            upMidC(req,resp);
        }else if("addInstitute".equals(oper)){
            addInstitute(req,resp);
        }else if("delInstitute".equals(oper)){
            delInstitute(req,resp);
        }else if("upInstitute".equals(oper)){
            upInstitute(req,resp);
        }else if("showInstitute".equals(oper)){
            showInstitute(req,resp);
        }else if("upMidI".equals(oper)){
            upMidI(req,resp);
        }else if("delAsk".equals(oper)){
            delAsk(req,resp);
        }else if("delAns".equals(oper)){
            delAns(req,resp);
        }else if("showAsk".equals(oper)){
            showAsk(req,resp);
        }else if("showAns".equals(oper)){
            showAns(req,resp);
        }else if("pwdChange".equals(oper)){
            pwdChange(req,resp);
        }
    }

    private void pwdChange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs=req.getSession();
        String aid=Integer.toString(((admin)hs.getAttribute("admin")).getAid());
        String pwd=req.getParameter("pwd");
        PrintWriter out=resp.getWriter();
        adminService as=new adminServiceImpl();
        boolean flag=as.pwdChange(aid,pwd);
        if(flag)
        {
            admin tp= (admin) hs.getAttribute("admin");
            tp.setPwd(pwd);
            hs.setAttribute("admin",tp);
            out.print("<script language='javascript'>alert('修改成功！请重新登录');window.top.location.href='ms?oper=out';</script>");
        }
        else{
            out.print("<script language='javascript'>alert('修改失败！');window.location.href='admin/pwdChange.jsp';</script>");
        }
    }

    private void showAns(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<uni> l=new ArrayList<>();
        int start=0;
        int count=5;
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            logger.debug("没有获取到start值");
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;
        total=rs.showReplyService("aid","").size();
        l=rs.showReplyService("aid","",start,count);
        if(total%count==0)last=total-last;
        else last=total-total%count;
        next=next>last?last:next;
        req.setAttribute("rsize",total);
        req.setAttribute("pre",pre);
        req.setAttribute("next",next);
        req.setAttribute("last",last);
        req.setAttribute("rlist",l);
        req.getRequestDispatcher("/admin/showAns.jsp").forward(req,resp);
    }

    private void showAsk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<uni> l=new ArrayList<>();
        int start=0;
        int count=5;
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            logger.debug("没有获取到start值");
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;
        total=ms.showMesService("aid","1","1").size();
        l=ms.showMesService("aid","1","1",start,count);
        if(total%count==0)last=total-last;
        else last=total-total%count;
        next=next>last?last:next;
        req.setAttribute("msize",total);
        req.setAttribute("pre",pre);
        req.setAttribute("next",next);
        req.setAttribute("last",last);
        req.setAttribute("mlist",l);
        req.getRequestDispatcher("/admin/showAsk.jsp").forward(req,resp);
    }

    private void delAns(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        String rid = req.getParameter("rid");
        PrintWriter out = resp.getWriter();
        boolean flag = rs.delReplyService(rid,mid);
        if (flag) out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showAns';</script>");
        else out.println("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showAns';</script>");
    }

    private void delAsk(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        PrintWriter out = resp.getWriter();
        boolean flag = ms.delMessageService(mid);
        if (flag) out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showAsk';</script>");
        else out.println("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showAsk';</script>");
    }

    private void upMidI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String iid=req.getParameter("iid");
        String iname=req.getParameter("iname");
        String iintro=req.getParameter("iintro");
        req.setAttribute("iid",iid);
        req.setAttribute("iname",iname);
        req.setAttribute("iintro",iintro);
        req.getRequestDispatcher("/admin/upInstitute.jsp").forward(req,resp);
    }

    private void showInstitute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start=0;
        int count=5;
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            logger.debug("没有获取到start值");
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;

        List<institute> li=new ArrayList<>();
        if(suboper==null||"all".equals(type))
        {
            total=(is.showAllInstituteService()).size();
            li=is.showAllInstituteService(start,count);
        }
        else {
            total=(is.showSubInstituteService(type,var)).size();
            li = is.showSubInstituteService(type, var,start,count);
        }
        if(li!=null){
            if(total%count==0)last=total-last;
            else last=total-total%count;
            next=next>last?last:next;
            req.setAttribute("ilist",li);
            req.setAttribute("isize",total);
            req.setAttribute("pre",pre);
            req.setAttribute("next",next);
            req.setAttribute("last",last);
            req.getRequestDispatcher("/admin/instituteInfo.jsp").forward(req,resp);
        }
    }

    private void upInstitute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String iid=req.getParameter("iid");
        String iname=req.getParameter("iname");
        String iintro=req.getParameter("iintro");
        institute i=new institute();
        int id=Integer.parseInt(iid);
        i.setIid(id);
        i.setIname(iname);
        i.setIintro(iintro);
        PrintWriter out=resp.getWriter();
        boolean flag=is.upInstituteService(i);
        if(flag){
            out.print("<script language='javascript'>alert('更新成功！');window.location.href='ms?oper=showInstitute';</script>");
        }else {
            out.print("<script language='javascript'>alert('更新失败！');window.location.href='ms?oper=showInstitute';</script>");
        }
    }

    private void delInstitute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String iid=req.getParameter("iid");
        PrintWriter out=resp.getWriter();
        boolean flag=is.delInstituteService(iid);
        if(flag){
            out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showInstitute';</script>");
        }else {
            out.print("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=showInstitute';</script>");
        }
    }

    private void addInstitute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        institute i=new institute();
        PrintWriter out=resp.getWriter();
        String iname=req.getParameter("iname");
        String iintro=req.getParameter("iintro");
        i.setIname(iname);
        i.setIintro(iintro);
        boolean flag=is.addInstituteService(i);
        if(flag==true){
            out.print("<script language='javascript'>alert('添加成功！');window.location.href='admin/addInstitute.jsp';</script>");
        }else{
            out.print("<script language='javascript'>alert('添加失败！');window.location.href='admin/addInstitute.jsp';</script>");
        }
    }

    private void upMidC(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String tid=req.getParameter("tid");
        String tname=req.getParameter("tname");
        String iid=req.getParameter("iid");
        String iname=req.getParameter("iname");
        String cintro=req.getParameter("cintro");
        req.setAttribute("cid",cid);
        req.setAttribute("cname",cname);
        req.setAttribute("tid",tid);
        req.setAttribute("tname",tname);
        req.setAttribute("iid",iid);
        req.setAttribute("iname",iname);
        req.setAttribute("cintro",cintro);
        req.getRequestDispatcher("/admin/upCourse.jsp").forward(req,resp);
    }

    private void showCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start=0;
        int count=5;
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            logger.debug("没有获取到start值");
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;

        List<course> lc=new ArrayList<>();
        if(suboper==null||"all".equals(type))
        {
            total=(cs.showAllCourseService()).size();
            lc=cs.showAllCourseService(start,count);
        }
        else {
            total=(cs.showSubCourseService(type,var)).size();
            lc = cs.showSubCourseService(type, var,start,count);
        }
        if(lc!=null){
            if(total%count==0)last=total-last;
            else last=total-total%count;
            next=next>last?last:next;
            req.setAttribute("clist",lc);
            req.setAttribute("csize",total);
            req.setAttribute("pre",pre);
            req.setAttribute("next",next);
            req.setAttribute("last",last);
            req.getRequestDispatcher("/admin/courseInfo.jsp").forward(req,resp);
        }
    }

    private void upCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String tid=req.getParameter("tid");
        String tname=req.getParameter("tname");
        String iid=req.getParameter("iid");
        String iname=req.getParameter("iname");
        String cintro=req.getParameter("cintro");
        course c=new course();
        int idc=Integer.parseInt(cid);
        int idt=Integer.parseInt(tid);
        int idi=Integer.parseInt(iid);
        c.setCid(idc);
        c.setCname(cname);
        c.setTid(idt);
        c.setTname(tname);
        c.setIid(idi);
        c.setIname(iname);
        c.setCintro(cintro);
        PrintWriter out=resp.getWriter();
        boolean flag=cs.upCourseService(c);
        if(flag){
            out.print("<script language='javascript'>alert('更新成功！');window.location.href='ms?oper=showCourse';</script>");
        }else {
            out.print("<script language='javascript'>alert('更新失败！');window.location.href='ms?oper=showCourse';</script>");
        }
    }

    private void delCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cid=req.getParameter("cid");
        PrintWriter out=resp.getWriter();
        boolean flag=cs.delCourseService(cid);
        if(flag){
            out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showCourse';</script>");
        }else {
            out.print("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=showCourse';</script>");
        }
    }

    private void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        course c=new course();
        PrintWriter out=resp.getWriter();
        String cname=req.getParameter("cname");
        String tid=req.getParameter("tid");
        String tname=req.getParameter("tname");
        String iid=req.getParameter("iid");
        String iname=req.getParameter("iname");
        String cintro=req.getParameter("cintro");
        int idt=Integer.parseInt(tid);
        int idi=Integer.parseInt(iid);
        c.setCname(cname);
        c.setTid(idt);
        c.setTname(tname);
        c.setIid(idi);
        c.setIname(iname);
        c.setCintro(cintro);
        boolean flag=cs.addCourseService(c);
        if(flag==true){
            out.print("<script language='javascript'>alert('添加成功！');window.location.href='admin/addCourse.jsp';</script>");
        }else{
            out.print("<script language='javascript'>alert('添加失败！');window.location.href='admin/addCourse.jsp';</script>");
        }
    }

    private void upMidT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tid=req.getParameter("tid");
        String tname=req.getParameter("tname");
        String pwd=req.getParameter("pwd");
        String title=req.getParameter("title");
        String intro=req.getParameter("intro");
        req.setAttribute("tid",tid);
        req.setAttribute("tname",tname);
        req.setAttribute("pwd",pwd);
        req.setAttribute("title",title);
        req.setAttribute("intro",intro);
        req.getRequestDispatcher("/admin/upTeacher.jsp").forward(req,resp);

    }

    private void upTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tid=req.getParameter("tid");
        String tname=req.getParameter("tname");
        String pwd=req.getParameter("pwd");
        String title=req.getParameter("title");
        String intro=req.getParameter("intro");
        teacher t=new teacher();
        int id=Integer.parseInt(tid);
        t.setTid(id);
        t.setTname(tname);
        t.setPwd(pwd);
        t.setTitle(title);
        t.setIntro(intro);
        PrintWriter out=resp.getWriter();
        boolean flag=ts.upTeacherService(t);
        if(flag){
            logger.debug("更新编号为"+tid+"的教师成功");
            out.print("<script language='javascript'>alert('更新成功！');window.location.href='ms?oper=showTeacher';</script>");
        }else {
            logger.debug("更新编号为"+tid+"的教师失败");
            out.print("<script language='javascript'>alert('更新失败！');window.location.href='ms?oper=showTeacher';</script>");
        }
    }

    private void delTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String tid=req.getParameter("tid");
        PrintWriter out=resp.getWriter();
        boolean flag=ts.delTeacherService(tid);
        if(flag){
            logger.debug("删除编号为"+tid+"的教师成功");
            out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=showTeacher';</script>");
        }else {
            logger.debug("删除编号为"+tid+"的教师失败");
            out.print("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=showTeacher';</script>");
        }
    }

    private void addTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        teacher t=new teacher();
        PrintWriter out=resp.getWriter();
        String tname= req.getParameter("tname");
        String pwd= req.getParameter("pwd");
        String title= req.getParameter("title");
        String intro= req.getParameter("intro");
        t.setTname(tname);
        t.setPwd(pwd);
        t.setTitle(title);
        t.setIntro(intro);
        boolean flag=ts.addTeacherService(t);
        if(flag==true){
            logger.debug("成功添加新教师"+t);
            out.print("<script language='javascript'>alert('添加成功！');window.location.href='admin/addTeacher.jsp';</script>");
        }else{
            logger.debug("添加新教师失败");
            out.print("<script language='javascript'>alert('添加失败！');window.location.href='admin/addTeacher.jsp';</script>");
        }
    }

    private void showTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start=0;
        int count=5;
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            logger.debug("没有获取到start值");
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;

        List<teacher> lt=new ArrayList<>();
        if(suboper==null||"all".equals(type))
        {
            logger.debug("查询全部老师  start:"+start+" count:"+count);
            total=(ts.allTeacherService()).size();
            lt=ts.showTeacherService(start,count);
            logger.debug("显示所有教师："+lt);

        }
        else {
            logger.debug("查询部分老师  start:"+start+" count:"+count);
            total=(ts.allSubTeacherService(type,var)).size();
            lt = ts.subshowTeacherService(type, var,start,count);
            logger.debug("根据 "+type+"："+var+" 查询教师信息："+lt);
        }
        if(lt!=null){
            if(total%count==0)last=total-last;
            else last=total-total%count;
            next=next>last?last:next;
            req.setAttribute("tlist",lt);
            req.setAttribute("tsize",total);
            req.setAttribute("pre",pre);
            req.setAttribute("next",next);
            req.setAttribute("last",last);
            req.getRequestDispatcher("/admin/teacherInfo.jsp").forward(req,resp);
        }
    }

    private void adminOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs=req.getSession();
        hs.invalidate();
        resp.sendRedirect("/Q_A_war_exploded/index.jsp");
    }

    private void checkAdminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession hs=req.getSession();
        admin tp= (admin) hs.getAttribute("admin");
        if(tp!=null){
            resp.sendRedirect("/Q_A_war_exploded/admin/index.jsp");
            return ;
        }
        String aname=req.getParameter("name");
        String pwd=req.getParameter("pwd");
        adminService as=new adminServiceImpl();
        admin a=as.checkAdminLoginService(aname,pwd);

        if(a!=null){
            hs.setAttribute("admin",a);
            resp.sendRedirect("/Q_A_war_exploded/admin/main.jsp");
            return ;
        }
        else{
            req.setAttribute("lf","false");
            hs.setAttribute("status",null);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            return ;
        }
    }
}
