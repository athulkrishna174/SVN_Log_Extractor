package com.logextractor.logextractor.services;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class RemoveDuplicateService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(RemoveDuplicateService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		Set<String> uniqueLines = new LinkedHashSet<>();
		

		try (Scanner lines = new Scanner(extractedString)) {

			while (lines.hasNextLine()) {
				uniqueLines.add(lines.nextLine().trim());
			}

		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}
		
		StringBuilder duplRemovedString = new StringBuilder();
		uniqueLines.forEach(line -> duplRemovedString.append(line).append(System.lineSeparator()));
		extractor.setExtractedString(duplRemovedString.toString());		
		return extractor;
	}

}