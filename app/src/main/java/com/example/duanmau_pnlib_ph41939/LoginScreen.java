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
    String username,password;
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
            if (thuThuDao.checkLogin(taiKhoan, matKhau) > 0) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberAccount(chkLuu.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("USERNAME", taiKhoan);
                startActivity(i);
                finish();
            } else {
                inTaiKhoan.setError("Tên đăng nhập không đúng");
                inMatKhau.setError("Mật khẩu không đúng");
            }
        }
    }
    private void rememberAccount(Boolean chkRemember) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", edTaiKhoan.getText().toString().trim());
        editor.putString("PASSWORD", edMatKhau.getText().toString().trim());
        editor.putBoolean("CHKREMEMBER", chkRemember);
        editor.apply();
    }
    private void checkSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        username = sharedPreferences.getString("USERNAME", "");
        password = sharedPreferences.getString("PASSWORD","");
        Boolean chkRemember = sharedPreferences.getBoolean("CHKREMEMBER",false);
        chkLuu.setChecked(chkRemember);
        if (chkLuu.isChecked()) {
            edTaiKhoan.setText(username);
            edMatKhau.setText(password);
        }
    }
}