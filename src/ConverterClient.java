import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;


public class ConverterClient {

    public ObjectOutputStream oos; // initializes the ObjectOutStream object
    public ObjectInputStream ois; // initializes the ObjectInputStream
    public OkHttpClient okHttpClient;

    ConverterClient(String toCurrency, String fromCurrency, String fromAmount) throws IOException {

        this.oos = new ObjectOutputStream(new FileOutputStream("output.txt"));
        String url = "https://api.apilayer.com/exchangerates_data/convert?to=%s&from=%s&amount=%s";

        String newUrl = String.format(url, toCurrency, fromCurrency, fromAmount);

        this.okHttpClient = new OkHttpClient();
        okHttpClient.connectionPool();
        Request request = new Request.Builder()
                .url(newUrl)
                .addHeader("apikey", "lnMuBJIJ7SIF0ww9jBKHMedBmBhyfijh")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        int responseCode = response.code();

        // 200 OK
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            System.out.println("HttpResponseCode: " + responseCode + " OK");

            String stringResponse = response.body().string();
            //InputStream inputStream = response.body().byteStream();

            System.out.println(stringResponse); // PRINTS JSON STRING

            JSONObject jsonObject = new JSONObject(stringResponse);
            fromCurrency = jsonObject.getJSONObject("query").getString("from");
            toCurrency = jsonObject.getJSONObject("query").getString("to");
            fromAmount = jsonObject.getJSONObject("query").get("amount").toString();
            String output = jsonObject.get("result").toString();

            System.out.println(fromCurrency);
            System.out.println(toCurrency);
            System.out.println(fromAmount);
            System.out.println(output);

            oos.writeObject(output);

        }

        okHttpClient.connectionPool().evictAll();

    }

    public String buildEndpoint(String request){
        return null;
    }


    public static void main(String []args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainPage page = new MainPage(); // shows login page upon start
                page.setSize(600, 600);
                page.setLocationRelativeTo(null);
                page.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                page.setVisible(true);
            }
        });

    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }



}

