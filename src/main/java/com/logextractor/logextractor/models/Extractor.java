package com.logextractor.logextractor.models;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

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

	public Extractor() {
		super();
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getOutputFileFormat() {
		return outputFileFormat;
	}

	public void setOutputFileFormat(String outputFileFormat) {
		this.outputFileFormat = outputFileFormat;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<String> getSelectedProjects() {
		return selectedProjects;
	}

	public void setSelectedProjects(List<String> selectedProjects) {
		this.selectedProjects = selectedProjects;
	}

	public List<String> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}

	public List<String> getRequiredFormats() {
		return requiredFormats;
	}

	public void setRequiredFormats(List<String> requiredFormats) {
		this.requiredFormats = requiredFormats;
	}

	public String getExtractedString() {
		return extractedString;
	}

	public void setExtractedString(String extractedString) {
		this.extractedString = extractedString;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
