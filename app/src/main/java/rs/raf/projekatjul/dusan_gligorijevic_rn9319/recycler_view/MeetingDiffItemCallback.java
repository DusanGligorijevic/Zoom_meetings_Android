package rs.raf.projekatjul.dusan_gligorijevic_rn9319.recycler_view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekatjul.dusan_gligorijevic_rn9319.database.Meeting;


public class MeetingDiffItemCallback extends DiffUtil.ItemCallback<Meeting> {
    @Override
    public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
        if(!oldItem.getName().contentEquals(newItem.getName()))
            return false;
        if(!oldItem.getDescription().contentEquals(newItem.getDescription()))
            return false;
        if(!oldItem.getTime().contentEquals(newItem.getTime()))
            return false;
        if(!oldItem.getUrl().contentEquals(newItem.getUrl()))
            return false;
        return true;
    }
}
