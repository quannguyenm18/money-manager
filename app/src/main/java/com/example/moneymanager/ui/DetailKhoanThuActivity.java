package com.example.moneymanager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.model.KhoanThu;

import java.util.Calendar;

public class DetailKhoanThuActivity extends AppCompatActivity {
    private Button btn_update;
    private EditText edt_tenKt, edt_money, edt_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_khoan_thu);
        findIDS();

    }
    private void findIDS() {

        btn_update = findViewById(R.id.btn_update);
        edt_money = findViewById(R.id.edt_money_khoanThu_detail);
        edt_tenKt = findViewById(R.id.edt_name_khoanthu_detail);
        edt_time = findViewById(R.id.edt_time_khoanthu_detail);
        setData();
        edt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                DatePickerDialog datePicker = new DatePickerDialog(DetailKhoanThuActivity.this, datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                //Create a cancel button and set the title of the dialog.
                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");
                datePicker.show();
            }
        });



        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateKhoanThu();

            }
        });
    }

    private void updateKhoanThu() {
        String nameKT = edt_tenKt.getText().toString().trim();
        int sotien = Integer.parseInt(edt_money.getText().toString().trim());
        String datetime = edt_time.getText().toString().trim();
        if (nameKT.equals("") && sotien == 0 && datetime.equals("")) {
            Toast.makeText(this, "vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Bundle bundle = getIntent().getExtras();
            KhoanThu khoanThu = (KhoanThu) bundle.get("data");
            khoanThu.setTenKhoanThu(nameKT);
            khoanThu.setThoigianKhoanThu(datetime);
            khoanThu.setTienKhoanThu(sotien);

            DatabaseQuanLiThuChi.getInstance(getApplicationContext()).KhoanThuDAO().updateKhoanThu(khoanThu);
            Toast.makeText(this, "cập nhật Thành Công", Toast.LENGTH_SHORT).show();
            finish();
            loadActivity();



        }


    }

    private void loadActivity() {
        Intent intent= new Intent(DetailKhoanThuActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    private void setData() {
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            return;
        }
        KhoanThu khoanThu = (KhoanThu) bundle.get("data");
        edt_tenKt.setText(khoanThu.getTenKhoanThu());
        edt_time.setText(khoanThu.getThoigianKhoanThu());
        edt_money.setText(String.valueOf(khoanThu.getTienKhoanThu()));

    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            edt_time.setText(month1 + "/" + day1 + "/" + year1);
        }
    };


}