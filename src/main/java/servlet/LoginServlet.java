package servlet;

import jakarta.servlet.http.HttpSession;
import model.dao.ClientDao;
import model.dao.impl.ClientDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import service.impl.SessionServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private final SessionServiceImpl sessionServiceImpl = new SessionServiceImpl();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ClientDao clientDao = new ClientDaoImpl();
        Client client = clientDao.getClientByUsernameAndPassword(username, password);

        if (client != null) {
            HttpSession session = request.getSession();
            session.setAttribute("client", client);

            response.sendRedirect("pages/account.jsp");
        } else {
            response.sendRedirect("pages/errorLogin.jsp");
        }
    }

}