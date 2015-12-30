package edu.uis.radiogis.sdr2;

import android.app.Activity;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.graphics.Color;
import com.androidplot.Plot;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import android.app.Activity;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.webkit.WebView;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import java.util.Arrays;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Juanfe on 15/12/2015.
 */
public class Grafica extends Activity {


    private WebView mWebView;;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica);

        // initialize our XYPlot reference:
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        mWebView.loadUrl("http:///");

    }
}