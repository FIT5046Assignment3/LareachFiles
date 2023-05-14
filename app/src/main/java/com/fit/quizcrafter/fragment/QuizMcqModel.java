package com.fit.quizcrafter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentQuizMcqModelBinding;
import com.fit.quizcrafter.viewmodel.SharedViewModel;

import java.util.ArrayList;

public class QuizMcqModel extends Fragment {

    FragmentQuizMcqModelBinding binding;
    int chosenOptionForMCQ;
    static int questionIndex = 0;
    private SharedViewModel model;
    int pickedAnswer;
    int correctAnswers = 0 ;
    int wrongAnswers = 0;
    boolean pressedOpt;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentQuizMcqModelBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        pressedOpt = false;
        chosenOptionForMCQ = 0;

        ArrayList<String> titleOfQuestions = QuizWelcomeFragment.questionTitles;
        ArrayList<String> option1sChoices = QuizWelcomeFragment.questionOption1s;
        ArrayList<String> option2sChoices = QuizWelcomeFragment.questionOption2s;
        ArrayList<String> option3sChoices = QuizWelcomeFragment.questionOption3s;
        ArrayList<String> option4sChoices = QuizWelcomeFragment.questionOption4s;
        ArrayList<String> option5sChoices = QuizWelcomeFragment.questionOption5s;
        ArrayList<String> option6sChoices = QuizWelcomeFragment.questionOption6s;
        ArrayList<Integer> chosenAnswer = QuizWelcomeFragment.questionCorrectOptions;


        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        TextView tw = view.findViewById(R.id.textViewMCQ);
        Button btn1 = view.findViewById(R.id.MCQbtn1);
        Button btn2 = view.findViewById(R.id.MCQbtn2);
        Button btn3 = view.findViewById(R.id.MCQbtn3);
        Button btn4 = view.findViewById(R.id.MCQbtn4);
        Button btn5 = view.findViewById(R.id.MCQbtn5);
        Button btn6 = view.findViewById(R.id.MCQbtn6);

        model.getNum().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                // Update the TextView with the new count.
                tw.setText(titleOfQuestions.get(count));
                btn1.setText(option1sChoices.get(count));
                btn2.setText(option2sChoices.get(count));
                btn3.setText(option3sChoices.get(count));
                btn4.setText(option4sChoices.get(count));
                btn5.setText(option5sChoices.get(count));
                btn6.setText(option6sChoices.get(count));
                questionIndex = count;
            }
        });

        // Set a click listener for the button
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This code runs when the button is clicked
                onButtonClick(v);

                if (v == binding.MCQbtn1) {
                    chosenOptionForMCQ = 1;
                    pressedOpt = true;
                } else if (v == binding.MCQbtn2) {
                    chosenOptionForMCQ = 2;
                    pressedOpt = true;
                } else if (v == binding.MCQbtn3) {
                    chosenOptionForMCQ = 3;
                    pressedOpt = true;
                } else if (v == binding.MCQbtn4) {
                    chosenOptionForMCQ = 4;
                    pressedOpt = true;
                } else if (v == binding.MCQbtn5) {
                    chosenOptionForMCQ = 5;
                    pressedOpt = true;
                } else if (v == binding.MCQbtn6) {
                    chosenOptionForMCQ = 6;
                    pressedOpt = true;
                }

                System.out.println("Button is pressed: " + pressedOpt);

                boolean theSame = false;
                if(pressedOpt && (chosenOptionForMCQ == chosenAnswer.get(questionIndex))){
                    theSame = true;
                    System.out.println("Question Sub " + (questionIndex + 1) + " : " + theSame);
                    model.setIsCorrect(theSame);
                } else if (!pressedOpt || (chosenOptionForMCQ != chosenAnswer.get(questionIndex))){
                    System.out.println("Question Sub " + (questionIndex + 1) + " : " + theSame);
                    model.setIsCorrect(theSame);
                } else{
                    System.out.println("Question Sub " + (questionIndex + 1) + " : " + theSame);
                    model.setIsCorrect(theSame);
                }
            }
        };

        binding.MCQbtn1.setOnClickListener(onClickListener);
        binding.MCQbtn2.setOnClickListener(onClickListener);
        binding.MCQbtn3.setOnClickListener(onClickListener);
        binding.MCQbtn4.setOnClickListener(onClickListener);
        binding.MCQbtn5.setOnClickListener(onClickListener);
        binding.MCQbtn6.setOnClickListener(onClickListener);

        return view;
    }

    private void onButtonClick(View view) {
        // Set the colors for the buttons
        binding.MCQbtn1.setBackgroundColor(getResources().getColor(R.color.purple_200));
        binding.MCQbtn2.setBackgroundColor(getResources().getColor(R.color.purple_200));
        binding.MCQbtn3.setBackgroundColor(getResources().getColor(R.color.purple_200));
        binding.MCQbtn4.setBackgroundColor(getResources().getColor(R.color.purple_200));
        binding.MCQbtn5.setBackgroundColor(getResources().getColor(R.color.purple_200));
        binding.MCQbtn6.setBackgroundColor(getResources().getColor(R.color.purple_200));
        binding.MCQbtn1.setEnabled(false);
        binding.MCQbtn2.setEnabled(false);
        binding.MCQbtn3.setEnabled(false);
        binding.MCQbtn4.setEnabled(false);
        binding.MCQbtn5.setEnabled(false);
        binding.MCQbtn6.setEnabled(false);

        // Highlight the clicked button
        view.setBackgroundColor(getResources().getColor(R.color.teal_200));
        view.setEnabled(true);
    }
}

