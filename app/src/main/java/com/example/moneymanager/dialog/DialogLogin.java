package com.example.moneymanager.dialog;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.model.User;
import com.example.moneymanager.ui.ForgetPasswordActivity;
import com.example.moneymanager.ui.HomeActivity;

import java.util.ArrayList;

public class DialogLogin {
    public Context context;
    EditText edt_username;
    EditText edt_password;
    TextView tv_quenmk;


    public DialogLogin(Context context) {
        this.context = context;
    }


    public  void openDialogLogin(Context context){
        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_dang_nhap);

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
        tv_quenmk= dialog.findViewById(R.id.tv_quenmk);
        edt_password=dialog.findViewById(R.id.edt_password_dangnhap_dialog);
        edt_username= dialog.findViewById(R.id.edt_user_dangnhap_dialog);




        Button btn_dangnhap_dialog=dialog.findViewById(R.id.btn_Dangnhap_Dialog);
        btn_dangnhap_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAcount(dialog);
            }
        });

        tv_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,ForgetPasswordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    private void loginAcount(Dialog dialog) {
        String username=edt_username.getText().toString().trim();
        String password=edt_password.getText().toString().trim();
        User user= new User(username,password);
        if (isCheckUserName(user) && isCheckPassWord(user)){
            Intent intent= new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, "Đăng Nhập Thành Công ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Tài Khoản Hoặc Mật Khẩu Không Đúng ", Toast.LENGTH_SHORT).show();

        }
    }

    public boolean isCheckUserName(User user) {
        ArrayList<User> users = (ArrayList<User>) DatabaseQuanLiThuChi.getInstance(context).UserDAO().checkUser(user.getUserName());
        return users != null && !users.isEmpty();
    }
    public boolean isCheckPassWord(User user) {
        ArrayList<User> users = (ArrayList<User>) DatabaseQuanLiThuChi.getInstance(context).UserDAO().checkPass(user.getPassWord());
        return users != null && !users.isEmpty();
    }



}
