package servlet;

import model.dao.ClientDao;
import model.dao.impl.ClientDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ClientDao clientDao = new ClientDaoImpl();
        Client client = clientDao.getClientByUsernameAndPassword(username, password);

        if (client != null) {
            response.sendRedirect("/account.jsp");
        } else {
            response.getWriter().println("Invalid username or password");
        }
    }

}