import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class CheckServerResponse {
    public static int checkResponse(String whichRequestToSend) throws IOException {
        URL url = getUrl();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(whichRequestToSend);
        conn.connect();
        int responseCode = conn.getResponseCode();
        System.out.println("Status code is:"+responseCode);
        return responseCode;
    }
    public static URL getUrl() throws IOException {
        URL url = new URL(loadProperties().getProperty("url"));
        return url;

    }

    public static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream propFile = new FileInputStream("src/main/resources/config.properties");
        prop.load(propFile);
        return prop;

    }
}
