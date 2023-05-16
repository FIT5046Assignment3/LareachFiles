package com.fit.quizcrafter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentQuizWrittenModelBinding;
import com.fit.quizcrafter.viewmodel.SharedViewModel;

import java.util.ArrayList;

public class QuizWrittenModel extends Fragment {
    FragmentQuizWrittenModelBinding binding;
    private SharedViewModel model;
    static int questionIndex;

    String currentTitle;
    String currentCorrectAnswer;
    String currentUserAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuizWrittenModelBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        ArrayList<String> titleOfQuestions = QuizWelcomeFragment.questionTitles;
        ArrayList<String> exactCorrectAnswer = QuizWelcomeFragment.questionCorrectWrittenAnswers;

        TextView tw = view.findViewById(R.id.textViewWritten);

        model.getNum().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                // Update the TextView with the new count.
                tw.setText(titleOfQuestions.get(count));
                currentTitle = titleOfQuestions.get(count);
                currentCorrectAnswer = exactCorrectAnswer.get(count);
                print();
            }
        });

        return view;
    }

    public void print(){
        System.out.println("Title: " + currentTitle);
        System.out.println("Correct Answer: " + currentCorrectAnswer);
    }

}