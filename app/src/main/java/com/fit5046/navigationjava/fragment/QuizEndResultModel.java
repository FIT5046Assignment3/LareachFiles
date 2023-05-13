package com.fit5046.navigationjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fit5046.navigationjava.databinding.FragmentQuizEndResultModelBinding;
import com.fit5046.navigationjava.viewmodel.SharedViewModel;

import java.util.ArrayList;

public class QuizEndResultModel extends Fragment {

    SharedViewModel model;
    public int correctMcq;
    public int wrongMcq;
    public int writtenQuestions;

    private FragmentQuizEndResultModelBinding binding;
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
            }
        });

        System.out.println(correctMcq + " " + wrongMcq+" "+writtenQuestions);

        return view;
    }
}