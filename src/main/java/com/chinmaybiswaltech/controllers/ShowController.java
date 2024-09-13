package com.chinmaybiswaltech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltech.services.ShowService;
import com.chinmaybiswaltech.views.request.ShowRequestBean;
import com.chinmaybiswaltech.views.responses.ShowDetailsResponseBean;
import com.chinmaybiswaltech.views.responses.ShowResponseBean;

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
	 
	 @PostMapping
	    public ResponseEntity<ShowResponseBean> createShow(@RequestBody ShowRequestBean showRequest) {
		 ShowResponseBean response = showService.createShow(showRequest);
	        return ResponseEntity.ok(response);
	    }

	    @PutMapping("/{showId}")
	    public ResponseEntity<ShowResponseBean> updateShow(@PathVariable Long showId, @RequestBody ShowRequestBean showRequest) {
	    	ShowResponseBean response = showService.updateShow(showId, showRequest);
	        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{showId}")
	    public ResponseEntity<Void> deleteShow(@PathVariable Long showId) {
	        showService.deleteShow(showId);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/theatre/{theatreId}")
	    public ResponseEntity<List<ShowResponseBean>> getShowsByTheatre(@PathVariable Long theatreId) {
	        List<ShowResponseBean> response = showService.getShowsByTheatre(theatreId);
	        return ResponseEntity.ok(response);
	    }
}
