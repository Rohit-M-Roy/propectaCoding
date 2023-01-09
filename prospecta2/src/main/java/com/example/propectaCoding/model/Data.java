package com.example.propectaCoding.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
public class Data {
	
	private Integer count;
	private List<Entry> entries;

}
