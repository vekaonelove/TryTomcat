package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Client;
import model.Curator;
import model.ExperienceLevel;
import model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ClientService;
import service.CuratorService;
import service.impl.ClientServiceImpl;
import service.impl.CuratorServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@WebServlet(name = "AccountServlet", urlPatterns = "/account-servlet")
@MultipartConfig
public class AccountServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AccountServlet.class.getName());
    private final ClientService clientService = new ClientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("client") != null) {
            Client client = (Client) session.getAttribute("client");
            String role = clientService.extractRole(client.getUsername());
            if ("ADMIN".equals(role)) {
                response.sendRedirect("pages/curators.jsp");
            } else {
                response.sendRedirect("pages/account.jsp");
            }
        } else {
            response.sendRedirect("pages/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Client client = (Client) request.getSession().getAttribute("client");


        if ("update".equals(action)) {
            clientService.updateClient(client);
            request.getSession().setAttribute("client", client);
            response.sendRedirect("pages/order.jsp");

        } else if ("logout".equals(action)) {
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");

        } else if ("delete".equals(action)) {
            clientService.deleteClient(client.getId());
            request.getSession().invalidate();
            response.sendRedirect("pages/login.jsp");

        } else if ("Add Curator".equals(action)) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String subjects = request.getParameter("subjects");
            String experience = request.getParameter("experience");
            String email = request.getParameter("email");
            Part filePart = request.getPart("photo");
            InputStream photo = filePart.getInputStream();

            Curator curator = new Curator(name, surname, Subject.valueOf(subjects),
                    ExperienceLevel.valueOf(experience), email, photo);

            CuratorService curatorService = new CuratorServiceImpl();
            curatorService.addCurator(curator);
            response.sendRedirect("pages/curatorsList.jsp");
        }
    }
}