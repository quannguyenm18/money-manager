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
import com.example.moneymanager.interfaces.IClickItemKhoanChi;
import com.example.moneymanager.model.KhoanChi;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.KhoanChiViewHolder> {
    ArrayList<KhoanChi> mListkhoanChis;
    Context context;
    IClickItemKhoanChi iClickItemKhoanChi;

    public KhoanChiAdapter(Context context, IClickItemKhoanChi iClickItemKhoanChi) {
        this.context = context;
        this.iClickItemKhoanChi = iClickItemKhoanChi;
    }

    public void setData(ArrayList<KhoanChi> mListkhoanChis){
        this.mListkhoanChis=mListkhoanChis;
    }


    @NonNull
    @Override
    public KhoanChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_khoanchi,parent,false);
        return new KhoanChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiViewHolder holder, int position) {
        KhoanChi khoanChi=mListkhoanChis.get(position);
        holder.nameKC.setText(khoanChi.getTenkhoanchi());
        holder.money.setText(FormatCost(khoanChi.getMoney()));
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemKhoanChi.deleteKhoanChi(khoanChi);

            }
        });
        holder.btn_chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemKhoanChi.detailKhoanchi(khoanChi);

            }
        });

    }


    @Override
    public int getItemCount() {

        if (mListkhoanChis==null){
            return 0;
        }
        return mListkhoanChis.size();
    }

    public  class KhoanChiViewHolder extends RecyclerView.ViewHolder {

        private TextView nameKC,money;
        private Button btn_chitiet,btn_delete;

        public KhoanChiViewHolder(@NonNull View itemView) {
            super(itemView);
            nameKC=itemView.findViewById(R.id.name_khoanchi);
            money=itemView.findViewById(R.id.money_khoanchi);
            btn_chitiet=itemView.findViewById(R.id.btn_chitietKC);
            btn_delete=itemView.findViewById(R.id.delete_khoanChi);

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
