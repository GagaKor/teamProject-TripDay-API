package com.gb.trip.controller.ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ==&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&"
				+ "areaCode="+code
				+ "&sigungucode=&_type=json";
		System.out.println("code : "+code);
		RestTemplate rt = new RestTemplate();
		String rs = rt.getForObject(url, String.class);
		ObjectMapper objMapper =new ObjectMapper();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Map<String,String>> list = null;
		try {
			JsonNode json = objMapper.readTree(rs);
			 list = objMapper.readValue(json.findValue("item").toString(),new TypeReference<ArrayList<Map<String,String>>>() {});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return list;
	}
}
