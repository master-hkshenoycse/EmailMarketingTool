package com.example.emailmarketing;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/AddEmployees")
public class AddEmployees extends HttpServlet {

    private static final long serialVersionUID=1L;

    public AddEmployees() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();


        int eid=Integer.parseInt(request.getParameter("e1"));
        String enm=request.getParameter("e2");
        String elg=request.getParameter("e3");
        String eps=request.getParameter("e4");

        System.out.println(eid);
        System.out.println(enm);
        System.out.println(elg);
        System.out.println(eps);


        try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/email?useSSL=false","hari","hari");
           PreparedStatement pstm=con.prepareStatement("insert into emprecord values(?,?,?,?)");
           pstm.setInt(1,eid);
           pstm.setString(2,enm);
           pstm.setString(3,elg);
           pstm.setString(4,eps);
           pstm.execute();
           out.println("Data Added Sucessfully");
           Thread.sleep(2000);
           response.sendRedirect("index.html");


       }catch(Exception e){
            System.out.println(e.getMessage());
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
