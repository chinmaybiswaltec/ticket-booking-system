package com.chinmaybiswaltech.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.chinmaybiswaltech.entities.MovieEntity;
import com.chinmaybiswaltech.entities.ShowEntity;
import com.chinmaybiswaltech.entities.TheatreEntity;
import com.chinmaybiswaltech.mappers.ShowMapper;
import com.chinmaybiswaltech.repositories.ShowRepository;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;

@SpringBootTest
public class ShowServiceImpltest {
	
	@InjectMocks
	private ShowServiceImpl showService;
	
	@Mock
	private ShowRepository showRepository;
	
	@Mock
	private ShowMapper showMapper;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	 public void testGetShowDetails() {
        // Given
        Long movieId = 1L;
        String cityName = "New York";
        long showDateEpoch = Instant.parse("2024-09-11T00:00:00Z").toEpochMilli();
        long showTimeEpoch = Instant.parse("2024-09-11T14:00:00Z").toEpochMilli();

        LocalDate showDate = Instant.ofEpochMilli(showDateEpoch).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime showStartTime = Instant.ofEpochMilli(showTimeEpoch).atZone(ZoneId.systemDefault()).toLocalTime();
        TheatreEntity theatreEntity = new TheatreEntity();
        theatreEntity.setName("Theatre One");

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setMovieId(movieId);
        movieEntity.setName("Movie Name");
        movieEntity.setTitle("Movie Title");
        movieEntity.setGenre("Action");

        ShowEntity showEntity = new ShowEntity();
        showEntity.setId(1L);
        showEntity.setTheatre(theatreEntity);
        showEntity.setMovie(movieEntity);
        showEntity.setShowDate(showDate);
        showEntity.setShowStartTime(showStartTime);
        showEntity.setShowEndTime(LocalTime.of(16, 0));
        showEntity.setAvailableSeats(100);

        ShowDetailsResponseBean responseBean = new ShowDetailsResponseBean();
        responseBean.setShowId(showEntity.getId());
        responseBean.setTheatreName(theatreEntity.getName());
        responseBean.setCityName(cityName);
        responseBean.setMovieId(movieEntity.getMovieId());
        responseBean.setMovieName(movieEntity.getName());
        responseBean.setMovieTitle(movieEntity.getTitle());
        responseBean.setMovieGenre(movieEntity.getGenre());
        responseBean.setShowDate(showEntity.getShowDate().toString());
        responseBean.setShowStartTime(showEntity.getShowStartTime().toString());
        responseBean.setShowEndTime(showEntity.getShowEndTime().toString());
        responseBean.setAvailableSeats(showEntity.getAvailableSeats());


        when(showRepository.findShowsByMovieCityAndTime(movieId, cityName, showDate, showStartTime))
                .thenReturn(Arrays.asList(showEntity));
        when(showMapper.toShowDetailsDTO(showEntity)).thenReturn(responseBean);

        // When
        List<ShowDetailsResponseBean> result = showService.getShowDetails(movieId, cityName, showDateEpoch, showTimeEpoch);

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(responseBean);

        verify(showRepository).findShowsByMovieCityAndTime(movieId, cityName, showDate, showStartTime);
        verify(showMapper).toShowDetailsDTO(showEntity);
    }

}
