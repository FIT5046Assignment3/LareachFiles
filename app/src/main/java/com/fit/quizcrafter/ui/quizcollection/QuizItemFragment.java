package com.fit.quizcrafter.ui.quizcollection;



import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fit.quizcrafter.R;

import com.fit.quizcrafter.domain.Quiz;
import com.fit.quizcrafter.ui.createquiz.recyclelist.QuestionViewModel;
import com.google.gson.Gson;

/**

 * Use the {@link QuizItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizItemFragment extends Fragment {

    private QuizCollectionViewModel viewModel ;

    public QuizItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuizItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizItemFragment newInstance() {
        QuizItemFragment fragment = new QuizItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_quizitem, container, false);

//        load data from view model
        viewModel = new ViewModelProvider(this).get(QuizCollectionViewModel.class);

        LinearLayout linearLayout = view.findViewById(R.id.quizlist);

//        observe data and update ui
        viewModel.getQuizs().observe(getViewLifecycleOwner(), quizzes -> {
            linearLayout.removeAllViews();
            for (Quiz quiz : quizzes)
            {
                Button btn = new Button(getContext());
                btn.setText(quiz.getTitle());
                linearLayout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                            tranform key and quiz detail
                        QuizItemFragmentDirections.ActionQuizItemFragmentToQuizDetail action =
                                QuizItemFragmentDirections.actionQuizItemFragmentToQuizDetail(new Gson().toJson(quiz));
                        Navigation.findNavController(view).navigate(action);
                    }
                });
            }
        });
        return view;
    }

}