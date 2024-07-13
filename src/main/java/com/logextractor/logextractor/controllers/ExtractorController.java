package com.logextractor.logextractor.controllers;

import java.nio.charset.StandardCharsets;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.enums.ContentType;
import com.logextractor.logextractor.enums.ErrorMessage;
import com.logextractor.logextractor.enums.FileFormat;
import com.logextractor.logextractor.enums.FileName;
import com.logextractor.logextractor.enums.HeaderType;
import com.logextractor.logextractor.interfaces.ExtractorInterface;
import com.logextractor.logextractor.mapper.ExtractorMapper;
import com.logextractor.logextractor.models.Extractor;
import com.logextractor.logextractor.models.ExtractorDto;
import com.logextractor.logextractor.services.CreateCsvService;
import com.logextractor.logextractor.services.ExtractorService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExtractorController implements AppConstants {
	Logger logger = LoggerFactory.getLogger(ExtractorController.class);

	ExtractorInterface createCsv = new CreateCsvService();

	ExtractorService extractorService = new ExtractorService();

	@GetMapping("/")
	public String extractSvnLog() {
		return "index";
	}

	@PostMapping("extract")
	public void extractor(@ModelAttribute("extractorDto") ExtractorDto extractorDto, HttpServletResponse response) {

		Extractor extractor = ExtractorMapper.INSTANCE.extractorDtoToExtractor(extractorDto);

		String outputFormat = extractor.getOutputFileFormat();

		try {
			if (FileFormat.XLSX.getFormat().equals(outputFormat)) {
				Workbook workbook = new XSSFWorkbook();
				extractor.setWorkbook(workbook);

				extractor = extractorService.generateExcelService(extractor);

				workbook = extractor.getWorkbook();

				response = createResponse(response, ContentType.SPREADSHEET, HeaderType.CONTENT_DISPOSITION,
						FileName.EXCEL_FILE.getName(), outputFormat);

				try (ServletOutputStream outputStream = response.getOutputStream()) {
					workbook.write(outputStream);
				}
				workbook.close();

			} else {
				extractor = extractorService.extractByOptions(extractor);

				if (FileFormat.CSV.getFormat().equals(outputFormat)) {
					extractor = createCsv.extract(extractor);
				}

				response = createResponse(response, ContentType.TEXT, HeaderType.CONTENT_DISPOSITION,
						extractor.getProjectName(), outputFormat);
				response.getOutputStream().write(extractor.getExtractedString().getBytes(StandardCharsets.UTF_8));
			}
		} catch (Exception e) {
			logger.error(ErrorMessage.ERROR_OCCURRED.getMessage(), e.getMessage(), e);
		}
	}

	private HttpServletResponse createResponse(HttpServletResponse response, ContentType contentType,
			HeaderType headerType, String filename, String outputFormat) {
		response.setContentType(contentType.getType());
		response.setHeader(headerType.getType(), "attachment; filename=\"" + filename + outputFormat + "\"");

		return response;
	}

}
