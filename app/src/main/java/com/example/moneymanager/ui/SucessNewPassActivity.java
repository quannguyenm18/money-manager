package com.example.moneymanager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;

public class SucessNewPassActivity extends AppCompatActivity {

    Button btn_backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess_new_pass);
        findIds();

    }

    private void findIds() {

        btn_backLogin= findViewById(R.id.btn_backLogin);

        btn_backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}