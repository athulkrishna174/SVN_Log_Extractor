package com.logextractor.logextractor.models;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Extractor {

	private MultipartFile file;
	private String outputFileFormat;
	private String projectName;
	private List<String> selectedProjects;
	private List<String> optionList;
	private List<String> requiredFormats;
	private String extractedString;
	private Workbook workbook;

	private String message;

}
