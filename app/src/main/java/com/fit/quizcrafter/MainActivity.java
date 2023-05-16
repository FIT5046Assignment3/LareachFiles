package com.fit.quizcrafter;


import static com.fit.quizcrafter.api.RetrofitClient.access_key;
import static com.fit.quizcrafter.api.RetrofitClient.getRetrofitService;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.fit.quizcrafter.api.RetrofitWeather;
import com.fit.quizcrafter.api.VolleyQueue;

import com.fit.quizcrafter.domain.User;
import com.fit.quizcrafter.domain.weather.Weather;
import com.fit.quizcrafter.loginactivity.CreateAccountClass;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.fit.quizcrafter.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private ActivityMainBinding binding;

    private FirebaseUser fireBaseUser;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    private String UserID;

    private String useForAccountName;

    private TextView textView,textViewForEmail;

    private String EmailPart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        init data for test delete after developed
//        initData(getApplicationContext());
//        getQuizByuserId(userId,null);
        setSupportActionBar(binding.appBarMain.toolbar);
//        email icon can be delete later
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//excute upload data work
                WorkRequest uploadWorkRequest =
                        new OneTimeWorkRequest.Builder(QuizWoker.class).build();
                WorkManager
                        .getInstance(getApplicationContext())
                        .enqueue(uploadWorkRequest);
                QuizWoker.quizList = new ArrayList<>();
                Snackbar.make(view, "Reveive upload function", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View headerView = navigationView.getHeaderView(0);
        textView = headerView.findViewById(R.id.user_name);
        textViewForEmail  = headerView.findViewById(R.id.userEmail);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_collection_quiz,R.id.nav_create,R.id.nav_report,R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //load weather image
//        RetrofitWeather retrofitWeather = getRetrofitService();
//        Call<ResponseBody> callAsync =
//                retrofitWeather.getWeatherbyLocation(access_key,"melbourne");
//        //makes an async request & invokes callback methods when the response returns
//        callAsync.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        Gson gson = new Gson();
//                        String str = response.body().string().toString();
//                        Weather weather = gson.fromJson(str, Weather.class);
//                        System.out.println(weather.getCurrent());
//                        if(weather.getCurrent() != null)
//                        {
//                            String url = weather.getCurrent().getWeather_icons().get(0);
//                            ImageLoader imageLoader = VolleyQueue.getInstance(getApplicationContext()).getImageLoader();
//                            ImageLoader.ImageListener listener = ImageLoader.getImageListener(findViewById(R.id.weather_imageView),
//                                    R.drawable.ic_launcher_background,
//                                    R.drawable.ic_launcher_foreground);
//                            imageLoader.get(url, listener);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    Log.i("Error ","Response failed");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                System.out.println("----------------------Fail to ge Weather");
//            }
//        });

    }
//for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
                    //get username
                    useForAccountName = createAccountClass.accountName;
                    textView.setText(useForAccountName);
                    //get email
                    EmailPart = createAccountClass.emailAddress;
                    textViewForEmail.setText(EmailPart);
//                    init all the user attr
                    User.name=useForAccountName;
                    User.email=EmailPart;
                    User.user_id = UserID;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        fireBaseUser = firebaseAuth.getCurrentUser();
        if(fireBaseUser == null)
        {
            Toast.makeText(getApplicationContext(), "user details is not on database", Toast.LENGTH_LONG).show();
        }
        else
        {
            ShowTheAccountName(fireBaseUser);
        }
    }
}