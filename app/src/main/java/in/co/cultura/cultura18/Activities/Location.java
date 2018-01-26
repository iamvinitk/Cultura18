package in.co.cultura.cultura18.Activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import in.co.cultura.cultura18.R;

public class Location extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public static final CameraPosition CMRIT =
            new CameraPosition.Builder().target(new LatLng(12.9661011, 77.712195)).zoom(18.0f).bearing(0.0f).tilt(25.0f).build();
    public static final CameraPosition CURRENT =
            new CameraPosition.Builder().target(new LatLng(12.957048d, 77.598928d))
                    .zoom(12.0f)
                    .bearing(0.0f)
                    .tilt(25.0f)
                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        if (Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_location);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_map);
        supportMapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(1);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CURRENT));
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CURRENT));
                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        onGoToCMRIT();
                    }
                });
            }
        });
        // We will provide our own zoom controls.
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(CMRIT.target).visible(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-33.87365, 151.20689), 10));
    }

    public void onGoToCMRIT() {
        if (this.mMap != null) {
            this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CMRIT), 3000, null);
        }
    }
}
