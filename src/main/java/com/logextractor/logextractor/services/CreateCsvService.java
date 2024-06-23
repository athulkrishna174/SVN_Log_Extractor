package com.logextractor.logextractor.services;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class CreateCsvService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(CreateCsvService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		StringBuilder csvString = new StringBuilder();

		try (Scanner lines = new Scanner(extractedString)) {

			while (lines.hasNextLine()) {
				csvString.append(lines.nextLine().trim()).append(COMMA).append(System.lineSeparator());
			}

		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		extractor.setExtractedString(csvString.toString());
		return extractor;
	}

}
