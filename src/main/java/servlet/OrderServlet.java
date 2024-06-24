package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import model.Curator;
import model.Order;
import service.CuratorService;
import service.OrderService;
import service.impl.CuratorServiceImpl;
import service.impl.OrderServiceImpl;

import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/order-servlet")
public class OrderServlet extends HttpServlet {
    private CuratorService curatorService = new CuratorServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int curatorId = Integer.parseInt(request.getParameter("curator"));
            Curator curator = curatorService.extractCuratorById(curatorId);

            Client client = (Client) request.getSession().getAttribute("client");

            Order order = new Order(0, curator, client);

            orderService.addOrder(order
            );
            response.sendRedirect("pages/order-success.jsp");

        } catch (Exception e){
            response.sendRedirect("pages/errorOrder.jsp");
        }

    }
}