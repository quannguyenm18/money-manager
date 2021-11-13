package com.example.moneymanager.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanager.model.KhoanThu;
import com.example.moneymanager.model.User;

import java.util.List;
@Dao
public interface KhoanThuDao {

    @Insert
    void insertKhoanThu(KhoanThu khoanThu);

    @Query("SELECT*FROM khoanthu")
    List<KhoanThu> getListKhoaHoc();
    @Delete
    void deleteKhoanThu(KhoanThu khoanThu);
    @Update
    void updateKhoanThu(KhoanThu khoanThu);

}
