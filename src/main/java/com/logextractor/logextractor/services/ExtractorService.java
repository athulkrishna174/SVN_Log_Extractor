//package com.logextractor.logextractor.services;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.logextractor.logextractor.constants.AppConstants;
//import com.logextractor.logextractor.interfaces.ExtractorInterface;
//import com.logextractor.logextractor.models.Extractor;
//
//@Service
//public class ExtractorService implements AppConstants, ExtractorInterface {
//	private static final String AN_ERROR_OCCURRED = "An error occurred: {}";
//	Logger logger = LoggerFactory.getLogger(ExtractorService.class);
//
//	@Override
//	public Extractor extractFileToString(Extractor extractor) {
//		MultipartFile file = extractor.getFile();
//		StringBuilder extractedString = new StringBuilder();
//
//		try (BufferedReader reader = new BufferedReader(
//				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
//			String line;
//
//			while ((line = reader.readLine()) != null) {
//				extractedString.append(line.trim()).append(System.lineSeparator());
//			}
//
//		} catch (Exception e) {
//			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
//		}
//
//		extractor.setExtractedString(extractedString.toString());
//		return extractor;
//	}
//
//	@Override
//	public Extractor extractByProject(Extractor extractor) {
//		String extractedString = extractor.getExtractedString();
//		String projectName = extractor.getSelectedProject();
//		StringBuilder projectExtractedString = new StringBuilder();
//
//		try (Scanner lines = new Scanner(extractedString)) {
//
//			while (lines.hasNextLine()) {
//				String line = lines.nextLine().trim();
//				if (line.contains(projectName)) {
//					projectExtractedString.append(line).append(System.lineSeparator());
//				}
//			}
//
//		} catch (Exception e) {
//			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
//		}
//
//		extractor.setExtractedString(projectExtractedString.toString());
//		return extractor;
//	}
//
//	@Override
//	public Extractor sortLines(Extractor extractor) {
//		String extractedString = extractor.getExtractedString();
//		List<String> sortedLines = new ArrayList<>();		
//
//		try (Scanner lines = new Scanner(extractedString)) {
//			
//			while (lines.hasNextLine()) {
//				sortedLines.add(lines.nextLine().trim());
//			}
//
//			Collections.sort(sortedLines);
//
//		} catch (Exception e) {
//			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
//		}
//
//		StringBuilder sortedString = new StringBuilder();
//		sortedLines.forEach(line -> sortedString.append(line).append(System.lineSeparator()));
//		extractor.setExtractedString(sortedString.toString());
//		return extractor;
//	}
//
//	@Override
//	public Extractor removeDuplicateLines(Extractor extractor) {
//		String extractedString = extractor.getExtractedString();
//		Set<String> uniqueLines = new LinkedHashSet<>();
//		
//
//		try (Scanner lines = new Scanner(extractedString)) {
//
//			while (lines.hasNextLine()) {
//				uniqueLines.add(lines.nextLine().trim());
//			}
//
//		} catch (Exception e) {
//			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
//		}
//		
//		StringBuilder duplRemovedString = new StringBuilder();
//		uniqueLines.forEach(line -> duplRemovedString.append(line).append(System.lineSeparator()));
//		extractor.setExtractedString(duplRemovedString.toString());		
//		return extractor;
//	}
//
//	@Override
//	public Extractor removeUnwantedLines(Extractor extractor) {
//		String extractedString = extractor.getExtractedString();
//		List<String> requiredFormats = extractor.getRequiredFormats();
//		StringBuilder unwantedRemovedString = new StringBuilder();
//
//		try (Scanner lines = new Scanner(extractedString)) {
//
//			while (lines.hasNextLine()) {
//				String line = lines.nextLine().trim();
//				if (requiredFormats.stream().anyMatch(line::contains)) {
//					unwantedRemovedString.append(line).append(System.lineSeparator());
//				}
//			}
//
//		} catch (Exception e) {
//			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
//		}
//
//		extractor.setExtractedString(unwantedRemovedString.toString());
//		return extractor;
//	}
//
//	@Override
//	public Extractor createCsv(Extractor extractor) {
//		String extractedString = extractor.getExtractedString();
//		StringBuilder csvString = new StringBuilder();
//
//		try (Scanner lines = new Scanner(extractedString)) {
//
//			while (lines.hasNextLine()) {
//				csvString.append(lines.nextLine().trim()).append(COMMA).append(System.lineSeparator());
//			}
//
//		} catch (Exception e) {
//			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
//		}
//
//		extractor.setExtractedString(csvString.toString());
//		return extractor;
//	}
//
//}
