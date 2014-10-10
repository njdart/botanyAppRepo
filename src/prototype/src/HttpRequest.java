import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

public class HttpRequest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://192.168.1.73");

        String data = "";
        //Generate 50KB of payload data
        for(int i = 0; i < 5; i++) {
            data += (char)(65+new Random().nextInt(23));
        }
        data = URLEncoder.encode(data, "UTF-8");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        con.setDoOutput(true);
        OutputStream wr = con.getOutputStream();
        wr.write(data.getBytes("UTF-8"));
        wr.flush();
        wr.close();

        /* Apparently  it is ABSOLUTELY required to receive a response code
         * from the server otherwise your data just sits on the OutputStream
         * (forever?) WTF java?!? at least document these things... :(
         * +10 xp, -8 hours
         */
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post data : " + data);
        System.out.println("Response Code : " + responseCode);
    }
}
