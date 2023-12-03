package lk.ijse.task3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloParameter2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // parameter catch
        String number = req.getParameter("number");

        // create writer
        var writer = resp.getWriter();
        resp.setContentType("text/html");

        // search number odd or even
        if(Integer.parseInt(number)%2==0){
            System.out.println(number+" is an Even number.");
            writer.println(number+" is an Even number.");
        }else{
            System.out.println(number+" is an Odd number.");
            writer.println(number+" is an Odd number.");
        }
    }
}