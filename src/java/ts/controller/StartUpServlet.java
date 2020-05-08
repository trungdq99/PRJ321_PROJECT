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
public class StartUpServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login_page";
    private static final String SEARCH_PAGE = "search_page";
    private static final String SHOW_STORE = "show_all_book";

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

        String url = LOGIN_PAGE;
        try {
            //Get all cookies
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "";
                String password = "";
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("USERNAME")) {
                        username = cookies[i].getValue();
                    }
                    if (cookies[i].getName().equals("PASSWORD")) {
                        password = cookies[i].getValue();
                    }
                }
                //Check login, authen
                TblUserDAO dao = new TblUserDAO();
                boolean result = dao.checkLogin(username, password);
                if (result) {
                    TblUserDTO dto = dao.getUser();
                    if (dto.isRole()) {
                        url = SEARCH_PAGE;
                    } else {
                        url = SHOW_STORE;
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                }
            }//end if cookies is null
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
