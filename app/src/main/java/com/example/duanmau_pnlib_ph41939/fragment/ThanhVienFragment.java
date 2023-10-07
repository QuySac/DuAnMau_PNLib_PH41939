package com.example.duanmau_pnlib_ph41939.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duanmau_pnlib_ph41939.R;
import com.example.duanmau_pnlib_ph41939.adapter.ThanhVienAdapter;
import com.example.duanmau_pnlib_ph41939.dao.ThanhVienDao;
import com.example.duanmau_pnlib_ph41939.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {

    FloatingActionButton fltAll;
    RecyclerView rcv;
    ThanhVienDao dao;
    ArrayList<ThanhVien> list;

    public ThanhVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        rcv = view.findViewById(R.id.rcv_TV);
        fltAll = view.findViewById(R.id.add_TV);
        dao = new ThanhVienDao(getContext());
        loadData();

        fltAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddTV();
            }
        });
        return view;
    }
    private void loadData(){
        list = dao.layDanhSach();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(),list);
        rcv.setAdapter(adapter);
    }

    @SuppressLint("MissingInflatedId")
    private void dialogAddTV(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanh_vien,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText edTenTV = view.findViewById(R.id.edTenTV);
        TextInputEditText edNamSinh = view.findViewById(R.id.edNamSinh);
        TextInputLayout inTenTV = view.findViewById(R.id.inTenTV);
        TextInputLayout inNamSinh = view.findViewById(R.id.inNamSinh);
        Button btnThem = view.findViewById(R.id.btnThemTV);
        Button btnHuy = view.findViewById(R.id.btnHuyThem);

        edTenTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    inTenTV.setError("Vui lòng nhập tên thành viên");
                }else{
                    inTenTV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edNamSinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    inNamSinh.setError("Vui lòng không để trống năm sinh");
                }else{
                    inNamSinh.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = edTenTV.getText().toString();
                String namSinh = edNamSinh.getText().toString();
                boolean check = dao.themTV(hoTen,namSinh);

                if(hoTen.isEmpty() || namSinh.isEmpty()){
                    if(hoTen.equals("")){
                        inTenTV.setError("Vui lòng nhập đầy đủ tên thành viên");
                    }else{
                        inTenTV.setError(null);
                    }

                    if(namSinh.equals("")){
                        inNamSinh.setError("Vui lòng nhập đầy đủ năm sinh thành viên");
                    }else{
                        inNamSinh.setError(null);
                    }
                }else{
                    if(check){
                        loadData();
                        Toast.makeText(getContext(), "Thêm Thành Viên Thành Công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm Thành Viên Không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenTV.setText("");
                edNamSinh.setText("");
            }
        });
    }
}