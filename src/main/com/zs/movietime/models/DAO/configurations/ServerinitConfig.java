package com.zs.movietime.models.DAO.configurations;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zs.movietime.models.DAO.DBConnection;
import com.zs.movietime.models.DAO.DBModule;
import com.zs.movietime.models.collectiontree.Collection;

@WebListener

public class ServerinitConfig implements ServletContextListener {

    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext context = sce.getServletContext();

        DBConnection con = new DBConnection();
        System.out.println(con);
        DBModule db = new DBModule(con.getConnection());
        System.out.println(db);

        Collection collectionTree = new Collection(db);
    
        context.setAttribute("db",db);
        context.setAttribute("tree",collectionTree);

    }
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        
    }

}
