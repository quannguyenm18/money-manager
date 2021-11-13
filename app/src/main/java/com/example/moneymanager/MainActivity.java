package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;


import com.example.moneymanager.dialog.DialogLogin;
import com.example.moneymanager.dialog.DialogRegister;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_dangki;
    private Button btn_dangnhap;
    private DialogRegister dialogRegister;
    private DialogLogin dialogLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();


    }

    private void findIds() {
        dialogRegister = new DialogRegister(this);
        dialogLogin= new DialogLogin(getApplicationContext());
        btn_dangki = findViewById(R.id.btn_dang_ky);
        btn_dangnhap = findViewById(R.id.btn_dang_nhap);
        btn_dangnhap.setOnClickListener(this::onClick);
        btn_dangki.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn_dangki) {
            dialogRegister.openDialogRegister(this);
        }
        if (view == btn_dangnhap) {
            dialogLogin.openDialogLogin(this);

        }

    }


}