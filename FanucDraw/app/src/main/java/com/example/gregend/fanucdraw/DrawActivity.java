package com.example.gregend.fanucdraw;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class DrawActivity extends AppCompatActivity {


    boolean lineFinished = true;

    float currentX = 0;
    float currentY = 0;
    FileUtilities dataFile;
    DrawingTools tempDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final Display display = getWindowManager().getDefaultDisplay();
        final DrawingTools drawer = new DrawingTools(display, imageView);
        tempDrawer = drawer;
        Intent startingIntent = getIntent();
        String fileName = startingIntent.getStringExtra("file_name");
        dataFile = new FileUtilities(this.getApplicationContext());
        dataFile.createFile(fileName + ".txt");

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                currentX = motionEvent.getX();
                currentY = motionEvent.getY();
                drawer.draw(currentX, currentY);

                return true;

            }

        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        dataFile.write(tempDrawer.getSaveText());
    }
}
