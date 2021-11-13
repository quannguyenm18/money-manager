package com.example.moneymanager.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button btn_send;
    private TextView tvBackLogin;
    private EditText edt_account;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        findIDs();

    }

    private void findIDs() {
        btn_send = findViewById(R.id.btn_send);
        tvBackLogin = findViewById(R.id.tvback_login);
        edt_account = findViewById(R.id.edt_forgetpassword);
        user= new User();

        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = edt_account.getText().toString().trim();

                 user = DatabaseQuanLiThuChi.getInstance(getApplicationContext())
                        .UserDAO().searchName(account);
                if (user != null) {
                    Intent intent = new Intent(getApplicationContext(), SuccessForgetPassActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ABC", user);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Tài Khoản Không tồn Tại", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}