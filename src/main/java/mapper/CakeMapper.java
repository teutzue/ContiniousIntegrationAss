package mapper;

import connector.DBConnector;
import entity.Bottom;
import entity.Cake;
import entity.Top;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CakeMapper {

    DBConnector dbc;

    public CakeMapper(DBConnector dbc)
    {
        this.dbc = dbc;
    }

    public void injectConnector(DBConnector dbc){
        this.dbc = dbc;
    }

    public Cake getCakeById(int id) {
        try (Connection con = dbc.getConnection()) {
            String sql = "select idCupcake, cupcakeName, idTopping, idBottom from cupcake WHERE idCupcake = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            //System.out.println(rs);
            if (rs.next()) {
                return new Cake(id, getTop(rs.getInt("idTopping")), getBottom(rs.getInt("idBottom")), rs.getString("cupcakeName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean deleteTopById(int id) {
        boolean deleted = false;

        try (Connection con = dbc.getConnection()) {
            String sql = "delete from cupcaketopping WHERE idTopping = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            deleted = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return deleted;
    }

    public Cake createCake(Bottom bottom, Top top) {
        /*System.out.println(bottom.getId());
        System.out.println(top.getId());*/
        String name = bottom.getName() + " with " + top.getName();

        try (Connection con = dbc.getConnection()) {
            String sql = "INSERT INTO cupcake (cupcakeName, idTopping, idBottom) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql,
                    4);
            System.out.println(name);
            System.out.println(pstmt);
            pstmt.
                    setString(
                    1,
                    name);
            pstmt.setInt(2, top.getId());
            pstmt.setInt(3, bottom.getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return new Cake(rs.getInt(1), top, bottom, name);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Bottom> getAllBottoms() {
        List<Bottom> bottoms = new ArrayList();
        try (Connection con = dbc.getConnection()) {
            String sql = "select idBottom, cupcakeBottomName, price from cupcakebottom";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idBottom");
                String name = rs.getString("cupcakeBottomName");
                double price = rs.getDouble("price");
                bottoms.add(new Bottom(id, name, price));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bottoms;
    }

    public List<Top> getAllTops() {
        List<Top> tops = new ArrayList();
        try (Connection con = dbc.getConnection()) {
            String sql = "select idTopping, cupcakeToppingName, price from cupcaketopping";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idTopping");
                String name = rs.getString("cupcakeToppingName");
                double price = rs.getDouble("price");
                tops.add(new Top(id, name, price));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tops;
    }

    public Bottom getBottom(int id) {
        try (Connection con = dbc.getConnection()) {
            String sql = "select cupcakeBottomName, price from cupcakebottom WHERE idBottom = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("cupcakeBottomName");
                double price = rs.getDouble("price");
                return new Bottom(id, name, price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Top getTop(int id) {
        try (Connection con = dbc.getConnection()) {
            String sql = "select cupcakeToppingName, price from cupcaketopping WHERE idTopping = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("cupcakeToppingName");
                double price = rs.getDouble("price");
                return new Top(id, name, price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
