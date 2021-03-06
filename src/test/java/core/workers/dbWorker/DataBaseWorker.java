package core.workers.dbWorker;

import java.sql.*;

import static core.configuration.preparations.PropertyReader.readProperty;

public class DataBaseWorker {

    private final String JDBC_DRIVER = readProperty("dB.jdbc.driver","core.properties");
    private final String DB_URL = readProperty("dB.url", "core.properties");
    private final String DB_LOGIN = readProperty("dB.login", "core.properties");
    private final String DB_PASSWORD = readProperty("dB.password", "core.properties");

    private Connection connection = null;

    public DataBaseWorker(){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN,DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection to dB FAILED!");
            e.printStackTrace();
        }
    }

    public ResultSet execSqlQuery(String query){
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void executeUpdateQuery(String query){
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
