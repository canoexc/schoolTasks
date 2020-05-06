package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "mainServlet",urlPatterns = "/ms")
public class mainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        String status=req.getParameter("status");
        String oper=req.getParameter("oper");
        if("signup".equals(oper)){
            req.getRequestDispatcher("/ss").forward(req,resp);
        }
        HttpSession hs=req.getSession();
        if(hs.getAttribute("status")==null)
        hs.setAttribute("status",status);
        else status=(String)hs.getAttribute("status");
        if("admin".equals(status)){
            req.getRequestDispatcher("/as").forward(req,resp);
        }
        else if("teacher".equals(status)){
            req.getRequestDispatcher("/ts").forward(req,resp);
        }
        else if("student".equals(status)){
            req.getRequestDispatcher("/ss").forward(req,resp);
        }

    }
}
