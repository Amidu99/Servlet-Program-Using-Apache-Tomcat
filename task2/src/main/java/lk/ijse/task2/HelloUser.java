package lk.ijse.task2;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello User!");
    }
}