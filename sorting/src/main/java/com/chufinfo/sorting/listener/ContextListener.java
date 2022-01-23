package com.chufinfo.sorting.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



@WebListener
public class ContextListener implements ServletContextListener{

    protected Log Logger = LogFactory.getLog(ContextListener.class);
    
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Logger.debug("---------------Context容器启动-----------------");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        Logger.debug("---------------Context容器销毁-----------------");
        
    }

}
