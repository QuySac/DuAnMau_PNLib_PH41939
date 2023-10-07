package com.example.duanmau_pnlib_ph41939;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.duanmau_pnlib_ph41939.dao.ThuThuDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    TextInputEditText edTaiKhoan, edMatKhau;
    TextInputLayout inTaiKhoan, inMatKhau;
    Button btnDangNhap, btnHuy;
    CheckBox chkLuu;
    ThuThuDao thuThuDao = new ThuThuDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);
        inTaiKhoan = findViewById(R.id.inTaiKhoan);
        inMatKhau = findViewById(R.id.inMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnHuy = findViewById(R.id.btnHuy);
        chkLuu = findViewById(R.id.chkLuuTk);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edTaiKhoan.setText(pref.getString("USERNAME", ""));
        edMatKhau.setText(pref.getString("PASSWORD", ""));
        chkLuu.setChecked(pref.getBoolean("REMEMBER", false));

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTaiKhoan.setText("");
                edMatKhau.setText("");
            }
        });
    }

    public void checkLogin() {
        String taiKhoan = edTaiKhoan.getText().toString();
        String matKhau = edMatKhau.getText().toString();
        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            if (taiKhoan.equals("")) {
                inTaiKhoan.setError("Không được để trống tên đăng nhập");
            } else {
                inTaiKhoan.setError(null);
            }
            if (matKhau.equals("")) {
                inMatKhau.setError("Không được để trống mật khẩu");
            } else {
                inMatKhau.setError(null);
            }
        } else {
            if (thuThuDao.checkLogin(taiKhoan, matKhau)) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(taiKhoan, matKhau, chkLuu.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("TAIKHOAN", taiKhoan);
                startActivity(i);
                finish();
            } else {
                inTaiKhoan.setError("Tên đăng nhập không đúng");
                inMatKhau.setError("Mật khẩu không đúng");
            }
        }
    }
    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            edit.clear();
        }else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }
}