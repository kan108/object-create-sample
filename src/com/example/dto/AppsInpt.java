package com.example.dto;

import java.util.List;

public class AppsInpt {
	
	private String string1;
	
	private List<String> list1;

	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public List<String> getList1() {
		return list1;
	}

	public void setList1(List<String> list1) {
		this.list1 = list1;
	}

	@Override
	public String toString() {
		return "AppsInpt [string1=" + string1 + ", list1=" + list1 + ", getString1()=" + getString1() + ", getList1()="
				+ getList1() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
