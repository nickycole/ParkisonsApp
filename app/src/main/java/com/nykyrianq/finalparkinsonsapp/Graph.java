package com.nykyrianq.finalparkinsonsapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

//import com.jjoe64.graphview.GraphView.GraphViewData;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.util.List;


public class Graph extends AppCompatActivity implements SensorEventListener {
    // Graph View Variables
    private Handler mHandler = new Handler();
    private LineGraphSeries<DataPoint> series;

    // Sensor variables
    private Sensor mySensor;
    private SensorManager SM;

    private double x = 0;
    private double y = 0;
    private double z = 0;

//    private List<GraphViewData> seriesX;
//    private List<GraphViewData> seriesY;
//    private List<GraphViewData> seriesZ;


//    private float x,y,z;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    //private static final int SHAKE_TRESHOLD = 600;


    //Not Needed
//    private double lastXPoint = 2 ;
//    private Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<>(new DataPoint[] {
                // Data Points
                new DataPoint(0, 1),
                new DataPoint(2, 5),
                new DataPoint(3, 2)


        });
        // add datapoints to graph
        graph.addSeries(series);

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setXAxisBoundsManual(true);

        // make graph scalable
        graph.getViewport().setScalable(true);

        /******************************
                Sensor Code
         *****************************/
        //Create Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register Sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL); //Normal or Fastest Best??


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        // do I need this
//        long curTime = System.currentTimeMillis();
//
//        if ((curTime - lastUpdate) > 100) {
//            long diffTime = (curTime - lastUpdate);
//            lastUpdate = curTime;
//        }

//        last_x = x;
//        last_y = y;
//        last_z = z;




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
