package sg.hangout.hangoutsg;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by qingt on 6/25/2016.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        String hour = "";
        String min = "";
        if (hourOfDay < 10){
            hour = "0" + Integer.toString(hourOfDay);
        }
        else{
            hour = Integer.toString(hourOfDay);
        }
        if (minute < 10)
            min = "0" + Integer.toString(minute);
        else {
            min = Integer.toString(minute);
        }
        ((EditText) getActivity().findViewById(R.id.time)).setText(hour + " : " + min);

    }
}