package com.example.duanmau_pnlib_ph41939;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duanmau_pnlib_ph41939.dao.ThuThuDao;
import com.example.duanmau_pnlib_ph41939.fragment.DoanhThuFragment;
import com.example.duanmau_pnlib_ph41939.fragment.DoiMatKhauFragment;
import com.example.duanmau_pnlib_ph41939.fragment.LoaiSachFragment;
import com.example.duanmau_pnlib_ph41939.fragment.PhieuMuonFragment;
import com.example.duanmau_pnlib_ph41939.fragment.SachFragment;
import com.example.duanmau_pnlib_ph41939.fragment.TaoTaiKhoanFragment;
import com.example.duanmau_pnlib_ph41939.fragment.ThanhVienFragment;
import com.example.duanmau_pnlib_ph41939.fragment.Top10Fragment;
import com.example.duanmau_pnlib_ph41939.model.ThuThu;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private static final int FRAGMENT_PHIEUMUON = 0;
    private static final int FRAGMENT_LOAISACH = 1;
    private static final int FRAGMENT_SACH = 2;
    private static final int FRAGMENT_THANHVIEN = 3;
    private static final int FRAGMENT_TOP10 = 4;
    private static final int FRAGMENT_DOANHTHU = 5;
    private static final int FRAGMENT_TAOTAIKHOAN = 6;
    private static final int FRAGMENT_DOIMATKHAU = 7;

    private int mCurrentFragment = FRAGMENT_PHIEUMUON;
    Toolbar toolbar;
    DrawerLayout drawerLayout_home;
    NavigationView navigationView_home;
    String[] title = {"Quản lý Phiếu mượn",
            "Quản lý Loại sách",
            "Quản lý Sách",
            "Quản lý Thành viên",
            "Top 10 Sách mượn nhiều nhất",
            "Doanh thu",
            "Tạo tài khoản",
            "Đổi mật khẩu"};
    TextView tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        navigationView_home();
        navigationView_home.setItemTextColor(ColorStateList.valueOf(getColor(R.color.black)));
        navigationView_home.setItemIconTintList(ColorStateList.valueOf(getColor(R.color.black)));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title[0]);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,drawerLayout_home,toolbar,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout_home.addDrawerListener(drawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,new PhieuMuonFragment());
        fragmentTransaction.commit();
        drawerLayout_home.close();

        showInf();
    }
    private void showInf() {
        ThuThuDao thuThuDAO = new ThuThuDao(MainActivity.this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        if (!username.equals("admin")) {
            ThuThu thuThu = thuThuDAO.getID(username);
            tv_username.setText(thuThu.getHoTen());
            navigationView_home.getMenu().findItem(R.id.nav_TND).setVisible(false);
        }
    }

    private void navigationView_home() {
        navigationView_home.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_QLPM) {
                    toolbar.setTitle(title[0]);
                    if (mCurrentFragment != FRAGMENT_PHIEUMUON) {
                        replaceFragment(new PhieuMuonFragment());
                        mCurrentFragment = FRAGMENT_PHIEUMUON;
                    }
                } else if(item.getItemId() == R.id.nav_QLLS) {
                    toolbar.setTitle(title[1]);
                    if (mCurrentFragment != FRAGMENT_LOAISACH) {
                        replaceFragment(new LoaiSachFragment());
                        mCurrentFragment = FRAGMENT_LOAISACH;
                    }
                } else if (item.getItemId() == R.id.nav_QLS) {
                    toolbar.setTitle(title[2]);
                    if (mCurrentFragment != FRAGMENT_SACH) {
                        replaceFragment(new SachFragment());
                        mCurrentFragment = FRAGMENT_SACH;
                    }
                } else if (item.getItemId() == R.id.nav_QLTV) {
                    toolbar.setTitle(title[3]);
                    if (mCurrentFragment != FRAGMENT_THANHVIEN) {
                        replaceFragment(new ThanhVienFragment());
                        mCurrentFragment = FRAGMENT_THANHVIEN;
                    }
                } else if (item.getItemId() == R.id.nav_ThongKeTop) {
                    toolbar.setTitle(title[4]);
                    if (mCurrentFragment != FRAGMENT_TOP10) {
                        replaceFragment(new Top10Fragment());
                        mCurrentFragment = FRAGMENT_TOP10;
                    }
                } else if (item.getItemId() == R.id.nav_ThongKeDT) {
                    toolbar.setTitle(title[5]);
                    if (mCurrentFragment != FRAGMENT_DOANHTHU) {
                        replaceFragment(new DoanhThuFragment());
                        mCurrentFragment = FRAGMENT_DOANHTHU;
                    }
                } else if (item.getItemId() == R.id.nav_TND) {
                    toolbar.setTitle(title[6]);
                    if (mCurrentFragment != FRAGMENT_TAOTAIKHOAN) {
                        replaceFragment(new TaoTaiKhoanFragment());
                        mCurrentFragment = FRAGMENT_TAOTAIKHOAN;
                    }
                } else if (item.getItemId() == R.id.nav_DoiMatKhau) {
                    toolbar.setTitle(title[7]);
                    if (mCurrentFragment != FRAGMENT_DOIMATKHAU) {
                        replaceFragment(new DoiMatKhauFragment());
                        mCurrentFragment = FRAGMENT_DOIMATKHAU;
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Bạn có chắc chắn muốn đăng xuất tài khoản này không ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this,LoginScreen.class));
                            finishAffinity();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
        drawerLayout_home.close();
    }
    private void initUI() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout_home = findViewById(R.id.drawerLayout);
        navigationView_home = findViewById(R.id.navigationView);
        tv_username = navigationView_home.getHeaderView(0).findViewById(R.id.tvHeaderName);
    }
}