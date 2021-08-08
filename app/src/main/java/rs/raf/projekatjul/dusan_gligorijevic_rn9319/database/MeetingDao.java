package rs.raf.projekatjul.dusan_gligorijevic_rn9319.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;



@Dao
public interface MeetingDao {

    @Insert
    public long insert(Meeting meeting);

    @Query("SELECT * FROM meeting_table")
    public LiveData<List<Meeting>> getAll();

    @Delete
    public void delete(Meeting meeting);
}
