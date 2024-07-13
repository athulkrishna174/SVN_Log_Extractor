package com.logextractor.logextractor.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.enums.ErrorMessage;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class RemoveUnwantedService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(RemoveUnwantedService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		List<String> requiredFormats = extractor.getRequiredFormats();
		StringBuilder unwantedRemovedString = new StringBuilder();

		try {
			extractedString.lines()
			.map(String::trim)
			.filter(line -> 
			requiredFormats.stream().anyMatch(line::contains))
			.forEach(line -> 
				unwantedRemovedString.append(line).append(System.lineSeparator()));
		} catch (Exception e) {
			logger.debug(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}

		extractor.setExtractedString(unwantedRemovedString.toString());
		return extractor;
	}

}
