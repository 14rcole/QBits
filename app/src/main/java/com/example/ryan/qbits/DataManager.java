package com.example.ryan.qbits;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ryan on 3/31/16.
 */
public class DataManager extends AsyncTask<String, Void, HashMap<String, Integer>>{
    HashMap<String, Integer> dataMap;
    public DataManager(){}

    protected String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    protected HashMap<String, Integer> doInBackground(String... params) {
        try {
            String method = params[0];
            String timeFrame = params[1];
            if(method.equals("getOccupancyData")){
                return getOccupancyData(timeFrame);
            }
        } catch (Exception e){
            System.out.println("CAUGHT AN EXCEPTION");
            System.out.println(e.getMessage());
        }
        return new HashMap<String, Integer>();
    }

    public HashMap<String, Integer> getOccupancyData(String timeFrame){
        dataMap = new HashMap<String, Integer>();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000-5").format(Calendar.getInstance().getTime());
        try {
            InputStream is = new URL("http://www.google.com/occupancy/" + timeStamp + "/" + timeFrame).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            JSONObject json = new JSONObject(readAll(rd));

            Iterator<?> keys = json.keys();
            while(keys.hasNext()){
                String key = (String)keys.next();
                String value = (String)json.get(key);
                dataMap.put(key, Integer.parseInt(value));
            }
        } catch (IOException e) {
            System.out.println("An io exception occured");
            System.out.println(e.getMessage());
        } catch (JSONException e) {
            System.out.println("A JSON exception occured");
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Something else");
            System.out.println(e.getMessage());
        }
        return dataMap;
    }

    public HashMap<String, Integer> getPercentData(String timeFrame){
        HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
        Calendar cal = new GregorianCalendar();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000-5").format(Calendar.getInstance().getTime());
        try {
            InputStream is = new URL("http://www.google.com/percent/" + timeStamp + "/" + timeFrame).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            JSONObject json = new JSONObject(readAll(rd));

            Iterator<?> keys = json.keys();
            while(keys.hasNext()){
                String key = (String)keys.next();
                String value = (String)json.get(key);
                dataMap.put(key, Integer.parseInt(value));
            }
        } catch (Exception e) {
            System.out.println("An exception occured");
        }
        return dataMap;
    }

    public HashMap<String, Integer> getEnergyData(String timeFrame){
        HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
        Calendar cal = new GregorianCalendar();
        String timeStamp = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.000-5").format(Calendar.getInstance().getTime());
        try {
            InputStream is = new URL("http://www.google.com/percent/" + timeStamp + "/" + timeFrame).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            JSONObject json = new JSONObject(readAll(rd));

            Iterator<?> keys = json.keys();
            int last = 0;
            boolean first = true;
            while(keys.hasNext()){
                String key = (String)keys.next();
                String value = (String)json.get(key);
                if(first)
                    dataMap.put(key, Integer.parseInt(value));
                else
                    dataMap.put(key, Integer.parseInt(value) + last);
                last = Integer.parseInt(value);
            }
        } catch (Exception e) {
            System.out.println("An exception occured");
        }
        return dataMap;
    }
}
