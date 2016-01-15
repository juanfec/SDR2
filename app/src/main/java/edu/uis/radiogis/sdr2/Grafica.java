package edu.uis.radiogis.sdr2;

import android.app.Activity;
import android.os.Bundle;

import com.androidplot.xy.XYPlot;


/**
 * Created by Juanfe on 15/12/2015.
 */
public class Grafica extends Activity {


    private XYPlot plot;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica);

    }
}