package com.fit.quizcrafter.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fit.quizcrafter.MainActivity;
import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentHomeBinding;
import com.fit.quizcrafter.databinding.FragmentLogoutBinding;
import com.fit.quizcrafter.loginactivity.accountscreen;
import com.fit.quizcrafter.loginactivity.loginScreen;
import com.fit.quizcrafter.ui.home.HomeViewModel;
public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;

    private Button logOutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View findView = inflater.inflate(R.layout.fragment_logout,container,false);

        logOutBtn = findView.findViewById(R.id.text_logout);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToLogin = new Intent(getContext(), loginScreen.class);
                startActivity(returnToLogin);
            }
        });

        return findView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}