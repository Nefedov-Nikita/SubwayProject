package com.onlylemi.mapview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class YandexMap extends AppCompatActivity {
    private final String MAPKIT_API_KEY = "7893c110-4a3f-489e-9227-68db4a6d2e92";
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        float latit = getIntent().getFloatExtra("latit",0);
        float longit = getIntent().getFloatExtra("longit",0);
        final Point TARGET_LOCATION = new Point(latit, longit);

        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        // Создание MapView.
        setContentView(R.layout.yandex_map);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);

        // Перемещение камеры в центр Санкт-Петербурга.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 17f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);

    }

    @Override
    protected void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}
