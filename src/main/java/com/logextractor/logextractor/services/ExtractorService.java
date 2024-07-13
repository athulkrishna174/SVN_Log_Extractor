package com.logextractor.logextractor.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.enums.ErrorMessage;
import com.logextractor.logextractor.enums.ExtractorAction;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;

@Service
public class ExtractorService implements AppConstants {
	Logger logger = LoggerFactory.getLogger(ExtractorService.class);
	
	ExtractorInterface filesTostring = new FileToStringService();
	ExtractorInterface extractByProject = new ExtractByProjectsService();
	ExtractorInterface sortLines = new SortService();
	ExtractorInterface removeDuplicate = new RemoveDuplicateService();
	ExtractorInterface removeUnwanted = new RemoveUnwantedService();
	ExtractorInterface createExcel = new CreateExcelService();	
	
	public Extractor extractByOptions(Extractor extractor) {
		List<String> options = extractor.getOptionList();
		try {
			extractor = filesTostring.extract(extractor);
			extractor = extractByProject.extract(extractor);
			
			if (options != null) {
				if (options.contains(ExtractorAction.SORT_FILE.getAction())) {
					extractor = sortLines.extract(extractor);
				}
				
				if (options.contains(ExtractorAction.REMOVE_DUPLICATE.getAction())) {
					extractor = removeDuplicate.extract(extractor);
				}
				
				if (options.contains(ExtractorAction.REMOVE_UNWANTED.getAction())) {
					extractor = removeUnwanted.extract(extractor); 
				}
			}
		} catch (Exception e) {
			logger.debug(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}
		
		return extractor;
	}
	
	public Extractor generateExcelService(Extractor extractor) {
		List<String> selectedProjects = extractor.getSelectedProjects();
		try {
			selectedProjects.stream().forEach(project -> {
				extractor.setProjectName(project);
				createExcel.extract(extractByOptions(extractor));
			});
		} catch (Exception e) {
			logger.debug(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}
		
		return extractor;		
	}
}
