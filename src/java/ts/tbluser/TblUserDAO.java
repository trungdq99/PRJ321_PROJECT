/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tbluser;

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
public class TblUserDAO implements Serializable {

    private TblUserDTO user;

    public TblUserDTO getUser() {
        return user;
    }

    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "SELECT lastname, isAdmin FROM tblUser WHERE username = ? AND password = ?";

                //Create statement
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);

                //Execute/query
                rs = ps.executeQuery();
                //Proccess
                if (rs.next()) {
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    user = new TblUserDTO(username, lastname, isAdmin);
                    check = true;
                }
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

    private List<TblUserDTO> accountList;

    public List<TblUserDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue) throws /*ClassNotFoundException*/ NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM tblUser "
                        + "WHERE lastname like ?";

                //Create statement
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");

                //Execute/query
                rs = ps.executeQuery();
                //Proccess
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    TblUserDTO dto = new TblUserDTO(username, password, lastname, role);

                    if (this.accountList == null) {
                        accountList = new ArrayList<>();
                    }//end if account list is null
                    this.accountList.add(dto);
                }
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
            return;
        }
    }

    public boolean updateAccount(TblUserDTO dto) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "UPDATE tblUser SET password = ?, lastname = ?, isAdmin = ? "
                        + "WHERE username = ?";

                //Create statement
                ps = conn.prepareStatement(sql);

                ps.setString(1, dto.getPassword());
                ps.setString(2, dto.getLastname());
                ps.setBoolean(3, dto.isRole());
                ps.setString(4, dto.getUsername());

                //Execute/query
                int rowEffect = ps.executeUpdate();
                //Proccess
                if (rowEffect > 0) {
                    check = true;
                }
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

    public boolean deleteAccount(String username) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "DELETE FROM tblUser WHERE username = ?";

                //Create statement
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);

                //Execute/query
                int rowEffect = ps.executeUpdate();
                //Proccess
                if (rowEffect > 0) {
                    check = true;
                }
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

    public boolean insertAccount(TblUserDTO dto) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Connection
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Create sql string
                String sql = "INSERT INTO tblUser(username, password, lastname, isAdmin) "
                        + "VALUES (?, ?, ?, ?)";

                //Create statement
                ps = conn.prepareStatement(sql);
                ps.setString(1, dto.getUsername());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getLastname());
                ps.setBoolean(4, dto.isRole());
                //Execute/query
                int rowEffect = ps.executeUpdate();
                //Proccess
                if (rowEffect > 0) {
                    return true;
                }
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
        }
        return false;
    }
}
