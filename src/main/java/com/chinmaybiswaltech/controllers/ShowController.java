package com.chinmaybiswaltech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltech.services.ShowService;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {

	@Autowired
	private ShowService showService;

	 @GetMapping("/search")
	    public List<ShowDetailsResponseBean> getShowDetails(
	            @RequestParam Long movieId,
	            @RequestParam String cityName,
	            @RequestParam long showDateEpoch,
	            @RequestParam long showTimeEpoch) {

	        return showService.getShowDetails(movieId, cityName, showDateEpoch, showTimeEpoch);
	    }
}
