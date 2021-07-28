package com.gb.trip.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
	@JsonProperty
	private List<Item> item;
	@JsonProperty
	private List<ItemLongSky> itemLongSky;
	@JsonProperty
	private List<ItemLongTemp> itemLongTemp;

}