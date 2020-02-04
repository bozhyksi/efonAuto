package core.workers.excelWorker;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelFileWorker {

    private <T> XSSFWorkbook createWorkbook(List<T> dtoArr, IAutoRowMapper<T> obj){
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
            createWorkbook(dtoArr, obj).write(out);
            System.out.println("Excel file: '"+fileName+"' was created!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String fileName){
        File file = new File(fileName);
        file.delete();
    }

    public<T> List<T> readExcelFile(String fileName, IAutoRowMapper<T> obj, T dto){
        List<T> arr = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(new File(fileName))){
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();


            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                arr.add(obj.rowReaderMapper(row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public boolean checkIfFileExists(String fileName){
        return new File(fileName).exists();
    }
}
