package com.logextractor.logextractor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.enums.ErrorMessage;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class CreateCsvService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(CreateCsvService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		StringBuilder csvString = new StringBuilder();

		try {			
			extractedString.lines()
			.map(String::trim)
			.forEach(line -> 
				csvString.append(line).append(COMMA).append(System.lineSeparator()));
		} catch (Exception e) {
			logger.debug(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}

		extractor.setExtractedString(csvString.toString());
		return extractor;
	}

}
