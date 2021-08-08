package rs.raf.projekatjul.dusan_gligorijevic_rn9319.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "meeting_table")
public class Meeting {

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("time")
    @Expose
    @ColumnInfo(name = "time")
    private String time;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    private String url;

    public Meeting(int id, String name, String description, String time, String url) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
