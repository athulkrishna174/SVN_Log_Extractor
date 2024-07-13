package com.logextractor.logextractor.models;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ExtractorDto {
	private MultipartFile file;
	private String outputFileFormat;
	private String projectName;
	private List<String> selectedProjects;
	private List<String> optionList;
	private List<String> requiredFormats;
	
	public ExtractorDto() {
		super();
	}

	public ExtractorDto(MultipartFile file, String outputFileFormat, String projectName, List<String> selectedProjects,
			List<String> optionList, List<String> requiredFormats) {
		super();
		this.file = file;
		this.outputFileFormat = outputFileFormat;
		this.projectName = projectName;
		this.selectedProjects = selectedProjects;
		this.optionList = optionList;
		this.requiredFormats = requiredFormats;
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
	
}
