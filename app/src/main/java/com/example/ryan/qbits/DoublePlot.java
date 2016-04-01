package com.example.ryan.qbits;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ryan on 3/30/16.
 */
public class DoublePlot {
    public static final String[] months = {" ", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "December"};
    private static final String colorPrimary = "#455A64";
    private static final String colorSecondary = "#FF9800";
    private ArrayList<String> labels;
    public DoublePlot(){
        labels = new ArrayList<String>();
    }

    public void createDoublePlot(LineChart lineChart, HashMap<String, Integer> values1, HashMap<String, Integer> values2, String span){
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);

        lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getAxisLeft().setDrawLabels(true);
        lineChart.getAxisLeft().setSpaceTop((float) 10);
        lineChart.getAxisLeft().setAxisMaxValue(20);

        lineChart.getAxisLeft().setDrawZeroLine(true);
        lineChart.getAxisLeft().setDrawTopYLabelEntry(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(createLineDataSet(createEntries(values1, true), "First Label", colorPrimary, true));
        dataSets.add(createLineDataSet(createEntries(values2, false), "Second Label", colorSecondary, false));

        if(span.equals("year"))
            labels = new ArrayList<String>(Arrays.asList(months));

        LineData data = new LineData(labels, dataSets);
        lineChart.setData(data);
        lineChart.getLegend().setTextSize(20);
    }

    private ArrayList<Entry> createEntries(HashMap<String, Integer> values, boolean hasLabels){
        ArrayList<Entry> entries = new ArrayList<Entry>();
        int i = 0;
        for(String key : values.keySet()){
            if(hasLabels)
                labels.add(key);
            entries.add(new Entry(values.get(key), i));
            i++;
        }
        return entries;
    }

    private LineDataSet createLineDataSet(ArrayList<Entry> entries, String label, String color,
                                          boolean isFilled) {
        LineDataSet dataset = new LineDataSet(entries, label);
        dataset.setDrawCircles(false);
        dataset.setDrawValues(false);
        dataset.setColor(Color.parseColor(color));
        if(isFilled){
            dataset.setDrawFilled(true);
            dataset.setFillColor(Color.parseColor(color));
            dataset.setFillAlpha(255);
        }
        return dataset;
    }

    private ArrayList<String> createLegendFromArray(String[] legend){
        ArrayList<String> legendList = new ArrayList<String>();
        for(String l : legend){
            legendList.add(l);
        }

        return legendList;
    }
}
