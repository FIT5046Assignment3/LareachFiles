package com.fit.quizcrafter.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fit.quizcrafter.MainActivity;
import com.fit.quizcrafter.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginScreen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        EditText emailAddress = findViewById(R.id.EmailAddressText);
        EditText password = findViewById(R.id.passwordinputText);
        Button loginButton = findViewById(R.id.loginButton);
        register = findViewById(R.id.signupfordotacc);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               String emailAdd = emailAddress.getText().toString();
                                               String thePasswordTxt = password.getText().toString();

                                               if (!emailAdd.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()) {
                                                   if (!thePasswordTxt.isEmpty()) {
                                                       firebaseAuth.signInWithEmailAndPassword(emailAdd, thePasswordTxt)
                                                               .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                                   @Override
                                                                   public void onSuccess(AuthResult authResult) {
                                                                       Toast.makeText(loginScreen.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                                       Intent intentforHomeScreen = new Intent(loginScreen.this, MainActivity.class);

                                                                       startActivity(intentforHomeScreen);

                                                                   }
                                                               }).addOnFailureListener(new OnFailureListener() {
                                                                   @Override
                                                                   public void onFailure(@NonNull Exception e) {
                                                                       Toast.makeText(loginScreen.this, "Login is not succeed", Toast.LENGTH_SHORT).show();
                                                                   }
                                                               });
                                                   } else if (emailAdd.isEmpty()) {
                                                       emailAddress.setError("email address is required");

                                                   } else if (thePasswordTxt.isEmpty()) {
                                                       password.setError("password is required");
                                                   }
                                               }

                                           }
                                       });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentforSignupScreen = new Intent(loginScreen.this,SignUpScreen.class);
                startActivity(intentforSignupScreen);
            }
            });
        }


    }

