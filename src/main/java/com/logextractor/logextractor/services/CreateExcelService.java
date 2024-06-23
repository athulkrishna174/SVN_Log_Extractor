package com.logextractor.logextractor.services;

import java.util.Scanner;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.logextractor.logextractor.constants.AppConstants;
import com.logextractor.logextractor.interfaces.CreateExcelInterface;
import com.logextractor.logextractor.models.Extractor;

@Service
public class CreateExcelService implements AppConstants, CreateExcelInterface {
	Logger logger = LoggerFactory.getLogger(CreateExcelService.class);

	@Override
	public Workbook generateExcel(Extractor extractor) {

		String extractedString = extractor.getExtractedString();
		
		Workbook workbook = new XSSFWorkbook();

		try (Scanner lines = new Scanner(extractedString)) {
			
			Sheet sheet = workbook.createSheet(extractor.getSelectedProject());

			int rowCount = 0;

			XSSFRow row = (XSSFRow) sheet.createRow(rowCount++);

			XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setBorderTop(BorderStyle.THIN);
			headerStyle.setBorderBottom(BorderStyle.THIN);
			headerStyle.setBorderLeft(BorderStyle.THIN);
			headerStyle.setBorderRight(BorderStyle.THIN);
			headerStyle.setWrapText(true);

			XSSFFont headerFont = ((XSSFWorkbook) workbook).createFont();
			headerFont.setFontName("Calibri");
			headerFont.setColor(IndexedColors.WHITE.getIndex());
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			XSSFCell headerCell = row.createCell(1);
			headerCell.setCellValue("Files");
			headerCell.setCellStyle(headerStyle);

			XSSFCellStyle contentStyle = (XSSFCellStyle) workbook.createCellStyle();
			contentStyle.setBorderTop(BorderStyle.THIN);
			contentStyle.setBorderBottom(BorderStyle.THIN);
			contentStyle.setBorderLeft(BorderStyle.THIN);
			contentStyle.setBorderRight(BorderStyle.THIN);

			XSSFFont contentFont = (XSSFFont) workbook.createFont();
			contentFont.setFontName("Calibri");
			contentStyle.setFont(contentFont);

			while (lines.hasNextLine()) {
				row = (XSSFRow) sheet.createRow(rowCount++);

				XSSFCell contentCell = row.createCell(1);
				contentCell.setCellValue(lines.nextLine().trim());
				contentCell.setCellStyle(contentStyle);
			}

			sheet.autoSizeColumn(1);
		} catch (Exception e) {
			logger.debug(AN_ERROR_OCCURRED, e.getMessage(), e);
		}

		return workbook;
	}

	public static void main(String[] args) {
		CreateExcelService service = new CreateExcelService();

		Extractor extractor = new Extractor();
		extractor.setSelectedProject("test");

		extractor.setExtractedString(
				"M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/.settings/org.eclipse.wst.common.project.facet.core.xml\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ebs/mymt/common/MessageProcess.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ebs/mymt/common/MessageProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ebs/mymt/common/ScheduleHelperEJBBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ebs/mymt/reports/ReportTemplate.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ebs/mymt/reports/ReportTemplateBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ebs/mymt/schedule/MymtScheduleBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/common/util/MsgProcessUtil.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/common/util/SubscribeNotifyUtil.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/common/window/WindowBasedProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/mymt/common/CommonMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/mymt/common/MsgProcess.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/mymt/common/MsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/mymt/corppymt/CorpPymtMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/mymt/mandate/MandateMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/mymt/stmt/StmtMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/ach/ACHMsgProcess.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/ach/ACHMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/bankhub/BankHubMsgProcess.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/bankhub/BankHubMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/fpx/FPXMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/realtime/RealTimeMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/realtime/util/ProcessAsynchronousMsgsCtrlr.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/rtgs/RTGSMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/rtp/RTPMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/rtp/util/RTPMsgProcessUtil.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecs/pymts/wps/WPSMsgProcessBean.java\r\n"
						+ "M /MYMT/branches/MYMT 7.1.0/MsgProcessEJB/src/com/ecsfin/integratedfiles/IntegratedFilesProcessBean.java");

		service.generateExcel(extractor);

		System.out.println("finished");
	}

}
