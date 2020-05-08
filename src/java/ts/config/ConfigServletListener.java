/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author SE130447
 */
public class ConfigServletListener implements ServletContextListener {

    private static final String RESOURCE_FILE = "ts.config.resource";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        try {
            ResourceBundle bundble = ResourceBundle.getBundle(RESOURCE_FILE);
            context.setAttribute("RESOURCE", bundble);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
