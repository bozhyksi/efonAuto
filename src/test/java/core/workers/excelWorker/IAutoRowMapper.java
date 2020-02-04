package core.workers.excelWorker;

import org.apache.poi.ss.usermodel.Row;

public interface IAutoRowMapper<T> {

    void rowMapper(T dto, Row row);

    T rowReaderMapper(Row row);
}
