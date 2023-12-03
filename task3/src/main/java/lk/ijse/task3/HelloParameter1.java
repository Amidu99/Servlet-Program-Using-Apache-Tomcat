package lk.ijse.task3;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloParameter1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // parameter catch
        String name = req.getParameter("name");
        // parameter use
        System.out.println("Hello "+name+"!");
    }
}