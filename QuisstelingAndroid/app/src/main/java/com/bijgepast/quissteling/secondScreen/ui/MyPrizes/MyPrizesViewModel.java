package com.bijgepast.quissteling.secondScreen.ui.MyPrizes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyPrizesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyPrizesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}