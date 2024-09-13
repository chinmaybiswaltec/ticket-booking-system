package com.chinmaybiswaltech.services;

import java.util.List;

import com.chinmaybiswaltech.views.request.ShowRequestBean;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;
import com.chinmaybiswaltech.views.responses.ShowResponseBean;

public interface ShowService {

	List<ShowDetailsResponseBean> getShowDetails(Long movieId, String cityName, long showDateEpoch, long showTimeEpoch);
	
	ShowResponseBean createShow(ShowRequestBean showRequest);
	
	ShowResponseBean updateShow(Long showId, ShowRequestBean showRequest);
	
	void deleteShow(Long showId);
	
	List<ShowResponseBean> getShowsByTheatre(Long theatreId);
}
