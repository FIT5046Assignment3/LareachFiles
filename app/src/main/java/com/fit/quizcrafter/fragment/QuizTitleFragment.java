package com.fit.quizcrafter.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentQuizTitleBinding;
import com.fit.quizcrafter.databinding.FragmentQuizWrittenModelBinding;

public class QuizTitleFragment extends Fragment {

    FragmentQuizTitleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuizTitleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}