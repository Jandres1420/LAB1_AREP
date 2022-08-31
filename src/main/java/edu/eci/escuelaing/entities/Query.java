package edu.eci.escuelaing.entities;

import java.util.List;

public class Query {

    private String url=  "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
    , specify;
    private Cache cache;
    public Query(String symbol, String time  ){
        specify = url+ symbol + "&interval=" + time + "&apikey=G0PV1W2FLYMLQ6JL";
        cache = new Cache(specify);
    }


    public String getQuery(){
        return specify;
    }

    public List<String> getCache(){
        return cache.getActualMemory();
    }

}
