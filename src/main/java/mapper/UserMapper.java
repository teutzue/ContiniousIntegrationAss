/*
package mapper;

import connector.DBConnector;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserMapper
{
    public User getUser(int id)
    {
        try
        {
            String sql = "select idUser, username, password, balance from user where idUser = ?";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                return new User(id, rs.getString("username"), rs.getString("password"), rs.getDouble("balance"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return null;
    }

    public double pay(User user, double amount)
    {
        double newBalance = user.getBalance() - amount;
        user.setBalance(newBalance);
        
        try
        {
            String sql = "update user set balance = ? where idUser = ?";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, user.getIdUser());
            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return newBalance;
    }

    public User createUser(String username, String password, double balance)
    {
        try
        {
            String sql = "INSERT INTO user (username, password, balance) VALUES (?,?,?)";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if (rs.next())
            {
                return new User(rs.getInt(1), username, password, balance);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return null;
    }

    public User validateUser(String username, String password)
    {
        try
        {
            String sql = "select idUser, balance from user where username = ? and password = ?";

            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next())
            {
                int id = rs.getInt("idUser");
                double balance = rs.getDouble("balance");
                return new User(id, username, password, balance);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        UserMapper um = new UserMapper();
        //um.createUser("admin", "Admin123", 1000);
        User u = um.validateUser("admin", "Admin123");
        System.out.println("validateUser()");
        System.out.println(u.getUserName());
        System.out.println("getUser()");
        System.out.println(um.getUser(3));
    }
}
*/
