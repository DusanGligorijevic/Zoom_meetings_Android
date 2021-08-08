package rs.raf.projekatjul.dusan_gligorijevic_rn9319;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.Meetings;

public class MainActivity extends AppCompatActivity {
    private Button add_event, show_events, export_to_json_file;
    public static Meetings meetings = new Meetings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListeners();
    }
    public void init(){

        add_event = findViewById(R.id.add_event);
        show_events = findViewById(R.id.show_events);
        export_to_json_file = findViewById(R.id.export_to_json_file);

    }
    public void initListeners(){

        add_event.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        show_events.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShowActivity.class);
            startActivity(intent);
        });

        export_to_json_file.setOnClickListener(v -> {
            export();
        });

    }
    public void export() {
        if (meetings.getMeetings().isEmpty()) {
            Toast.makeText(this, "Lista je prazna", Toast.LENGTH_SHORT).show();
            return;
        }

        //prvi nacin

        try (FileOutputStream outputStream = openFileOutput("my_json_meetings.json", Context.MODE_APPEND)) {
            if(!meetings.getMeetings().isEmpty()) {
                outputStream.write((new Gson().toJson(meetings.getMeetings())).getBytes());
                System.out.println((new Gson().toJson(meetings.getMeetings())));

                outputStream.flush();
                outputStream.close();
                Toast.makeText(this, "Exported successfully!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Nije uspelo", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
        /*
        //drugi nacin

        // Define the File Path and its Name
        File file = new File(getApplicationContext().getFilesDir(), "fajl.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < meetings.getMeetings().size(); i++) {

                JSONObject jsonObject = toJsonObject(meetings.getMeetings().get(i).getId(), meetings.getMeetings().get(i).getName(),
                        meetings.getMeetings().get(i).getDescription(), meetings.getMeetings().get(i).getTime(),
                        meetings.getMeetings().get(i).getUrl());
                System.out.println(meetings.getMeetings().get(i).toString());
                String string = jsonObject.toString();

                bufferedWriter.write(string);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            Toast.makeText(this, "Exported successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //treci nacin
        File dir = new File(getApplicationContext().getExternalFilesDir(null), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, "fajl.txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("ovo je fajlll");
            writer.flush();
            writer.close();
            Toast.makeText(this, "Exported successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }

         */
    }
    public JSONObject toJsonObject(int id, String name, String desc, String time, String url){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("name", name);
            jsonObject.put("description", desc);
            jsonObject.put("time", time);
            jsonObject.put("url", url);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}