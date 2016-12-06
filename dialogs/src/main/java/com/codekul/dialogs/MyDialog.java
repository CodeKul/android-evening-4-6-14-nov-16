package com.codekul.dialogs;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment {

    public static final String DIALOG_ALERT = "alert";
    public static final String DIALOG_DATE_PICKER = "datePicker";
    public static final String DIALOG_TIME_PICKER = "timePicker";
    public static final String DIALOG_PROGRESS = "progress" ;
    public static final String DIALOG_CUSTOM = "custom";


  /* for rahul only
  public interface OnDatePicked {
        void date(int day, int month, int year);
    }

    private OnDatePicked datePicked;

    public void setDatePicked(OnDatePicked datePicked) {
        this.datePicked = datePicked;
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog  = null;

        if(getTag().equals(DIALOG_ALERT)) dialog = showAlertDialog();
        if(getTag().equals(DIALOG_DATE_PICKER)) dialog = showDatePicker();
        if(getTag().equals(DIALOG_TIME_PICKER)) dialog = showTimePicker();
        if(getTag().equals(DIALOG_PROGRESS)) dialog = showProgressDialog();
        if(getTag().equals(DIALOG_CUSTOM)) dialog = showCustomDialog();
        return dialog;
    }

    private Dialog showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_dialog,null,false);
        view.findViewById(R.id.btnLogin).setOnClickListener(v -> mt("Login Clicked"));
        builder.setView(view);
        return builder.create();
    }

    private Dialog showProgressDialog() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(R.string.title);
        progressDialog.setMessage(getResources().getString(R.string.message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,getResources().getString(R.string.btnYes),(dialog, which) -> mt("Yes Clicked"));
        progressDialog.setMax(100);
        progressDialog.setProgress(45);
        return progressDialog;
    }

    private Dialog showTimePicker() {

        TimePickerDialog timePicker =
                new TimePickerDialog(getActivity(),(view, hourOfDay, minute) -> mt(""+hourOfDay +" : "+ minute),6,8,true);

        return timePicker;
    }

    private Dialog showDatePicker() {

        //Calendar
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), (view, year, month, dayOfMonth) -> {

            /* for rahul only
            if(datePicked != null)
                datePicked.date(dayOfMonth,month,year);*/

        }  , 2017,0,1);
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
        return  datePicker;
    }

    private AlertDialog showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.ic_launcher)
                .setMessage(R.string.message)
                .setTitle(R.string.title)
                .setPositiveButton(R.string.btnYes, this::alertButtonClicked)
                .setNegativeButton(R.string.btnNo, this::alertButtonClicked);
       return  builder.create();
    }

    private void alertButtonClicked(DialogInterface di, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE){
            mt("Positive");
        }
        else if(which == DialogInterface.BUTTON_NEGATIVE) {
            mt("Negative");
        }
        else {
            mt("Neutral");
        }
    }
    
    private void mt(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
