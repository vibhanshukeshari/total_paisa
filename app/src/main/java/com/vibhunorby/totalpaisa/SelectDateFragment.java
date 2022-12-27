package com.vibhunorby.totalpaisa;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public Dialog onCreateDialog(Bundle saveInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int  yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DATE);


        return new DatePickerDialog(getActivity(),R.style.vibhuCalenderPickerTheme, this, yy, mm, dd);
    }


    public void onDateSet(DatePicker view, int yy, int mm, int dd){
        try {
            populateSetDate(yy, mm+1, dd);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void populateSetDate(int year, int month, int day) throws ParseException {

        TextView textViewCalender = getActivity().findViewById(R.id.textViewCalender);
        TextView textViewDayOfTheWeek = getActivity().findViewById(R.id.textViewDayOfTheWeek);
        TextView textViewTodayTomorrowYesterday = getActivity().findViewById(R.id.textViewTodayTomorrowYesterday);

        String fm=""+month;
        String fd =""+day;
        if(month < 10) {
            fm = "0" + month;
        }
        if(day < 10) {
            fd ="0" + day;
        }
        String date = fd+"-"+fm+"-"+year;

        textViewCalender.setText(date);

        DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = sourceFormat.parse(date);
        String  newDay = android.text.format.DateFormat.format("EEEE",date1).toString();

        textViewDayOfTheWeek.setText(newDay);

        if( DateUtils.isToday(date1.getTime())){
            textViewTodayTomorrowYesterday.setText("Today :");
        } else if(DateUtils.isToday(date1.getTime() + DateUtils.DAY_IN_MILLIS)){
            textViewTodayTomorrowYesterday.setText("Yesterday :");
        } else if(DateUtils.isToday(date1.getTime() - DateUtils.DAY_IN_MILLIS)){
            textViewTodayTomorrowYesterday.setText("Tomorrow :");
        } else {
            textViewTodayTomorrowYesterday.setText("Date :");
        }
    }

}
