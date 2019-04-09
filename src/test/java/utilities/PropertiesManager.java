package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    public static void load(Pair<Properties, String>... pairs) throws IOException {
        for(int i = 0; i < pairs.length; i++) {
            InputStream inputStream = new FileInputStream(pairs[i].getPath());
            pairs[i].getProperties().load(inputStream);
        }
    }
}
