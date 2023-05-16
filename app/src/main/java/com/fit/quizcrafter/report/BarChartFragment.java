package com.fit.quizcrafter.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.fit.quizcrafter.databinding.BarChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BarChartFragment extends Fragment {
    private BarChartFragmentBinding binding;
    public BarChartFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the View for this fragment using the binding
        binding = BarChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SharedPreferences sharedPref= requireActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
        String startDate = sharedPref.getString("StartDate",null);
        String endDate = sharedPref.getString("EndDate",null);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser fireBaseUser = firebaseAuth.getCurrentUser();
        String userID = fireBaseUser.getUid();
        //Used ChatGPT to get the data between chosen dates
        Query query = FirebaseDatabase.getInstance().getReference("Result of quiz").child(userID).orderByKey().startAt(String.valueOf(startDate)).endAt(String.valueOf(endDate));

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int correctMCQ = 0;
                int wrongMCQ = 0;
                int writtenQns = 0;
                for (DataSnapshot dateSnapshot : snapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    correctMCQ += dateSnapshot.child("CorrectMcq").getValue(Integer.class);
                    wrongMCQ += dateSnapshot.child("WrongMcq").getValue(Integer.class);
                    writtenQns += dateSnapshot.child("Written").getValue(Integer.class);
                }
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(0, correctMCQ));
                barEntries.add(new BarEntry(1, wrongMCQ));
                barEntries.add(new BarEntry(2, writtenQns));


                BarDataSet barDataSet = new BarDataSet(barEntries, "Quiz Results");
                barDataSet.setColors(Color.GREEN, Color.RED, Color.BLUE);
                barDataSet.setValueTextSize(14f);
                BarData barData = new BarData(barDataSet);
                binding.barChart.setData(barData);
                barData.setBarWidth(1f);
                binding.barChart.setFitBars(true);
                binding.barChart.setVisibility(View.VISIBLE);
                binding.barChart.animateY(1000);
                Legend legend = binding.barChart.getLegend();
                List<LegendEntry> legendEntries = new ArrayList<>();
                legendEntries.add(new LegendEntry("Correct", Legend.LegendForm.SQUARE, Float.NaN, Float.NaN, null, Color.GREEN));
                legendEntries.add(new LegendEntry("Wrong", Legend.LegendForm.SQUARE, Float.NaN, Float.NaN, null, Color.RED));
                legendEntries.add(new LegendEntry("Written", Legend.LegendForm.SQUARE, Float.NaN, Float.NaN, null, Color.BLUE));
                legend.setCustom(legendEntries);
                legend.setTextSize(12f);
                legend.setEnabled(true);
                binding.barChart.getDescription().setEnabled(false);
                binding.barChart.invalidate();
            }
        @Override
        public void onCancelled (@NonNull DatabaseError error){

        }
    });
    return view;
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}