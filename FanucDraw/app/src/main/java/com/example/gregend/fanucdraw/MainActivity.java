package com.example.gregend.fanucdraw;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Dialog drawDialog;
    EditText dialogDrawTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        drawDialog =  new Dialog(this);
        drawDialog.setContentView(R.layout.draw_dialog_layout);
        dialogDrawTextInput = (EditText)drawDialog.findViewById(R.id.drawDialogInput);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void loadButtonClicked(View view) {

    }

    public void drawButtonClicked(View view) {
        drawDialog.show();
    }

    public void dialogNegativeButtonClicked(View view) {
        drawDialog.dismiss();
    }

    public void dialogPositiveButtonClicked(View view) {
        Intent startDrawing = new Intent(this, DrawActivity.class);
        startDrawing.putExtra("file_name", dialogDrawTextInput.getText().toString());
        drawDialog.dismiss();
        startActivity(startDrawing);
    }
}
