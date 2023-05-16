package com.fit.quizcrafter.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.databinding.FragmentLogoutBinding;
import com.fit.quizcrafter.loginactivity.loginScreen;
public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;

    private Button logOutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


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