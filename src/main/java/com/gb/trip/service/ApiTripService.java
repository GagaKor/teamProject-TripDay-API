package com.gb.trip.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.trip.dto.Detail;
import com.gb.trip.dto.Intro;
import com.gb.trip.dto.Place;

@Service
public class ApiTripService {
	public List<Map<String, String>> getArrange(String code) {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ==&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&"
				+ "areaCode=" + code + "&sigungucode=&_type=json";

		RestTemplate rt = new RestTemplate();
		String rs = rt.getForObject(url, String.class);
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Map<String, String>> list = null;
		try {
			JsonNode json = objMapper.readTree(rs);
			list = objMapper.readValue(json.findValue("item").toString(),
					new TypeReference<ArrayList<Map<String, String>>>() {
					});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Place> getPlaceList(Map<String, String> map) {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ=="
				+ "&pageNo=" + map.get("pageNo") + "&numOfRows=" + map.get("numOfPage") + "&MobileApp=test"
				+ "&MobileOS=ETC" + "&arrange=" + map.get("arrange") + "&contentTypeId=12" + "&areaCode="
				+ map.get("areacode") + "&sigunguCode=" + map.get("sigunguCode") + "&listYN=Y" + "&_type=json";

		RestTemplate rt = new RestTemplate();

		ObjectMapper objMapper = new ObjectMapper();
		List<Place> list = new ArrayList<Place>();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String rs = rt.getForObject(url, String.class);

		try {
			JsonNode json = objMapper.readTree(rs);
			list = Arrays.asList(objMapper.readValue(json.findValue("item").toString(), Place[].class));
		} catch (JsonMappingException e) {
			e.getStackTrace();
		} catch (JsonProcessingException e) {
			e.getStackTrace();
		}
		return list;
	}

	public int getCount(String area1, String area2) {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ=="
				+ "&pageNo=1" + "&numOfRows=1" + "&MobileApp=test" + "&MobileOS=ETC" + "&arrange=A"
				+ "&contentTypeId=12" + "&areaCode=" + area1 + "&sigunguCode=" + area2 + "&listYN=N" + "&_type=json";
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		int totalCnt = 0;
		try {
			JsonNode node = mapper.readTree(rt.getForObject(url, String.class));
			totalCnt = Integer.parseInt(node.findValue("totalCnt").toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalCnt;
	}

	public Detail getDetail(String contentid) {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ=="
				+ "&numOfRows=10" + "&pageNo=1" + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&contentId=" + contentid
				+ "&defaultYN=Y" + "&firstImageYN=Y" + "&areacodeYN=Y" + "&catcodeYN=Y" + "&addrinfoYN=Y"
				+ "&mapinfoYN=Y" + "&overviewYN=Y" + "&_type=json";
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Detail detail = null;
		System.out.println(rt.getForObject(url, String.class));
		try {
			JsonNode json = mapper.readTree(rt.getForObject(url, String.class));
			detail = mapper.readValue(json.findValue("item").toString(), Detail.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return detail;
	}

	public Intro getIntro(String contentid) {

		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ=="
				+ "&numOfRows=10" + "&pageNo=1" + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&contentId=" + contentid
				+ "&&contentTypeId=12" + "&_type=json";

		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Intro intro = null;
		try {
			JsonNode json = mapper.readTree(rt.getForObject(url, String.class));
			intro = mapper.readValue(json.findValue("item").toString(), Intro.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return intro;
	}

	public List<JsonNode> getImg(String contentid) {

		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ=="
				+ "&numOfRows=10" + "&pageNo=1" + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&contentId=" + contentid
				+ "&imageYN=Y" + "&subImageYN=Y" + "&_type=json";

		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<JsonNode> jList = new ArrayList<JsonNode>();
		try {
			JsonNode json = mapper.readTree(rt.getForObject(url, String.class));
			jList = json.findValues("originimgurl");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jList;
	}
}
