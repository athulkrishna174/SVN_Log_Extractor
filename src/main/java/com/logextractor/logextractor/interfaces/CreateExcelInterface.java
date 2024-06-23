package com.logextractor.logextractor.interfaces;

import org.apache.poi.ss.usermodel.Workbook;

import com.logextractor.logextractor.models.Extractor;

public interface CreateExcelInterface {
	Workbook generateExcel(Extractor extractor);
}
