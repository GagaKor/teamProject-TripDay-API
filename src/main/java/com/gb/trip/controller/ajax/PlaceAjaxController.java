package com.gb.trip.controller.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gb.trip.dto.Place;
import com.gb.trip.service.ApiTripService;

@RestController
public class PlaceAjaxController {
	
	@Autowired
	private ApiTripService apiTripService;
	
	@GetMapping("/placeList")
	public List<Place> getList(@RequestParam Map<String, String> data) {
	return apiTripService.getPlaceList(data);
	}
	
	@GetMapping("/placeSearch")
	public List<Map<String,String>> search(@RequestParam(required = false, defaultValue = "") String code){
		
		return apiTripService.getArrange(code);
	}
}
