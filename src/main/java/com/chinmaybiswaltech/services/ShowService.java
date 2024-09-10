package com.chinmaybiswaltech.services;

import java.util.List;

import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;

public interface ShowService {

	List<ShowDetailsResponseBean> getShowDetails(Long movieId, String cityName, long showDateEpoch, long showTimeEpoch);
}
