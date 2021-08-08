package rs.raf.projekatjul.dusan_gligorijevic_rn9319.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Meetings {
    @SerializedName("meetings")
    @Expose
    private ArrayList<Meeting> meetings = null;

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }
}
