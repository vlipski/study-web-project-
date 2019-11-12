package by.lipski.socket;



import org.apache.log4j.Logger;
import java.net.*;


class HttpConnection {

    private static final Logger log = Logger.getLogger(HttpConnection.class);
    private static Socket socket;

    static HttpURLConnection getConnection() {
        try {
            URL url = new URL("http://localhost:8090/order");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
