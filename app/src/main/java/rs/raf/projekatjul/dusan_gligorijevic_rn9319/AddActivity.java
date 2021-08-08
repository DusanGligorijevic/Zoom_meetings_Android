package rs.raf.projekatjul.dusan_gligorijevic_rn9319;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.Meeting;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.MyRoomDatabase;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view.RecyclerViewModel;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.retrofit.EasternStandardTimeModel;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.retrofit.WorldClockService;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText event_name, description, url;
    private AutoCompleteTextView city;
    private Button set_time, set_date, check_for_time_location, save_event;
    private Spinner spinner;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private List<String> cities= new ArrayList<>();
    final WorldClockService worldClockService = new WorldClockService();
    public final String link = "https://zoom.us/j/";
    public static final String  KEY ="location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        initListeners();

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String mess = check_for_time_location.getText().toString();
        outState.putString(KEY, mess);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String mess = savedInstanceState.getString(KEY);
        check_for_time_location.setText(mess);
    }

    public void init() {
        event_name = findViewById(R.id.event_name);
        description = findViewById(R.id.description);
        check_for_time_location = findViewById(R.id.check_for_time_location);
        city = findViewById(R.id.city);
        set_date = findViewById(R.id.set_date);
        set_time = findViewById(R.id.set_time);
        url = findViewById(R.id.url);
        save_event = findViewById(R.id.save_event);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void initListeners() {
        set_date.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            datePicker = new DatePickerDialog(AddActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> set_date.setText(dayOfMonth + "/" +
                            (monthOfYear + 1) + "/" + year1), year, month, day);
            datePicker.show();
        });

        set_time.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            timePicker = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> set_time.setText("Hours: " + hourOfDay + ", minutes: " + minute), mHour,
                    mMinute, false);
            timePicker.show();
        });

        check_for_time_location.setOnClickListener(v->{
            if(city.getText().toString().isEmpty()){
                Toast.makeText(this,"Choose city!",Toast.LENGTH_SHORT).show();
                return;
            }
            worldClockService.invokeCityService(spinner.getSelectedItem().toString(), city.getText().toString());
        });
        worldClockService.getEasternStandardTime().observe(
                this,
                new Observer<EasternStandardTimeModel>() {
                    @Override
                    public void onChanged(EasternStandardTimeModel easternStandardTimeModel) {
                        check_for_time_location.setText(easternStandardTimeModel.getDatetime());
                    }
                });

        save_event.setOnClickListener(v -> {
            final MyRoomDatabase db = MyRoomDatabase.getDatabase(this);
            if(event_name.getText().toString().isEmpty()){
                Toast.makeText(this,"Event name cannot be empty!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(description.getText().toString().isEmpty()){
                Toast.makeText(this,"Description cannot be empty!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(set_date.getText().equals("Set date")){
                Toast.makeText(this,"Choose date, please!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(set_time.getText().equals("Set time")){
                Toast.makeText(this,"Choose time, please!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(url.getText().toString().isEmpty()){
                Toast.makeText(this,"url cannot be empty!",Toast.LENGTH_SHORT).show();
                return;
            }


            new Thread(() -> {
                 db.meetingDao().insert(new Meeting(RecyclerViewModel.counter, event_name.getText().toString(),
                        description.getText().toString(),set_time.getText().toString(),link + url.getText().toString()));
            }).start();

           event_name.getText().clear();
           description.getText().clear();
           check_for_time_location.setText("Check time for location");
           city.getText().clear();
           spinner.setSelection(0);
           set_date.setText("Set date");
           set_time.setText("Set time");
           url.getText().clear();
           Toast.makeText(this,"A new meeting has been added successfully!",Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
        parent.getItemAtPosition(position);

        worldClockService.showCities(spinner.getSelectedItem().toString());
        worldClockService.getCities().observe(
                this,
                new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> citiesValue) {
                        getCities().clear();
                        for (int i=0; i< citiesValue.size(); i++) {
                            if (i < 10) {
                                String[] string = citiesValue.get(i).split("/",2);
                                getCities().add(string[1]);
                            }
                        }

                    }
                });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, getCities());
        city.setThreshold(0);//will start working from first character
        city.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }

    public List<String> getCities() {
        return cities;
    }
}