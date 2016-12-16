package com.codekul.uimanipulation;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnOkay).setOnClickListener(this::click);
    }

    private void click(View view) {
        //new MyTask().execute(0,100/* Params*/);

        //new MyTask().execute(new Integer[]{0,100});

        howRxjavaWillHelpMe();
    }

    private void simpleRun() {
        TextView textView = (TextView) findViewById(R.id.textInfo);

        for(int i = 0; i < 100 ;i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView.setText(String.valueOf(i));
        }
    }

    private void newThread() {

        new Thread(() -> {

            TextView textView = (TextView) findViewById(R.id.textInfo);

            for(int i = 0; i < 100 ;i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textView.setText(String.valueOf(i));
            }
        }).start();
    }

    private class MyTask extends AsyncTask<Integer/*Params*/, Integer/*Progress*/, Boolean/*Result*/> {

        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,"Counter","I am counting");
            //Ui Thread
        }

        @Override
        protected Boolean/*Result*/ doInBackground(Integer... params/*Params for execute method*/) {
            // Worker Thread
            for(int i = params[0]; i < params[1] ;i++){

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i/* progress*/);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean /*Result*/) {
            super.onPostExecute(aBoolean);

            progressDialog.dismiss();
            if(aBoolean){

            }
            // UI Thread
        }

        @Override
        protected void onProgressUpdate(Integer ... values/*progress*/) {
            super.onProgressUpdate(values);

            ((TextView)findViewById(R.id.textInfo)).setText(String.valueOf(values[0]));
            // UI thread
        }
    }

    int i = 0;
    private void howHandlerWorks() {

        new Thread(() -> {
            for(i = 0; i < 100 ; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                new Handler(Looper.getMainLooper()).post(() -> ((TextView)findViewById(R.id.textInfo))
                        .setText(String.valueOf(i)));
            }
        }).start();
    }

    private void howRxjavaWillHelpMe() {

        counterObservableRxJava().
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> Log.i("@codekul","Completed"))
                .doOnNext(s -> ((TextView)findViewById(R.id.textInfo)).setText(s))
                .doOnError(e -> Log.i("@codekul",""+e.toString()))
                .subscribe();
    }

    Observable<String> counterObservableRxJava(){
        return Observable.create(subscriber -> {
            for(int i = 0; i < 100 ;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(""+i);
            }
            subscriber.onCompleted();
        });
    }
}
