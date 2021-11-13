package com.example.moneymanager.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.interfaces.ICallLoadDataKhoanChi;
import com.example.moneymanager.model.KhoanChi;
import com.example.moneymanager.model.KhoanThu;
import com.example.moneymanager.ui.ForgetPasswordActivity;
import com.example.moneymanager.ui.HomeActivity;

import java.util.Calendar;

public class DialogThemKhoanChi {
    private Context context;
    private EditText edt_name,edt_money,edt_time;
    private  Button btn_themKhoanchi;

    private ICallLoadDataKhoanChi iCallLoadDataKhoanChi;

    public DialogThemKhoanChi(Context context, ICallLoadDataKhoanChi iCallLoadDataKhoanChi) {
        this.context = context;
        this.iCallLoadDataKhoanChi = iCallLoadDataKhoanChi;
    }

    public  void openDialogThemKhoanChi(){
         Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_khoan_chi);

        Window window= dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams=window.getAttributes();
        layoutParams.gravity= Gravity.BOTTOM;
        window.setAttributes(layoutParams);
        dialog.setCancelable(true);

        findIdsDialog(dialog);
        dialog.show();

    }

    private void findIdsDialog(Dialog dialog) {
        edt_time=dialog.findViewById(R.id.edt_time_khoanchi_dialog);
        edt_money=dialog.findViewById(R.id.edt_money_khoanchi_dialog);
        edt_name= dialog.findViewById(R.id.edt_name_khoanchi_dialog);

        btn_themKhoanchi=dialog.findViewById(R.id.btn_themKhoanchi_Dialog);

        edt_time.setOnClickListener(new View.OnClickListener() {
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
        btn_themKhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKhoanChi(dialog);
            }
        });



}

    private void addKhoanChi(Dialog dialog) {


        String tenkhoanchi = edt_name.getText().toString().trim();
        int moneykhoanchi = Integer.parseInt(edt_money.getText().toString().trim());
        String time = edt_time.getText().toString().trim();
        if (tenkhoanchi.equals("") && moneykhoanchi ==0&& time.equals("")) {
            Toast.makeText(context, "vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        } else {
            KhoanChi khoanChi = new KhoanChi(tenkhoanchi, time,moneykhoanchi);
            DatabaseQuanLiThuChi.getInstance(context).KhoanChiDAO().insertKhoanChi(khoanChi);
            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            iCallLoadDataKhoanChi.loadDataFragment();
        }







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
