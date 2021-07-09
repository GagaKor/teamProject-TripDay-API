package com.gb.trip.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Schedule {
	private int id;
	private String contentid;
	private String title;
	private String addr;
	private String img;
	private Date s_date;
	private int uid;
}
