package com.example.giulia.usingcamera;


import android.Manifest;
import android.content.Context;


import android.content.pm.PackageManager;
import android.hardware.Camera;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity implements LocationListener {

    private Camera mCamera = null;
    private CameraView mCameraView = null;
    private ImageButton lupe;

    private TextView text;

    private int id;
    private LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            Log.d("Error", "Failed to get camera: " + e.getMessage());
        }

        if (mCamera != null) {
            mCameraView = new CameraView(this, mCamera);
            FrameLayout camera_view = (FrameLayout) findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);
        }

        lupe = (ImageButton) findViewById(R.id.lupe);
        text = (TextView) findViewById(R.id.textForUser);
        id = 1;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, this);
    }

    public void changeToTextView(View view) {
         lupe.setVisibility(View.GONE);

         if(id == 1) {
             text.setText("Du wolltest dich mit deinem Freund vor dem E Gebäude treffen um gemeinsam zu lernen." +
                     " Du bist etwas zu spät dran. Begib dich am besten sofort dorthin um ihn nicht warten zu lassen.");
         } else if (id == 2){
             text.setText("Hier scheint er nicht zu sein. Aber es ist schließlich auch Mittagszeit. " +
                     " Vielleicht solltest du einfach mal in der Mensa vorbei schauen.");
         } else if (id == 3){
             text.setText("In der Mensa sind zwar viele Studenten, aber dein Freund ist leider nicht dabei. " +
                     " Allerdings trefft ihr euch oft in der Bibliothek. Vielleicht kannst du ihn vor dem A Gebäude finden.");
         } else if (id == 4){
             text.setText("Auch dort scheint er nicht zu sein. Du weist aber, dass er vom Gebäude, das immer zu ist, fasziniert ist. " +
                     " Vielleicht ist er mal wieder dort um zu schauen, ob es noch immer verschlossen ist.");
         } else if (id == 5) {
             text.setText("Vor dem verschlossenen Gebäude ist kein Mensch. Jetzt hast du nur noch eine Idee. " +
                     " Schau doch einfach mal im Multimediaraum nach ihm");
         } else if (id == 6) {
             text.setText("Herzlichen Glückwunsch du hast deinen Freund gefunden. Er war schon fleißig und hat die Aufgaben des Labores angefangen");
         }
         text.setVisibility(View.VISIBLE);
         id++;
    }

    public void changeToButton(View view) {
        text.setVisibility(View.GONE);
        lupe.setVisibility(View.VISIBLE);

        if (id == 7) {
            System.exit(0);
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longlitude = location.getLongitude();
        Toast.makeText(getApplicationContext(),"Latitude = " + latitude + "Longlitude = " + longlitude,Toast.LENGTH_SHORT).show();

        if (id == 2 && 49.014735 <= latitude && latitude <= 49.015290  &&   8.390394 < longlitude && longlitude < 8.391000) {
            changeToButton(null); //Fachschaft
        } else if (id == 3 && 49.014454 <= latitude && latitude <= 49.014638  &&   8.393713 < longlitude && longlitude < 8.394252) {
            changeToButton(null); //Mensa
        } else if (id == 4 && 49.015218 <= latitude && latitude <= 49.015514  &&   8.391294 < longlitude && longlitude < 8.391922) {
            changeToButton(null); //Bib
        } else if (id == 5 && 49.016359  <= latitude && latitude <= 49.016637 &&   8.390020 < longlitude && longlitude < 8.3910627) {
            changeToButton(null); //verschlossens Gebäude
        } else if (id == 6 && 49.015182 <= latitude && latitude <= 49.015602  &&   8.389390 < longlitude && longlitude < 8.389597) {
            changeToButton(null); //Multimediaraum
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(getApplicationContext(), "GPS Disabled", Toast.LENGTH_SHORT).show();
    }



}