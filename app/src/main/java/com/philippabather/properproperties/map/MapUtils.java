package com.philippabather.properproperties.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.philippabather.properproperties.R;

public class MapUtils {
    private static final double DEFAULT_PITCH = 45.00;
    private static final double DEFAULT_ZOOM = 10.00;
    private static final double DEFAULT_BEARING = -17.6;
    private static final double DEFAULT_LATITUDE_MADRID = 40.416729;
    private static final double DEFAULT_LONGITUDE_MADRID = -3.703339;

    public static PointAnnotationManager initializePointAnnotationManager(MapView mapView) {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        return PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    public static void setCameraPositionAndZoom(MapView mapView) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(DEFAULT_LONGITUDE_MADRID, DEFAULT_LATITUDE_MADRID))
                .pitch(DEFAULT_PITCH)
                .zoom(DEFAULT_ZOOM)
                .bearing(DEFAULT_BEARING)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

    public static void addMarker(PointAnnotationManager pointAnnotationManager, Bitmap bitmap, double rentalLat, double rentalLong) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(rentalLong, rentalLat))
                .withIconImage(bitmap);
        pointAnnotationManager.create(pointAnnotationOptions);
    }

}
