package jetty.sample.apps;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/request/response")
public class RequestResponseApp extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        out.println(String.format("{\"id\": %s, \"pass\": \"%s\"}", id, pass));
    }
}
