package com.logextractor.logextractor.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.CreateExcelInterface;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.models.Extractor;
import com.logextractor.logextractor.services.CreateCsvService;
import com.logextractor.logextractor.services.ExtractByProjectsService;
import com.logextractor.logextractor.services.FileToStringService;
import com.logextractor.logextractor.services.RemoveDuplicateService;
import com.logextractor.logextractor.services.RemoveUnwantedService;
import com.logextractor.logextractor.services.SortService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExtractorController implements AppConstants {
	Logger logger = LoggerFactory.getLogger(ExtractorController.class);
	
//	@Autowired
//	ExtractorInterface extractorInterface;
	@Autowired
	CreateExcelInterface createExcel;
	
	ExtractorInterface filesTostring = new FileToStringService();
	ExtractorInterface extractByProject = new ExtractByProjectsService();
	ExtractorInterface sortLines = new SortService();
	ExtractorInterface removeDuplicate = new RemoveDuplicateService();
	ExtractorInterface removeUnwanted = new RemoveUnwantedService();
	ExtractorInterface createCsv = new CreateCsvService();
	
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
			extractor = filesTostring.extract(extractor);
			extractor = extractByProject.extract(extractor);

			if (options != null) {
				if (options.contains(SORT_FILE)) {
					extractor = sortLines.extract(extractor);
				}
				
				if (options.contains(REMOVE_DUPLICATE)) {
					extractor = removeDuplicate.extract(extractor);
				}

				if (options.contains(REMOVE_UNWNTED)) {
					extractor = removeUnwanted.extract(extractor); 
				}
			}

			if(FORMAT_XLSX.equals(outputFormat)) {
				Workbook workbook = createExcel.generateExcel(extractor);
				
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + projectName + outputFormat + "\"");
				
				try (ServletOutputStream outputStream = response.getOutputStream()) {
					workbook.write(outputStream);		           
				}
				workbook.close();
				
			} else {
				if (FORMAT_CSV.equals(outputFormat)) {
					extractor = createCsv.extract(extractor);
				}
				
				response.setContentType(CONTENT_TYPE);
				response.setHeader("Content-Disposition", "attachment; filename=\"" + projectName + outputFormat + "\"");
				response.getOutputStream().write(extractor.getExtractedString().getBytes(StandardCharsets.UTF_8));
			}
		} catch (Exception e) {
			logger.debug("An error occurred: {}", e.getMessage(), e);
		}
    }
}
