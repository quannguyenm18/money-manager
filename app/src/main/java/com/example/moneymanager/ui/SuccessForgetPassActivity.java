package com.example.moneymanager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneymanager.R;
import com.example.moneymanager.model.User;

public class SuccessForgetPassActivity extends AppCompatActivity {
    Button btn_newpass;
    User usernew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_forget_pass);

        getData();
        findIds();
    }

    private void findIds() {
        btn_newpass= findViewById(R.id.btn_newpass);
        btn_newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),NewPasswordActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ABCD", usernew);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    public void getData(){
        usernew= new User();
        Bundle bundle = getIntent().getExtras();
        usernew= (User) bundle.get("ABC");
    }
}