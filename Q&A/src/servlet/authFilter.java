package servlet;

import pojo.admin;
import pojo.student;
import pojo.teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authFilter",urlPatterns="/*")
public class authFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        request.setCharacterEncoding("UTF-8");
        String url=request.getRequestURL().toString();
        //System.out.println(url);
        //如果访问的资源是以css或者js结尾的，那么就不需要判断是否登录
        if (uri.endsWith(".css") || uri.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }
        if (uri.endsWith("index.jsp")||uri.endsWith("ms")||uri.endsWith("student/signup.jsp")||uri.endsWith("student/signupR.jsp")||uri.endsWith("ss")) {
            chain.doFilter(request, response);
            return;
        }
        admin a=(admin)request.getSession().getAttribute("admin");
        teacher t=(teacher)request.getSession().getAttribute("teacher");
        student s=(student)request.getSession().getAttribute("student");
        if (a==null&&t==null&&s==null) {
            //System.out.println("没人登录啊");
            response.sendRedirect("/Q_A_war_exploded/index.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
