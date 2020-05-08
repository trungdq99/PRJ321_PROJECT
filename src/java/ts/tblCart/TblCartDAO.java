/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tblCart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import ts.utils.DBUtils;

/**
 *
 * @author SE130447
 */
public class TblCartDAO implements Serializable{

    public int getNextCartID() throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int num = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT MAX(cartID) AS 'max' FROM tblCart";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    num = rs.getInt("max");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            return ++num;
        }
    }

    public boolean insertCart(TblCartDTO dto) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "INSERT INTO tblCart(cartID, username, total) VALUES(?, ?, ?)";

                //Create statement
                ps = conn.prepareStatement(sql);
                ps.setInt(1, dto.getCartID());
                ps.setString(2, dto.getUsername());
                ps.setFloat(3, dto.getTotal());

                //Execute/query
                int result = ps.executeUpdate();
                check = result > 0;
            } //end if conn is null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            return check;
        }
    }
}
