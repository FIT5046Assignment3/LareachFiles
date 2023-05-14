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

import com.fit.quizcrafter.MainActivity;
import com.fit.quizcrafter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class accountscreen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;

    private String useForAccountName;

    private FirebaseUser fireBaseUser;

    private DatabaseReference databaseReference;

    TextView inputAccountName;
    private Button changePasswordAndUsername,submitPhoto;

    private Button ClickBackToHomePage;

    private Button mapbtn;

    ImageView imageViewForProfile;
    private String UserID;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountscreen);

        firebaseAuth = FirebaseAuth.getInstance();

        fireBaseUser = firebaseAuth.getCurrentUser();

        inputAccountName = findViewById(R.id.inputAccountName);

        changePasswordAndUsername = findViewById(R.id.changePasswordAndUsername);

//        submitPhoto = findViewById(R.id.submitPhoto);

        ClickBackToHomePage = findViewById(R.id.ClickBackToHomePage);

        imageViewForProfile = findViewById(R.id.imageViewForProfile);

        if(fireBaseUser == null)
        {
            Toast.makeText(accountscreen.this, "user details is not on database", Toast.LENGTH_LONG).show();
        }
        else{
            ShowTheAccountName(fireBaseUser);
        }

        changePasswordAndUsername.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intentforchangeaccandpassword = new Intent(accountscreen.this,changePasswordAndAccountNameScreen.class);
                startActivity(intentforchangeaccandpassword);
            }
        });

        mapbtn = findViewById(R.id.mapbutton);

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(accountscreen.this,mapActivity.class);
                startActivity(intent);
            }
        });

        ClickBackToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForbackToHome = new Intent(accountscreen.this, MainActivity.class);
                startActivity(intentForbackToHome);
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
                    inputAccountName.setText(useForAccountName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(accountscreen.this, "some error happened", Toast.LENGTH_LONG).show();
            }
        });
    }
}
