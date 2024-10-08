package com.chinmaybiswaltech.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinmaybiswaltech.entities.ShowEntity;
import com.chinmaybiswaltech.mappers.ShowMapper;
import com.chinmaybiswaltech.repositories.ShowRepository;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;

@Service
public class ShowServiceImpl implements ShowService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShowServiceImpl.class);

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private ShowMapper showMapper;

	public List<ShowDetailsResponseBean> getShowDetails(Long movieId, String cityName, long showDateEpoch,
			long showTimeEpoch) {
		 logger.info("Fetching show details for movieId: {}, cityName: {}, showDateEpoch: {}, showTimeEpoch: {}", 
		            movieId, cityName, showDateEpoch, showTimeEpoch);
		 
		LocalDate showDate = Instant.ofEpochMilli(showDateEpoch)
				                    .atZone(ZoneId.systemDefault()).toLocalDate();
		LocalTime showStartTime = Instant.ofEpochMilli(showTimeEpoch)
				                         .atZone(ZoneId.systemDefault()).toLocalTime();
		
		logger.debug("Converted showDate: {} and showStartTime: {}", showDate, showStartTime);

		List<ShowEntity> shows = showRepository
				.findShowsByMovieCityAndTime(movieId, cityName, showDate, showStartTime);
		
		logger.debug("Found {} shows", shows.size());

		 List<ShowDetailsResponseBean> responseBeans = shows.stream()
	                .map(showMapper::toShowDetailsDTO)
	                .collect(Collectors.toList());
		
		 logger.info("Returning {} show details", responseBeans.size());
		 
		 return responseBeans;
	}

}
