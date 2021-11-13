package com.example.moneymanager.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.fragment.HomeFragment;
import com.example.moneymanager.fragment.KhoanChiFragment;
import com.example.moneymanager.fragment.KhoanThuFragment;
import com.example.moneymanager.fragment.ThongKeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationBarView.OnItemSelectedListener {
    private Button btn_logout;
    private NavigationView myNav;
    private DrawerLayout myDrawer;
    private Toolbar toolbar;
    private ImageView img_clickopendrawer;
    private Animation animation;
    private BottomNavigationView myBottomMenu;
    private TextView tvTitle;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_KHOANTHU = 1;
    private static final int FRAGMENT_KHOANCHI = 2;
    private static final int FRAGMENT_THONGKE=3;
    private int mCurrent = FRAGMENT_HOME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findIDs();
    }

    private void findIDs() {
        tvTitle= findViewById(R.id.tv_titleToolbar);
        btn_logout = findViewById(R.id.btnLogout);
        btn_logout.setOnClickListener(this);
        setNavigation();
        setToolbar();

        replaceFragment(new HomeFragment());


        setTitleToolbar();

        setMenuBottom();

    }

    public void setTitleToolbar() {
        switch (mCurrent){
            case FRAGMENT_HOME:
                tvTitle.setText("Xin Chào !!");
                break;
            case FRAGMENT_KHOANCHI:
                tvTitle.setText("Danh Sách Khoản Chi Tiêu");
                break;
            case FRAGMENT_KHOANTHU:
                tvTitle.setText("Danh Sách Khoản Thu");
                break;
            case FRAGMENT_THONGKE:
                tvTitle.setText("Thông Kê");
                break;
        }
    }
    public   void openHomeFragment(){
        if (mCurrent!=FRAGMENT_HOME){
            replaceFragment(new HomeFragment());
            mCurrent=FRAGMENT_HOME;
        }
    }
    public   void openKhoanChiFragment(){
        if (mCurrent!=FRAGMENT_KHOANCHI){
            replaceFragment(new KhoanChiFragment());
            mCurrent=FRAGMENT_KHOANCHI;
        }


    }
    public   void openKhoanThuFragment(){
        if (mCurrent!=FRAGMENT_KHOANTHU){
            replaceFragment(new KhoanThuFragment());
            mCurrent=FRAGMENT_KHOANTHU;
        }


    }
    public   void openThongKeFragment(){
        if (mCurrent!=FRAGMENT_THONGKE){
            replaceFragment(new ThongKeFragment());
            mCurrent=FRAGMENT_THONGKE;
        }

    }

    private void setMenuBottom() {
        myBottomMenu = findViewById(R.id.myBottomNavi);
        myBottomMenu.setOnItemSelectedListener(this);
    }

    private void setNavigation() {
        myNav = findViewById(R.id.my_navi);
        myDrawer = findViewById(R.id.myDrawer);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_home);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_rotate);
        img_clickopendrawer = findViewById(R.id.img_clickopendrawer);
        img_clickopendrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDrawer.openDrawer(GravityCompat.START);
                img_clickopendrawer.setAnimation(animation);
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view == btn_logout) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {

        if (myDrawer.isDrawerOpen(GravityCompat.START)) {
            myDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.it_bottom_home:
                openHomeFragment();
                break;
            case R.id.it_bottom_khoan_Chi:
                openKhoanChiFragment();
                break;
            case R.id.it_bottom_khoan_thu:
                openKhoanThuFragment();
                break;
            case R.id.it_bottom_thong_ke:
                openThongKeFragment();
                break;
        }
        setTitleToolbar();
        return true;
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.myFrame, fragment);
        fragmentTransaction.commit();
    }

}


