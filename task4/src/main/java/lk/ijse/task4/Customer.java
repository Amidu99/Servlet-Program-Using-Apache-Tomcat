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
import java.sql.ResultSet;
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
    private static final String GET_DATA = "SELECT * FROM customer";
    private static final String GET_S_DATA = "SELECT * FROM customer WHERE Name = ?";
    private static final String UPDATE_DATA = "UPDATE customer SET Address = ?, Mobile = ? WHERE Name = ?";
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    // doGet without parameter
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Start retrieving..");

        // create writer
        var writer = resp.getWriter();
        resp.setContentType("text/html");

        // get data
        try{
            ResultSet resultSet = connection.createStatement().executeQuery(GET_DATA);
            while(resultSet.next()){
                String name = resultSet.getString(1);
                String address = resultSet.getString(2);
                String mobile = resultSet.getString(3);
                System.out.println("Name = "+name+" | Address = "+address+" | Mobile = "+mobile);
                writer.println("Name = "+name+" | Address = "+address+" | Mobile = "+mobile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    // doGet with parameter
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("name")==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // The error what u want to send
        }
        System.out.println("Start retrieving..");

        // parameter catch
        var Name = req.getParameter("name");

        var writer = resp.getWriter();
        resp.setContentType("text/html");

        // get data
        try{
            var ps = connection.prepareStatement(GET_S_DATA);
            ps.setString(1,Name);
            var resultSet = ps.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString(1);
                String address = resultSet.getString(2);
                String mobile = resultSet.getString(3);
                System.out.println("Name = "+name+" | Address = "+address+" | Mobile = "+mobile);
                writer.println("Name = "+name+" | Address = "+address+" | Mobile = "+mobile);
            }else{
                System.out.println("Not Retrieved!");
                writer.println("DATA Not Retrieved!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("name")==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // The error what u want to send
        }
        System.out.println("Start updating..");

        // parameter catch
        var name = req.getParameter("name");
        var address = req.getParameter("address");
        var mobile = req.getParameter("mobile");

        var writer = resp.getWriter();
        resp.setContentType("text/html");

        // update data
        try{
            var ps = connection.prepareStatement(UPDATE_DATA);
            ps.setString(1,address);
            ps.setString(2,mobile);
            ps.setString(3,name);
            if(ps.executeUpdate()!=0){
                System.out.println("Customer Updated!");
                writer.println("DATA Updated!");
            }else{
                System.out.println("Customer Not Updated!");
                writer.println("DATA Not Updated!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}