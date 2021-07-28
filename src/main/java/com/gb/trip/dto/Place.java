package com.gb.trip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Place {
	@JsonProperty
	private String addr1;
	@JsonProperty
	private Integer areacode;
	@JsonProperty
	private String cat1;
	@JsonProperty
	private Integer contentid;
	@JsonProperty
	private Integer contenttypeid;
	@JsonProperty
	private Long createdtime;
	@JsonProperty
	private String firstimage;
	@JsonProperty
	private String firstimage2;
	@JsonProperty
	private String mapx;
	@JsonProperty
	private String mapy;
	@JsonProperty
	private Integer readcount;
	@JsonProperty
	private Integer sigungucode;
	@JsonProperty
	private String title;
	@JsonProperty
	private Integer zipcode;
	@JsonProperty
	private String addr2;

}
