package com.logextractor.logextractor.services;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class ExtractByProjectsService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(ExtractByProjectsService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		String extractedString = extractor.getExtractedString();
		String projectName = extractor.getSelectedProject();
		StringBuilder projectExtractedString = new StringBuilder();

		try (Scanner lines = new Scanner(extractedString)) {

			while (lines.hasNextLine()) {
				String line = lines.nextLine().trim();
				if (line.contains(projectName)) {
					projectExtractedString.append(line).append(System.lineSeparator());
				}
			}

		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		extractor.setExtractedString(projectExtractedString.toString());
		return extractor;
	}
}
