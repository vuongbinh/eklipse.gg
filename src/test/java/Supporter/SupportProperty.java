package Supporter;

import java.io.*;
import java.util.Properties;

public class SupportProperty {
    Properties pros = new Properties();

    public Properties load(String fileName) {
        {
            try {
                InputStream input = new FileInputStream("src/test/java/Supporter/resources/" + fileName + ".properties");
                pros.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pros;
    }

    public void saveAccount(String email, String pwd) {
        try {
            pros.setProperty("email", email);
            pros.setProperty("password", pwd);
            OutputStream output = new FileOutputStream("src/test/java/framework/supporter/resources/config.properties");
            pros.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
