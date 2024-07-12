package com.logextractor.logextractor.services;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class SortService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(SortService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		try {
			String sortedString = extractedString.lines()
					.map(String::trim)
					.sorted()
					.collect(Collectors.joining(System.lineSeparator()));
			extractor.setExtractedString(sortedString);
		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}
		return extractor;
	}
}
