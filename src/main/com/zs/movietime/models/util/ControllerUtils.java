package com.zs.movietime.models.util;

import com.opensymphony.xwork2.ActionSupport;

public interface ControllerUtils {

    default String processRequest(ActionSupport action,lamdaFunctionHandler handler){

        try {

            return handler.handle();
            
        } catch (Exception e) {
            e.printStackTrace();
            com.zs.movietime.models.util.Logger.logError(e);
        }
        return ActionSupport.ERROR;

    }

    @FunctionalInterface
    
    interface lamdaFunctionHandler {
        
        String handle() throws Exception;
        
    }
    
}