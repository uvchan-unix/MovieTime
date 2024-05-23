package com.zs.movietime.models.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zs.movietime.models.DAO.pojo.Movie;
import com.zs.movietime.models.DAO.pojo.Theatre;
import com.zs.movietime.models.DAO.pojo.User;
import com.zs.movietime.models.util.JsonUtils;
import com.zs.movietime.models.util.SQLQueries;
import com.zs.movietime.models.util.StatusCode;

public class DBModule {

    private Connection con = null;

    public DBModule(Connection con) {
        this.con = con;
    }

    // Get all data
    // Return type will be in ResultSet

    public ResultSet getAllMovie() {

        try {
            
            System.out.println(con);
            PreparedStatement stm = con.prepareStatement((SQLQueries.GET_ALL_MOVIE));
            System.out.println(stm);
            ResultSet result = stm.executeQuery();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return null;
    }

    public ResultSet getAllTheatre() {
        try {
            
            PreparedStatement stm = con.prepareStatement((SQLQueries.GET_ALL_THEATRE));
            ResultSet result = stm.executeQuery();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get pojo class objects
    // Return type will be StatusCode

    public StatusCode getMovieObj(String movieName) {

        try (PreparedStatement stm = con.prepareStatement(SQLQueries.GET_MOVIE)) {

            stm.setString(1, movieName);
            ResultSet result = stm.executeQuery();

            if (result.next()) {
                return new StatusCode("201", new Movie(result.getString("movie_name"), result.getString("movie_img_id"),
                        result.getString("movie_meta_json")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StatusCode("101");
    }

    public StatusCode getUserObj(String userName) {

        try (PreparedStatement stm = con.prepareStatement(SQLQueries.GET_USER)) {

            stm.setString(1, userName);
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {

                User user = new User(resultSet.getInt("user_id"), resultSet.getString("username"),
                        resultSet.getString("mail_id"), resultSet.getString("phonenumber"),
                        resultSet.getInt("user_type"));
                return new StatusCode("201", user);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StatusCode("101");

    }

    public StatusCode getTheatreObj(String theatreName) {

        try (PreparedStatement stm = con.prepareStatement(SQLQueries.GET_USER)) {

            stm.setString(1, theatreName);
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {

                Theatre theatre = new Theatre(resultSet.getInt("theatre_id"), resultSet.getString("theatre_name"),
                        resultSet.getString("theatre_location"));
                return new StatusCode("201", theatre);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StatusCode("101");

    }

    public StatusCode getShowsWithMovie(String movieName, Date time) {

        JSONObject wrapperJson = new JSONObject();

        try (PreparedStatement stm = con.prepareStatement(SQLQueries.GET_SHOWS_WITH_MOVENAME_AND_TIME)) {

            stm.setString(1, movieName);
            stm.setDate(2, time);

            System.out.println(stm.toString());

            ResultSet result = stm.executeQuery();

            StatusCode status = getMovieObj(movieName);

            if (status.isSuccess()) {
                Movie movie = (Movie) status.getObject();
                wrapperJson.put("movie", JsonUtils.objToJson(movie));

                JSONArray theatreJson = JsonUtils.resultsetToJson(result);
                System.out.println(theatreJson.toString());
                wrapperJson.put("theatre", theatreJson);

                return new StatusCode("222", wrapperJson);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StatusCode("111");

    }

    public static void main(String[] args) {

        DBConnection con = new DBConnection();
        DBModule db = new DBModule(con.getConnection());

        LocalDate localDate = LocalDate.of(2024, 5, 21);
        Date date = Date.valueOf(localDate);

        System.out.println(db.getShowsWithMovie("DADA", date));

    }

}
