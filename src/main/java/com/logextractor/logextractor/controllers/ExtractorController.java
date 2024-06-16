package com.logextractor.logextractor.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExtractorController implements AppConstants {
	Logger logger = LoggerFactory.getLogger(ExtractorController.class);
	
	@Autowired
	ExtractorInterface extractorInterface;
	
    @GetMapping("/")
    public String extractSvnLog(){
        return "index";
    }
    
    @PostMapping("extract")
    public void extractor(@ModelAttribute("extractor") Extractor extractor,
    		HttpServletResponse response){
    	
    	String projectName = extractor.getSelectedProject();
		String outputFormat = extractor.getOutputFileFormat();
		List<String> options = extractor.getOptionList();

		try {
			extractor = extractorInterface.extractFileToString(extractor);
			extractor = extractorInterface.extractByProject(extractor);

			if (options != null) {
				if (options.contains(SORT_FILE)) {
					extractor = extractorInterface.sortLines(extractor);
				}
				
				if (options.contains(REMOVE_DUPLICATE)) {
					extractor = extractorInterface.removeDuplicateLines(extractor);
				}

				if (options.contains(REMOVE_UNWNTED)) {
					extractor = extractorInterface.removeUnwantedLines(extractor);
				}
			}

			if (FORMAT_CSV.equals(outputFormat)) {
				extractor = extractorInterface.createCsv(extractor);
			}

			response.setContentType(CONTENT_TYPE);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + projectName + outputFormat + "\"");
			response.getOutputStream().write(extractor.getExtractedString().getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			logger.debug("An error occurred: {}", e.getMessage(), e);
		}
    }
}
