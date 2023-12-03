package lk.ijse.task4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "customer", urlPatterns = "/customer",
        initParams = {
                @WebInitParam(name = "db-user",value = "root"),
                @WebInitParam(name = "db-pw",value = "1234"),
                @WebInitParam(name = "db-url",value = "jdbc:mysql://localhost:3306/minitask"),
                @WebInitParam(name = "db-class",value = "com.mysql.cj.jdbc.Driver")
        }
        ,loadOnStartup = 5
)
public class Customer extends HttpServlet {
    Connection connection;
    private static final String SAVE_DATA = "INSERT INTO customer (Name, Address, Mobile) VALUES ( ?,?,? )";
    @Override
    public void init() throws ServletException {
        try {
            var user = getServletConfig().getInitParameter("db-user");
            var password = getServletConfig().getInitParameter("db-pw");
            var url = getServletConfig().getInitParameter("db-url");
            Class.forName(getServletConfig().getInitParameter("db-class"));
            this.connection = DriverManager.getConnection(url,user,password);

            System.out.println("User : "+user);
            System.out.println("Password : "+password);
            System.out.println("URL : "+url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Start saving..");
        // parameter catch
        var name = req.getParameter("name");
        var address = req.getParameter("address");
        var mobile = req.getParameter("mobile");

        // create writer
        var writer = resp.getWriter();
        resp.setContentType("text/html");

        // save data
        try{
            var ps = connection.prepareStatement(SAVE_DATA);
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,mobile);
            if(ps.executeUpdate()!=0){
                System.out.println("Customer Saved!");
                writer.println("DATA Saved!");
            }else{
                System.out.println("Customer Not Saved!");
                writer.println("DATA Not Saved!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}