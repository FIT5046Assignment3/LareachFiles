package com.fit.quizcrafter.report;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fit.quizcrafter.databinding.PieChartFragmentBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PieChartFragment extends Fragment {
    private PieChartFragmentBinding binding;
    public PieChartFragment(){}
    public DatabaseReference fDatabase;
    public FirebaseUser fireBaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = PieChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        fireBaseUser= firebaseAuth.getCurrentUser();
        String userID = fireBaseUser.getUid();
        fDatabase = FirebaseDatabase.getInstance().getReference("Result of Quiz").child(userID);
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(6766, "Correct"));
        pieEntries.add(new PieEntry(4444, "Incorrect"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Quiz Questions");
        pieDataSet.setColors(new int[] {Color.GREEN, Color.RED});
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueTextColor(Color.BLACK);

        PieData pieData = new PieData(pieDataSet);
        binding.pieChart.setData(pieData);
        binding.pieChart.setVisibility(View.VISIBLE);
        binding.pieChart.animateY(1000);
        Description description = new Description();
        description.setText("Quiz Questions");
        binding.pieChart.setDescription(description);
        binding.pieChart.invalidate();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
/*HashMap<String, Object> hashmap = new HashMap<>();
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    Object object = dataSnapshot.getValue();

                    hashmap.put(key, object);

                    System.out.printf(String.valueOf(hashmap));*/

/*int wrongMcq = dataSnapshot.child("WrongMcq").getValue(Integer.class);
                int correctMcq = dataSnapshot.child("correctMCQ").getValue(Integer.class);
                int writeTheQuestions = dataSnapshot.child("writethequestions").getValue(Integer.class);

                // Generate bar chart
               /* generatePieChart(wrongMcq, correctMcq, writeTheQuestions);*/

                /*PieDataSet pieDataSet = new PieDataSet(userID, "Quiz Questions");
                pieDataSet.setColors(Color.GREEN, Color.RED);
                pieDataSet.setValueTextSize(12f);
                pieDataSet.setValueTextColor(Color.BLACK);
                PieData pieData = new PieData(pieDataSet);
                binding.pieChart.setData(pieData);
                binding.pieChart.setVisibility(View.VISIBLE);
                binding.pieChart.animateY(1000);
                Description description = new Description();
                description.setText("Quiz Questions");
                binding.pieChart.setDescription(description);
                binding.pieChart.invalidate();*/
