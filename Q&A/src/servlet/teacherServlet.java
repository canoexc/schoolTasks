package servlet;

import org.apache.log4j.Logger;
import pojo.*;
import service.*;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "teacherServlet",urlPatterns = "/ts")
public class teacherServlet extends HttpServlet {
    Logger logger=Logger.getLogger(teacherServlet.class);
    teacherService ts=new teacherServiceImpl();
    courseService cs=new courseServiceImpl();
    whitelistService ws=new whitelistServiceImpl();
    applyService as=new applyServiceImpl();
    messageService ms=new messageServiceImpl();
    replyService rs=new replyServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        String oper=req.getParameter("oper");
        if("login".equals(oper)){
            checkTeacherLogin(req,resp);
        }else if ("out".equals(oper)){
            teacherOut(req,resp);
        }else if("personalInfo".equals(oper)){
            personalInfo(req,resp);
        }else if("pwdChange".equals(oper)){
            pwdChange(req,resp);
        }else if("courseInfo".equals(oper)){
            courseInfo(req,resp);
        }else if("limitSet".equals(oper)){
            limitSet(req,resp);
        }else if("appNews".equals(oper)){
            appNews(req,resp);
        }else if("askInfo".equals(oper)){
            askInfo(req,resp);
        }else if("addReply".equals(oper)){
            addReply(req,resp);
        }else if("showAns".equals(oper)){
            showAns(req,resp);
        }else if("ansCheck".equals(oper)){
            ansCheck(req,resp);
        }else if("askCheck".equals(oper)){
            askCheck(req,resp);
        }else if("showAsk".equals(oper)){
            showAsk(req,resp);
        }else if("delAsk".equals(oper)){
            delAsk(req,resp);
        }else if("delAns".equals(oper)){
            delAns(req,resp);
        }else if("upAns".equals(oper)){
            upAns(req,resp);
        }
    }

    private void upAns(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String rid=req.getParameter("rid");
        String mid=req.getParameter("mid");
        String answer=req.getParameter("answer");
        String header=req.getParameter("header");
        String question=req.getParameter("question");
        String cname=req.getParameter("cname");
        String suboper=req.getParameter("suboper");
        req.setAttribute("rid",rid);
        req.setAttribute("mid",mid);
        req.setAttribute("answer",answer);
        req.setAttribute("header",header);
        req.setAttribute("question",question);
        req.setAttribute("cname",cname);
        if(suboper!=null){
            reply r=new reply();
            r.setRid(Integer.parseInt(rid));
            r.setMid(Integer.parseInt(mid));
            r.setAnswer(answer);
            boolean flag=rs.upReplyService(r);
            if(flag)
            out.println("<script language='javascript'>alert('更新成功！');window.location.href='ms?oper=ansCheck';</script>");
            else out.println("<script language='javascript'>alert('更新失败！');window.location.href='ms?oper=ansCheck';</script>");
            return ;
        }
        req.getRequestDispatcher("/teacher/upReply.jsp").forward(req,resp);
    }

    private void delAns(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid=req.getParameter("mid");
        String rid=req.getParameter("rid");
        String cid=req.getParameter("cid");
        PrintWriter out=resp.getWriter();
        boolean flag=rs.delReplyService(rid,mid);
        if (flag)
            out.println("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=ansCheck';</script>");
        else out.println("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=ankCheck';</script>");

    }

    private void delAsk(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        String cid = req.getParameter("cid");
        String cname = req.getParameter("cname");
        PrintWriter out = resp.getWriter();
        boolean flag = ms.delMessageService(mid);
        if (flag) {
            if (cid != null)
                out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=askInfo&cid=" + cid + "&cname=" + cname + "&type=" + "cid" + "&var=" + cid + "';</script>");
            else
                out.println("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=askCheck&type=new';</script>");
        } else {
            if(cid!=null)
            out.print("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=askInfo&cid=" + cid + "&cname=" + cname + "&type=" + "cid" + "&var=" + cid + "';</script>");
            else out.println("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=askCheck&type=new';</script>");
        }
    }
    private void showAsk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid=req.getParameter("cid");
        String mid=req.getParameter("mid");
        String cname=req.getParameter("cname");
        String sid=req.getParameter("sid");
        String sname=req.getParameter("sname");
        String header=req.getParameter("header");
        String question=req.getParameter("question");
        String date=req.getParameter("date");
        String status=req.getParameter("status");
        req.setAttribute("cid",cid);
        req.setAttribute("mid",mid);
        req.setAttribute("cname",cname);
        req.setAttribute("sid",sid);
        req.setAttribute("sname",sname);
        req.setAttribute("header",header);
        req.setAttribute("question",question);
        req.setAttribute("status",status);
        req.setAttribute("date",date);
        req.getRequestDispatcher("/teacher/howAsk.jsp").forward(req,resp);
    }

    private void askCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs=req.getSession();
        String tid=Integer.toString(((teacher)hs.getAttribute("teacher")).getTid());
        String type=req.getParameter("type");
        List<uni> l=new ArrayList<>();
        int start = 0;
        int count = 5;
        try {
            start = Integer.parseInt(req.getParameter("start"));
        } catch (NumberFormatException e) {
            start = 0;
        }
        int next = start + count;
        int pre = start - count;
        int total = 0;
        int last = 0;
        pre = pre < 0 ? 0 : pre;
        if(total%count==0)last=total-last;
        else last=total-total%count;

        l=ms.showMesService(type,tid,null,start,count);
        total=ms.showMesService(type,tid,null).size();
        if("new".equals(type))hs.setAttribute("msize",total);
        req.setAttribute("mlist", l);
        req.setAttribute("msize", total);
        req.setAttribute("ui",type);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/teacher/askCheck.jsp").forward(req,resp);
    }

    private void ansCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs=req.getSession();
        String tid=Integer.toString(((teacher)hs.getAttribute("teacher")).getTid());
        List<uni> l=new ArrayList<>();
        int start = 0;
        int count = 5;
        try {
            start = Integer.parseInt(req.getParameter("start"));
        } catch (NumberFormatException e) {
            start = 0;
        }
        int next = start + count;
        int pre = start - count;
        int total = 0;
        int last = 0;
        pre = pre < 0 ? 0 : pre;
        if(total%count==0)last=total-last;
        else last=total-total%count;
        l=rs.showReplyService("tid",tid,start,count);
        total=rs.showReplyService("tid",tid).size();
        req.setAttribute("rlist", l);
        req.setAttribute("rsize", total);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/teacher/ansCheck.jsp").forward(req,resp);
    }

    private void showAns(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid=req.getParameter("mid");
        String type=req.getParameter("type");
        String var1=req.getParameter("var1");
        req.setAttribute("type",type);
        req.setAttribute("mid",mid);
        req.setAttribute("var1",var1);
        List<uni> l=new ArrayList<>();
        int start = 0;
        int count = 5;
        try {
            start = Integer.parseInt(req.getParameter("start"));
        } catch (NumberFormatException e) {
            start = 0;
        }
        int next = start + count;
        int pre = start - count;
        int total = 0;
        int last = 0;
        pre = pre < 0 ? 0 : pre;
        if(total%count==0)last=total-last;
        else last=total-total%count;
        if("show".equals(type)) {
            l = rs.showReplyService(type, mid, start, count);
            total = rs.showReplyService(type, mid).size();
        }else{
            type=mid;
            l=rs.showReplyService(type,var1,start,count);
            total=rs.showReplyService(type,var1).size();
        }
        uni u=new uni();
        if(l!=null)
            u=l.get(0);
        req.setAttribute("rlist", l);
        req.setAttribute("rsize", total);
        req.setAttribute("l",u);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/teacher/howAns.jsp").forward(req,resp);
    }

    private void addReply(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession hs=req.getSession();
        PrintWriter out=resp.getWriter();
        teacher t=new teacher();
        t= (teacher) hs.getAttribute("teacher");
        String suboper=req.getParameter("suboper");
        String mid=req.getParameter("mid");
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String sid=req.getParameter("sid");
        String sname=req.getParameter("sname");
        String header=req.getParameter("header");
        String question=req.getParameter("question");
        String date=req.getParameter("date");
        String sub=req.getParameter("sub");
        req.setAttribute("mid",mid);
        req.setAttribute("cid",cid);
        req.setAttribute("cname",cname);
        req.setAttribute("sid",sid);
        req.setAttribute("sname",sname);
        req.setAttribute("header",header);
        req.setAttribute("question",question);
        req.setAttribute("date",date);
        req.setAttribute("sub",sub);
        reply r=new reply();
        if(suboper!=null) {
            String ans = req.getParameter("answer");
            r.setMid(Integer.parseInt(mid));
            r.setAnswer(ans);
            r.setTid(t.getTid());
            r.setTname(t.getTname());
            boolean flag = rs.addReplyService(r);
            if (sub.equals("cid"))
                out.print("<script language='javascript'>alert('回复成功！');window.location.href='ms?oper=askInfo&cid=" + cid + "&cname=" + cname + "&type=cid&var=" + cid + "';</script>");
            else
                out.print("<script language='javascript'>alert('回复成功！');window.location.href='ms?oper=askCheck&type=new';</script>");
            return ;
        }
        req.getRequestDispatcher("/teacher/addReply.jsp").forward(req,resp);
    }

    private void askInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        String var1=req.getParameter("var1");
        List<uni> l=new ArrayList<>();
        int start = 0;
        int count = 5;
        try {
            start = Integer.parseInt(req.getParameter("start"));
        } catch (NumberFormatException e) {
            start = 0;
        }
        int next = start + count;
        int pre = start - count;
        int total = 0;
        int last = 0;
        pre = pre < 0 ? 0 : pre;
        System.out.println(var);
        l=ms.showMesService(type,var,var1,start,count);
        total=ms.showMesService(type,var,var1).size();
        if(total%count==0)last=total-last;
        else last=total-total%count;
        req.setAttribute("mlist", l);
        req.setAttribute("msize", total);
        req.setAttribute("cid",var);
        req.setAttribute("cname",cname);
        req.setAttribute("type",type);
        req.setAttribute("var",var);
        req.setAttribute("var1",var1);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/teacher/messageInfo.jsp").forward(req,resp);
    }

    private void personalInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<teacher> l=new ArrayList<>();
        HttpSession hs=req.getSession();
        teacher t= (teacher) hs.getAttribute("teacher");
        String tid=Integer.toString(t.getTid());
        l=ts.allSubTeacherService("tid",tid);
        req.setAttribute("tlist",l);
        req.getRequestDispatcher("/teacher/personalInfo.jsp").forward(req,resp);
    }

    private void pwdChange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs=req.getSession();
        String tid=Integer.toString(((teacher)hs.getAttribute("teacher")).getTid());
        String pwd=req.getParameter("pwd");
        PrintWriter out=resp.getWriter();
        boolean flag=ts.pwdChange(tid,pwd);
        if(flag)
        {
            logger.debug(tid+"密码修改成功");
            teacher tp= (teacher) hs.getAttribute("teacher");
            tp.setPwd(pwd);
            hs.setAttribute("teacher",tp);
            out.print("<script language='javascript'>alert('修改成功！请重新登录');window.top.location.href='ms?oper=out';</script>");
        }
        else{
            logger.debug(tid+"密码修改失败");
            out.print("<script language='javascript'>alert('修改失败！');window.location.href='teacher/pwdChange.jsp';</script>");
        }
    }

    private void courseInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<course> lc=new ArrayList<>();
        HttpSession hs=req.getSession();
        teacher tp= (teacher) hs.getAttribute("teacher");
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        String tid=Integer.toString(tp.getTid());
        int start=0;
        int count=5;
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;
        if(suboper==null||"all".equals(type)||"allc".equals(suboper))
        {
            lc=cs.showSubCourseService("tid",tid,start,count);
            total=(cs.showSubCourseService("tid",tid)).size();
        }else {
            lc=cs.showSubCourseService(type,var,start,count,tp.getTid());
            total=(cs.showSubCourseService(type,var,tp.getTid())).size();
        }
        if(lc!=null) {
            if(total%count==0)last=total-last;
            else last=total-total%count;
            req.setAttribute("clist", lc);
            req.setAttribute("csize", total);
            req.setAttribute("pre", pre);
            req.setAttribute("next", next);
            req.setAttribute("last", last);
            if("allc".equals(suboper)||"subc".equals(suboper)) {
                req.setAttribute("ui","allc");
                req.getRequestDispatcher("/teacher/limitSet.jsp").forward(req, resp);
            }
            else
            req.getRequestDispatcher("/teacher/courseInfo.jsp").forward(req, resp);
        }
    }

    private void limitSet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid=req.getParameter("cid");
        String sid=req.getParameter("sid");
        PrintWriter out=resp.getWriter();
        String suboper=req.getParameter("suboper");
        int start=0;
        int count=5;
        try{
            start=Integer.parseInt(req.getParameter("start"));
        }catch (NumberFormatException e){
            start=0;
        }
        int next=start+count;
        int pre=start-count;
        int total=0;
        int last=0;
        pre=pre<0?0:pre;
        if("fsl".equals(suboper)){
            List<student> l=new ArrayList<>();
            l=ws.findSListService(cid,start,count);
            total=ws.findSListService(cid).size();
            if(total%count==0)last=total-last;
            else last=total-total%count;
            req.setAttribute("slist", l);
            req.setAttribute("ssize", total);
            req.setAttribute("pre", pre);
            req.setAttribute("cid",cid);
            req.setAttribute("next", next);
            req.setAttribute("last", last);
            req.setAttribute("ui","fsl");
            req.getRequestDispatcher("/teacher/limitSet.jsp").forward(req,resp);
        }else if("awsl".equals(suboper)){
            List<student> l=new ArrayList<>();
            l=ws.addSListService(cid,start,count);
            total=ws.addSListService(cid).size();
            if(total%count==0)last=total-last;
            else last=total-total%count;
            req.setAttribute("slist", l);
            req.setAttribute("ssize", total);
            req.setAttribute("pre", pre);
            req.setAttribute("cid",cid);
            req.setAttribute("next", next);
            req.setAttribute("last", last);
            req.setAttribute("ui","other");
            req.getRequestDispatcher("/teacher/limitSet.jsp").forward(req,resp);
        }
        else if("asl".equals(suboper)){
            boolean flag=ws.addListService(sid,cid);
            if(flag){
                out.print("<script language='javascript'>alert('添加成功！');window.location.href='ms?oper=limitSet&suboper=awsl&cid="+cid+"';</script>");
            }
            else out.print("<script language='javascript'>alert('添加失败！');window.location.href='ms?oper=limitSet&suboper=awsl&cid="+cid+"';</script>");
        }else if("dsl".equals(suboper)){
            boolean flag=ws.delListService(sid,cid);
            if(flag){
                out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=limitSet&suboper=fsl&cid="+cid+"';</script>");
            }
            else out.print("<script language='javascript'>alert('删除失败！');window.location.href='ms?oper=limitSet&suboper=fsl&cid="+cid+"';</script>");
        }
    }

    private void appNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<stuandcos> l=new ArrayList<>();
        HttpSession hs=req.getSession();
        PrintWriter out=resp.getWriter();
        teacher t= (teacher) hs.getAttribute("teacher");
        String suboper=req.getParameter("suboper");
        if("allow".equals(suboper)||"refuse".equals(suboper)){
            String pid=req.getParameter("pid");
            String type="";
            boolean ok1=true;
            if("allow".equals(suboper)) {
                type = "1";
                String sid=req.getParameter("sid");
                String cid=req.getParameter("cid");
                ok1=ws.addListService(sid,cid);
            }
            else type="2";
            boolean ok2=as.changeStatusService(type,pid);
            if(ok1&&ok2)
            out.print("<script language='javascript'>alert('操作成功！');window.location.href='ms?oper=appNews&suboper=new';</script>");
        }else {
            int start = 0;
            int count = 5;
            try {
                start = Integer.parseInt(req.getParameter("start"));
            } catch (NumberFormatException e) {
                start = 0;
            }
            int next = start + count;
            int pre = start - count;
            int total = 0;
            int last = 0;
            pre = pre < 0 ? 0 : pre;
            l = as.showApplyService(Integer.toString(t.getTid()), suboper, start, count);
            total = as.showApplyService(Integer.toString(t.getTid()), suboper).size();
            if ("new".equals(suboper)) {
                hs.setAttribute("nasize",total);
                req.setAttribute("ui", "new");
            }
            else req.setAttribute("ui", "all");
            if (total % count == 0) last = total - last;
            else last = total - total % count;
            req.setAttribute("clist", l);
            req.setAttribute("csize", total);
            req.setAttribute("pre", pre);
            req.setAttribute("next", next);
            req.setAttribute("last", last);
            req.getRequestDispatcher("/teacher/applyCheck.jsp").forward(req, resp);
        }
    }

    private void teacherOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs=req.getSession();
        hs.invalidate();
        resp.sendRedirect("/Q_A_war_exploded/index.jsp");
    }

    private void checkTeacherLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession hs=req.getSession();
        teacher tp= (teacher) hs.getAttribute("teacher");
        if(tp!=null){
            hs.setAttribute("nasize",as.showApplyService(Integer.toString(tp.getTid()), "new").size());
            hs.setAttribute("msize",ms.showMesService("new",Integer.toString(tp.getTid()),"").size());
            resp.sendRedirect("/Q_A_war_exploded/teacher/index.jsp");
            return ;
        }
        teacher t=new teacher();
        String tname=req.getParameter("name");
        String pwd=req.getParameter("pwd");
        teacherService ts=new teacherServiceImpl();
        t=ts.checkTeacherLoginService(tname,pwd);
        //System.out.println(t==null);
        if(t!=null){
            hs.setAttribute("teacher",t);
            hs.setAttribute("nasize",as.showApplyService(Integer.toString(t.getTid()), "new").size());
            hs.setAttribute("msize",ms.showMesService("new",Integer.toString(t.getTid()),"").size());
            resp.sendRedirect("/Q_A_war_exploded/teacher/main.jsp");
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
