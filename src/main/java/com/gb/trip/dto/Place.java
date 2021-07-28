package com.gb.trip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Place {
	@JsonProperty
	public String addr1;
	@JsonProperty
	public Integer areacode;
	@JsonProperty
	public String cat1;
	@JsonProperty
	public String cat2;
	@JsonProperty
	public String cat3;
	@JsonProperty
	public Integer contentid;
	@JsonProperty
	public Integer contenttypeid;
	@JsonProperty
	public Long createdtime;
	@JsonProperty
	public String firstimage;
	@JsonProperty
	public String firstimage2;
	@JsonProperty
	public String mapx;
	@JsonProperty
	public String mapy;
	@JsonProperty
	public Integer mlevel;
	@JsonProperty
	public Long modifiedtime;
	@JsonProperty
	public Integer readcount;
	@JsonProperty
	public Integer sigungucode;
	@JsonProperty
	public String title;
	@JsonProperty
	public Integer zipcode;
	@JsonProperty
	public String addr2;

}
