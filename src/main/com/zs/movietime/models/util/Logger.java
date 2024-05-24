package com.zs.movietime.models.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Logger {

    
    private static final String ERROR_LOG_PATH = "WEB-INF/logs/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String DATE = String.valueOf(LocalDate.now());

    static File logFile = new File(ERROR_LOG_PATH+DATE+".log");

    public static void logInfo(String message) {
        log("INFO", message);
    }

    public static void logWarning(String message) {
        log("WARNING", message);
    }

    public static void logError(Exception e) {

        String message = getStactTrace(e);
        log("ERROR", message);

    }

    private static boolean isLogExist(){

        if (logFile.exists()) {
            return true;
        }
        
        return false;
    }

    private static void createLogFile(){
        
        try {
            logFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static String getStactTrace(Exception e){

        StringWriter sWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(sWriter);

        e.printStackTrace(writer);
        writer.close();

        return sWriter.toString();

    }

    private static void log(String type , String message){     

        if (isLogExist()) {

            try(PrintWriter writer = new PrintWriter(new FileWriter(logFile,true))){
               
                String dateTime = LocalDateTime.now().format(formatter);
                writer.write("[" + dateTime + "][" + type + "] " + message);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            
        }
        else{

            createLogFile();
            if (isLogExist()) {
                log(type, message);
            }
        }

    }

    public static void main(String[] args) {
        try {
            createLogFile();
            throw new SQLException("Testing da body soda");

        } catch (Exception e) {
            logError(e);
        }
    }
}
