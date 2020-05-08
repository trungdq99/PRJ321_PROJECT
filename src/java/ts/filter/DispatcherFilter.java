/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ts.tbluser.TblUserDTO;

/**
 *
 * @author SE130447
 */
public class DispatcherFilter implements Filter {

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    private final List<String> user;
    private final List<String> admin;
    private final List<String> guest;

    public DispatcherFilter() {
        admin = new ArrayList<>();
        admin.add("start_up");
        admin.add("login");
        admin.add("login_page");
        admin.add("logout");
        admin.add("search_lastname");
        admin.add("search_page");
        admin.add("delete_account");
        admin.add("update_account");
        admin.add("error_page");
        admin.add("invalid");

        user = new ArrayList<>();
        user.add("start_up");
        user.add("login");
        user.add("login_page");
        user.add("logout");
        user.add("show_all_book");
        user.add("add_item_to_cart");
        user.add("remove_item_from_cart");
        user.add("update_quantity");
        user.add("view_cart_page");
        user.add("check_out");
        user.add("check_out_success");
        user.add("store_page");
        user.add("error_page");
        user.add("invalid");

        guest = new ArrayList<>();
        guest.add("login");
        guest.add("login_page");
        guest.add("create_account");
        guest.add("create_account_page");
        guest.add("create_account_error_page");
        guest.add("invalid");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoAfterProcessing");
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        try {
            String uri = req.getRequestURI();
            int index = uri.lastIndexOf("/");
            String label = uri.substring(index + 1);
            ServletContext context = req.getServletContext();
            ResourceBundle bundle = (ResourceBundle) context.getAttribute("RESOURCE");
            if (bundle != null) {
                String url = null;
                if (label.equals("")) {
                    url = bundle.getString("start_up");
                } else {
                    url = bundle.getString(label);
                }

                HttpSession session = req.getSession(false);
                if (session == null || session.getAttribute("USER") == null) {
                    if (!guest.contains(label)) {
                        url = bundle.getString("start_up");
                    }
                }//
                else {
                    TblUserDTO dto = (TblUserDTO) session.getAttribute("USER");
                    boolean isAdmin = dto.isRole();
                    if ((isAdmin == true && admin.contains(label)) || (isAdmin == false && user.contains(label))) {
                        index = label.lastIndexOf("?");
                        if (index > 0) {
                            url = bundle.getString(label.substring(0, index)) + label.substring(index);
                        }
                    } else {
                        chain.doFilter(request, response);
                    }
                }
                if (url != null) {
                    RequestDispatcher rd = req.getRequestDispatcher(url);
                    rd.forward(request, response);
                }//end if url is null
                else {
                    chain.doFilter(request, response);
                }

            } else {
                chain.doFilter(request, response);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("DispatcherFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatcherFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatcherFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
