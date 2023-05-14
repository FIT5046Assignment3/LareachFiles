package com.fit.quizcrafter.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> mNum;
    private MutableLiveData<Integer> pickedNum;
    private MutableLiveData<Boolean> isCorrect;
    private MutableLiveData<ArrayList<Integer>> results;

    public SharedViewModel(){
        mText = new MutableLiveData<String>();
        mNum = new MutableLiveData<Integer>();
        pickedNum = new MutableLiveData<Integer>();
        isCorrect = new MutableLiveData<Boolean>();
        results = new MutableLiveData<ArrayList<Integer>>();
    }
    public void setText(String message) {
        mText.setValue(message);
    }
    public LiveData<String> getText() {
        return mText;
    }

    public void setNum(Integer num) {
        mNum.setValue(num);
    }
    public LiveData<Integer> getNum() {
        return mNum;
    }

    public void setPickedNum(Integer num) {
        pickedNum.setValue(num);
    }
    public LiveData<Integer> getPickedNum() {
        return pickedNum;
    }
    public void setIsCorrect(Boolean boo) {
        isCorrect.setValue(boo);
    }
    public LiveData<Boolean> getIsCorrect() {
        return isCorrect;
    }

    public void setResults(ArrayList<Integer>list) {
        results.setValue(list);
    }
    public LiveData<ArrayList<Integer>> getResults() {
        return results;
    }


}
