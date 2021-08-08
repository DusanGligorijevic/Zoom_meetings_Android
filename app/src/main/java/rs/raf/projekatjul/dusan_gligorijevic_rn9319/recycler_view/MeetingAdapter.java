package rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.function.Function;

import rs.raf.projekatjul.dusan_gligorijevic_rn9319.R;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.ShowActivity;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.Meeting;
import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.MyRoomDatabase;

public class MeetingAdapter extends ListAdapter<Meeting, MeetingAdapter.ViewHolder> {
    private final Function<Meeting, Void> onMeetingClicked;
    private RecyclerViewModel recyclerViewModel;

    public MeetingAdapter(@NonNull DiffUtil.ItemCallback<Meeting> diffCallback, Function<Meeting, Void> onMeetingClicked) {
        super(diffCallback);
        this.onMeetingClicked = onMeetingClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Meeting meeting = getItem(position);
            onMeetingClicked.apply(meeting);


            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = getItem(position);
        holder.bind(meeting);
        holder.itemView.setOnLongClickListener(v->{
            Context context = v.getContext();
            MyRoomDatabase db = MyRoomDatabase.getDatabase(context);
            recyclerViewModel = new ViewModelProvider((FragmentActivity) v.getContext()).get(RecyclerViewModel.class);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Delete "+ meeting.getName());
            builder.setMessage("Are you sure you want to delete this meeting?")
                    .setCancelable(false)
                    .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // TODO: handle the OK
                            new Thread(() -> {
                                db.meetingDao().delete(meeting);
                            }).start();
                            db.meetingDao()
                                    .getAll().observe(
                                    (FragmentActivity) v.getContext(), meetings -> {
                                        recyclerViewModel.deleteMeeting(meeting);
                                    });
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        return false;
        });
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
            });


        }

        public void bind(Meeting meeting) {
            ((TextView)itemView.findViewById(R.id.item_name)).setText(meeting.getName());
            ((TextView)itemView.findViewById(R.id.item_description)).setText(meeting.getDescription());
            ((TextView)itemView.findViewById(R.id.item_time)).setText(meeting.getTime());
            ((TextView)itemView.findViewById(R.id.item_url)).setText(meeting.getUrl());
        }

    }

    @Override
    public Meeting getItem(int position) {
        return super.getItem(position);
    }
}
