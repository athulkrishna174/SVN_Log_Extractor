package com.logextractor.logextractor.services;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class RemoveUnwantedService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(RemoveUnwantedService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		List<String> requiredFormats = extractor.getRequiredFormats();
		StringBuilder unwantedRemovedString = new StringBuilder();

		try (Scanner lines = new Scanner(extractedString)) {

			while (lines.hasNextLine()) {
				String line = lines.nextLine().trim();
				if (requiredFormats.stream().anyMatch(line::contains)) {
					unwantedRemovedString.append(line).append(System.lineSeparator());
				}
			}

		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		extractor.setExtractedString(unwantedRemovedString.toString());
		return extractor;
	}

}
