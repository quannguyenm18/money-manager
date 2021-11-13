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
import com.example.moneymanager.model.KhoanChi;
import com.example.moneymanager.model.KhoanThu;

import java.util.Calendar;

public class DetailKhoanChiActivity extends AppCompatActivity {
    private EditText edt_Name,edt_money,edt_time;
    private Button btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_khoan_chi);
        findID();
    }

    private void findID() {

        edt_Name= findViewById(R.id.edt_name_khoanchi_detail);
        edt_money=findViewById(R.id.edt_money_khoanChi_detail);
        edt_time=findViewById(R.id.edt_time_khoanchi_detail);
        btn_update=findViewById(R.id.btn_update_khoanChi);

        setData();
        edt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();

                DatePickerDialog datePicker = new DatePickerDialog(DetailKhoanChiActivity.this, datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                //Create a cancel button and set the title of the dialog.
                datePicker.setCancelable(false);
                datePicker.setTitle("Chọn Thời Gian");
                datePicker.show();

            }
        });
        
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateKhoanChi();

            }
        });


    }

    private void updateKhoanChi() {

        String nameKT = edt_Name.getText().toString().trim();
        int sotien = Integer.parseInt(edt_money.getText().toString().trim());
        String datetime = edt_time.getText().toString().trim();
        if (nameKT.equals("") && sotien == 0 && datetime.equals("")) {
            Toast.makeText(this, "vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Bundle bundle = getIntent().getExtras();
            KhoanChi khoanchi = (KhoanChi) bundle.get("ABC");
            khoanchi.setTenkhoanchi(nameKT);
            khoanchi.setTime(datetime);
            khoanchi.setMoney(sotien);

            DatabaseQuanLiThuChi.getInstance(getApplicationContext()).KhoanChiDAO().updateKhoanChi(khoanchi);
            Toast.makeText(this, "cập nhật Thành Công", Toast.LENGTH_SHORT).show();
            finish();
            loadActivity();
        }



    }

    private void setData() {
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            return;
        }
        KhoanChi khoanChi = (KhoanChi) bundle.get("ABC");
        edt_Name.setText(khoanChi.getTenkhoanchi());
        edt_time.setText(khoanChi.getTime());
        edt_money.setText(String.valueOf(khoanChi.getMoney()));

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

    private void loadActivity() {
        Intent intent= new Intent(DetailKhoanChiActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}