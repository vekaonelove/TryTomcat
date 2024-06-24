package servlet;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import service.ClientService;
import service.impl.ClientServiceImpl;
import service.impl.SessionServiceImpl;

import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private final SessionServiceImpl sessionServiceImpl = new SessionServiceImpl();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ClientService clientService = new ClientServiceImpl();
        Client client = clientService.extractClientByUsernameAndPassword(username, password);

        if (client != null) {
            HttpSession session = request.getSession();
            session.setAttribute("client", client);
            response.sendRedirect("account-servlet");
        } else {
            response.sendRedirect("pages/errorLogin.jsp");
        }
    }

}