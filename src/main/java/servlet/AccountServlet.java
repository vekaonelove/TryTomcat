package servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ClientService;
import service.impl.ClientServiceImpl;

import java.io.IOException;

@WebServlet(name = "AccountServlet", urlPatterns = "/account-servlet")
public class AccountServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AccountServlet.class.getName());
    private final ClientService clientService = new ClientServiceImpl();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        Client client = (Client) request.getSession().getAttribute("client");


        if ("update".equals(action)) {
            clientService.updateClient(client);
            request.getSession().setAttribute("client", client);
            response.sendRedirect("book.jsp");

        } else if ("logout".equals(action)) {
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");

        } else if ("delete".equals(action)) {
            clientService.deleteClient(client.getId());
            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
        }

        //fixme change to switch case??

    }
}