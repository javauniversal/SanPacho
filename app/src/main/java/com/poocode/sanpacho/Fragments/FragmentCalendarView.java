package com.poocode.sanpacho.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.poocode.sanpacho.R;

import java.util.ArrayList;

public class FragmentCalendarView extends Fragment {

    private CalendarView calendar;

    public FragmentCalendarView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);
        calendar = (CalendarView) view.findViewById(R.id.calendarView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeCalendar();
    }

    public void initializeCalendar() {

        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);
        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);



        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getActivity(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

            }

        });
    }

}
