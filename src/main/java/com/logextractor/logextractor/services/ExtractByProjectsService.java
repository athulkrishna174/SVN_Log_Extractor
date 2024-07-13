package com.logextractor.logextractor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.enums.ErrorMessage;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class ExtractByProjectsService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(ExtractByProjectsService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		String projectName = extractor.getProjectName();
		StringBuilder projectExtractedString = new StringBuilder();
		
		try {
			extractedString.lines()
			.filter(line -> line.contains(projectName))
			.forEach(line -> 
				projectExtractedString.append(line).append(System.lineSeparator())
			);
		} catch (Exception e) {
			logger.debug(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}
		
		extractor.setExtractedString(projectExtractedString.toString());
		return extractor;
	}
}
