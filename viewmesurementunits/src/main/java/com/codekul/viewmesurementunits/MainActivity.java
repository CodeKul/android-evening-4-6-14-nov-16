package com.codekul.viewmesurementunits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnConvertToPx)
                .setOnClickListener(this::clicked);

        findViewById(R.id.btnConvertToDp)
                .setOnClickListener(this::clicked);
    }

    private void clicked(View view) {

        if(view.getId() == R.id.btnConvertToDp) {
            setTextInfo(""+convertToDp(getEdtValue(),240));
            //Measurable dp = (val,dpi) -> setTextInfo(""+(val * -(dpi/160)));

            /*((TextView) findViewById(R.id.textInfo))
                    .setText(""+(getEdtValue() * (240/160)));*/
        }
        else {
            setTextInfo(""+convertToPx(getEdtValue(),240));
            //Measurable px = (val,dpi) -> setTextInfo(""+((val * 160) / dpi));
        }
    }

    private int getEdtValue() {
        /*EditText edtvalue = (EditText) findViewById(R.id.edtDpValue);
        int val = Integer.parseInt(edtvalue.getText().toString());
        return  val;*/

        return Integer.parseInt(((EditText) findViewById(R.id.edtDpValue))
                        .getText()
                        .toString());
    }

    public int convertToPx(int val,int dpi){
       // px = dp * (dpi / 160)

        return val * (dpi/160);
    }

    public int convertToDp(int val, int dpi){
        return (val * 160) / dpi;
    }

    public void setTextInfo(String info){
        /*TextView text = (TextView) findViewById(R.id.textInfo);
        text.setText(info);*/

        ((TextView) findViewById(R.id.textInfo)).setText(info);
    }

    private interface Measurable {
        void measure(int val, int dpi);
    }
}
