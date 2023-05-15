package com.fit.quizcrafter.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.fit.quizcrafter.databinding.FragmentQuizEndResultModelBinding;
import com.fit.quizcrafter.report.BarChartFragment;
import com.fit.quizcrafter.viewmodel.SharedViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizEndResultModel extends Fragment {

    SharedViewModel model;
    public int correctMcq;
    public int wrongMcq;
    public int writtenQuestions;

    private Button sendDataBtn;

    HashMap<String,Integer> hashMap = new HashMap<>();



    private FragmentQuizEndResultModelBinding binding;

    private FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();

    private FirebaseAuth firebaseAuth;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuizEndResultModelBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getResults().observe(getViewLifecycleOwner(), new Observer<ArrayList<Integer>>() {
            @Override
            public void onChanged(ArrayList<Integer> data) {
                // Update the TextView with the new count.
                System.out.println(data.get(0));
                binding.correctResultTextNumber.setText(String.valueOf(data.get(0)));
                binding.wrongResultTextNumber.setText(String.valueOf(data.get(1)));
                binding.WrittenResultTextNumber.setText(String.valueOf(data.get(2)));

                correctMcq = data.get(0);
                wrongMcq = data.get((1));
                writtenQuestions = data.get(2);

                hashMap.put("correctMCQ", correctMcq);
                hashMap.put("WrongMcq",wrongMcq);
                hashMap.put("writethequestions",writtenQuestions);



            }
        });

        sendDataBtn = binding.sendDataToDatabase;
        sendDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
                Bundle args = new Bundle();
                args.putInt("wrongMCQ", wrongMcq);
                args.putInt("correctMCQ", correctMcq);
                args.putInt("qns", writtenQuestions);




            }
        });

        System.out.println(correctMcq + " " + wrongMcq+" "+writtenQuestions);
        return view;
    }

    public void sendData(){



        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebase = firebaseAuth.getCurrentUser();
        String Uid = firebase.getUid();

        DatabaseReference databaseReference = firebaseDatabase.getReference("Result of quiz");
        databaseReference.child(Uid).setValue(hashMap);
    }
}