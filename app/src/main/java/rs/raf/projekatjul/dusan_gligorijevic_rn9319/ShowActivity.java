package rs.raf.projekatjul.dusan_gligorijevic_rn9319;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.Meeting;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.MyRoomDatabase;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view.MeetingAdapter;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view.MeetingDiffItemCallback;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view.RecyclerViewModel;

public class ShowActivity extends AppCompatActivity {
    private TextView event;
    private EditText search_event;
    private Button search;
    private RecyclerView recyclerView;
    private RecyclerViewModel recyclerViewModel;
    private MeetingAdapter meetingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        recyclerViewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
        init();
    }



    private void init() {
        final MyRoomDatabase db = MyRoomDatabase.getDatabase(this);
        initView();
        initListeners();
        initObservers();
        initRecycler();
        db.meetingDao()
                .getAll().observe(
                ShowActivity.this, (List<Meeting> meetings) -> {
                    for (int i = 0; i < meetings.size(); i++) {
                        recyclerViewModel.addMeeting(meetings.get(i).getName(),
                                meetings.get(i).getDescription(),meetings.get(i).getTime(),meetings.get(i).getUrl());
                    }
                    MainActivity.meetings.setMeetings((ArrayList<Meeting>) meetings);
                });
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        search_event = findViewById(R.id.search_event);
        search = findViewById(R.id.search);
    }

    private void initListeners() {
        search_event.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(search_event.getText().toString().isEmpty())
                recyclerViewModel.filter2Meetings(s.toString());
            }
        });
        search.setOnClickListener(v->{
            recyclerViewModel.filterMeetings(search_event.getText().toString());

        });
    }
    private void initObservers() {
        recyclerViewModel.getMeetings().observe(this, meetings -> {
            meetingAdapter.submitList(meetings);
        });
    }

    private void initRecycler() {
        meetingAdapter = new MeetingAdapter(new MeetingDiffItemCallback(), meeting -> {
            Toast.makeText(ShowActivity.this, meeting.getId() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(meeting.getUrl()));
            startActivity(intent);
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(meetingAdapter);
    }

}