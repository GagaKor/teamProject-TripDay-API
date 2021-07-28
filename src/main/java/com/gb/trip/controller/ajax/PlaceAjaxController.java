package com.gb.trip.controller.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	
	@GetMapping("/search")
	public List<String> search(){
		String code = "";
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ==&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&"
				+ "areaCode=" +code
				+ "&sigungucode=&_type=json";
		
		RestTemplate rt = new RestTemplate();

		
		return null;
	}
}
