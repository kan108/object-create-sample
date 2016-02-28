package com.example.dto;

import java.util.Date;

public class HatApps {
	
	private String string1;
	
	private Date date1;
	
	private boolean boolean1;

	private AppsInpt appsInpt;
	
	
	//////////////////////////////////
	
	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public AppsInpt getAppsInpt() {
		return appsInpt;
	}

	public void setAppsInpt(AppsInpt appsInpt) {
		this.appsInpt = appsInpt;
	}


	public boolean isBoolean1() {
		return boolean1;
	}

	public void setBoolean1(boolean boolean1) {
		this.boolean1 = boolean1;
	}

	@Override
	public String toString() {
		return "HatApps [string1=" + string1 + ", date1=" + date1 + ", boolean1=" + boolean1 + ", appsInpt=" + appsInpt
				+ "]";
	}

	
	
}
