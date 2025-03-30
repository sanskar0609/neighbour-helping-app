package TestBase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReportListener implements ITestListener {
    private static final String FILE_PATH = "test-output/TestReport.xlsx";
    private Workbook workbook;
    private Sheet sheet;
    private int rowCount = 0;

    public ExcelReportListener() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Test Results");

        // Create Header Row
        Row headerRow = sheet.createRow(rowCount++);
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        String[] columns = {"Test Case", "Status", "Execution Time (ms)", "Error Message"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private void writeTestResult(ITestResult result, String status) {
        Row row = sheet.createRow(rowCount++);
        row.createCell(0).setCellValue(result.getName());
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(result.getEndMillis() - result.getStartMillis());

        if (result.getThrowable() != null) {
            row.createCell(3).setCellValue(result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writeTestResult(result, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writeTestResult(result, "Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        writeTestResult(result, "Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        try (FileOutputStream fileOut = new FileOutputStream(new File(FILE_PATH))) {
            workbook.write(fileOut);
            System.out.println("✅ Excel report generated: " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Failed to generate Excel report");
        }
    }
}
