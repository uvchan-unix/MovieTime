package com.zs.movietime.controllers.movie;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.zs.movietime.models.DAO.DBModule;
import com.zs.movietime.models.util.FunctionDefanitions;
import com.zs.movietime.models.util.StatusCode;

@WebServlet("/services/movie/getMoviebyDate")

public class GetMoviesWithDate extends ActionSupport {

    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpServletRequest request = ServletActionContext.getRequest();

    
    String movie = request.getParameter("movie");
    String date = request.getParameter("date");
    
    @Override
    public String execute() throws Exception {
        
        response.setHeader("Content-Type", "application/json");

        LocalDate localDate = LocalDate.of(2024, 5, 21);
        Date ldate = Date.valueOf(localDate);

        if (date.length() == 0 || date == null) {

            FunctionDefanitions.outputWriter("no input", response);

        } else {

            DBModule db = FunctionDefanitions.getDbOperarion(request);
            StatusCode status = db.getShowsWithMovie(movie, ldate);

            if (status.isSuccess()) {
                FunctionDefanitions.outputWriter(status.getObject().toString(), response);
            }
        }

        return SUCCESS;

    }

}
