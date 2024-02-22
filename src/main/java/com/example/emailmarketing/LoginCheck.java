package com.example.emailmarketing;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginCheck", value = "/LoginCheck")
public class LoginCheck extends HttpServlet {

    private static final long serialVersionUID=1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user=request.getParameter("n1");
        String pass=request.getParameter("p1");
        PrintWriter out=response.getWriter();
        if(user.equals("admin") && pass.equals("admin@123")){
            response.sendRedirect("admin.html");
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/email?useSSL=false","hari","hari");
            Statement stm=con.createStatement();
            ResultSet rst=stm.executeQuery("select login,password,empname from emprecord");

            while(rst.next()){
                if(user.equals(rst.getString(1)) && pass.equals(rst.getString(2))){
                    String name=rst.getString(3);
                    //Cookie session, url session, http session
                    HttpSession res=request.getSession();
                    res.setAttribute("username",name);
                    response.sendRedirect("Employee");

                }else{
                    out.println("Login Invalid");
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
