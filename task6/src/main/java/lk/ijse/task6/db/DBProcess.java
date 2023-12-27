package lk.ijse.task6.db;

import lk.ijse.task6.dto.ItemDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBProcess {

    private static final String SAVE_DATA = "INSERT INTO Customers (Name, City, Email) VALUES (?,?,?)";
    private static final String GET_DATA = "SELECT * FROM Customers WHERE id = ?";
    private static final String SAVE_ITEM_DATA = "INSERT INTO Items (Code ,Dec , Qty, UnitPrice) VALUES (?,?,?,?)";

    public String saveCustomerData(String Name, String City, String Email, Connection connection){
        // save, manipulate data
        try {
            var ps = connection.prepareStatement(SAVE_DATA);
            ps.setString(1, Name);
            ps.setString(2, City);
            ps.setString(3, Email);

            if (ps.executeUpdate() != 0) {
                return  "Data saved";
            } else {
                return "Failed to save data";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getCustomerData(String ID, Connection connection){
        //get data
        List<String> selectedCustomer = new ArrayList<>();
        try {
            var ps = connection.prepareStatement(GET_DATA);
            ps.setInt(1, Integer.parseInt(ID));
            var rs = ps.executeQuery();
            while (rs.next()){
                int custId = rs.getInt("ID");
                String name = rs.getString("Name");
                String city = rs.getString("City");
                String email = rs.getString("Email");

                selectedCustomer.add(String.valueOf(custId));
                selectedCustomer.add(name);
                selectedCustomer.add(city);
                selectedCustomer.add(email);
            }
            return selectedCustomer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveItem(ItemDTO item,Connection connection){
        try {
            var ps = connection.prepareStatement(SAVE_ITEM_DATA);
            ps.setString(1, item.getCode());
            ps.setString(2, item.getDes());
            ps.setInt(3, item.getQty());
            ps.setDouble(4, item.getPrice());

            if (ps.executeUpdate() != 0) {
                System.out.println("Data saved");
            } else {
                System.out.println("Failed to save");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}