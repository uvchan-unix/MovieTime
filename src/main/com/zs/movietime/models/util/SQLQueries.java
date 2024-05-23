package com.zs.movietime.models.util;

public class SQLQueries {

    // users table relate queries goes here.......:)

    public static final String CREATE_USERS = "INSERT INTO users(username,mail_id,phonenumber,user_type) VALUES('?','?','?',?)";
    public static final String GET_USER = "SELECT * FROM users WHERE username = ?";

    // theatres table relate queries goes here.......:)

    public static final String GET_ALL_THEATRE = "SELECT * FROM theatres";
    public static final String CREATE_THEATRE = "INSERT INTO theatres(admin_id,theatre_name,theatre_location) VALUES('?','?','?')";
    public static final String GET_THEATRE = "SELECT * FROM theatres WHERE theatre_name = ?";

    // screens tables queries

    public static final String CREATE_THEARTRE_SCREEN = "";

    // movies table ralated query......

    public static final String GET_ALL_MOVIE = "SELECT * FROM movies";
    public static final String CREATE_MOVIE = "INSERT INTO movies(movie_name, movie_img_id, movie_meta_json) VALUES ('?','?','?')";
    public static final String GET_MOVIE = "SELECT * FROM movies WHERE movie_name = ?";

    // Retriving data

    public static final String GET_SHOWS_WITH_MOVENAME_AND_TIME = "SELECT theatres.theatre_name,theatres.theatre_location,screens.screen_name,screens.capacity,shows.start_time,shows.end_time,shows.price,shows.show_date FROM shows LEFT JOIN screens ON screens.screen_id = shows.screen_id LEFT JOIN theatres  ON screens.theatre_id = theatres.theatre_id LEFT JOIN movies ON movies.movie_id = shows.movie_id WHERE movies.movie_name = ? AND shows.show_date = ?";

}
