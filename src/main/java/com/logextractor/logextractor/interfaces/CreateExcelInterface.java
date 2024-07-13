package com.logextractor.logextractor.interfaces;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.logextractor.logextractor.models.Extractor;

@Component
public interface CreateExcelInterface {
	Workbook generateExcel(Extractor extractor);
}
