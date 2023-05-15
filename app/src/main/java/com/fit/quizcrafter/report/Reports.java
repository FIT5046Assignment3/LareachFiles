package com.fit.quizcrafter.report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.ReportsBinding;



public class Reports extends Fragment {
    private ReportsBinding binding;
    public Reports(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=ReportsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        //chatGPT Initialize Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.chart_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.chartSpinner.setAdapter(adapter);
        //chatGPT end

        binding.generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chartType = binding.chartSpinner.getSelectedItem().toString();
                if (chartType.equals("Bar Chart")) {
                    replaceChildFragment(new BarChartFragment());

                }
                else if (chartType.equals("Pie Chart")) {
                    replaceChildFragment(new PieChartFragment());

                }
            }
        });
        return view;
    }

    private void replaceChildFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.childWinRecordsFragment, nextFragment);
        fragmentTransaction.commit();

    }
}
