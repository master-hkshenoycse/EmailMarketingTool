package com.example.emailmarketing;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Employee", value = "/Employee")
public class Employee extends HttpServlet {

    private static final long serialVersionUID=1L;

    public Employee(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();

        out.println("<!DOCTYPE html> <html lang='en'>");
        out.println("<head> <meta charset='UTF-8'> <title>Email Marketing Tool</title> <link rel='stylesheet' type='text/css' href='css/style.css'> </head>");
        out.println("<body> <div id='head'> <h1> Email Marketing Tool </h1> </div>");
        out.println("<nav id='navi'><span class='m1'> Home </span> <span class='m2'> <a href='email.html'>Send Emails </a>  </span> </nav>");

        HttpSession res=request.getSession();
        String user=(String)res.getAttribute("username");
        out.println("<div id='main-page'> Hello "+user+"</div>");
        out.println("</body> </html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
