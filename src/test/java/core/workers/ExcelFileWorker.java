package core.workers;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileWorker {

    public void writeExcelFile(Phonebook[] phonebookArray){
        String path = "test.xlsx";

        //create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //create blank sheet
        XSSFSheet sheet = workbook.createSheet("sheet");

        //write data to file
        int rownum = 0;
        for (Phonebook entry: phonebookArray) {
            Row row = sheet.createRow(rownum++);
            int cellnum=0;

            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(entry.getNumber());

            Cell cell2 = row.createCell(cellnum++);
            cell2.setCellValue(entry.getName());

            Cell cell3 = row.createCell(cellnum++);
            cell3.setCellValue(entry.getShortDial());
        }

        try(FileOutputStream out = new FileOutputStream(new File(path))) {
            workbook.write(out);
            System.out.println("Excel file: '"+path+"' was created!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ;

    }

}
