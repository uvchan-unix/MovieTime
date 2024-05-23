SELECT movies.movie_name, movies.movie_img_id, theatres.theatre_name , screens.screen_name, shows.start_time, shows.end_time, shows.price FROM shows LEFT JOIN movies ON shows.movie_id = movies.movie_id LEFT JOIN screens ON shows.screen_id = screens.screen_id LEFT JOIN theatres ON screens.theatre_id = theatres.theatre_id WHERE movies.movie_name = 'DADA' AND shows.show_time = NOW();

