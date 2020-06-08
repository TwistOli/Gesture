package com.example.gen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GestureLibrary gestureLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GestureOverlayView gestureview = (GestureOverlayView) findViewById(R.id.gestures1);

        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLib.load()) {
            finish();
        }

        gestureview.addOnGesturePerformedListener(handleGestureListener);
    }

    private OnGesturePerformedListener handleGestureListener = new OnGesturePerformedListener() {
        public void onGesturePerformed(GestureOverlayView gestureView,
                                       Gesture gesture) {

            ArrayList<Prediction> predictions = gestureLib.recognize(gesture);

            if (predictions.size() > 0) {
                Prediction prediction = predictions.get(0);
                if (prediction.score > 1.0) {
                    Toast.makeText(MainActivity.this,
                            "Ваш жест : " + prediction.name,
                            Toast.LENGTH_LONG).show();
                }
            }

        }
    };
}