package com.chinmaybiswaltech.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chinmaybiswaltech.entities.ShowEntity;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;

@Mapper(componentModel = "spring")
public interface ShowMapper {

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
}
