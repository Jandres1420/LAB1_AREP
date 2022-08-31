package edu.eci.escuelaing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.sound.sampled.Port;

import org.eclipse.jetty.http.MetaData.Response;

import edu.eci.escuelaing.entities.Cache;
import edu.eci.escuelaing.entities.Query;

public class HttpConnectionExample{

    private static final String USER_AGENT = "Mozilla/5.0";
    private static String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo";
    private static final String API_KEY = "G0PV1W2FLYMLQ6JL";
    private static String date, time;
    public void getDate(String date){
        this.date = date;
    }

    public void getTime(String time) {
        this.date = date;
    }

    public static String getAPIAdvantageIntraDay(String symbol,String time) throws IOException {
        Query query = new Query(symbol,time);
        Cache cache = new Cache(query.getQuery());
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
            System.out.println("GET request not worked");
            return "nada";
        }
        
        //System.out.println("GET DONE");
        
    }

}
