package edu.eci.escuelaing;

import static spark.Spark.*;

import java.net.URL;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Lab1Stocks {
    


    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
       // String logFile = "YOUR_SPARK_HOME/README.md"; // Should be some file on your system
       // SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
      //  Dataset<String> logData = spark.read().textFile(logFile).cache();
        getEndPoints();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;// returns default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void getEndPoints(){

        get("/getSymbol", (req, res) -> {
            String symbol = req.queryParams("symbol");
            return "Hello " + symbol;
        });

        get("/getTime", (req, res) -> {
            String time = req.queryParams("time");
            return "Hello " + time;
        });

        get("/getQuery", (req, res) -> {
            String time = req.queryParams("time");
            String symbol = req.queryParams("symbol");
            System.out.println("Entrada desde el front " + time);
            System.out.println("Symbol + " + symbol);
            res.type("/application.json");
            System.out.println("URL " + HttpConnectionExample.getAPIAdvantageIntraDay(symbol, time));
            return HttpConnectionExample.getAPIAdvantageIntraDay(symbol,time);
        });


    }

}
