package com.fit.quizcrafter.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.ReportsBinding;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class Reports extends Fragment {
    private ReportsBinding binding;
    public Reports(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=ReportsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser fireBaseUser = firebaseAuth.getCurrentUser();
        String userID = fireBaseUser.getUid();
        DatabaseReference fDatabase = FirebaseDatabase.getInstance().getReference("Result of quiz").child(userID);

        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> dateSpinner = new ArrayList<>();
                // Set default prompt
                dateSpinner.add("Select a date");
                for (DataSnapshot dateSnapshot : snapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    dateSpinner.add(date);
                }
                //ChatGPT used for filling spinners
                //Creates ArrayAdapter using List
                ArrayAdapter<String> startAdapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, dateSpinner);
                // Set the dropdown layout style
                startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Set the adapter to the spinner
                binding.startSpinner.setAdapter(startAdapter);


                //Creates ArrayAdapter using List
                ArrayAdapter<String> endAdapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, dateSpinner);
                // Set the dropdown layout style
                endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Set the adapter to the spinner
                binding.endSpinner.setAdapter(endAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "No data found, please complete a quiz", Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.chart_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.chartSpinner.setAdapter(adapter);

        SharedPreferences sharedPref = requireActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
        SharedPreferences.Editor spStart = sharedPref.edit();
        SharedPreferences.Editor spEnd = sharedPref.edit();

        binding.generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chartType = binding.chartSpinner.getSelectedItem().toString();
                String startDate = binding.startSpinner.getSelectedItem().toString();
                String endDate = binding.endSpinner.getSelectedItem().toString();

                if (!startDate.equals("") && !startDate.equals("Select a date") && !endDate.equals("") && !endDate.equals("Select a date")){
                    spStart.putString("StartDate", startDate);
                    spEnd.putString("EndDate", endDate);
                    spStart.apply();
                    spEnd.apply();
                    if (chartType.equals("Bar Chart")) {
                        replaceChildFragment(new BarChartFragment());
                    } else if (chartType.equals("Pie Chart")) {
                        replaceChildFragment(new PieChartFragment());
                    }
                } else {
                    Toast.makeText(getContext(), "Please select a date", Toast.LENGTH_LONG).show();
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
