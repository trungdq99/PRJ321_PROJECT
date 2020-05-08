/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ts.cart.CartObject;
import ts.tblBook.TblBookDTO;
import ts.tblCart.TblCartDAO;
import ts.tblCart.TblCartDTO;
import ts.tblCartDetail.TblCartDetailDAO;
import ts.tblCartDetail.TblCartDetailDTO;
import ts.tbluser.TblUserDTO;

/**
 *
 * @author SE130447
 */
public class CheckOutServlet extends HttpServlet {

    private static final String SUCCESS = "check_out_success";
    private static final String ERROR = "error_page";

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

        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            TblUserDTO user = (TblUserDTO) session.getAttribute("USER");

            TblCartDAO cd = new TblCartDAO();
            int cartID = cd.getNextCartID();
            TblCartDTO cd1 = new TblCartDTO(cartID, user.getUsername(), cart.getTotal());
            boolean result = cd.insertCart(cd1);
            if (result == true) {
                TblCartDetailDAO cdd = new TblCartDetailDAO();
                Map<TblBookDTO, Integer> books = cart.getBooks();
                for (TblBookDTO tblBookDTO : books.keySet()) {
                    TblCartDetailDTO cdd1 = new TblCartDetailDTO(cartID, tblBookDTO.getBookID(), books.get(tblBookDTO));
                    result = cdd.insertCartDetail(cdd1);
                }
                if (result == true) {
                    url = SUCCESS;
                    cart = null;
                    session.setAttribute("CART", cart);
                }
            }

        } catch (NamingException e) {
            log("Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            log("Sql Exception: " + e.getMessage());
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
