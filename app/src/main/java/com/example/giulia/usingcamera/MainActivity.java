package com.example.giulia.usingcamera;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Camera mCamera = null;
    private CameraView mCameraView = null;
    private ImageButton lupe;
    private TextView text;
    private int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            mCamera = Camera.open();
        } catch (Exception e) {
            Log.d("Error", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null) {
            mCameraView = new CameraView(this,mCamera);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);
        }

        lupe =  (ImageButton)findViewById(R.id.lupe);
        text = (TextView) findViewById(R.id.textForUser);
        id = 1;

    }


     public void changeToTextView(View view) {
         lupe.setVisibility(View.GONE);

         if(id == 1) {
             text.setText("Du wolltest dich mit deinem Freund in der Fachschaft Informatik treffen." +
                     "Und du bist etwas zu spät dran. Begib dich am besten sofort dahin um ihn nicht warten zu lassen.");
         } else if (id == 2){
             text.setText("Hier scheint er nicht zu sein. Aber es ist schließlich auch Mittagszeit. " +
                     "Vielleicht solltest du einfach mal in der Mensa vorbei schauen.");
         } else if (id == 3){
             text.setText("In der Mensa sind zwar viele Studenten, aber dein Freund ist leider nicht dabei. " +
                     "Allerdings trefft ihr euch oft in der Bibliothek. Vielleicht kannst du ihn vor dem A Gebäude finden.");
         } else if (id == 4){
             text.setText("Auch dort scheint er nicht zu sein. Du weist aber das er vom Gebäude, dass immer zu ist, fasziniert ist. " +
                     "Vielleicht ist er mal wieder dort um zu schauen, ob es noch immer verschlossen ist.");
         } else if (id == 5) {
             text.setText("Vor dem verschlossenen Gebäude ist kein Mensch. Jetzt hast du nur noch eine Idee. " +
                     "Schau doch einfach mal im Multimediaraum nach ihm");
         } else if (id == 6) {
             text.setText("Herzlichen Glückwunsch du hast ihn gefunden. Er war schon fleißig und hat die Aufgaben des Labores angefangen");
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
}
