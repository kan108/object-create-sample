package com.example.dto;

public class RowData {
	
	private String kata;
	private String fieldName;
	private String value;
	
	public String getKata() {
		return kata;
	}
	public void setKata(String kata) {
		this.kata = kata;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "RowData [kata=" + kata + ", fieldName=" + fieldName + ", value=" + value + "]";
	}	
	
	
	

}
