package com.fit.quizcrafter.loginactivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fit.quizcrafter.MainActivity;
import com.fit.quizcrafter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignupMoreDetails extends AppCompatActivity {


    private EditText accountName,AddressOne,AddressTwo,Cityinput,zipinput,countryinput;

    private Spinner spinnergender;

    private Spinner stateSpinner;

    private Button SignUP;

    private String regex = "^[a-zA-Z]+$";




    EditText datePicker;
    TextView DateOfBirth,gotologin;

    DatePickerDialog.OnDateSetListener setListener;

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_more);

        gotologin = findViewById(R.id.gotologintu);
        accountName = findViewById(R.id.enterAccountname);
        SignUP = findViewById(R.id.SignUP);
        AddressOne = findViewById(R.id.AddressOne);
        AddressTwo = findViewById(R.id.AddressTwo);
        Cityinput = findViewById(R.id.Cityinput);
        zipinput = findViewById(R.id.zipinput);
        countryinput = findViewById(R.id.countryinput);
        spinnergender = findViewById(R.id.genderspinner);
        stateSpinner = findViewById(R.id.statespinner);
        firebaseAuth = FirebaseAuth.getInstance();

        //calendar
        datePicker = findViewById(R.id.datePicker);
        DateOfBirth = findViewById(R.id.DateOfBirth);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                SignupMoreDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        datePicker.setText(date);
                    }


                },year,month,day);
                datePickerDialog.show();
            }
        });


        //spinner for gender
        Spinner spinner = findViewById(R.id.genderspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.genderarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignupMoreDetails.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //spinner for state(postal)
        Spinner spinnersate = findViewById(R.id.statespinner);
        ArrayAdapter<CharSequence> stateadpater = ArrayAdapter.createFromResource(getApplicationContext(),R.array.statearray,android.R.layout.simple_spinner_item);
        stateadpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersate.setAdapter(stateadpater);

        spinnersate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(SignupMoreDetails.this,"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

        SignUP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String EmailInput = getIntent().getStringExtra("Email");
                String passWordInput = getIntent().getStringExtra("password");
                String account_name = accountName.getText().toString();
                String date_picker = datePicker.getText().toString();
                String Address_One = AddressOne.getText().toString();
                String Address_two = AddressTwo.getText().toString();
                String city_input = Cityinput.getText().toString();
                String state_spinner = stateSpinner.getSelectedItem().toString();
                String zip_input = zipinput.getText().toString();
                String country_input = countryinput.getText().toString();
                String spinner_gender = spinnergender.getSelectedItem().toString();
                CreateAccountClass createAccountClass = new CreateAccountClass(account_name, date_picker, Address_One, Address_two, city_input, state_spinner, zip_input, country_input, spinner_gender, EmailInput, passWordInput);

                if (accountName.length() == 0) {
                    accountName.setError("account name is required");
                } else if (AddressOne.length() == 0) {
                    AddressOne.setError("address one is required");
                } else if (Cityinput.length() == 0) {
                    Cityinput.setError("city is required");
                } else if (zipinput.length() == 0) {
                    zipinput.setError("zip is required");
                } else if (zip_input.matches(regex)) {
                    zipinput.setError("only numbers are allowed");
                } else if (countryinput.length() == 0) {
                    countryinput.setError("country is required");
                } else {

                    firebaseAuth.createUserWithEmailAndPassword(EmailInput, passWordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //create user account and if succeed
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                                //store data in firebase realtime database
                                DatabaseReference databaseReference = firebaseDatabase.getReference("Data entry for create account");
                                databaseReference.child(firebaseUser.getUid()).setValue(createAccountClass);


                                Toast.makeText(SignupMoreDetails.this, "Sign up successful, both email and address are uploaded to realtime database and authentication" , Toast.LENGTH_SHORT).show();

                                Intent intentforHomeScreen = new Intent(SignupMoreDetails.this, MainActivity.class);
                                intentforHomeScreen.putExtra("AccountName",account_name);
                                startActivity(intentforHomeScreen);
                                finish();
                            } else {
                                Toast.makeText(SignupMoreDetails.this, "Sign up has not been succeed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }
            }

        });
    }
}