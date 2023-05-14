package com.fit.quizcrafter.ui.createquiz.recyclelist;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fit.quizcrafter.databinding.FragmentQuestionListBinding;
import com.fit.quizcrafter.domain.Question;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class QuestionListRecyclerViewAdapter extends RecyclerView.Adapter<QuestionListRecyclerViewAdapter.ViewHolder> {
    private QuestionViewModel questionViewModel;

    public QuestionListRecyclerViewAdapter(QuestionViewModel viewModel ) {
        questionViewModel =viewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentQuestionListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int inner_position = position;
        holder.mIdView.setText(String.valueOf(inner_position));

        String text = questionViewModel.getQuizs().getValue().get(inner_position).getQuestion();
        if(text.length() > 30)
        {
            text = text.substring(0,29);
        }
        holder.mContentView.setText(text);
        holder.binding.ivItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionViewModel.getQuizs().getValue().remove(inner_position);
                notifyDataSetChanged();
            }
        });
//       like  delete it
        if(questionViewModel.isFlag())
        {
            holder.binding.ivItemDelete.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return questionViewModel.getQuizs().getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public FragmentQuestionListBinding binding;

        public ViewHolder(FragmentQuestionListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            this.binding = binding;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public void addQuestion(Question question) {
        questionViewModel.addQuestion(question);
        notifyDataSetChanged();
    }


}