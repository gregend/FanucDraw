package com.example.gregend.fanucdraw;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by gregend on 05.12.16.
 */

public class FileUtilities {
    private Writer writer;
    private String absolutePath;
    private final Context context;
    public FileUtilities(Context context){
        this.context = context;
    }
    File outputFile;
    public void createFile(String fileName){
        File root = Environment.getExternalStorageDirectory();
        File outDir = new File(root.getAbsolutePath() + File.separator + "coordinatesData");
        if (!outDir.isDirectory()){
            outDir.mkdir();
        }

        outputFile = new File(outDir, fileName);

    }

    public void write(String data){

        try {

            writer = new BufferedWriter( new FileWriter(outputFile));
            writer.write(data+ "\n");
            writer.close();
        }catch (IOException e){
            Log.w("fanucDraw", e.getMessage(), e);
        }

    }
    public Writer getWriter(){
        return writer;
    }
    public String getAbsolutePath(){
        return absolutePath;
    }
}
