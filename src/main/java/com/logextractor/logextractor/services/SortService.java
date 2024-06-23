package com.logextractor.logextractor.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class SortService implements AppConstants, ExtractorInterface{
	Logger logger = LoggerFactory.getLogger(SortService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		List<String> sortedLines = new ArrayList<>();		

		try (Scanner lines = new Scanner(extractedString)) {
			
			while (lines.hasNextLine()) {
				sortedLines.add(lines.nextLine().trim());
			}

			Collections.sort(sortedLines);

		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		StringBuilder sortedString = new StringBuilder();
		sortedLines.forEach(line -> sortedString.append(line).append(System.lineSeparator()));
		extractor.setExtractedString(sortedString.toString());
		return extractor;
	}
}
