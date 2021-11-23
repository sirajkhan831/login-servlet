package com.bridgelabz.login_webapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");
        String pass = req.getParameter("pass");

        PrintWriter out = res.getWriter();
        if (id.equals("Siraj") && pass.equals("password")) {
            out.println("<h1>");
            out.println("Login Successful");
            out.println("</h1>");
        }
        else{
            out.println("<h1>");
            out.println("Login Failed");
            out.println("</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
