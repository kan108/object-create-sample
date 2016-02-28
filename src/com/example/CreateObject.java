package com.example;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.dto.AppsInpt;
import com.example.dto.HatApps;
import com.example.dto.RowData;

public class CreateObject {

	private static final String FILE_NAME = "C:\\work\\data.xlsx";
	private static final String SHEET_NAME = "Sheet1";

	private static final int HAT_APPS_ROW_START = 3;
	private static final int HAT_APPS_COLUMN_START = 2;

	public static void main(String[] args) throws Exception {

		CreateObject createObject = new CreateObject();

		// AppsInpt
		List<RowData> listAppsInpt = createObject.readExcel(2, 20, 5);
		AppsInpt appsInpt = createObject.createObjectAppsInpt(listAppsInpt);

		// HatApps
		List<RowData> listHatApps = createObject.readExcel(2, 20, 1);
		HatApps hatApps = createObject.createObjectHatApps(listHatApps, appsInpt);
		
		
		System.out.println(hatApps);


	};

	
	private HatApps createObjectHatApps(List<RowData> dataList, AppsInpt appsInpt) throws Exception {

		// HatAppsのデータを作成する
		HatApps hatApps = new HatApps();

		hatApps.setAppsInpt(appsInpt);
		
		Class clsHatApps = hatApps.getClass();

		for (RowData data : dataList) {
			
			//　String
			if ("String".equals(data.getKata())) {
				Method m1 = clsHatApps.getMethod("set" + firstCharConvUpper(data.getFieldName()), String.class);
				m1.invoke(hatApps, data.getValue());
			}
			
			// List<String>
			if ("List<String".equals(data.getKata())) {
				Method m2 = clsHatApps.getMethod("set" + firstCharConvUpper(data.getFieldName()), List.class);
				List<String> l = new ArrayList<>();
				String[] ss = data.getValue().split(",");
				for (String s : ss) {
					l.add(s);					
				}
				m2.invoke(hatApps, l);
				
			}
			
			// Date
			if ("Date".equals(data.getKata())) {
				Method m3 = clsHatApps.getMethod("set" + firstCharConvUpper(data.getFieldName()), Date.class);
				m3.invoke(hatApps, new Date(data.getValue()));
			}
		}		
		
		return hatApps;
		
		
	}
	
	private AppsInpt createObjectAppsInpt(List<RowData> dataList) throws Exception {

		// AppsInptのデータを作成する。
		AppsInpt appsInpt = new AppsInpt();

		Class clsAppsInpt = appsInpt.getClass();

		for (RowData data : dataList) {
			
			//　String
			if ("String".equals(data.getKata())) {
				Method m1 = clsAppsInpt.getMethod("set" + firstCharConvUpper(data.getFieldName()), String.class);
				m1.invoke(appsInpt, data.getValue());
			}
			
			// List<String>
			if ("List<String".equals(data.getKata())) {
				Method m2 = clsAppsInpt.getMethod("set" + firstCharConvUpper(data.getFieldName()), List.class);
				List<String> l = new ArrayList<>();
				String[] ss = data.getValue().split(",");
				for (String s : ss) {
					l.add(s);					
				}
				m2.invoke(appsInpt, l);
				
			}
		}

		return appsInpt;

	}

	private List<RowData> readExcel(int rowStart, int rowEnd, int columnStart) {

		List<RowData> dataList = new ArrayList<>();

		try {
			FileInputStream fi = new FileInputStream(FILE_NAME);
			Workbook book = new XSSFWorkbook(fi);
			fi.close();
			for (int s = 0; s < book.getNumberOfSheets(); ++s) {
				Sheet sheet = book.getSheetAt(s);
				if (SHEET_NAME.equals(sheet)) {
					continue;
				}

				for (int i = rowStart; i < rowEnd; i++) {

					Row row = sheet.getRow(i);

					if (row == null) {
						break;
					}

					RowData data = new RowData();
					int a = columnStart;
					data.setKata(getStr(row.getCell(a)));
					data.setFieldName(getStr(row.getCell(++a)));
					data.setValue(getStr(row.getCell(++a)));

					dataList.add(data);

				}

			}
		} catch (Exception e) {
			e.printStackTrace(System.err);

		}

		for (RowData row : dataList) {
			System.out.println(row.toString());
		}

		return dataList;

	}

	public static String getStr(Cell cell) { // データ型毎の読み取り
		if (cell ==null) {return "";}
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		// return cell.getStringCellValue();(※）
		case Cell.CELL_TYPE_NUMERIC:
			return Double.toString(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		}
		return "";// CELL_TYPE_BLANK,CELL_TYPE_ERROR
	}

	public String firstCharConvUpper(String s) {
		
		if (s == null) {
			return null;
		}
		
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
}
