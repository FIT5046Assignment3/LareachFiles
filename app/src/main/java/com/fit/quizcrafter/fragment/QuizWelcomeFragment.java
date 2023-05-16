package com.fit.quizcrafter.fragment;

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

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.QuizwelcomeFragmentBinding;
import com.fit.quizcrafter.domain.Question;
import com.fit.quizcrafter.domain.Quiz;
import com.fit.quizcrafter.loginactivity.CreateAccountClass;
import com.fit.quizcrafter.viewmodel.SharedViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizWelcomeFragment extends Fragment{
    private FirebaseAuth firebaseAuth;

    private FirebaseUser fireBaseUser;

    private TextView titleText;

    private DatabaseReference databaseReference;
    private QuizwelcomeFragmentBinding binding;
    public QuizWelcomeFragment(){}
    private SharedViewModel model;
    Button changeFragmentModelBtn;
    static int currentQuestion = 1;
    int totalQuestions;
    int numberOfCorrectAnswers = 0;
    int numberOfWrongAnswers = 0;
    int numberOfWrittenQuestions = 0;
    ArrayList<Integer>resultData;
    ArrayList<Boolean>questionIsMcqs;
    static ArrayList<String>questionTitles, questionCorrectWrittenAnswers,
            questionOption1s, questionOption2s, questionOption3s, questionOption4s,
            questionOption5s, questionOption6s;
    static ArrayList<Integer>questionCorrectOptions;

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
                null, null, null);
        QuizQuestion qqObj2 = new QuizQuestion("What is an animal?", true,
                "table", "Car","Dog","Jacket",3,
                null, null,null);
        QuizQuestion qqObj3 = new QuizQuestion("What does OOP stand for?",
                false, null, null,null,null,0,
                "Object Oriented Programming",null,null);
        QuizQuestion qqObj4 = new QuizQuestion("What is a bed?", false,
                null, null,null,null,0,
                "Something soft to sleep on",null,null);
        QuizQuestion qqObj5 = new QuizQuestion("Which is not a color?", true,
                "Orange", "Magenta","Blue","Dark",4,
                null, null,null);
        QuizQuestion qqObj6 = new QuizQuestion("Which are not countries?",
                true, "Australia", "Japan","China",
                "London",4, null, null,null);
        ArrayList<QuizQuestion> quizQuestionsArray = new ArrayList<QuizQuestion>();
        quizQuestionsArray.add(qqObj1);
        quizQuestionsArray.add(qqObj2);
        quizQuestionsArray.add(qqObj3);
        quizQuestionsArray.add(qqObj4);
        quizQuestionsArray.add(qqObj5);
        quizQuestionsArray.add(qqObj6);

        //ArrayList<>theArrayFromDatabase = quizQuestionsArray;
        String a;
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXvwscdc");
        //showQuizDetails(fireBaseUser);

        System.out.println(titleText);
        System.out.println(title123);

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXvwscdc");


        //initializing variables
        questionIsMcqs = new ArrayList<>();
        questionTitles = new ArrayList<>();
        questionOption1s = new ArrayList<>();
        questionOption2s = new ArrayList<>();
        questionOption3s = new ArrayList<>();
        questionOption4s = new ArrayList<>();
        questionOption5s = new ArrayList<>();
        questionOption6s = new ArrayList<>();
        questionCorrectOptions = new ArrayList<>();
        questionCorrectWrittenAnswers = new ArrayList<>();

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
            questionOption5s.add(quizQuestionsArray.get(i).option5);
            questionOption6s.add(quizQuestionsArray.get(i).option6);
            questionCorrectOptions.add(quizQuestionsArray.get(i).correctOption);
            questionCorrectWrittenAnswers.add(quizQuestionsArray.get(i).correctWrittenAnswer);
        }

        for(int i =0; i< totalQuestions;i++){
            if(questionIsMcqs.get(i).equals(false)){
                numberOfWrittenQuestions++;
            }
        }

        replaceFragment(new QuizTitleFragment());

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

                    currentQuestion = 1;

                    replaceFragment(new QuizEndResultModel());
                }
                else
                {
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        Button btn = view.findViewById(R.id.endQuizButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navController.navigate(R.id.action_quizWelcomeFragment_to_nav_home);
                Navigation.findNavController(view).navigate(R.id.action_quizWelcomeFragment_to_nav_home);

            }
        });
    }

    String title123;

    private void showQuizDetails(FirebaseUser fireBaseUser){

        ArrayList<Question>questionListFromDb = new ArrayList<>();
        for(int i =0; i<questionListFromDb.size(); i++){
            System.out.println(questionListFromDb.get(i));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("create-quiz");
        databaseReference.child(String.valueOf(R.string.user_id)).child("1").child("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Quiz quiz = snapshot.getValue(Quiz.class);
                if(quiz != null)
                {
                    title123 = quiz.title;
                    titleText.setText(title123);
                    System.out.println(titleText);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
