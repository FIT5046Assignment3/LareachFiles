package com.fit.quizcrafter.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fit.quizcrafter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeScreenActivity extends AppCompatActivity {


    private FirebaseUser fireBaseUser;

    private DatabaseReference databaseReference;

    private TextView accountNameFromDataBaseForClass;

    private String UserID;

    private Button goToProfile;

    private FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;

    private String useForAccountName;

    ImageView ImageForHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);


        goToProfile = findViewById(R.id.goToprofile);

        accountNameFromDataBaseForClass = findViewById(R.id.accountNameFromDataBase);

        firebaseAuth = FirebaseAuth.getInstance();

        fireBaseUser = firebaseAuth.getCurrentUser();

//        ImageForHome = findViewById(R.id.imageViewForHome);

        if(fireBaseUser == null)
        {
            Toast.makeText(HomeScreenActivity.this, "user details is not on database", Toast.LENGTH_LONG).show();
        }
        else{
                ShowTheAccountName(fireBaseUser);
        }

        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToProfile = new Intent(HomeScreenActivity.this,accountscreen.class);
                startActivity(intentToProfile);
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
                Toast.makeText(HomeScreenActivity.this, "some error happened", Toast.LENGTH_LONG).show();
            }
        });
    }
}
