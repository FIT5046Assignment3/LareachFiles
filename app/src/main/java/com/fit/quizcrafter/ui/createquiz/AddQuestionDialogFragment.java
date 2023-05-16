package com.fit.quizcrafter.ui.createquiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


import com.fit.quizcrafter.databinding.FragmentAddQuestionBinding;
import com.fit.quizcrafter.domain.Correct_answers;
import com.fit.quizcrafter.domain.Question;


public class AddQuestionDialogFragment extends DialogFragment {
    private FragmentAddQuestionBinding binding;
    private AddQuestionListener addQuestionListener;
    public interface AddQuestionListener {
        void onAddInputComplete(Question question);
    }

    public void setAddQuestionListener(AddQuestionListener addQuestionListener) {
        this.addQuestionListener = addQuestionListener;
    }

    public AddQuestionListener getAddQuestionListener() {
        return addQuestionListener;
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_add_question, null);
        binding = FragmentAddQuestionBinding.inflate(inflater);
        View root = binding.getRoot();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(root)
                // Add action buttons
                .setPositiveButton("Add Question",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Question question = new Question();
                                EditText editText;
                                editText = binding.QuestioneditText;
                                question.setQuestion(editText.getText().toString());

                                CheckBox checkBox = binding.mulcheckBox;
                                Boolean mul =  checkBox.isChecked();
//              set option
                                editText = binding.OptioneditText1;
                                question.getAnswers().setAnswer_a(editText.getText().toString());

                                editText = binding.OptioneditText2;
                                question.getAnswers().setAnswer_b(editText.getText().toString());

                                editText = binding.OptioneditText3;
                                question.getAnswers().setAnswer_c(editText.getText().toString());

                                editText = binding.OptioneditText4;
                                question.getAnswers().setAnswer_d(editText.getText().toString());

                                editText = binding.OptioneditText5;
                                question.getAnswers().setAnswer_e(editText.getText().toString());
//                is multiple
                                if(mul)
                                {
                                    System.out.println(question);
                                    question.setMultiple_correct_answers("true");
                                    editText = binding.AnswereditText;
                                    System.out.println(question);
                                    String[] answers = editText.getText().toString().split(",");
                                    Correct_answers correct_answers = new Correct_answers();
                                    for(String answer: answers)
                                    {
                                        System.out.println(answer);
                                        switch (answer)
                                        {
                                            case "1":
                                                correct_answers.setAnswer_a_correct("true");
                                                break;
                                            case "2":
                                                correct_answers.setAnswer_b_correct("true");
                                                break;
                                            case "3":
                                                correct_answers.setAnswer_b_correct("true");
                                                break;
                                            case "4":
                                                correct_answers.setAnswer_b_correct("true");
                                                break;
                                            case "5":
                                                correct_answers.setAnswer_b_correct("true");
                                                break;
                                            default:
                                                System.out.println("error ");

                                        }
                                    }
                                    question.setCorrect_answers(correct_answers);
                                }
                                else
                                {
                                    question.setMultiple_correct_answers("false");
                                    editText = binding.AnswereditText;
                                    question.setCorrect_answer(editText.getText().toString());
                                }

//                add question function
                                getAddQuestionListener().onAddInputComplete(question);
                            }
                        })
                .setNegativeButton("Cancel", null);


        return builder.create();
    }

}

