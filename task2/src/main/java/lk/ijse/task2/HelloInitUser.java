package lk.ijse.task2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloInitUser extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init the HelloInitUser Object");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("This is the HelloInitUser Class");
        String username = getServletContext().getInitParameter("username");
        System.out.println("Hello "+username+"!");
    }
}