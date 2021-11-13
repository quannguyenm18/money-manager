package com.example.moneymanager.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanager.model.KhoanChi;
import com.example.moneymanager.model.KhoanThu;

import java.util.List;
@Dao
public interface KhoanChiDAO {

    @Insert
    void insertKhoanChi(KhoanChi khoanChi);

    @Query("SELECT*FROM khoanchi")
    List<KhoanChi> getListKhoanChi();
    @Delete
    void deleteKhoanChi(KhoanChi khoanChi);
    @Update
    void updateKhoanChi(KhoanChi khoanChi);
}
