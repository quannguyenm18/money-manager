package com.example.moneymanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseQuanLiThuChi;
import com.example.moneymanager.model.KhoanChi;
import com.example.moneymanager.model.KhoanThu;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView tv_sodu,tv_khoanchi,tv_khoanthu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_home,container,false);
       findIDs(view);
        return view;
    }

    private void findIDs(View view) {
        tv_sodu=view.findViewById(R.id.tv_sodu);
        tv_khoanchi=view.findViewById(R.id.tv_khoanchi);
        tv_khoanthu=view.findViewById(R.id.tv_khoanthu);
        setData();

    }

    private void setData() {
        ArrayList<KhoanChi> khoanChiList= (ArrayList<KhoanChi>) DatabaseQuanLiThuChi.getInstance(getContext()).KhoanChiDAO().getListKhoanChi();
        ArrayList<KhoanThu> khoanthulist= (ArrayList<KhoanThu>) DatabaseQuanLiThuChi.getInstance(getContext()).KhoanThuDAO().getListKhoaHoc();
        int tongthu=0;
        for(int i=0;i<khoanthulist.size();i++){
          tongthu=tongthu+khoanthulist.get(i).getTienKhoanThu();
        }
        tv_khoanthu.setText(FormatCost(tongthu));

        int tongchi=0;
        for(int i=0;i<khoanChiList.size();i++){
            tongchi=tongchi+khoanChiList.get(i).getMoney();
        }
        tv_khoanchi.setText(FormatCost(tongchi));

        int sodu= tongthu-tongchi;
        tv_sodu.setText(FormatCost(sodu));


    }
    public String FormatCost(int cost){
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            return decimalFormat.format(Integer.parseInt(cost+""));
        }catch (Exception e) {
            return cost + "";
        }
    }

}
