package com.logextractor.logextractor.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

public class FileToStringService implements AppConstants, ExtractorInterface {
	Logger logger = LoggerFactory.getLogger(FileToStringService.class);

	@Override
	public Extractor extract(Extractor extractor) {
		MultipartFile file = extractor.getFile();
		StringBuilder extractedString = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			String line;

			while ((line = reader.readLine()) != null) {
				extractedString.append(line.trim()).append(System.lineSeparator());
			}

		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		extractor.setExtractedString(extractedString.toString());
		return extractor;
	}

}
