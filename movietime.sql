
CREATE DATABASE IF NOT EXISTS `movietime`;
USE movietime;

-- Users Table
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint AUTO_INCREMENT,
  `username` varchar(64),
  `mail_id` varchar(64),
  `phonenumber` varchar(20),
  `user_type` tinyint,
  PRIMARY KEY (`user_id`)
);

-- Theatres Table
CREATE TABLE IF NOT EXISTS `theatres` (
  `admin_id` bigint,
  `theatre_id` bigint AUTO_INCREMENT,
  `theatre_name` varchar(64),
  `theatre_location` varchar(64),
  PRIMARY KEY (`theatre_id`),
  FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`)
);

-- Screens Table
CREATE TABLE IF NOT EXISTS `screens` (
  `theatre_id` bigint,
  `screen_id` bigint AUTO_INCREMENT,
  `screen_name` varchar(64),
  `capacity` integer,
  PRIMARY KEY (`screen_id`),
  FOREIGN KEY (`theatre_id`) REFERENCES `theatres` (`theatre_id`)
);

-- Movies Table
CREATE TABLE IF NOT EXISTS `movies` (
  `movie_id` bigint AUTO_INCREMENT,
  `movie_name` varchar(64),
  `movie_img_id` varchar(64),
  `movie_meta_json` varchar(255),
  PRIMARY KEY (`movie_id`)
);

-- Shows Table
CREATE TABLE IF NOT EXISTS `shows` (
  `show_id` bigint AUTO_INCREMENT,
  `screen_id` bigint,
  `movie_id` bigint,
  `start_time` time,
  `end_time` time,
  `price` decimal(10,2),
  PRIMARY KEY (`show_id`),
  FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`)
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS `bookings` (
  `booking_id` bigint AUTO_INCREMENT,
  `show_id` bigint,
  `user_id` bigint,
  `no_of_tickets` int,
  `time_of_booking` timestamp,
  PRIMARY KEY (`booking_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  FOREIGN KEY (`show_id`) REFERENCES `shows` (`show_id`)
);

-- Payment Details Table
CREATE TABLE IF NOT EXISTS `payments` (
  `payment_id` bigint AUTO_INCREMENT,
  `booking_id` bigint,
  `amount` decimal(10, 2),
  `payment_method` varchar(64),
  `payment_status` varchar(32),
  `payment_date` timestamp,
  PRIMARY KEY (`payment_id`),
  FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
);

-- Reviews Table
CREATE TABLE IF NOT EXISTS `reviews` (
  `review_id` bigint AUTO_INCREMENT,
  `movie_id` bigint,
  `user_id` bigint,
  `rating` tinyint,
  `comment` text,
  `review_date` timestamp,
  PRIMARY KEY (`review_id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

-- Seat Reservations Table
CREATE TABLE IF NOT EXISTS `seats` (
  `seat_id` bigint AUTO_INCREMENT,
  `screen_id` bigint,
  `seat_number` varchar(10),
  PRIMARY KEY (`seat_id`),
  FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`)
);

CREATE TABLE IF NOT EXISTS `seat_reservations` (
  `reservation_id` bigint AUTO_INCREMENT,
  `booking_id` bigint,
  `seat_id` bigint,
  PRIMARY KEY (`reservation_id`),
  FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`)
);



