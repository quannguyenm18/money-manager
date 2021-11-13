package com.example.moneymanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moneymanager.DAO.KhoanChiDAO;
import com.example.moneymanager.DAO.KhoanThuDao;
import com.example.moneymanager.DAO.UserDAO;
import com.example.moneymanager.model.KhoanChi;
import com.example.moneymanager.model.KhoanThu;
import com.example.moneymanager.model.User;

@Database(entities = {User.class,KhoanThu.class, KhoanChi.class},version = 1)
public abstract class DatabaseQuanLiThuChi extends  RoomDatabase{

        private  static  final  String DBName="ThuChiDB.db";
        private  static DatabaseQuanLiThuChi instance;

        public static synchronized DatabaseQuanLiThuChi getInstance(Context context) {

            if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        DatabaseQuanLiThuChi.class, DBName).allowMainThreadQueries().build();
            }
            return instance;
        }

     public abstract UserDAO UserDAO();
     public abstract KhoanThuDao KhoanThuDAO();
     public abstract KhoanChiDAO KhoanChiDAO();
}
