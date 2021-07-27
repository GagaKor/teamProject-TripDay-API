package com.gb.trip.controller.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gb.trip.model.Place;
import com.gb.trip.service.ApiTripService;

@RestController
public class PlaceAjaxController {
	
	@Autowired
	private ApiTripService apiTripService;
	
	@GetMapping("/placeList")
	public List<Place> getList(@PathVariable Map<String, String> map) {
	
	return apiTripService.getPlaceList(map);
	} 
}
