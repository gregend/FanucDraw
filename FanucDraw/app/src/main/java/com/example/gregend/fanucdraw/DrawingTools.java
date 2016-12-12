package com.example.gregend.fanucdraw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by gregend on 06.12.16.
 */

public class DrawingTools {

    ImageView imageView;
    Paint paint;
    Bitmap bitmap;
    Canvas canvas;
    String saveText;
    public DrawingTools(Display display, ImageView imageView){
        setupPaints();
        setupCanvasAndBitmap(display, imageView);
    }


    public void setupPaints(){

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        final Paint paintWithFill = new Paint();
        paintWithFill.setStyle(Paint.Style.FILL);
        paintWithFill.setColor(Color.WHITE);
    }

    public void setupCanvasAndBitmap(Display display, ImageView imageView){
        this.imageView = imageView;
        this.bitmap = Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(bitmap);
        this.imageView.setImageBitmap(bitmap);
    }

    float lastX = 0;
    float lastY = 0;
    boolean lineFinished = true;
    double distance = 0;
    public void  draw(float currentX, float currentY){

        distance = calculateDistance(currentX, currentY, lastX, lastY);

        if ( distance >= 20 && distance <= 40){
            canvas.drawLine(lastX, lastY, currentX, currentY, paint);
            imageView.setImageBitmap(bitmap);
            saveText += "X:" + String.valueOf((int)lastX) +
                    "Y:" + String.valueOf((int)lastY) + "\n";
            lineFinished = true;
        }else if ( distance > 40){
            lastX = currentX;
            lastY = currentY;
            saveText += "LIFT\n";
        }
        if(lineFinished){
            lastX = currentX;
            lastY = currentY;
            lineFinished = false;
        }
    }
    double calculateDistance( float currentX, float currentY, float lastX, float lastY){
        return Math.sqrt( Math.pow(( lastX - currentX ), 2) +
                Math.pow(( lastY - currentY ), 2)
        );
    }
    String getSaveText(){
        return this.saveText;
    }
}
