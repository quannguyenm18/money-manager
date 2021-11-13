package com.example.moneymanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "khoanthu")
public class KhoanThu implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String thoigianKhoanThu;
    String tenKhoanThu;
    int tienKhoanThu;

    public KhoanThu(String tenKhoanThu, int tienKhoanThu, String thoigianKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
        this.tienKhoanThu = tienKhoanThu;
        this.thoigianKhoanThu = thoigianKhoanThu;
    }

    public String getThoigianKhoanThu() {
        return thoigianKhoanThu;
    }

    public void setThoigianKhoanThu(String thoigianKhoanThu) {
        this.thoigianKhoanThu = thoigianKhoanThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public int getTienKhoanThu() {
        return tienKhoanThu;
    }

    public void setTienKhoanThu(int tienKhoanThu) {
        this.tienKhoanThu = tienKhoanThu;
    }
}
