package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Client;
import model.dao.ClientDao;
import model.dao.impl.ClientDaoImpl;

import java.io.IOException;

@WebServlet(name = "AccountServlet", urlPatterns = "/account-servlet")
public class AccountServlet extends HttpServlet {
    private final ClientDao clientDao = new ClientDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = (Client) request.getSession().getAttribute("client");
        //todo extract all strings to constants
        if (client == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        Client client = (Client) request.getSession().getAttribute("client");

        if ("update".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            clientDao.updateClientUsername(client.getId(), username);
            clientDao.updateClientPassword(client.getId(), password);
            clientDao.updateClientFirstName(client.getId(), firstName);
            clientDao.updateClientLastName(client.getId(), lastName);
            clientDao.updateClientEmail(client.getId(), email);

            client.setUsername(username);
            client.setPassword(password);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);

            request.getSession().setAttribute("client", client);
            response.sendRedirect("account.jsp");
        } else if ("logout".equals(action)) {
            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
        } else if ("delete".equals(action)) {
            clientDao.deleteClient(client.getId());
            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
        }

        //fixme change to switch case??

    }
}