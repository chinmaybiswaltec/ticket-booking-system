package com.chinmaybiswaltech.views;

import lombok.Data;

@Data
public class Show {

	private Long showId;
	
	private Theatre theatre;
	
	private Movie movie;
	
	private Long startTime;
	
	private Long endTime;
	
	private Integer availableSeats;
	
}
