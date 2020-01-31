package core.workers.excelWorker;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelFileWriter {

    private <T> XSSFWorkbook writeExcelFile(List<T> dtoArr, IAutoRowMapper<T> obj){
        //create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //create blank sheet
        XSSFSheet sheet = workbook.createSheet("sheet");

        //write data to file
        int rownum = 0;
        for (T entry: dtoArr) {
            obj.rowMapper(entry,sheet.createRow(rownum));
            rownum++;
        }
        return workbook;
    }

    public <T> void writeExcelFile(String fileName, List<T> dtoArr, IAutoRowMapper<T> obj){
        try(FileOutputStream out = new FileOutputStream(new File(fileName))) {
            writeExcelFile(dtoArr, obj).write(out);
            System.out.println("Excel file: '"+fileName+"' was created!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }

}
