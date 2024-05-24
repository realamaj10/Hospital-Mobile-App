package com.rapidzz.hospital_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rapidzz.hospital_management.databinding.ActivityLoginBinding;
import com.rapidzz.hospital_management.databinding.ActivityMainBinding;
import com.rapidzz.hospital_management.models.PatientModel;
import com.rapidzz.hospital_management.models.UserModel;
import com.rapidzz.hospital_management.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference reference;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("user");
        binding.btnLogin.setOnClickListener(view -> {
            if (isValidForm()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                goToLogin();
            }
        });
    }

    private void goToLogin() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel model = snapshot.getValue(UserModel.class);
                successLogin(model);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void successLogin(UserModel model) {
        if (model != null) {
            if (model.getEmail().equals(binding.etEmailOrUsername.getText().toString()) && model.getPassword().equals(binding.etPassword.getText().toString())) {
                binding.progressBar.setVisibility(View.GONE);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                new SessionManager(LoginActivity.this).saveUser(model);
                finish();
            } else {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean isValidForm() {
        if (binding.etEmailOrUsername.getText().toString().isEmpty()) {
            binding.etEmailOrUsername.setError("Email is required");
            binding.etEmailOrUsername.requestFocus();
            return false;
        } else if (binding.etPassword.getText().toString().isEmpty()) {
            binding.etPassword.setError("Password is required");
            binding.etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}