package servlet;

import model.dao.ClientDao;
import model.dao.impl.ClientDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        ClientDao clientDao = new ClientDaoImpl();
        clientDao.addClient(new Client(username, password, firstName, lastName, email));

        logger.info("User registered successfully: " + username);

        response.sendRedirect("pages/account.jsp");
    }
}