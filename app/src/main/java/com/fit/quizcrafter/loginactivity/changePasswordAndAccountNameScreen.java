package com.fit.quizcrafter.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fit.quizcrafter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class changePasswordAndAccountNameScreen extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    private Button submitChanges;

    private TextView newaccountnameinput, newpasswordinput, confirmnewpasswordinput;


    private String UserID;

    private FirebaseAuth firebaseAuth;


    private FirebaseUser fireBaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_accountname);

        submitChanges = findViewById(R.id.SubmitChnages);
        newaccountnameinput = findViewById(R.id.newaccountnameinput);
        newpasswordinput = findViewById(R.id.newpasswordinput);
        confirmnewpasswordinput = findViewById(R.id.confirmnewpasswordinput);
        firebaseAuth = FirebaseAuth.getInstance();

        fireBaseUser = firebaseAuth.getCurrentUser();

        submitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserID = fireBaseUser.getUid();
                DatabaseReference databaseReference = firebaseDatabase.getReference("Data entry for create account").child(UserID);

                String new_Account_Name = newaccountnameinput.getText().toString();
                String newpassword_input = newpasswordinput.getText().toString();
                String confirm_new_password = confirmnewpasswordinput.getText().toString();


                if(new_Account_Name.isEmpty() || newpassword_input.isEmpty() || confirm_new_password.isEmpty())
                {
                    Toast.makeText(changePasswordAndAccountNameScreen.this, "You have to fill in the infomration", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (!newpassword_input.equals(confirm_new_password)) {
                        Toast.makeText(changePasswordAndAccountNameScreen.this, "password and confirm password should be the same", Toast.LENGTH_SHORT).show();
                    } else {
                        fireBaseUser.updatePassword(newpassword_input)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {

                                        databaseReference.child("accountName").setValue(new_Account_Name);
                                        databaseReference.child("password").setValue(newpassword_input);

                                        Toast.makeText(changePasswordAndAccountNameScreen.this, "password and accountname has been updated successful, you can now login with new password", Toast.LENGTH_SHORT).show();

                                        Intent intentforprofilescreen = new Intent(changePasswordAndAccountNameScreen.this, accountscreen.class);
                                        startActivity(intentforprofilescreen);

                                    } else {
                                        Toast.makeText(changePasswordAndAccountNameScreen.this, "password and account name not updated, try again", Toast.LENGTH_SHORT).show();
                                    }

                                });
                    }

                }

            }
            });
        }
    }



