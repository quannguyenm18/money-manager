package com.example.moneymanager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.model.User;

import java.util.ArrayList;

public class NewPasswordActivity extends AppCompatActivity {
    Button btn_sendnewpass;
    EditText edt_newpassword;
    EditText edt_reNewpassword;
    User usernew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        findIds();

    }

    private void findIds() {
        usernew= new User();
        edt_newpassword = findViewById(R.id.edt_forgetpass);
        edt_reNewpassword = findViewById(R.id.edt_repassword_new);
        btn_sendnewpass = findViewById(R.id.btn_sendnewpass);
        btn_sendnewpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPass = edt_newpassword.getText().toString().trim();
                String newRePass = edt_reNewpassword.getText().toString().trim();
                if (newPass.equals(newRePass)) {
                    Bundle bundle = getIntent().getExtras();
                    usernew= (User) bundle.get("ABCD");
                    usernew.setPassWord(newPass);
                    DatabaseQuanLiThuChi.getInstance(getApplicationContext()).UserDAO().Update(usernew);
                    Intent intent = new Intent(getApplicationContext(), SucessNewPassActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}