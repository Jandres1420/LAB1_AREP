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
        get("/hello", (req, res) -> {
            String name = req.queryParams("name"); 
            URL url = new URL(name);
            return "Hello "+ name;
            });
        

        get("/intraday", (req, res) -> {
            String data = req.queryParams("name");
            res.type("/application.json");
            return HttpConnectionExample.getAPIAdvantageIntraDay();
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;// returns default port if heroku-port isn't set (i.e. on localhost)
    }

}
