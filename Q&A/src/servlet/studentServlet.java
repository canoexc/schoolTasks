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

@WebServlet(name = "studentServlet",urlPatterns = "/ss")
public class studentServlet extends HttpServlet {
    Logger logger=Logger.getLogger(studentServlet.class);
    studentService ss=new studentServiceImpl();
    whitelistService ws=new whitelistServiceImpl();
    applyService as=new applyServiceImpl();
    messageService ms=new messageServiceImpl();
    replyService rs=new replyServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        String oper=req.getParameter("oper");
        if("login".equals(oper)){
            checkStudentLogin(req,resp);
        }else if("signup".equals(oper)){
            signup(req,resp);
        }else if("pwdChange".equals(oper)){
            pwdChange(req,resp);
        }else if("out".equals(oper)){
            studentOut(req,resp);
        }else if("courseInfo".equals(oper)){
            courseInfo(req,resp);
        }else if("apply".equals(oper)){
            applyCourse(req,resp);
        }else if("applying".equals(oper)){
            applying(req,resp);
        }else if("appNews".equals(oper)){
            appNews(req,resp);
        }else if("askInfo".equals(oper)){
            askInfo(req,resp);
        }else if("addMessage".equals(oper)){
            addMessage(req,resp);
        }else if("showAsk".equals(oper)){
            showAsk(req,resp);
        }else if("askCheck".equals(oper)){
            askCheck(req,resp);
        }else if("ansCheck".equals(oper)){
            ansCheck(req,resp);
        }else if("showAns".equals(oper)){
            showAns(req,resp);
        }else if("readed".equals(oper)){
            readed(req,resp);
        }else if("upAsk".equals(oper)){
            upAsk(req,resp);
        }else if("delAsk".equals(oper)){
            delAsk(req,resp);
        }
    }

    private void delAsk(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        PrintWriter out = resp.getWriter();
        boolean flag = ms.delMessageService(mid);
        if (flag)
                out.print("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=askCheck';</script>");
            else
                out.println("<script language='javascript'>alert('删除成功！');window.location.href='ms?oper=askCheck';</script>");

    }

    private void upAsk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String mid=req.getParameter("mid");
        String header=req.getParameter("header");
        String question=req.getParameter("question");
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String suboper=req.getParameter("suboper");
        req.setAttribute("mid",mid);
        req.setAttribute("cid",cid);
        req.setAttribute("header",header);
        req.setAttribute("question",question);
        req.setAttribute("cname",cname);
        if("up".equals(suboper)){
            message m=new message();
            m.setHeader(header);
            m.setMid(Integer.parseInt(mid));
            m.setQuestion(question);
            boolean flag=ms.upMessageService(m);
            if(flag)
                out.println("<script language='javascript'>alert('更新成功！');window.location.href='ms?oper=askCheck';</script>");
            else out.println("<script language='javascript'>alert('更新失败！');window.location.href='ms?oper=askCheck';</script>");
            return ;
        }
        req.getRequestDispatcher("/student/upAsk.jsp").forward(req,resp);
    }

    private void readed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid=req.getParameter("rid");
        rs.readed(rid);
        req.getRequestDispatcher("ms?oper=ansCheck&type=new").forward(req,resp);
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
        if(l.size()>0)
        {
            u=l.get(0);
        }
        else {

        }
        req.setAttribute("rlist", l);
        req.setAttribute("rsize", total);
        req.setAttribute("l",u);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/student/showAns.jsp").forward(req,resp);
    }

    private void ansCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs=req.getSession();
        String sid=Integer.toString(((student)hs.getAttribute("student")).getSid());
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
        l=rs.showReplyService(type,sid,start,count);
        total=rs.showReplyService(type,sid).size();
        if("new".equals(type))hs.setAttribute("rsize",total);
        req.setAttribute("rlist", l);
        req.setAttribute("rsize", total);
        req.setAttribute("ui",type);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/student/ansCheck.jsp").forward(req,resp);
    }

    private void askCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs=req.getSession();
        String sid=Integer.toString(((student)hs.getAttribute("student")).getSid());
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
        l=ms.showMesService("sid",sid,null,start,count);
        total=ms.showMesService("sid",sid,null).size();
        req.setAttribute("mlist", l);
        req.setAttribute("msize", total);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/student/askCheck.jsp").forward(req,resp);
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
        req.getRequestDispatcher("/student/showAsk.jsp").forward(req,resp);
    }

    private void addMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        message m=new message();
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String sid=req.getParameter("sid");
        String sname=req.getParameter("sname");
        String header=req.getParameter("header");
        String question=req.getParameter("question");
        m.setCid(Integer.parseInt(cid));
        m.setSid(Integer.parseInt(sid));
        m.setSname(sname);
        m.setHeader(header);
        m.setQuestion(question);
        boolean flag=ms.addMessageService(m);
        if(flag)out.print("<script language='javascript'>alert('留言成功！请等待回复');window.location.href='ms?oper=askInfo&cid="+cid+"&cname="+cname+"&type=cid&var="+cid+"';</script>");
        else out.print("<script language='javascript'>alert('留言失败！');window.location.href='ms?oper=askInfo&cid="+cid+"&cname="+cname+"&type=cid&var="+cid+"';</script>");
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
        req.getRequestDispatcher("/student/messageInfo.jsp").forward(req,resp);
    }

    private void appNews(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<course> l=new ArrayList<>();
        HttpSession hs=req.getSession();
        PrintWriter out=resp.getWriter();
        student s= (student) hs.getAttribute("student");
        String suboper=req.getParameter("suboper");
        if("know".equals(suboper)){
            String pid=req.getParameter("pid");
            String status=req.getParameter("status");
            String type="";
            if("1".equals(status)) {
                type = "3";
            }
            else type="4";
            boolean flag=as.changeStatusService(type,pid);
            if(flag)
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
            l = as.showRappService(Integer.toString(s.getSid()), suboper, start, count);
            total = as.showRappService(Integer.toString(s.getSid()), suboper).size();
            if ("new".equals(suboper)) {
                req.setAttribute("ui", "new");
                hs.setAttribute("narsize",total);
            }
            else req.setAttribute("ui", "all");
            if (total % count == 0) last = total - last;
            else last = total - total % count;
            req.setAttribute("clist", l);
            req.setAttribute("csize", total);
            req.setAttribute("pre", pre);
            req.setAttribute("next", next);
            req.setAttribute("last", last);
            req.getRequestDispatcher("/student/applyCheck.jsp").forward(req, resp);
        }
    }

    private void applying(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid=req.getParameter("cid");
        String cname=req.getParameter("cname");
        String tid=req.getParameter("tid");
        String tname=req.getParameter("tname");
        String ps=req.getParameter("ps");
        req.setAttribute("cid",cid);
        req.setAttribute("cname",cname);
        req.setAttribute("tid",tid);
        req.setAttribute("tname",tname);
        req.setAttribute("ps",ps);
        req.getRequestDispatcher("/student/apply.jsp").forward(req,resp);
    }

    private void applyCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        String cid=req.getParameter("cid");
        String sid=req.getParameter("sid");
        String tid=req.getParameter("tid");
        String ps=req.getParameter("ps");
        apply a=new apply();
        a.setCid(Integer.parseInt(cid));
        a.setSid(Integer.parseInt(sid));
        a.setTid(Integer.parseInt(tid));
        a.setPs(ps);
        boolean flag=as.addApplyService(a);
        if(flag)out.print("<script language='javascript'>alert('发送申请成功！请等待审核');window.location.href='ms?oper=courseInfo&suboper=other&type=all&ui=other';</script>");
        else out.print("<script language='javascript'>alert('发送申请失败！请重试');window.location.href='ms?oper=courseInfo&suboper=other&type=all&ui=other';</script>");
    }

    private void courseInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<course> l=new ArrayList<>();
        String suboper=req.getParameter("suboper");
        String type=req.getParameter("type");
        String var=req.getParameter("var");
        HttpSession hs=req.getSession();
        student s= (student) hs.getAttribute("student");
        String sid=Integer.toString(s.getSid());
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
        if("all".equals(type)) {
            if ("mine".equals(suboper)) {
                l = ws.findCListService(sid, start, count);
                total = ws.findCListService(sid).size();
                req.setAttribute("ui","mine");

            } else {
                l = ws.addCListService(sid, start, count);
                total = ws.addCListService(sid).size();
                req.setAttribute("ui","other");

            }
        }else{
            if("mine".equals(suboper)){
                l=ws.findCListService(sid, start, count,type,var);
                total=ws.findCListService(sid,type,var).size();
                req.setAttribute("ui","mine");
            }else {
                l=ws.addCListService(sid,start,count,type,var);
                total=ws.addCListService(sid,type,var).size();
                req.setAttribute("ui","other");
            }
        }
        if(total%count==0)last=total-last;
        else last=total-total%count;
        req.setAttribute("clist", l);
        req.setAttribute("type",type);
        req.setAttribute("csize", total);
        req.setAttribute("pre", pre);
        req.setAttribute("next", next);
        req.setAttribute("last", last);
        req.getRequestDispatcher("/student/courseInfo.jsp").forward(req,resp);
    }

    private void studentOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs=req.getSession();
        hs.invalidate();
        resp.sendRedirect("/Q_A_war_exploded/index.jsp");
    }

    private void pwdChange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession hs=req.getSession();
        String sid=Integer.toString(((student)hs.getAttribute("student")).getSid());
        String pwd=req.getParameter("pwd");
        PrintWriter out=resp.getWriter();
        boolean flag=ss.pwdChange(sid,pwd);
        if(flag)
        {
            logger.debug(sid+"密码修改成功");
            student tp= (student) hs.getAttribute("student");
            tp.setPwd(pwd);
            hs.setAttribute("student",tp);
            out.print("<script language='javascript'>alert('修改成功！请重新登录');window.top.location.href='ms?oper=out';</script>");
        }
        else{
            logger.debug(sid+"密码修改失败");
            out.print("<script language='javascript'>alert('修改失败！');window.location.href='student/pwdChange.jsp';</script>");
        }
    }

    private void signup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sname=req.getParameter("sname");
        String pwd=req.getParameter("pwd");
        int sid=ss.addStudentService(sname,pwd);
        req.setAttribute("sid",sid);
        req.getRequestDispatcher("/student/signupR.jsp").forward(req,resp);
    }

    private void checkStudentLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession hs=req.getSession();
        student st= (student) hs.getAttribute("student");
        if(st!=null){
            hs.setAttribute("narsize",as.showRappService(Integer.toString(st.getSid()), "new").size());
            hs.setAttribute("csize",ws.findCListService(Integer.toString(st.getSid())).size());
            hs.setAttribute("rsize",rs.showReplyService("new",Integer.toString(st.getSid())).size());
            resp.sendRedirect("/Q_A_war_exploded/student/index.jsp");
            return ;
        }
        String sid=req.getParameter("name");
        String pwd=req.getParameter("pwd");
        student s=ss.checkStudentLoginService(sid,pwd);

        //System.out.println(t==null);
        if(s!=null){
            hs.setAttribute("narsize",as.showRappService(Integer.toString(s.getSid()), "new").size());
            hs.setAttribute("csize",ws.findCListService(sid).size());
            hs.setAttribute("rsize",rs.showReplyService("new",Integer.toString(s.getSid())).size());
            hs.setAttribute("student",s);
            resp.sendRedirect("/Q_A_war_exploded/student/main.jsp");
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
