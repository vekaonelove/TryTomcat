package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import model.Curator;
import service.CuratorService;
import service.impl.CuratorServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CuratorServlet", urlPatterns = "/curator-servlet")
public class CuratorServlet extends HttpServlet {
    private final CuratorService curatorService = new CuratorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Curator> curators = curatorService.getAllCurators();
        System.out.println(curators);
        request.getSession().setAttribute("curators", curators);
        response.sendRedirect("pages/curatorsList.jsp");
    }
}