package com.example.moneymanager.dialog;

import android.app.Dialog;
import android.content.Context;
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

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.fragment.KhoanThuFragment;
import com.example.moneymanager.model.User;

import java.util.ArrayList;

public class DialogRegister {
    Context context;
    EditText edt_username;
    EditText edt_passWord;
    EditText edt_rePassword;


    public DialogRegister(Context context) {
        this.context = context;
    }

    public void openDialogRegister(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_dang_ky);

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

    public void findIdsDialog(Dialog dialog) {
        edt_username = dialog.findViewById(R.id.edt_user_dangky_dialog);
        edt_passWord = dialog.findViewById(R.id.edt_password_dangky_dialog);
        edt_rePassword = dialog.findViewById(R.id.edt_reppassword_dangky_dialog);


        Button btn_dangki_dialog = dialog.findViewById(R.id.btn_Dangky_Dialog);
        btn_dangki_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerAccount(dialog);
            }
        });


    }

    public void registerAccount(Dialog dialog) {
        String userName = edt_username.getText().toString().trim();
        String password = edt_passWord.getText().toString().trim();
        String rePassword = edt_rePassword.getText().toString().trim();
        if (!password.equals(rePassword)) {
            Toast.makeText(context, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_LONG).show();
            return;
        } else {
            User user = new User(userName, password);
            if (isCheckUser(user)) {
                Toast.makeText(context, "Tên Tài Khoản Đã Tồn Tại ", Toast.LENGTH_SHORT).show();
                return;
            }
            DatabaseQuanLiThuChi.getInstance(context).UserDAO().insertUser(user);
            Toast.makeText(context, "Đăng Kí Thành Công", Toast.LENGTH_LONG).show();
            dialog.dismiss();

        }
    }
    public boolean isCheckUser(User user) {
        ArrayList<User> users = (ArrayList<User>) DatabaseQuanLiThuChi.getInstance(context).UserDAO().checkUser(user.getUserName());
        return users != null && !users.isEmpty();
    }

}
