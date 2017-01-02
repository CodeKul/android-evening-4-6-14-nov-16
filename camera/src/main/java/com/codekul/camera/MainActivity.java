package com.codekul.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import id.zelory.compressor.Compressor;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_VIDEO = 7952 ;
    private File file;
    private Uri fileUri;
    private final static int REQ_CAPTURE = 1542;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnCapture).setOnClickListener(this::image);
        findViewById(R.id.btnVideo).setOnClickListener(this::video);
    }

    private void video(View view) {

        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"my.mp4");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQ_VIDEO);
    }

    private void image(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "test.jpg");
        fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQ_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Image Captured Successfully", Toast.LENGTH_SHORT).show();

                ((ImageView) findViewById(R.id.imageCaptured)).setImageURI(fileUri);

                Compressor.getDefault(this)
                        .compressToFileAsObservable(file)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(this::onCompression)
                        .subscribe();
            }
        }

        if(requestCode == REQ_VIDEO) {
            if(resultCode == RESULT_OK) {
                findViewById(R.id.imageCaptured).setVisibility(View.GONE);
                findViewById(R.id.videoView).setVisibility(View.VISIBLE);
                ((VideoView)findViewById(R.id.videoView)).setVideoURI(Uri.fromFile(file));
                ((VideoView)findViewById(R.id.videoView)).start();
            }
        }
    }

    private void onCompression(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte []imageBytes = new byte[(int) file.length()];
            fis.read(imageBytes);
            fis.close();

            File fileOut = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"comp.jpg");
            FileOutputStream fos = new FileOutputStream(fileOut);
            fos.write(imageBytes);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
