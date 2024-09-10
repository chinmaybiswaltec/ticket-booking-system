package com.chinmaybiswaltech.views.responses;

import lombok.Data;

@Data
public class ShowDetailsResponseBean {
	
	private Long showId;
	
	private String theatreName;
	
	private String cityName;
	
	private Long movieId;
	
	private String movieName;
	
	private String movieTitle;
	
	private String movieGenre;
	
	private String showDate;
	
	private String showStartTime;
	
	private String showEndTime;
	
	private int availableSeats;
}
