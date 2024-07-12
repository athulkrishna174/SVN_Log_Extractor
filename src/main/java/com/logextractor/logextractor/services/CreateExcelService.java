package com.logextractor.logextractor.services;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.CreateExcelInterface;
import com.logextractor.logextractor.models.Extractor;

@Service
public class CreateExcelService implements AppConstants, CreateExcelInterface {
	Logger logger = LoggerFactory.getLogger(CreateExcelService.class);

	@Override
	public Workbook generateExcel(Extractor extractor) {

		String extractedString = extractor.getExtractedString();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(extractor.getSelectedProject());
				
		XSSFCellStyle headerStyle = createHeaderStyle(workbook);
	    XSSFCellStyle contentStyle = createContentStyle(workbook);

		try {
			int[] rowCount = {1};
			int cellCount = 1;

			XSSFRow headerRow = (XSSFRow) sheet.createRow(rowCount[0]++);
			createCell(headerRow, cellCount, "Files", headerStyle);
			

			extractedString.lines().map(String::trim).forEach(line -> {
				XSSFRow contentRow = (XSSFRow) sheet.createRow(rowCount[0]++);
	            createCell(contentRow, cellCount, line, contentStyle);
			});

			sheet.autoSizeColumn(cellCount);
		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		return workbook;
	}
	
	private XSSFCellStyle createHeaderStyle(Workbook workbook) {
	    XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style.setBorderTop(BorderStyle.THIN);
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setBorderRight(BorderStyle.THIN);
	    style.setWrapText(true);

	    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
	    font.setFontName("Calibri");
	    font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBold(true);
	    style.setFont(font);

	    return style;
	}
	
	private XSSFCellStyle createContentStyle(Workbook workbook) {
	    XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
	    style.setBorderTop(BorderStyle.THIN);
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setBorderRight(BorderStyle.THIN);

	    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
	    font.setFontName("Calibri");
	    style.setFont(font);

	    return style;
	}

	private void createCell(XSSFRow row, int cellCount, String value, XSSFCellStyle style) {
	    XSSFCell cell = row.createCell(cellCount);
	    cell.setCellValue(value);
	    cell.setCellStyle(style);
	}
}
