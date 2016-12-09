package com.codekul.externalstorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    // getExternalDir- /storage/emulated/0/Android/data/com.codekul.externalstorage/files/my
    // publicDir - /storage/emulated/0/Download
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fewImpMethods();

        findViewById(R.id.btnSave).setOnClickListener(this::click);
        findViewById(R.id.btnDisplay).setOnClickListener(this::click);
    }

    private void click(View view) {
        if (view.getId() == R.id.btnSave) saveAppPrivate();
        else readAppPrivate();
    }

    private void savePublicData() {

        if (isMediaGood()) {
            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "my.txt");

            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("Hello , CodeKul".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else mt(getResources().getString(R.string.bad_media));
    }

    private void readPublicData() {
        if (isMediaGood()) {
            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "my.txt");

            try {
                FileInputStream fis = new FileInputStream(file);
                StringBuilder builder = new StringBuilder();
                while (true) {
                    int ch = fis.read();
                    if (ch == -1) break;
                    else builder.append((char) ch);
                }
                fis.close();

                ((TextView) findViewById(R.id.textData))
                        .setText(builder.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else mt(getResources().getString(R.string.bad_media));
    }

    private Boolean isMediaGood() {
        return Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

    private void mt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void saveAppPrivate() {

        if (isMediaGood()) {
            File file = new File(getExternalFilesDir(""), "my.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("Hello , CodeKul".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else mt(getResources().getString(R.string.bad_media));
    }

    private void readAppPrivate() {
        if (isMediaGood()) {
            File file = new File(getExternalFilesDir(""),"my.txt");

            try {
                FileInputStream fis = new FileInputStream(file);
                StringBuilder builder = new StringBuilder();
                while (true) {
                    int ch = fis.read();
                    if (ch == -1) break;
                    else builder.append((char) ch);
                }
                fis.close();

                ((TextView) findViewById(R.id.textData))
                        .setText(builder.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else mt(getResources().getString(R.string.bad_media));
    }

    private void fewImpMethods() {

        StringBuilder builder = new StringBuilder();
        builder.append("getExternalFilesDir - ").append(getExternalFilesDir("my").getAbsolutePath()).append("\n")
                .append("publicDir - ").append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()).append("\n")
                .append("Cache - ").append(getExternalCacheDir().getAbsolutePath());

        Log.i("@codekul",""+builder.toString());
        ((TextView)findViewById(R.id.textData)).setText(builder.toString());
    }
}
