package rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.Meeting;



public class RecyclerViewModel extends ViewModel {

    public static int counter = 101;

    private final MutableLiveData<List<Meeting>> meetings = new MutableLiveData<>();
    private final ArrayList<Meeting> list_of_meetings = new ArrayList<>();

    public RecyclerViewModel() {

        // ovo radimo zato sto meetings.setValue u pozadini prvo proverava da li je pokazivac na objekat isti i ako jeste nece uraditi notifyAll
        // kreiranjem nove liste dobijamo novi pokazivac svaki put
        ArrayList<Meeting>  meetingsToSubmit = new ArrayList<>(list_of_meetings);
        meetings.setValue(meetingsToSubmit);
    }

    public MutableLiveData<List<Meeting>> getMeetings() {
        return meetings;
    }

    public void filterMeetings(String filter) {
        List<Meeting> filteredList = list_of_meetings.stream().filter(meeting1 -> meeting1.getName().toLowerCase().contentEquals(filter.toLowerCase())).collect(Collectors.toList());
        meetings.setValue(filteredList);
    }
    public void filter2Meetings(String filter) {
        List<Meeting> filteredList = list_of_meetings.stream().filter(meeting1 -> meeting1.getName().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        meetings.setValue(filteredList);
    }
    public void addMeeting(  String name, String description, String time, String url) {
        Meeting meeting = new Meeting(counter++, name, description, time, url);
        list_of_meetings.add(meeting);
        ArrayList<Meeting> listToSubmit = new ArrayList<>(list_of_meetings);
        meetings.setValue(listToSubmit);

    }
    public  void deleteMeeting(Meeting meeting){
        list_of_meetings.remove(meeting);
        ArrayList<Meeting> listToSubmit = new ArrayList<>(list_of_meetings);
        meetings.setValue(listToSubmit);

    }

}
