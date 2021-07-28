package com.gb.trip.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.trip.dto.Intro;
import com.gb.trip.dto.Place;
import com.gb.trip.model.Detail;

@Service
public class ApiTripService{
	
	public List<Place> getPlaceList(Map<String, String> map){
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ=="
				+ "&pageNo="+map.get("pageNo")
				+ "&numOfRows="+map.get("numOfPage")
				+ "&MobileApp=test"
				+ "&MobileOS=ETC"
				+ "&arrange="+map.get("arrange")
				+ "&contentTypeId=12"
				+ "&areaCode="+map.get("areacode")
				+ "&sigunguCode="+map.get("sigunguCode")
				+ "&listYN=Y"
				+ "&_type=json";
		
		RestTemplate rt = new RestTemplate();

		ObjectMapper objMapper = new ObjectMapper();
		List<Place> list = new ArrayList<Place>();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		String rs = rt.getForObject(url, String.class);

		try {
			JsonNode json = objMapper.readTree(rs);					
			list = Arrays.asList(objMapper.readValue(json.findValue("item").toString(), Place[].class));
		}catch (JsonMappingException e) {
			e.getStackTrace();
		}catch (JsonProcessingException e) {
			e.getStackTrace();
		}
		return list;
	}
	
	public int getCount(String area1, String area2) throws IOException {
		StringBuffer result = new StringBuffer();

		String apiUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ%3D%3D"
				+ "&pageNo=1" + "&numOfRows=1" + "&MobileApp=test" + "&MobileOS=ETC" + "&arrange=A"
				+ "&contentTypeId=12" + "&areaCode="+area1 + "&sigunguCode="+area2+ "&listYN=N" + "&_type=json";
		URL url = new URL(apiUrl);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		String returnLine;

		while ((returnLine = br.readLine()) != null) {
			result.append(returnLine);

		}
		br.close();
		urlConnection.disconnect();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode node = mapper.readTree(result.toString());
		
		int totalCnt = Integer.parseInt(node.findValue("totalCnt").toString());
		

		return totalCnt;
	}
	
	public Detail getDetail(String contentid) throws IOException {
		
			StringBuffer result = new StringBuffer();

		String apiUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ%3D%3D"
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentid
				+ "&defaultYN=Y"
				+ "&firstImageYN=Y"
				+ "&areacodeYN=Y"
				+ "&catcodeYN=Y"
				+ "&addrinfoYN=Y"
				+ "&mapinfoYN=Y"
				+ "&overviewYN=Y" 
				+ "&_type=json";
		URL url = new URL(apiUrl);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		String returnLine;

		while ((returnLine = br.readLine()) != null) {
			result.append(returnLine);

		}
		
		br.close();
		urlConnection.disconnect();
	
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode json = mapper.readTree(result.toString());
		Detail detail = mapper.readValue(json.findValue("item").toString(), Detail.class);
		
		return detail;
	}

	public Intro getIntro(String contentid) throws IOException {
		StringBuffer result = new StringBuffer();

		String apiUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ%3D%3D"
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentid
				+ "&&contentTypeId=12"
				+ "&_type=json";
		URL url = new URL(apiUrl);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		String returnLine;

		while ((returnLine = br.readLine()) != null) {
			result.append(returnLine);

		}
		
		br.close();
		urlConnection.disconnect();
	
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode json = mapper.readTree(result.toString());
		Intro intro = mapper.readValue(json.findValue("item").toString(), Intro.class);
		
		return intro;
	}

	public List<JsonNode> getImg(String contentid) throws IOException {
		StringBuffer result = new StringBuffer();

		String apiUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?serviceKey=jiMuTvx6cLYWRrR2EKwGefsF3O966xEpgeU0UcEsuAtfzmtXVsG8pHw3JYK4uSUv6kgWiHX77rZDdjMnNaWRXQ%3D%3D"
				+ "&numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentid
				+ "&imageYN=Y"
				+ "&subImageYN=Y"
				+ "&_type=json";
		URL url = new URL(apiUrl);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		String returnLine;

		while ((returnLine = br.readLine()) != null) {
			result.append(returnLine);

		}
		
		br.close();
		urlConnection.disconnect();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode json = mapper.readTree(result.toString());
		List<JsonNode> jList = json.findValues("originimgurl");
		return jList;
	}
}
