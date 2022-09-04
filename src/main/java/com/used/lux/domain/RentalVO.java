package com.used.lux.domain;

import java.util.List;

import lombok.Data;

@Data
public class RentalVO {
	
	List<String> brandName;
	List<String> bigCategory;
	List<String> smallCategory;
	List<String> gender;
	List<String> size;
	String productName;
	String a;
	
	public static String cuttingSide(List<String> string)
	{
		String b =  string.toString().substring(1, string.toString().length()-2);
		
		return b;
	}
}
