package com.chinmaybiswaltech.mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.chinmaybiswaltech.entities.ShowEntity;
import com.chinmaybiswaltech.views.request.ShowRequestBean;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;
import com.chinmaybiswaltech.views.responses.ShowResponseBean;

@Mapper(componentModel = "spring")
public interface ShowMapper {

	ShowMapper INSTANCE = Mappers.getMapper(ShowMapper.class);

	@Mapping(source = "showDate", target = "showDate", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "showStartTime", target = "showStartTime", dateFormat = "HH:mm:ss")
	@Mapping(source = "showEndTime", target = "showEndTime", dateFormat = "HH:mm:ss")
	ShowEntity toEntity(ShowRequestBean request);

	@Mapping(source = "theatre.name", target = "theatreName")
	@Mapping(source = "theatre.city.name", target = "cityName")
	@Mapping(source = "movie.movieId", target = "movieId")
	@Mapping(source = "movie.name", target = "movieName")
	@Mapping(source = "movie.title", target = "movieTitle")
	@Mapping(source = "movie.genre", target = "movieGenre")
	@Mapping(source = "showDate", target = "showDate", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "showStartTime", target = "showStartTime", dateFormat = "HH:mm:ss")
	@Mapping(source = "showEndTime", target = "showEndTime", dateFormat = "HH:mm:ss")
	ShowDetailsResponseBean toShowDetailsDTO(ShowEntity show);

	@Mapping(source = "showDate", target = "showDate", dateFormat = "yyyy-MM-dd")
	@Mapping(source = "showStartTime", target = "showStartTime", dateFormat = "HH:mm:ss")
	@Mapping(source = "showEndTime", target = "showEndTime", dateFormat = "HH:mm:ss")
	ShowResponseBean toResponse(ShowEntity showEntity);

	default long convertToEpoch(LocalDate date) {
		return date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
	}

	default long convertToEpoch(LocalTime time) {
		return time.toSecondOfDay();
	}
}
