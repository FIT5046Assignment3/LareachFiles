package com.fit.quizcrafter.ui.quizcollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentQuizdetailBinding;
import com.fit.quizcrafter.domain.Quiz;

import com.fit.quizcrafter.ui.createquiz.recyclelist.QuestionViewModel;
import com.google.gson.Gson;


public class QuizDetail extends Fragment {

    private FragmentQuizdetailBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQuizdetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//get quiz infor from args
        String strquiz= QuizDetailArgs.fromBundle(getArguments()).getQuiz();
        Quiz quiz = new Gson().fromJson(strquiz,Quiz.class);

        binding.quizDetailTitle.setText(quiz.getTitle());
        binding.quizDetailDesciption.setText(quiz.getDescription());

        QuestionViewModel viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        System.out.println(quiz);
        viewModel.setFlag(true);
        viewModel.setData(quiz.getQuestionList());

        return root;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        NavController navController = Navigation.findNavController(view);
//
//        Button btn = view.findViewById(R.id.launchQuizWelcomeBtn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navController.navigate(R.id.action_quizDetail_to_nav_quizWelcome_fragment);
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}