
package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Bottom;
import entity.Top;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import com.mockrunner.mock.jdbc.MockResultSet;

import connector.DBConnector;
import entity.Cake;
import mapper.CakeMapper;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CakeMapperTest {

    @Mock
    DBConnector dbc;

    @Mock
    Connection conn;
    @Mock
    MockResultSet rs;

    @Mock
    MockResultSet rs2;

    @Mock
    MockResultSet rs3;
    @Mock
    PreparedStatement pstmt;

    @Mock
    PreparedStatement pstmt2;

    @Mock
    PreparedStatement pstmt3;

    @Mock
    PreparedStatement pstmt4;


    CakeMapper cm = new CakeMapper(dbc);

    @Before
    public void setUp() {
        // System.out.println("You fat");

        // dbc.injectConnection(conn);

    }

    @Test
    public void testGetCakeByIdMockito() throws SQLException {
        //System.out.println(cm);
        rs = new MockResultSet("myMock");
        //1,'ChoccoNut',3,1
        rs.addColumn("idTopping");
        rs.addColumn("idBottom");
        rs.addColumn("cupcakeName");
        rs.addRow(new Object[]{3, 1, "ChoccoNut"});
        //System.out.println(rs.toString());
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(conn.prepareStatement(
                "select idCupcake, cupcakeName, idTopping, idBottom from cupcake WHERE idCupcake = ?")).thenReturn(pstmt);

        rs2 = new MockResultSet("22");
        rs2.addColumn("cupcakeToppingName");
        rs2.addColumn("price");
        rs2.addRow(new Object[]{"tasty topping", 21});

        Mockito.when(pstmt2.executeQuery()).thenReturn(rs2);
        Mockito.when(conn.prepareStatement(
                "select cupcakeToppingName, price from cupcaketopping WHERE idTopping = ?")).thenReturn(pstmt2);

        rs3 = new MockResultSet("22");
        rs3.addColumn("cupcakeBottomName");
        rs3.addColumn("price");
        rs3.addRow(new Object[]{"chocolate bottom", 31});
        Mockito.when(pstmt3.executeQuery()).thenReturn(rs3);
        Mockito.when(conn.prepareStatement(
                "select cupcakeBottomName, price from cupcakebottom WHERE idBottom = ?")).thenReturn(pstmt3);


        Mockito.when(dbc.getConnection()).thenReturn(conn);

        cm.injectConnector(dbc);
        Cake c = cm.getCakeById(1);

        System.out.println(c.toString());

        //Mockito.when(pstmt.executeQuery()).thenReturn(value)

    }

    @Test
    public void top() throws SQLException {
        rs2 = new MockResultSet("22");
        rs2.addColumn("cupcakeToppingName");
        rs2.addColumn("price");
        rs2.addRow(new Object[]{"tasty topping", 21});

        Mockito.when(pstmt2.executeQuery()).thenReturn(rs2);
        Mockito.when(conn.prepareStatement(
                "select cupcakeToppingName, price from cupcaketopping WHERE idTopping = ?")).thenReturn(pstmt2);

        Mockito.when(dbc.getConnection()).thenReturn(conn);

        cm.injectConnector(dbc);
        Top c = cm.getTop(1);
        Top t = new Top(1, "tasty topping", 21.0);

        assertEquals(c.toString(), t.toString());
    }

    @Test
    public void bottom() throws SQLException {

        rs3 = new MockResultSet("22");
        rs3.addColumn("cupcakeBottomName");
        rs3.addColumn("price");
        rs3.addRow(new Object[]{"chocolate bottom", 31});
        Mockito.when(pstmt3.executeQuery()).thenReturn(rs3);
        Mockito.when(conn.prepareStatement(
                "select cupcakeBottomName, price from cupcakebottom WHERE idBottom = ?")).thenReturn(pstmt3);

        Mockito.when(dbc.getConnection()).thenReturn(conn);

        cm.injectConnector(dbc);

        Bottom c = cm.getBottom(1);
        Bottom t = new Bottom(1, "chocolate bottom", 31);
        assertEquals(c.toString(), t.toString());
        ;

    }

    @Test
    public void createCake() throws SQLException {
        rs = new MockResultSet("myMock");
        //1,'ChoccoNut',3,1
        rs.addColumn("columnIndex");
        rs.addRow(new Integer[]{1});
        //System.out.println(rs.toString());

        Mockito.when(pstmt4.getGeneratedKeys()).thenReturn(rs);
        System.out.println(pstmt4);
        Mockito.when(conn.prepareStatement(
                "INSERT INTO cupcake (cupcakeName, idTopping, idBottom) VALUES (?,?,?)", 4)).thenReturn(pstmt4);
        CakeMapper cm = new CakeMapper(dbc);
        Top t = new Top(1, "Adam", 223);
        Bottom b = new Bottom(2, "Teo", 345);
        Mockito.when(dbc.getConnection()).thenReturn(conn);

        Cake smth = cm.createCake(b, t);

        System.out.println(smth.toString());
        assertEquals(smth.toString(), new Cake(1, t, b, "Teo with Adam").toString());

    }

    @Test
    public void deleteCake() throws SQLException {

        Mockito.when(conn.prepareStatement("delete from cupcaketopping WHERE idTopping = ?")).thenReturn(pstmt);
        Mockito.when(dbc.getConnection()).thenReturn(conn);
        CakeMapper cm = new CakeMapper(dbc);
        assertTrue(cm.deleteTopById(1));

    }

}