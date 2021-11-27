package com.bridgelabz.login_webapp;

import com.google.common.hash.Hashing;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("rstid");
        String pass = req.getParameter("rstpass");
        String pass2 = req.getParameter("repass");
        PrintWriter out = res.getWriter();
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = pattern.matcher(pass);
        boolean check = matcher.matches();
        if (!pass.equals(pass2)) {
            res.sendRedirect("/login_webapp_war_exploded/PasswordMatchFail.html");
        }
        if (check && pass.equals(pass2)) {
            String sha256hex = Hashing.sha256()
                    .hashString(pass, StandardCharsets.UTF_8)
                    .toString();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                addToDB(id, sha256hex);
                out.println("<h1>");
                out.println("Registration Successful");
                out.println("</h1>");
                out.println("<a href='/login_webapp_war_exploded'>");
                out.println("Click here to goto login page");
                out.println("</a>");
            } catch (SQLException | ClassNotFoundException e) {
                out.println("<h1>");
                out.println(e);
                out.println("</h1>");
                e.printStackTrace();
            }
        } else {
            res.sendRedirect("/login_webapp_war_exploded/regex.html");
        }
    }

    private void addToDB(String id, String sha256) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "password");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO credentials VALUES (?,?)");
        statement.setString(1, id);
        statement.setString(2, sha256);
        statement.execute();
    }
}
