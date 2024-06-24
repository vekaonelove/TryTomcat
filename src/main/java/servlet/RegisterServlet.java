package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ClientService;
import service.impl.ClientServiceImpl;
import java.io.IOException;

@WebServlet(name = "registerServlet", urlPatterns = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        logger.info("Received registration request from user: " + username);

        Client client = new Client(username, password, firstName, lastName, email);
        ClientService clientService = new ClientServiceImpl();
        clientService.addClient(client);

        HttpSession session = request.getSession();
        session.setAttribute("client", client);
        logger.info("User registered successfully: " + username);

        response.sendRedirect("pages/account.jsp");
    }
}