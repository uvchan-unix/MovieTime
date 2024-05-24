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
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.zs.movietime.models.DAO.DBModule;
import com.zs.movietime.models.util.ControllerUtils;
import com.zs.movietime.models.util.FunctionDefanitions;
import com.zs.movietime.models.util.StatusCode;

@WebServlet("/services/movie/getMoviebyDate")

public class GetMoviesWithDate extends ActionSupport
        implements ServletResponseAware, ServletRequestAware, ControllerUtils {

    private HttpServletResponse response;
    private HttpServletRequest request;

    private String movie;
    private String date;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String execute() throws Exception {

        response.setHeader("Content-Type", "application/json");

        return processRequest(this, () -> {

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

        });

    }

}
