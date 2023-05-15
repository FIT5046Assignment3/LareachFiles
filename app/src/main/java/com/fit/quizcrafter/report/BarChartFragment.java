package com.fit.quizcrafter.report;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.fit.quizcrafter.databinding.BarChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartFragment extends Fragment {
    private BarChartFragmentBinding binding;
    public BarChartFragment(){}
    public DatabaseReference fDatabase;
    public FirebaseUser fireBaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
         binding = BarChartFragmentBinding.inflate(inflater, container, false);
         View view = binding.getRoot();

         /*FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
         fireBaseUser= firebaseAuth.getCurrentUser();
         String userID = fireBaseUser.getUid();
         fDatabase = FirebaseDatabase.getInstance().getReference("Result of Quiz").child(userID);*/
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 6766));
        barEntries.add(new BarEntry(1, 4444));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Quiz Questions");
        barDataSet.setColors(new int[] {Color.GREEN, Color.RED});
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Correct", "Incorrect"));
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        barData.setBarWidth(1.0f);
        binding.barChart.setVisibility(View.VISIBLE);
        binding.barChart.animateY(1000);
        Description description = new Description();
        description.setText("Quiz Questions");
        binding.barChart.setDescription(description);
        binding.barChart.invalidate();
        return view;
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

/*             fDatabase.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {*/

                /*@Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BarEntry> userResults = new ArrayList<>();
                ArrayList<String> xAxisValues = new ArrayList<>();

                int index = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String xAxisValue = dataSnapshot.child("category").getValue(String.class); //ADD .child(database name) for each category in database
                    int value = dataSnapshot.child("value").getValue(Integer.class); //ADD .child(database name) for each category in database
                    userResults.add(new BarEntry(index, value));
                    xAxisValues.add(xAxisValue);
                    index++;
                }
                BarDataSet barDataSet = new BarDataSet(userResults, "Quiz Questions");
                barDataSet.setColors(Color.GREEN, Color.RED, Color.YELLOW);
                binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
                BarData barData = new BarData(barDataSet);
                binding.barChart.setData(barData);
                barData.setBarWidth(1.0f);
                binding.barChart.setVisibility(View.VISIBLE);
                binding.barChart.animateY(1000);
                YAxis yAxis = binding.barChart.getAxisLeft();
                yAxis.setAxisMaximum(15);
                Description description = new Description();
                description.setText("Quiz Questions");
                binding.barChart.setDescription(description);
                binding.barChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

/*BarDataSet barDataSet = new BarDataSet(barEntryList, "Quiz Questions");
         barDataSet.setColors(Color.GREEN, Color.RED, Color.YELLOW);
         binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(Collections.singleton("qns")));
         BarData barData = new BarData(barDataSet);
         binding.barChart.setData(barData);
         barData.setBarWidth(1.0f);
         binding.barChart.setVisibility(View.VISIBLE);
         binding.barChart.animateY(1000);
         YAxis yAxis = binding.barChart.getAxisLeft();
         yAxis.setAxisMaximum(15);
         Description description = new Description();
         description.setText("Quiz Questions");
         binding.barChart.setDescription(description);
         binding.barChart.invalidate();*/