package com.example.emailmarketing;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "EmailSender", value = "/EmailSender")
public class EmailSender extends HttpServlet {

    public EmailSender(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
        String rec=request.getParameter("eid");
        String sub=request.getParameter("sub");
        String con= request.getParameter("body");

        String sen="dummy@gmail.com";
        String pas="checkandvalidate";
        String host="localhost";

        Properties prop=new Properties();

        prop.setProperty("mail.transport.protocol","smtp");
        prop.setProperty("mail.host","smtp.gmail.com");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.port",465);
        prop.put("mail.debug","true");
        prop.put("mail.smtp.socketFactory.port",465);
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback","false");


        Session ses= Session.getDefaultInstance(prop,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(sen,pas);
            }
        });


        try{
            MimeMessage message=new MimeMessage(ses);
            message.setFrom(new InternetAddress(sen));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(rec));
            message.setSubject(sub);
            message.setText(con);

            Transport.send(message);
            out.println("Email Sent sucessfully");
            response.setContentType("text/html");
            out.println("<html><body><a href='email.html'> Go Back </a> <body> </html>");



        }catch(Exception e){

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
