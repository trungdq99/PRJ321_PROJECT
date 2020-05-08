/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tblCartDetail;

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
public class TblCartDetailDAO implements Serializable{

    public boolean insertCartDetail(TblCartDetailDTO dto) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "INSERT INTO tblCartDetail(cartID, bookID, quantity) VALUES(?, ?, ?)";

                //Create statement
                ps = conn.prepareStatement(sql);
                ps.setInt(1, dto.getCartID());
                ps.setInt(2, dto.getBookID());
                ps.setInt(3, dto.getQuantity());

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
