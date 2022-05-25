import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class main {
    public static void main(String []args) throws IOException{
        URLConnection connection = new URL
                ("https://api.apilayer.com/fixer/convert?to=EURO&from=USD&amount=45").openConnection();

        connection.setRequestProperty("apikey", "lnMuBJIJ7SIF0ww9jBKHMedBmBhyfijh");

        //Get Response
        InputStream is = connection.getInputStream();
        System.out.println(connection.getContentType());

        //System.out.println(connection.get());
    }
}