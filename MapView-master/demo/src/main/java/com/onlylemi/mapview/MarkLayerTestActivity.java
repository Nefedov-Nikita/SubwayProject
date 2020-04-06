package com.onlylemi.mapview;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MarkLayer;

import java.io.IOException;
import java.util.List;

import android.view.View;

public class MarkLayerTestActivity extends AppCompatActivity {

    private MapView mapView;
    private MarkLayer markLayer;
    Dialog myDialog;
    private Window window;
    TextView metroname;
    TextView metronameinf;
    TextView infoDesc;

    String nameStation;
    String stationDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_layer_test);
        myDialog = new Dialog(this);
        myDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        mapView = (MapView) findViewById(R.id.mapview);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open("map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);
        mapView.setMapViewListener(new MapViewListener() {
            @Override
            public void onMapLoadSuccess() {
                List<PointF> marks = TestData.getMarks();
                final List<String> marksName = TestData.getMarksName();
                final List<String> marksDesc = TestData.getMarksDesc();
                markLayer = new MarkLayer(mapView, marks, marksName);
                markLayer.setMarkIsClickListener(new MarkLayer.MarkIsClickListener() {
                    @Override
                    public void markIsClick(int num) {
                        //Toast.makeText(getApplicationContext(), marksName.get(num) + "", Toast.LENGTH_SHORT).show();
                        ShowPopup(null);
                        nameStation = marksName.get(num);
                        stationDesc = marksDesc.get(num);
                        metroname = (TextView) myDialog.findViewById(R.id.metroname);
                        metroname.setText(marksName.get(num));
                    }
                });
                mapView.addLayer(markLayer);
                mapView.refresh();
            }

            @Override
            public void onMapLoadFail() {

            }

        });
    }
    public void ShowPopup (View v) {
        TextView txtclose;
        Button infobutton;
        myDialog.setContentView(R.layout.popup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        infobutton = (Button) myDialog.findViewById(R.id.info);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                ShowInfo(null);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowInfo (View v) {
        TextView txtcloseinf;
        myDialog.setContentView(R.layout.station_inf);
        metronameinf = (TextView) myDialog.findViewById(R.id.metronameinf);
        metronameinf.setText(nameStation);
        infoDesc = (TextView) myDialog.findViewById(R.id.infoDesc);
        infoDesc.setText(stationDesc);
        txtcloseinf = (TextView) myDialog.findViewById(R.id.txtcloseinf);
        txtcloseinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
