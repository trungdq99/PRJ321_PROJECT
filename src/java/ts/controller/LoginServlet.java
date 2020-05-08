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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ts.tbluser.TblUserDAO;
import ts.tbluser.TblUserDTO;

/**
 *
 * @author SE130447
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid";
    private final String LOGIN_WITH_AD_ROLE = "search_page";
    private final String LOGIN_WITH_US_ROLE = "show_all_book";
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

        String url = INVALID_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            TblUserDAO dao = new TblUserDAO();
            boolean result = dao.checkLogin(username, password);
            if (result) {
                Cookie cookie1 = new Cookie("USERNAME", username);
                Cookie cookie2 = new Cookie("PASSWORD", password);
                cookie1.setMaxAge(60 * 5);
                cookie2.setMaxAge(60 * 5);
                response.addCookie(cookie1);
                response.addCookie(cookie2);

                TblUserDTO dto = dao.getUser();
                if(dto.isRole()){
                    url = LOGIN_WITH_AD_ROLE;
                }else{
                    url = LOGIN_WITH_US_ROLE;
                }
                HttpSession session = request.getSession();
                session.setAttribute("USER", dto);
            }
        } catch (NamingException e) {
            log("NamingException error: " + e.getMessage());
        } catch (SQLException e) {
            log("SQLException error: " + e.getMessage());
        } catch (Exception e) {
            log("Exception error: " + e.getMessage());
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
