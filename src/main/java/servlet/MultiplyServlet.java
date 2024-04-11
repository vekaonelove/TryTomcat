package servlet;

import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "multiplyServlet", value = "/multiply-servlet")
public class MultiplyServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MultiplyServlet.class.getName());
    private final int NUMBER_TO_MULTIPLY = 7;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String param = request.getParameter("digit");
        int digit = Integer.parseInt(param);
        int result = digit * NUMBER_TO_MULTIPLY;
        logger.info("Result of multiplication " + digit + " is " + result);

        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/result.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Error while forwarding request", e);
        }

//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Result: " + result + "</h1>");
//        out.println("</body></html>");
    }
}