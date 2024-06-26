package com.logextractor.logextractor.services;

import java.util.Scanner;

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

		try (Scanner lines = new Scanner(extractedString)) {
			
			Sheet sheet = workbook.createSheet(extractor.getSelectedProject());

			int rowCount = 0;
			int cellCount = 1;

			XSSFRow row = (XSSFRow) sheet.createRow(rowCount++);

			XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setBorderTop(BorderStyle.THIN);
			headerStyle.setBorderBottom(BorderStyle.THIN);
			headerStyle.setBorderLeft(BorderStyle.THIN);
			headerStyle.setBorderRight(BorderStyle.THIN);
			headerStyle.setWrapText(true);

			XSSFFont headerFont = ((XSSFWorkbook) workbook).createFont();
			headerFont.setFontName("Calibri");
			headerFont.setColor(IndexedColors.WHITE.getIndex());
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			XSSFCell headerCell = row.createCell(cellCount);
			headerCell.setCellValue("Files");
			headerCell.setCellStyle(headerStyle);

			XSSFCellStyle contentStyle = (XSSFCellStyle) workbook.createCellStyle();
			contentStyle.setBorderTop(BorderStyle.THIN);
			contentStyle.setBorderBottom(BorderStyle.THIN);
			contentStyle.setBorderLeft(BorderStyle.THIN);
			contentStyle.setBorderRight(BorderStyle.THIN);

			XSSFFont contentFont = (XSSFFont) workbook.createFont();
			contentFont.setFontName("Calibri");
			contentStyle.setFont(contentFont);

			while (lines.hasNextLine()) {
				row = (XSSFRow) sheet.createRow(rowCount++);

				XSSFCell contentCell = row.createCell(cellCount);
				contentCell.setCellValue(lines.nextLine().trim());
				contentCell.setCellStyle(contentStyle);
			}

			sheet.autoSizeColumn(cellCount);
		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		return workbook;
	}
}
