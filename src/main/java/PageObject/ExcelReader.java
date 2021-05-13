package PageObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.nio.file.Path;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.*;

public class ExcelReader {
    public List<String> contentData;
    public List<String> listHeader = new ArrayList<>();
    public List<String> rowData;
    public List<String> config;
    public List<String> data;
    private XSSFWorkbook excelFile;
    private File file;
    private FileInputStream inputStream;
    public String filePath = "src/main/resources/data/";
    public String fileName = "FacialFolder.xlsx";
    public String sheetNameData = "";


    public void startWorkingExcel(String sheetNameData) throws IOException {
        this.sheetNameData = sheetNameData;
        // Handle to action with excel file -- prepare to write
        // Create an object of File class to open xlsx file
        file = new File(filePath + fileName);
        // Create an object of FileInputStream class to read excel file
        inputStream = new FileInputStream(file);
        // Create object of XSSFWorkbook class
        excelFile = new XSSFWorkbook(inputStream);
        // ## Get header name
        listHeader = getRowData(0);
    }

    public void finishWorkingExcel() throws IOException {
        // ### Done on work with excel file.
        // Close input stream
        inputStream.close();
        // Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        // write data in the excel file
        excelFile.write(outputStream);
        // close output stream
        outputStream.close();
    }

    // ## collect a row data for a cycle of test script
    public List<String> getRowData(int index) {
        // ## Define new content variable
        contentData = new ArrayList<String>();
        rowData = new ArrayList<>();
        // ## Read sheet inside the workbook by its name
        Sheet sheet = excelFile.getSheet(sheetNameData);
        // ## Get the first row- header
        Row firstRow = sheet.getRow(index);
        // ## Get length of the first row-num of column
        int column = firstRow.getLastCellNum();
        // ## Get header name
        String cellValue;
        for (int i = 0; i < column; i++) {
            CellType type = firstRow.getCell(i).getCellTypeEnum();
            if (type.toString().equals("NUMERIC")) {
                cellValue = String.valueOf(firstRow.getCell(i).getNumericCellValue());
            } else {
                cellValue = firstRow.getCell(i).getStringCellValue();
            }
            rowData.add(cellValue);
        }
        return rowData;
    }

    public int getColumnIndex(String getColumn, List<String> listCols) {
        int index = -1;
        for (int i = 0; i < listCols.size(); i++) {
            if (listCols.get(i).toString().equals(getColumn)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public String getPathByUIName(String UIName) {
        String path = null;
        // ## Read sheet inside the workbook by its name
        Sheet sheet = excelFile.getSheet(sheetNameData);

        int countRows = sheet.getLastRowNum();
        Row row;
        for (int i = 0; i < countRows; i++) {
            row = sheet.getRow(i);
            Cell firstCell = row.getCell(0);
            if (firstCell.getStringCellValue().equals(UIName)) {
                Cell secondCell = row.getCell(1);
                path = secondCell.getStringCellValue();
                break;
            }
        }
        return path;
    }

    public String getColumDataByRowData(String rowData, String sourceColumnName, String targetColumnName) throws IOException {
        String path = null;
        // ## Read sheet inside the workbook by its name
        Sheet sheet = excelFile.getSheet(sheetNameData);

        int countRows = sheet.getLastRowNum();
        int indexByColumnName1 = getColumnIndex(sourceColumnName, listHeader);
        int indexByColumnName2 = getColumnIndex(targetColumnName, listHeader);
        Row row;

        for (int i = 0; i <= countRows; i++) {
            row = sheet.getRow(i);
            Cell firstCell = row.getCell(indexByColumnName1);
            if (firstCell.getStringCellValue().equals(rowData)) {
                Cell secondCell = row.getCell(indexByColumnName2);
                path = secondCell.getStringCellValue();
                break;
            }
        }
        return path;
    }

    public String getDataByColumnName(String columnName) {
        String value = "";
        int index = getColumnIndex(columnName, listHeader);
        value = rowData.get(index).toString();
        return value;
    }

    public int getNumRow() {
        int column = 0;
        // ## Read sheet inside the workbook by its name
        Sheet sheet = excelFile.getSheet(sheetNameData);
        // ## Get the location of the insert row- below the last having data row
        column = sheet.getLastRowNum() - sheet.getFirstRowNum();
        return column;
    }

    //Get configuration-----------------------
    public List<String> getConfiguration()
    {
        config = new ArrayList<>();
        int order = 1;
        Sheet sheet = excelFile.getSheet("config");
        Row firstRow = sheet.getRow(1);
        int column = firstRow.getLastCellNum();
        String cellValue;
        for (int i = 0; i < column; i++) {
            CellType type = firstRow.getCell(i).getCellTypeEnum();
            if (type.toString().equals("NUMERIC")) {
                int value = Integer.valueOf((int) firstRow.getCell(i).getNumericCellValue());
                cellValue = String.valueOf(value);
            } else {
                cellValue = firstRow.getCell(i).getStringCellValue();
            }
            config.add(cellValue);
        }
        return config;
    }

    public List<String> getData(){
        List<String> config = getConfiguration();
        data = new ArrayList<>();
        String sheetName = config.get(0).toString();
        int order = Integer.parseInt(config.get(1).toString());
        Sheet sheet = excelFile.getSheet(sheetName);
        Row firstRow = sheet.getRow(order);
        int column = firstRow.getLastCellNum();
        String cellValue;
        for (int i = 0; i < column; i++) {
            CellType type = firstRow.getCell(i).getCellTypeEnum();
            if (type.toString().equals("NUMERIC")) {
                int value = Integer.valueOf((int) firstRow.getCell(i).getNumericCellValue());
                cellValue = String.valueOf(value);
            } else {
                cellValue = firstRow.getCell(i).getStringCellValue();
            }
            data.add(cellValue);
        }
        return data;
    }


}
