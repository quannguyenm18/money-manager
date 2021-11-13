package com.example.moneymanager.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.interfaces.ILoadDataKhoanThu;
import com.example.moneymanager.model.KhoanThu;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DiaLogThemKhoanThu {

    private Button btn_them;
    private EditText edt_tenKhoanThu_dialog, edt_tienKhoanThu_dialog, edt_thoiGian_KhoanTHu_dialog;
    private Context context;

    private ILoadDataKhoanThu iLoadDataKhoanThu;
    private KhoanThu khoanThu;


    public DiaLogThemKhoanThu(Context context,ILoadDataKhoanThu iLoadDataKhoanThu) {
        this.iLoadDataKhoanThu=iLoadDataKhoanThu;
        this.context = context;
    }

    public void openDialogThemKhoanThu() {
        Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_khoan_thu);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);
        dialog.setCancelable(true);

        findIdsDialog(dialog);
        dialog.show();

    }

    private void findIdsDialog(Dialog dialog) {
        edt_tenKhoanThu_dialog = dialog.findViewById(R.id.edt_name_khoanthu_dialog);
        edt_tienKhoanThu_dialog = dialog.findViewById(R.id.edt_money_khoanThu_dialog);
        edt_thoiGian_KhoanTHu_dialog = dialog.findViewById(R.id.edt_time_khoanthu_dialog);
        btn_them = dialog.findViewById(R.id.btn_themKhoanthu_Dialog);

        edt_thoiGian_KhoanTHu_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                DatePickerDialog datePicker = new DatePickerDialog(context, datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                //Create a cancel button and set the title of the dialog.
                datePicker.setCancelable(false);
                datePicker.setTitle("Chọn Thời Gian");
                datePicker.show();
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKhoanThu(dialog);
            }
        });

    }

    private void addKhoanThu(Dialog dialog) {


            String tenkhoanthu = edt_tenKhoanThu_dialog.getText().toString().trim();
            int moneykhoanthu = Integer.parseInt(edt_tienKhoanThu_dialog.getText().toString().trim());
            String time = edt_thoiGian_KhoanTHu_dialog.getText().toString().trim();
            if (tenkhoanthu.equals("") && moneykhoanthu ==0&& time.equals("")) {
                Toast.makeText(context, "vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            } else {
                khoanThu = new KhoanThu(tenkhoanthu, moneykhoanthu, time);
                DatabaseQuanLiThuChi.getInstance(context).KhoanThuDAO().insertKhoanThu(khoanThu);
                Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                iLoadDataKhoanThu.ICallbackLoadData();
            }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            edt_thoiGian_KhoanTHu_dialog.setText(month1 + "/" + day1 + "/" + year1);
        }
    };



}
