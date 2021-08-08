package rs.raf.projekatjul.dusan_gligorijevic_rn9319.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShowViewModel extends ViewModel {

    private final MutableLiveData<String> titleLiveData = new MutableLiveData<>();

    public ShowViewModel() {
        titleLiveData.setValue("null");
    }

    public LiveData<String> getTitle() {
        return titleLiveData;
    }

}
