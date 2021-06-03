package com.bijgepast.quissteling.secondScreen.ui.LeaderBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeaderBoardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LeaderBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}