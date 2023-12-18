package lk.ijse.task6.api;

import lk.ijse.task6.db.DBProcess;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "customer",urlPatterns = "/customer",
     initParams = {
         @WebInitParam(name = "db-user",value = "root"),
         @WebInitParam(name = "db-pw",value = "1234"),
         @WebInitParam(name = "db-url",value = "jdbc:mysql://localhost:3306/minitask?createDatabaseIfNotExist=true"),
         @WebInitParam(name = "db-class",value = "com.mysql.cj.jdbc.Driver")
     }
)
public class Customer extends HttpServlet {
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            var user = getServletConfig().getInitParameter("db-user");
            var password = getServletConfig().getInitParameter("db-pw");
            var url = getServletConfig().getInitParameter("db-url");
            Class.forName(getServletConfig().getInitParameter("db-class"));
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("URL : "+url);
            System.out.println("User : "+user);
            System.out.println("PW : "+password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // param catch
        var name = req.getParameter("name");
        var city = req.getParameter("city");
        var email = req.getParameter("email");
        var writer = resp.getWriter();
        resp.setContentType("text/html");
        var data = new DBProcess();
        writer.println(data.saveCustomerData(name,city,email,connection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var writer = resp.getWriter();
        String id = req.getParameter("id");
        if(id == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            resp.setContentType("text/html");
            var data = new DBProcess();
            var getData = data.getCustomerData(id, connection);
            for (String eachData : getData){
                writer.println(eachData+"\n");
            }
        }
    }
}