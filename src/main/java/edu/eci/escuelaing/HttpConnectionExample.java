package edu.eci.escuelaing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.sound.sampled.Port;

import org.eclipse.jetty.http.MetaData.Response;

import edu.eci.escuelaing.entities.Cache;
import edu.eci.escuelaing.entities.Query;

public class HttpConnectionExample{

    private static final String USER_AGENT = "Mozilla/5.0";

    public static String getAPIAdvantageIntraDay(String symbol,String time) throws IOException {
        Query query = new Query(symbol,time);
        System.out.println("URL DESDE CLASE QUERY " + query);
        Cache cache = new Cache(query.getQuery());
        List<String> memoria  = cache.getActualMemory();
        URL obj = new URL(query.getQuery());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
             memoria.add("fin");
             System.out.println("GET request not worked");
            return "nada";
        }
       
        //System.out.println("GET DONE");
        
    }

}
