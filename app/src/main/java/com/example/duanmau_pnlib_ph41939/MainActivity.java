package com.example.duanmau_pnlib_ph41939;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.duanmau_pnlib_ph41939.R;
import com.example.duanmau_pnlib_ph41939.fragment.LoaiSachFragment;
import com.example.duanmau_pnlib_ph41939.fragment.PhieuMuonFragment;
import com.example.duanmau_pnlib_ph41939.fragment.SachFragment;
import com.example.duanmau_pnlib_ph41939.fragment.ThanhVienFragment;
import com.example.duanmau_pnlib_ph41939.fragment.ThongKeDoanhThuFragment;
import com.example.duanmau_pnlib_ph41939.fragment.ThongKeTop10Fragment;
import com.example.duanmau_pnlib_ph41939.fragment.ThuThuFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_QLPM){
                    PhieuMuonFragment frgPM = new PhieuMuonFragment();
                    relaceFrg(frgPM);
                    toolbar.setTitle("Quản lý phiếu mượn");
                }else if(item.getItemId() == R.id.nav_QLLS){
                    LoaiSachFragment frgLS =  new LoaiSachFragment();
                    relaceFrg(frgLS);
                    toolbar.setTitle("Quản lý loại sách");
                }else if(item.getItemId() == R.id.nav_QLS){
                    SachFragment frgS = new SachFragment();
                    relaceFrg(frgS);
                    toolbar.setTitle("Quản lý sách");
                }else if(item.getItemId() == R.id.nav_QLTV){
                    ThanhVienFragment frgTV = new ThanhVienFragment();
                    relaceFrg(frgTV);
                    toolbar.setTitle("Quản lý thành viên");
                }else if(item.getItemId() == R.id.nav_ThongKeTop){
                    ThongKeTop10Fragment frgt = new ThongKeTop10Fragment();
                    relaceFrg(frgt);
                    toolbar.setTitle("Top 10 sách mượn nhiều nhất");
                }else if(item.getItemId() == R.id.nav_ThongKeDT){
                    ThongKeDoanhThuFragment frgDT = new ThongKeDoanhThuFragment();
                    relaceFrg(frgDT);
                    toolbar.setTitle("Doanh thu");
                }else if(item.getItemId() == R.id.nav_TND){
                    ThuThuFragment frgTND = new ThuThuFragment();
                    relaceFrg(frgTND);
                    toolbar.setTitle("Thêm người dùng");
                }else if(item.getItemId() == R.id.nav_DoiMatKhau){
                    dialogDoiMatKhau();
                }else if(item.getItemId() == R.id.nav_DangXuat){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chăn muướn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(MainActivity.this, LoginScreen.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("Hủy",null);
                    builder.create().show();
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }
    public void dialogDoiMatKhau (){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_doi_mat_khau, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        //ánh xạ
        EditText txtMatKhauCu = view.findViewById(R.id.txtMatKhauCu);
        EditText txtMatKhauMoi = view.findViewById(R.id.txtMatKhauMoi);
        EditText txtMatKhauMoixn = view.findViewById(R.id.txtMatKhauMoixn);
        Button btnCancelud = view.findViewById(R.id.btnCancelud);
        Button btnSaveud = view.findViewById(R.id.btnSaveud);
    }
    public void relaceFrg(Fragment frg){
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }
}