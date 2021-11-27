package com.bridgelabz.login_webapp;

import com.google.common.hash.Hashing;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Purpose : Checks if the given id, password combinations are correct
     *
     * @param req : Http request
     * @param res : Http response
     * @throws IOException : IO Exception
     */
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");
        String pass = req.getParameter("pass");
        PrintWriter out = res.getWriter();
        try {
            String sha256hex = Hashing.sha256()
                    .hashString(pass, StandardCharsets.UTF_8)
                    .toString();
            Class.forName("com.mysql.jdbc.Driver");
            String password = verify(id);
            if (password.equals(sha256hex)) {
                out.println("<h1>");
                out.println("Login Successful");
                out.println("</h1>");
            } else {
                res.sendRedirect("/login_webapp_war_exploded/InvalidPassword.html");
            }
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<h1>");
            out.println(e.toString());
            out.println("</h1>");
            e.printStackTrace();
        }
    }

    public String verify(String id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "password");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM credentials WHERE ID = ?");
        statement.setString(1, id);
        ResultSet set = statement.executeQuery();
        set.next();
        return set.getString("pass");
    }
}
