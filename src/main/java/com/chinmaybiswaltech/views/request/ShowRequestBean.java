package com.chinmaybiswaltech.views.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ShowRequestBean {
	
	private Long theatreId;
	
	private Long movieId;
	
	private LocalDate showDate;
	
	private LocalTime showStartTime;
	
	private LocalTime showEndTime;
	
	private int availableSeats;
}
