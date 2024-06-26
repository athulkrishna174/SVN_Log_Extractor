package com.logextractor.logextractor.models;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Extractor {

	private MultipartFile file;
	private String outputFileFormat;
	private String selectedProject;
	private List<String> optionList;
	private List<String> requiredFormats;
	private String extractedString;

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

	public String getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(String selectedProject) {
		this.selectedProject = selectedProject;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
