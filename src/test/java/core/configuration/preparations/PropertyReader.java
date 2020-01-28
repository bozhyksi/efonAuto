package core.configuration.preparations;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

    public static String readProperty(String propName, String fileName) {
        String filePath = "target/classes/" + fileName;
        try {
            InputStream inputStream = new FileInputStream(filePath);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(propName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyPropertiesFile(String sourceFilePath, String destFilePath) {
        File fsource = new File(sourceFilePath);
        File fdestination = new File(destFilePath);

        try {
            FileUtils.copyFile(fsource, fdestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPropertyToFile(String filePath, HashMap<String,String> values) {
        try( FileOutputStream outputStream = new FileOutputStream (filePath)) {
            Properties properties = new Properties();
            for (Map.Entry<String,String> entry: values.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
            properties.store(outputStream,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
