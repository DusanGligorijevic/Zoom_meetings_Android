package rs.raf.projekatjul.dusan_gligorijevic_rn9319.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;


@Database(version = 2,
        entities = {
                Meeting.class,
        },
        exportSchema = false
)
public abstract class MyRoomDatabase extends RoomDatabase {

    private static MyRoomDatabase singletonInstance;

    public abstract MeetingDao meetingDao();

    public static MyRoomDatabase getDatabase(final Context context) {
        if (singletonInstance == null) {
            synchronized (MyRoomDatabase.class) {
                if (singletonInstance == null) {
                    singletonInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyRoomDatabase.class,
                            "meeting_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return singletonInstance;
    }

    private static Callback callback =
            new Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDatabaseAsync(singletonInstance).execute();
                }
            };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final MeetingDao meetingDao;

        PopulateDatabaseAsync(MyRoomDatabase myRoomDatabase) {
            meetingDao = myRoomDatabase.meetingDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
                   // meetingDao.insert(new Meeting(111,"Mika","Mikic","asdasdas","asdasdas"));
                  //  meetingDao.insert(new Meeting(112,"Marko", "Maric","asdasdsa","asddsa"));
            return null;
        }
    }

}