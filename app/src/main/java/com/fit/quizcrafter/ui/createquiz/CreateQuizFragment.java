package com.fit.quizcrafter.ui.createquiz;

import static com.fit.quizcrafter.api.FirebaseApi.addQuiz;
import static com.fit.quizcrafter.api.FirebaseApi.userId;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentCreateQuizBinding;
import com.fit.quizcrafter.domain.Question;

import com.fit.quizcrafter.domain.Quiz;
import com.fit.quizcrafter.ui.createquiz.recyclelist.QuestionListFragment;
import com.fit.quizcrafter.ui.createquiz.recyclelist.QuestionViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class CreateQuizFragment extends Fragment  {

    private FragmentCreateQuizBinding binding;
    private QuestionViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateQuizBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        binding.addQuestionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddQuestionDialogFragment fragment = new AddQuestionDialogFragment();
                fragment.setAddQuestionListener(new AddQuestionDialogFragment.AddQuestionListener() {
                    @Override
                    public void onAddInputComplete(Question question) {
                        addQuestion(question);
                    }
                });
                fragment.show(getActivity().getSupportFragmentManager(),"addQuestionbtn");
            }
        });

        binding.Addquizbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quiz quiz = new Quiz();
                System.out.println("add quiz");
                EditText text = binding.quizTitle;
                quiz.setTitle(text.getText().toString());
                quiz.setQuestionList(viewModel.getQuizs().getValue());
                quiz.setDescription(binding.quizDescription.getText().toString());


                addQuiz(quiz, String.valueOf(R.string.user_id), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Toast.makeText(getContext(),"Complete",Toast.LENGTH_LONG).show();
                        viewModel.refresh();
                    }
                });
            }
        });
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addQuestion(Question question) {
        QuestionListFragment questionListFragment = (QuestionListFragment)binding.recycleQuestionList.getFragment();
        questionListFragment.addQuestion(question);
    }
}