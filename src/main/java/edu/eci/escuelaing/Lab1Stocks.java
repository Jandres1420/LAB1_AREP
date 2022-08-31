package edu.eci.escuelaing;

import static spark.Spark.*;

import java.net.URL;

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
        String symbol = "",time = "" ;
        get("/getSymbol", (req, res) -> {
            String name = req.queryParams("name");
            symbol = req.queryParams("name");
            URL url = new URL(name);
            return "Hello " + name;
        });

        get("/getTime", (req, res) -> {
            String name = req.queryParams("name");
            time = req.queryParams("name");
            URL url = new URL(name);
            return "Hello " + name;
        });

        get("/getQuery", (req, res) -> {
            String data = req.queryParams("name");
            System.out.println("Entrada desde el front " + data);
            res.type("/application.json");
            return HttpConnectionExample.getAPIAdvantageIntraDay(symbol,time);
        });


    }

}
