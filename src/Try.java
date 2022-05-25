import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Try {

    public ObjectOutputStream oos; // initializes the ObjectOutStream object
    public ObjectInputStream ois; // initializes the ObjectInputStream

    Try () throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/convert?to=EUR&from=USD&amount=50")
                .addHeader("apikey", "lnMuBJIJ7SIF0ww9jBKHMedBmBhyfijh")
                .build();

        Response response = client.newCall(request).execute();

        String result = response.body().string();

        System.out.println(result);

        //System.out.println(response.toString()); // prints Response{protocol=h2, code=200, message=, url=...}

        int responseCode = response.code();

        // 200 OK
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            System.out.println("HttpResponseCode: " + responseCode + " OK");
        }

        client.connectionPool().evictAll();
    }


    public static void main(String []args) throws IOException {

        Try client = new Try();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainPage page = new MainPage(client); // shows login page upon start
                page.setSize(450, 600);
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

