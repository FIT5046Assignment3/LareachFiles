package com.fit.quizcrafter.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.Response;
import com.fit.quizcrafter.R;
import com.fit.quizcrafter.api.ApiQuiz;
import com.fit.quizcrafter.databinding.FragmentHomeBinding;
import com.fit.quizcrafter.loginactivity.CreateAccountClass;
import com.fit.quizcrafter.loginactivity.HomeScreenActivity;
import com.fit.quizcrafter.loginactivity.accountscreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FirebaseUser fireBaseUser;

    private DatabaseReference databaseReference;

    private TextView accountNameFromDataBaseForClass;

    private String UserID;

    private Button goToProfile;

    private FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;

    private String useForAccountName;

    ImageView ImageForHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        new ApiQuiz().getRandomQuiz(getActivity().getApplicationContext(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                System.out.println(response);
//            }
//        });



        goToProfile = binding.goToprofile;

        accountNameFromDataBaseForClass = binding.accountNameFromDataBase;

        firebaseAuth = FirebaseAuth.getInstance();

        fireBaseUser = firebaseAuth.getCurrentUser();

//        ImageForHome = findViewById(R.id.imageViewForHome);

        if(fireBaseUser == null)
        {
            Toast.makeText(getContext(), "user details is not on database", Toast.LENGTH_LONG).show();
        }
        else{
            ShowTheAccountName(fireBaseUser);
        }

        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToProfile = new Intent(getActivity(), accountscreen.class);
                startActivity(intentToProfile);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        Button btn = view.findViewById(R.id.LaunchQuizFromHome);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_home_to_quizWelcomeFragment);
            }
        });
    }


    private void ShowTheAccountName(FirebaseUser fireBaseUser) {
        UserID = fireBaseUser.getUid();

        //get the user reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Data entry for create account");
        databaseReference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CreateAccountClass createAccountClass = snapshot.getValue(CreateAccountClass.class);
                if(createAccountClass != null)
                {
                    useForAccountName = createAccountClass.accountName;
                    accountNameFromDataBaseForClass.setText("Hello " + useForAccountName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "some error happened", Toast.LENGTH_LONG).show();

            }
        });
    }
}