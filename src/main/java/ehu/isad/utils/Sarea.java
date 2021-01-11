package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.model.Txanpona;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    public static Txanpona readFromURL(String mota) {
        Txanpona txanpona = null;
        try {
            String inputLine;
            URL coinmarket= new URL("https://api.gdax.com/products/" + mota + "-eur/ticker");
            URLConnection con = coinmarket.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            inputLine = in.readLine();
            in.close();

            Gson gson = new Gson();
            txanpona = gson.fromJson(inputLine, Txanpona.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txanpona;
    }
}
