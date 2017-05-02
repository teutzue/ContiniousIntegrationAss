/*
package mapper;

import connector.DBConnector;
import entity.Invoice;
import entity.InvoiceLine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceMapper {

    CakeMapper ccm = new CakeMapper(null);

    public void checkInvoice(int invoiceId) {
        try {
            String sql = "UPDATE invoice SET paid = 1 WHERE idInvoice = ?";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, invoiceId);
            int result = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Invoice getInvoiceById(int id) {
        try {
            String sql = "select idInvoice, idUser, created from invoice WHERE idInvoice = ?";

            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("idUser");
                Date created = new Date(rs.getDate("created").getTime());
                return new Invoice(id, userId, created, getLinesByInvoice(id));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<InvoiceLine> getLinesByInvoice(int invoiceId) {
        List<InvoiceLine> lines = new ArrayList();
        try {
            String sql = "select id, idInvoice, idCupcake, qty from invoicedetails WHERE idInvoice = ?";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql);
            pstmt.setInt(1, invoiceId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int cupcakeId = rs.getInt("idCupcake");
                int qty = rs.getInt("qty");
                lines.add(new InvoiceLine(id, invoiceId, ccm.getCakeById(cupcakeId), qty));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        InvoiceMapper im = new InvoiceMapper();
        im.createInvoice(null, 1);
    }

    public int createInvoice(List<InvoiceLine> lines, int userId) {
        try {
            String sql = "INSERT INTO invoice (idUser) VALUES (?)";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                final int invoiceId = rs.getInt(1);
                if (lines != null) {
                    lines.stream().forEach((line)
                            -> {
                        addInvoiceLine(invoiceId, line.getCupcake().getId(), line.getAmount());
                    });
                }

                return invoiceId;
            } else {
                throw new SQLException("could not insert invoice");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public InvoiceLine addInvoiceLine(int invoiceId, int cupcakeId, int qty) {
        try {
            String sql = "INSERT INTO invoicedetails (idInvoice, idCupcake, qty) VALUES (?,?,?)";
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, invoiceId);
            pstmt.setInt(2, cupcakeId);
            pstmt.setInt(3, qty);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return new InvoiceLine(rs.getInt(1), invoiceId, ccm.getCakeById(cupcakeId), qty);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
*/
