package com.example.ryan.qbits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import android.util.Log;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class OccupencyDataManager 
{
    static final String path = "LutronData2.csv";

    public static void parseCSV()
    {
        JSONArray jArray = new JSONArray();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                JSONObject json = new JSONObject();
                String[] arr = line.split(",");
                json.put("date", arr[0]);
                json.put("zone", arr[1]);
                json.put("occupied", arr[2] == "occupied" ? true : false);
                
                jArray.put(json);
            } 
            doShit(jArray);
        }
        catch (Exception e)
        {
            System.out.println("ERROR IN OCCUPANCY DATA MANAGER");
        }
    }

    public static void doShit(JSONArray jArray)
    {
        //POST method
        String data = "";
        StringBuilder sb = new StringBuilder();
        try
        {
            data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(jArray.toString(), "UTF-8");
            String link = "http://qbitsserver.mybluemix.net";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            //read server response
            //while ((line = reader.readLine()) != null)
            //{
            //    sb.append(line);
            //    Log.d("My JSON result:", sb.toString());
            //}
            //if (sb.toString().equals("no results") || sb.toString().equals(null))
            //{
            //    Log.d("NO RESULTS", "NO RESULTS");
            //    return new JSONArray();
            //}
            //return new JSONArray(sb.toString());

        }
        catch (Exception e)
        {
            Log.d("My JSON catch result", e.getMessage());
        }
    }
}