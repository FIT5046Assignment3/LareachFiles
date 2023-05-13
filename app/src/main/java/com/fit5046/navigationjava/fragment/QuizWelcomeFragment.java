package com.fit5046.navigationjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.fit5046.navigationjava.R;
import com.fit5046.navigationjava.databinding.QuizwelcomeFragmentBinding;
import com.fit5046.navigationjava.viewmodel.SharedViewModel;

import java.util.ArrayList;

public class QuizWelcomeFragment extends Fragment{
    private QuizwelcomeFragmentBinding binding;
    public QuizWelcomeFragment(){}
    private SharedViewModel model;

    Button changeFragmentModelBtn;
    QuizQuestion[]quizQuestionsArray;
    static int currentQuestion = 1;
    int totalQuestions;
    int chosenOptionForMCQ;
    int numberOfCorrectAnswers = 0;
    int numberOfWrongAnswers = 0;
    int numberOfWrittenQuestions = 0;
    ArrayList<Integer>resultData;
    ArrayList<Boolean>questionIsMcqs;
    static ArrayList<String>questionTitles, questionCorrectWrittenAnswers,
            questionOption1s, questionOption2s, questionOption3s, questionOption4s;
    static ArrayList<Integer>questionCorrectOptions;
    static ArrayList<ArrayList<String>>questionCorrectKeywords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        binding = QuizwelcomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        resultData = new ArrayList<>();

        //creating sample data to test
        ArrayList<String> set1 = new ArrayList<String>();
        set1.add("Object");
        set1.add("Oriented");
        set1.add("Programming");
        ArrayList<String> set2 = new ArrayList<String>();
        set1.add("Sleep");
        set1.add("Soft");
        QuizQuestion qqObj1 = new QuizQuestion("What is 1 + 1?", true,
                "1", "2","3","4",2,
                null, null);
        QuizQuestion qqObj2 = new QuizQuestion("What is an animal?", true,
                "table", "Car","Dog","Jacket",3,
                null, null);
        QuizQuestion qqObj3 = new QuizQuestion("What does OOP stand for?",
                false, null, null,null,null,0,
                "Object Oriented Programming", set1);
        QuizQuestion qqObj4 = new QuizQuestion("What is a bed?", false,
                null, null,null,null,0,
                "Something soft to sleep on", set2);
        QuizQuestion qqObj5 = new QuizQuestion("Which is not a color?", true,
                "Orange", "Magenta","Blue","Dark",4,
                null, null);
        QuizQuestion qqObj6 = new QuizQuestion("Which are not countries?",
                true, "Australia", "Japan","China",
                "London",4, null, null);

        //reference to use when retrieving data from realtime database
        ArrayList<QuizQuestion> quizQuestionsArray = new ArrayList<QuizQuestion>();
        quizQuestionsArray.add(qqObj1);
        quizQuestionsArray.add(qqObj2);
        quizQuestionsArray.add(qqObj3);
        quizQuestionsArray.add(qqObj4);
        quizQuestionsArray.add(qqObj5);
        quizQuestionsArray.add(qqObj6);

        //initializing variables
        questionIsMcqs = new ArrayList<>();
        questionTitles = new ArrayList<>();
        questionOption1s = new ArrayList<>();
        questionOption2s = new ArrayList<>();
        questionOption3s = new ArrayList<>();
        questionOption4s = new ArrayList<>();
        questionCorrectOptions = new ArrayList<>();
        questionCorrectWrittenAnswers = new ArrayList<>();
        questionCorrectKeywords = new ArrayList<>();

        totalQuestions = quizQuestionsArray.size();
        changeFragmentModelBtn = view.findViewById(R.id.GameNextButton);

        //inputting question data into arraylists for each quiz variables
        for(int i = 0; i<totalQuestions;i++){
            questionIsMcqs.add(quizQuestionsArray.get(i).isMcqQuestion);
            questionTitles.add(quizQuestionsArray.get(i).questionTitle);
            questionOption1s.add(quizQuestionsArray.get(i).option1);
            questionOption2s.add(quizQuestionsArray.get(i).option2);
            questionOption3s.add(quizQuestionsArray.get(i).option3);
            questionOption4s.add(quizQuestionsArray.get(i).option4);
            questionCorrectOptions.add(quizQuestionsArray.get(i).correctOption);
            questionCorrectWrittenAnswers.add(quizQuestionsArray.get(i).correctWrittenAnswer);
            questionCorrectKeywords.add(quizQuestionsArray.get(i).identifiedCorrectKeywords);
        }

        for(int i =0; i< totalQuestions;i++){
            if(questionIsMcqs.get(i).equals(false)){
                numberOfWrittenQuestions++;
            }
        }



        System.out.println("X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X" +
                "-X-X-X-X-X-X-X-X-X-X-X-X-X-X" +
                "-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-" +
                "X-X-X-X-X-X-X-X-X-X-X-X-X-X-X");

        gameplay();

        model.getIsCorrect().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean itIsCorrect) {
                // Do something with the data
                System.out.println("Question Main: " + (currentQuestion-1) + " : " + itIsCorrect);

                if(itIsCorrect){
                    numberOfCorrectAnswers++;
                }
            }
        });

        return view;
    }

    //method to manage switching fragments of quiz types
    private void replaceFragment(Fragment fragmentModel) {
        FragmentManager fragManager = getParentFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frameLayout,fragmentModel);
        fragTransaction.commit();
    }

    //activate the fragment switching by button
    public void switchQuizTypeFragment(){

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        changeFragmentModelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int questionIndex = currentQuestion - 1;

                numberOfWrongAnswers = (totalQuestions -
                        numberOfCorrectAnswers - numberOfWrittenQuestions);

                if(currentQuestion > totalQuestions){
                    System.out.println("Total Correct : " + numberOfCorrectAnswers);
                    System.out.println("Total Wrong : " + numberOfWrongAnswers);
                    System.out.println("Written Questions : " + numberOfWrittenQuestions);

                    resultData.add(numberOfCorrectAnswers);
                    resultData.add(numberOfWrongAnswers);
                    resultData.add(numberOfWrittenQuestions);

                    model.setResults(resultData);

                    binding.GameNextButton.setEnabled(false);

                    replaceFragment(new QuizEndResultModel());
                }
                else
                {
                    //questionIndex = model.getNum().getValue();
                    model.setNum(questionIndex);

                    if (questionIsMcqs.get(questionIndex).equals(true)) {
                        replaceFragment(new QuizMcqModel());

                    } else if (questionIsMcqs.get(questionIndex).equals(false)) {
                        replaceFragment(new QuizWrittenModel());
                    }
                    currentQuestion++;
                }

            }
        });
    }

    public void gameplay(){
        switchQuizTypeFragment();
    }

    public void calculateEachQuestionResult(int currentQuestionOrder ){
        if(chosenOptionForMCQ == quizQuestionsArray[currentQuestionOrder].correctOption){
            numberOfCorrectAnswers++;
        } else{
            numberOfWrongAnswers++;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        Button btn = view.findViewById(R.id.endQuizButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_quizWelcome_fragment_self);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //{"a","b","a","b","b","b","a","a","b","a"};
}
