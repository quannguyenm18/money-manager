package com.example.moneymanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "khoanchi")
public class KhoanChi implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id ;
    String tenkhoanchi;
    String time;
    int money;

    public KhoanChi(String tenkhoanchi, String time, int money) {
        this.tenkhoanchi = tenkhoanchi;
        this.time = time;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhoanchi() {
        return tenkhoanchi;
    }

    public void setTenkhoanchi(String tenkhoanchi) {
        this.tenkhoanchi = tenkhoanchi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
