package tests.phonebookPageTests.phonebookPageTestData;

import core.workers.excelWorker.IAutoRowMapper;
import org.apache.poi.ss.usermodel.Row;

public class PhonebookRowMapper implements IAutoRowMapper<Phonebook> {

    @Override
    public void rowMapper(Phonebook dto, Row row) {
        row.createCell(0).setCellValue(dto.getNumber());
        row.createCell(1).setCellValue(dto.getName());
        row.createCell(2).setCellValue(dto.getShortDial());
    }
}
