package com.example.moneymanager.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.Adapter.KhoanThuAdapter;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.dialog.DiaLogThemKhoanThu;
import com.example.moneymanager.interfaces.IClickItemKhoanThu;
import com.example.moneymanager.interfaces.ILoadDataKhoanThu;
import com.example.moneymanager.model.KhoanThu;
import com.example.moneymanager.ui.DetailKhoanThuActivity;

import java.util.ArrayList;

public class KhoanThuFragment extends Fragment   {
    ImageButton btn_themkhoanthu;
    RecyclerView recyclerView;
    KhoanThuAdapter khoanThuAdapter;
    DiaLogThemKhoanThu diaLogThemKhoanThu;
    ArrayList<KhoanThu> khoanThuArrayList;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
        findIDs(view);
        return view;
    }

    private void findIDs(View view) {
        diaLogThemKhoanThu = new DiaLogThemKhoanThu(getContext(), new ILoadDataKhoanThu() {
            @Override
            public void ICallbackLoadData() {
                loadData();
            }
        });





        btn_themkhoanthu = view.findViewById(R.id.btn_themkhoanthu);
        recyclerView = view.findViewById(R.id.my_Recycler_KhoanThu);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        loadData();

        btn_themkhoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogThemKhoanThu.openDialogThemKhoanThu();
            }
        });


    }

    public void loadData() {
        khoanThuAdapter = new KhoanThuAdapter(getContext(), new IClickItemKhoanThu() {
            @Override
            public void onClickDeleteItem(KhoanThu khoanThu) {
                deleteKhoanThu(khoanThu);
            }

            @Override
            public void onClickDetailKhoanThu(KhoanThu khoanThu) {
                showDetailKhoanThu(khoanThu);

            }

        });
        khoanThuArrayList = new ArrayList<>();
        khoanThuArrayList = (ArrayList<KhoanThu>) DatabaseQuanLiThuChi.getInstance(getContext()).KhoanThuDAO().getListKhoaHoc();
        khoanThuAdapter.setData(khoanThuArrayList);
        recyclerView.setAdapter(khoanThuAdapter);
    }

    private void showDetailKhoanThu(KhoanThu khoanThu) {
        Intent intent = new Intent(getContext(), DetailKhoanThuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", khoanThu);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void deleteKhoanThu(KhoanThu khoanThu) {
        new AlertDialog.Builder(getContext())
                .setTitle("Xóa Khoản Thu Này ?")
                .setMessage("Bạn Chắc Chắn chứ  ?")
                .setPositiveButton("Đồng Ý ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseQuanLiThuChi.getInstance(getContext()).KhoanThuDAO().deleteKhoanThu(khoanThu);
                        Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();

                        loadData();

                    }
                }).setNegativeButton("Không", null).show();


    }



}
