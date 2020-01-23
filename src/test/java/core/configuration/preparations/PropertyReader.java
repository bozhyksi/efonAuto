package core.configuration.preparations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    public static String readProperty(String propName, String fileName){
                String filePath = "target/classes/"+fileName;
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
}
