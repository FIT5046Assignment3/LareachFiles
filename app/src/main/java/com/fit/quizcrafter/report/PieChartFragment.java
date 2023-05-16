package com.fit.quizcrafter.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fit.quizcrafter.databinding.PieChartFragmentBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PieChartFragment extends Fragment {
    private PieChartFragmentBinding binding;

    String date;
    public PieChartFragment(){}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = PieChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SharedPreferences sharedPref= requireActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
        String startDate = sharedPref.getString("StartDate",null);
        String endDate = sharedPref.getString("EndDate",null);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser fireBaseUser = firebaseAuth.getCurrentUser();
        String userID = fireBaseUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("Result of quiz").child(userID).orderByKey().startAt(String.valueOf(startDate)).endAt(String.valueOf(endDate));

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int correctMCQTotal = 0;
                int wrongMCQTotal = 0;
                int writeTheQuestionsTotal = 0;
                for (DataSnapshot dateSnapshot : snapshot.getChildren()) {
                    correctMCQTotal += dateSnapshot.child("CorrectMcq").getValue(Integer.class);
                    wrongMCQTotal += dateSnapshot.child("WrongMcq").getValue(Integer.class);
                    writeTheQuestionsTotal += dateSnapshot.child("Written").getValue(Integer.class);
                }
                ArrayList<PieEntry> pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(correctMCQTotal, "Correct MCQ"));
                pieEntries.add(new PieEntry(wrongMCQTotal, "Wrong MCQ"));
                pieEntries.add(new PieEntry(writeTheQuestionsTotal, "Write The Questions"));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Quiz Results");
                pieDataSet.setColors(Color.GREEN, Color.RED, Color.BLUE);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueTextSize(14f);

                PieData pieData = new PieData(pieDataSet);
                binding.pieChart.setData(pieData);
                binding.pieChart.setEntryLabelColor(Color.WHITE);
                binding.pieChart.setEntryLabelTextSize(10f);
                binding.pieChart.setDrawEntryLabels(true);
                binding.pieChart.setData(pieData);
                binding.pieChart.setVisibility(View.VISIBLE);
                binding.pieChart.animateY(1000);
                Legend legend = binding.pieChart.getLegend();
                legend.setEnabled(true);
                binding.pieChart.getDescription().setEnabled(false);
                binding.pieChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

/*if (snapshot.exists()) {
                    ArrayList<String> keys = new ArrayList<>();
                    ArrayList<Integer> values = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String key = dataSnapshot.getKey();
                        int value = dataSnapshot.getValue(Integer.class);

                        keys.add(key);
                        values.add(value);
                    }*/