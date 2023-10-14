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
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duanmau_pnlib_ph41939.dao.ThuThuDao;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
        TextInputLayout inMkCu = view.findViewById(R.id.inMKHienTai);
        TextInputLayout inMkMoi = view.findViewById(R.id.inMKMoi);
        TextInputLayout inNhapLaiMK = view.findViewById(R.id.inNhapLaiMK);
        TextInputEditText edMkCu = view.findViewById(R.id.edMKHienTai);
        TextInputEditText edMkMoi = view.findViewById(R.id.edMKMoi);
        TextInputEditText edNhapLaiMK = view.findViewById(R.id.edNhapLaiMK);
        Button btnLuu = view.findViewById(R.id.btnLuuMK);
        Button btnHuy = view.findViewById(R.id.btnHuyLuu);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edMkCu.setText("");
                edMkMoi.setText("");
                edNhapLaiMK.setText("");
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = edMkCu.getText().toString();
                String newPass = edMkMoi.getText().toString();
                String repass = edNhapLaiMK.getText().toString();
                if(newPass.equals(repass)){
                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("USER_FILE",MainActivity.this.MODE_PRIVATE);
                    String matt = sharedPreferences.getString("USERNAME","");
                    String mk = sharedPreferences.getString("PASSWORD","");
                    //cập nhật
                    ThuThuDao thuThuDao =  new ThuThuDao(MainActivity.this);
                    boolean check = thuThuDao.capNhatMatKhau(matt,oldPass,newPass);
                    if(oldPass.equals(mk)){
                        if(check){
                            Toast.makeText(MainActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        inMkCu.setError("Mật khẩu hiện tại không đúng");
                    }
                }else{
                    inMkMoi.setError("Mật Khẩu Không Khớp");
                    inNhapLaiMK.setError("Mật Khẩu Không Khớp");
                }
            }
        });
    }
    public void relaceFrg(Fragment frg){
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }
}