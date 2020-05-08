/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tblBook;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ts.utils.DBUtils;

/**
 *
 * @author SE130447
 */
public class TblBookDAO implements Serializable {

    public List<TblBookDTO> getAllBook() throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TblBookDTO> listBook = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT bookID, title, price "
                        + "FROM tblBook";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int bookID = rs.getInt("bookID");
                    String title = rs.getString("title");
                    float price = rs.getFloat("price");
                    TblBookDTO dto = new TblBookDTO(bookID, title, price);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
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
            return listBook;
        }
    }
}
