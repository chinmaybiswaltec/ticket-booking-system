package com.chinmaybiswaltech.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shows")
public class ShowEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "show_id")
	private Long id;

	@Column(name = "show_theatre_id")
	private Long theatreId;

	@Column(name = "show_movie_id")
	private Long movieId;

	@ManyToOne
	@JoinColumn(name = "theatre_id", nullable = false)
	private TheatreEntity theatre;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private MovieEntity movie;

	@Column(name = "show_date")
	private LocalDate showDate;

	@Column(name = "show_start_time")
	private LocalTime showStartTime;

	@Column(name = "show_end_time")
	private LocalTime showEndTime;

	@Column(name = "available_seats")
	private int availableSeats;

}
