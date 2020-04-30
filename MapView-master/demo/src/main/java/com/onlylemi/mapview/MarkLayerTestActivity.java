package com.onlylemi.mapview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MarkLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
    TextView timeDist;

    String nameStation;
    String stationDesc;
    String firtsSt;
    String secSt;
    static int distance = 0;

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

    @Override
    protected void onResume() {
        super.onResume();
        if (distance != 0) {
            ShowTime(null);
        }
    }

    public void ShowPopup (View v) {
        final background bg = new background(this);
        TextView txtclose;
        Button otButton;
        Button sudButton;
        Button infobutton;
        myDialog.setContentView(R.layout.popup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        infobutton = (Button) myDialog.findViewById(R.id.info);
        otButton = (Button) myDialog.findViewById(R.id.Ot);
        sudButton = (Button) myDialog.findViewById(R.id.Sud);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        otButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secSt == null) {
                    firtsSt = nameStation;
                    myDialog.dismiss();
                }
                else {
                    myDialog.dismiss();
                    firtsSt = nameStation;
                    ShortestPath.GraphFind(firtsSt,secSt);
                    onResume();
                }
            }
        });

        sudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firtsSt == null) {
                    secSt = nameStation;
                    myDialog.dismiss();
                }
                else {
                    myDialog.dismiss();
                    secSt = nameStation;
                    ShortestPath.GraphFind(firtsSt, secSt);
                    onResume();
                }
            }
        });

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                bg.execute(nameStation);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowInfo (View v, String desc) {
        TextView txtcloseinf;
        myDialog.setContentView(R.layout.station_inf);
        metronameinf = (TextView) myDialog.findViewById(R.id.metronameinf);
        metronameinf.setText(nameStation);
        infoDesc = (TextView) myDialog.findViewById(R.id.infoDesc);
        infoDesc.setText(desc);
        //infoDesc.setText(stationDesc);
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

    public void ShowTime (View v) {
        TextView otStation;
        TextView doStation;
        TextView txtclosetime;
        myDialog.setContentView(R.layout.time);
        timeDist = (TextView) myDialog.findViewById(R.id.timer);
        timeDist.setText(distance+" Мин.");
        otStation = (TextView) myDialog.findViewById(R.id.otStation);
        doStation = (TextView) myDialog.findViewById(R.id.doStation);
        otStation.setText(firtsSt);
        doStation.setText(secSt);
        txtclosetime = (TextView) myDialog.findViewById(R.id.txtclosetime);
        txtclosetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public class background extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        String result = "";
        public Boolean login = false;
        public background(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(String s) {

            ShowInfo(null, s);
            //Log.d("@@@@@@@@@@@@@@@@@",s);
        }

        @Override
        public String doInBackground(String... voids) {
            String user = voids[0];

            String connstr = "http://subway-project.000webhostapp.com/info.php";

            try {
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();

                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
                String line ="";
                while ((line = reader.readLine()) != null)
                {
                    result += line;
                }
                reader.close();
                ips.close();
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                result = e.getMessage();
            } catch (IOException e) {
                result = e.getMessage();
            }

            return result;
        }
    }
}
