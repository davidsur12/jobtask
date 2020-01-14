package com.example.jobtask.Dialogos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.widget.DatePicker;

import java.util.Calendar;

public class DateFecha  extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;
  public static DateFecha newInstance(DatePickerDialog.OnDateSetListener listener) {
      DateFecha fragment = new DateFecha();
      fragment.setListener(listener);
      return fragment;
  }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), listener, anio, mes, dia);

    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    }
}


