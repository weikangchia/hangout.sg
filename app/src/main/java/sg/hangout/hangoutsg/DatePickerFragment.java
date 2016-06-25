package sg.hangout.hangoutsg;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by qingt on 6/25/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String mon = "";
        String dd = "";
        if (month < 10)
            mon = "0" + Integer.toString(month);
        else{
            mon = Integer.toString(month);
        }
        if (day < 10)
            dd = "0" + Integer.toString(day);
        else{
            dd = Integer.toString(day);
        }
        ((EditText) getActivity().findViewById(R.id.date)).setText(dd + " / " + mon + " / " + year);
    }
}