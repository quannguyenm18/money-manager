package com.example.moneymanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.interfaces.IClickItemKhoanThu;
import com.example.moneymanager.model.KhoanThu;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.ViewHolder> {

    private Context mcontext;
    private ArrayList<KhoanThu> khoanThuArrayList;
    IClickItemKhoanThu iClickItemKhoanThu;

    public KhoanThuAdapter(Context mcontext, IClickItemKhoanThu iClickItemKhoanThu) {
        this.mcontext = mcontext;
        this.iClickItemKhoanThu = iClickItemKhoanThu;

    }

    public void setData(ArrayList<KhoanThu> khoanThuArrayList) {
        this.khoanThuArrayList = khoanThuArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_khoanthu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        KhoanThu khoanThu = khoanThuArrayList.get(position);
        holder.tv_tenKhoanThu.setText(khoanThu.getTenKhoanThu());
        holder.tv_tienKhoanThu.setText(FormatCost(khoanThu.getTienKhoanThu()));
        holder.delete_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemKhoanThu.onClickDeleteItem(khoanThu);

            }
        });
        holder.btn_chitietKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemKhoanThu.onClickDetailKhoanThu(khoanThu);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (khoanThuArrayList == null) {
            return 0;
        }
        return khoanThuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_tenKhoanThu;
        private TextView tv_tienKhoanThu;
        private Button btn_chitietKT;
        private Button delete_khoanthu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenKhoanThu = itemView.findViewById(R.id.tv_name_khoanthu);
            tv_tienKhoanThu = itemView.findViewById(R.id.tv_money_khoanthu);
            btn_chitietKT = itemView.findViewById(R.id.btn_chitietKT);
            delete_khoanthu = itemView.findViewById(R.id.delete_khoanthu);

        }
    }

    public String FormatCost(int cost) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            return decimalFormat.format(Integer.parseInt(cost + ""));
        } catch (Exception e) {
            return cost + "";
        }
    }
}
