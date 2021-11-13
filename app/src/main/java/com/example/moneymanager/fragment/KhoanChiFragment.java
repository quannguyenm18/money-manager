package com.example.moneymanager.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.Adapter.KhoanChiAdapter;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.dialog.DialogThemKhoanChi;
import com.example.moneymanager.interfaces.ICallLoadDataKhoanChi;
import com.example.moneymanager.interfaces.IClickItemKhoanChi;
import com.example.moneymanager.model.KhoanChi;
import com.example.moneymanager.model.KhoanThu;
import com.example.moneymanager.ui.DetailKhoanChiActivity;

import java.util.ArrayList;

public class KhoanChiFragment extends Fragment {
    ImageButton btn_themKhoanChi;
    DialogThemKhoanChi dialogThemKhoanChi;
    private RecyclerView rvkhoanChi;
    private KhoanChiAdapter khoanChiAdapter;
    private ArrayList<KhoanChi>  listKhoanChi;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_khoan_chi,container,false);
        findbyids(view);
        return view;
    }

    private void findbyids(View view) {
        dialogThemKhoanChi = new DialogThemKhoanChi(getContext(), new ICallLoadDataKhoanChi() {
            @Override
            public void loadDataFragment() {
                loadData();
            }
        });
        rvkhoanChi=view.findViewById(R.id.rc_khoanchi);
        khoanChiAdapter=new KhoanChiAdapter(getContext(), new IClickItemKhoanChi() {
            @Override
            public void deleteKhoanChi(KhoanChi khoanChi) {
                deleteKhoanchi(khoanChi);


            }

            @Override
            public void detailKhoanchi(KhoanChi khoanChi) {
                showDetailKhoanChi(khoanChi);



            }
        });
        listKhoanChi= new ArrayList<>();
        loadData();



        btn_themKhoanChi= view.findViewById(R.id.btn_themkhoanchi);
        btn_themKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogThemKhoanChi.openDialogThemKhoanChi();

            }
        });

    }

    private void showDetailKhoanChi(KhoanChi khoanChi) {

        Intent intent= new Intent(getContext(), DetailKhoanChiActivity.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("ABC",khoanChi);
        intent.putExtras(bundle);
        startActivity(intent);



    }

    private void deleteKhoanchi(KhoanChi khoanChi) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Xóa Khoản Chi Này ?")
                    .setMessage("Bạn Chắc Chắn chứ  ?")
                    .setPositiveButton("Đồng Ý ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseQuanLiThuChi.getInstance(getContext()).KhoanChiDAO().deleteKhoanChi(khoanChi);
                            Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();

                            loadData();

                        }
                    }).setNegativeButton("Không", null).show();




    }

    private void loadData() {
        listKhoanChi= (ArrayList<KhoanChi>)
                DatabaseQuanLiThuChi.getInstance(getContext()).KhoanChiDAO().getListKhoanChi();
        khoanChiAdapter.setData(listKhoanChi);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvkhoanChi.setLayoutManager(linearLayoutManager);
        rvkhoanChi.setAdapter(khoanChiAdapter);


    }
}
