package com.logextractor.logextractor.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.enums.ErrorMessage;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class RemoveDuplicateService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(RemoveDuplicateService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		Set<String> uniqueLines = new LinkedHashSet<>();
		
		try {
			extractedString.lines()
			.map(String::trim)
			.forEach(uniqueLines::add);
		} catch (Exception e) {
			logger.debug(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}
		
		StringBuilder duplRemovedString = new StringBuilder();
		uniqueLines.forEach(line -> duplRemovedString.append(line).append(System.lineSeparator()));
		extractor.setExtractedString(duplRemovedString.toString());		
		return extractor;
	}

}
