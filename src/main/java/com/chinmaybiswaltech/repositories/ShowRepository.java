package com.chinmaybiswaltech.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chinmaybiswaltech.entities.ShowEntity;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Long>{

	@Query(value = "SELECT s.show_id AS show_id, " +
            "s.theatre_id AS show_theatre_id, " +
            "s.movie_id AS show_movie_id, " +
            "s.show_date AS show_date, " +
            "s.show_start_time AS show_start_time, " +
            "s.show_end_time AS show_end_time, " +
            "s.available_seats AS available_seats, " +
            "t.theatre_id AS theatre_id, " +
            "t.name AS theatre_name, " +
            "t.city_id AS theatre_city_id, " +
            "c.city_id AS city_id, " +
            "c.name AS city_name, " +
            "m.movie_id AS movie_id, " +
            "m.name AS movie_name, " +
            "m.title AS movie_title, " +
            "m.genre AS movie_genre " +
            "FROM shows s " +
            "JOIN theatres t ON s.theatre_id = t.theatre_id " +
            "JOIN cities c ON t.city_id = c.city_id " +
            "JOIN movies m ON s.movie_id = m.movie_id " +
            "WHERE m.movie_id = :movieId " +
            "AND c.name = :cityName " +
            "AND s.show_date = :showDate " +
            "AND s.show_start_time = :showStartTime", 
    nativeQuery = true)
List<ShowEntity> findShowsByMovieCityAndTime(
     @Param("movieId") Long movieId,
     @Param("cityName") String cityName,
     @Param("showDate") LocalDate showDate,
     @Param("showStartTime") LocalTime showStartTime);
	
	 List<ShowEntity> findByShowDateAndShowStartTime(LocalDate showDate, LocalTime showStartTime);

	 List<ShowEntity> findByTheatreId(Long theatreId);

	   
}
