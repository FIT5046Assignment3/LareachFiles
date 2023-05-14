package com.fit.quizcrafter.ui.createquiz.recyclelist;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.domain.Question;
import com.fit.quizcrafter.domain.Quiz;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class QuestionListFragment extends Fragment {
    private QuestionViewModel viewModel;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private QuestionListRecyclerViewAdapter adapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuestionListFragment newInstance(int columnCount) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_list_list, container, false);

        viewModel = new ViewModelProvider(getParentFragment()).get(QuestionViewModel.class);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new QuestionListRecyclerViewAdapter(viewModel);
            recyclerView.setAdapter(adapter);

            System.out.println(viewModel.getQuizs().getValue());
        }
        return view;
    }

    public void addQuestion(Question question) {
       adapter.addQuestion(question);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.setData(new ArrayList<>());
    }


}