/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ts.tbluser.TblUserDAO;
import ts.tbluser.TblUserDTO;

/**
 *
 * @author SE130447
 */
public class UpdateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "error_page";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = ERROR_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastname = request.getParameter("txtLastname");
        String searchValue = request.getParameter("lastSearchValue");
        String admin = request.getParameter("chkAdmin");
        boolean role = false;
        if (admin != null) {
            role = true;
        }
        TblUserDTO dto = new TblUserDTO(username, password, lastname, role);
        try {
            TblUserDAO dao = new TblUserDAO();
            boolean result = dao.updateAccount(dto);
            if (result) {
                url = "search_lastname"
                        + "?txtSearch=" + searchValue;
            }
        } catch (NamingException e) {
            log("UpdateAccountServlet naming exception: " + e.getMessage());
        } catch (SQLException e) {
            log("UpdateAccountServlet sql exception: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
