package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    // Cache fields
    private static String lastFilePath = null;
    private static String lastSheetName = null;
    private static Object[][] lastData = null;

    public static Object[][] getTestData(String filePath, String sheetName) {
        // Check cache
        if (filePath.equals(lastFilePath) && sheetName.equals(lastSheetName) && lastData != null) {
            return lastData;
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = getCellValue(cell);
                }
            }

            // Update cache
            lastFilePath = filePath;
            lastSheetName = sheetName;
            lastData = data;

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + filePath, e);
        }
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() : cell.getNumericCellValue();
            case BOOLEAN: return cell.getBooleanCellValue();
            case FORMULA: return cell.getCellFormula();
            case BLANK: return "";
            default: return "";
        }
    }
}
