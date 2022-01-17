package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Callable implements Runnable {

    @Override
    public void run() {
    }

    public static String call(String URL) throws IOException {
        String aut = "False";
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (URL.contains("GET")) {
            conn.setRequestMethod("GET");
        }
        if (URL.contains("POST")) {
            conn.setRequestMethod("POST");
        }
        if (URL.contains("PUT")) {
            conn.setRequestMethod("PUT");
        }
        if (URL.contains("DELETE")) {
            conn.setRequestMethod("DELETE");
        }
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        String output;
        System.out.println(conn.getResponseCode());
        if (conn.getResponseCode() == 200) {
            aut = "True";
        } else {
            aut = "False";
        }
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            if (output.contains("Waiting for other players")) {
                aut = "False";
                String PlayerNo = output.split("\\?")[1].split(":")[1];
                System.out.println(PlayerNo);
                if (PlayerNo.equals("0")) {
                    aut = "True";
                }
            }
            if (output.contains("Fight has been started")) {
                aut = "False";
                String PlayerNo = output.split("\\?")[1].split(":")[1];
                System.out.println(PlayerNo);
                if (PlayerNo.equals("0")) {
                    aut = "True";
                }
            }
            if (output.contains("Fight is over. You receive")) {
                String PlayerPoint = output.split("\\?")[1].split(":")[1];
                System.out.println(PlayerPoint);
                aut = PlayerPoint;

            }
        }
        conn.disconnect();
        return aut;
    }
}

