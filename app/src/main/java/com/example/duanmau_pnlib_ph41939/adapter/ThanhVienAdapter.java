package com.example.duanmau_pnlib_ph41939.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_pnlib_ph41939.R;
import com.example.duanmau_pnlib_ph41939.dao.ThanhVienDao;
import com.example.duanmau_pnlib_ph41939.model.ThanhVien;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ThanhVien> list;
    ThanhVienDao dao;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        dao = new ThanhVienDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanh_vien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lblMaTV.setText(String.valueOf(list.get(position).getMaTV()));
        holder.lblTenTV.setText(list.get(position).getHoTen());
        holder.lblNamSinh.setText(list.get(position).getNamSinh());
        ThanhVien tv = list.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogUpdateTV(tv);
                return true;
            }
        });

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn chắc chắn muốn xóa thành viên này chứ");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = dao.xoaTV(list.get(holder.getAdapterPosition()).getMaTV());
                        switch (check){
                            case 1:
                                loadData();
                                Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa thành viên thất bại", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Thành viên đang tồn tại phiếu mượn, hiện tại không thể xóa", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Hủy",null);
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblMaTV, lblTenTV, lblNamSinh;
        ImageButton xoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMaTV = itemView.findViewById(R.id.tvMaTV);
            lblTenTV = itemView.findViewById(R.id.tvTenThanhVien);
            lblNamSinh = itemView.findViewById(R.id.tvNamSinh);
            xoa = itemView.findViewById(R.id.imgXoa);
        }
    }

    @SuppressLint("MissingInflatedId")
    private void dialogUpdateTV(ThanhVien tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanh_vien,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText edTenTV = view.findViewById(R.id.edTenTV);
        TextInputEditText edNamSinh = view.findViewById(R.id.edNamSinh);
        TextInputLayout inTenTV = view.findViewById(R.id.inTenTV);
        TextInputLayout inNamSinh = view.findViewById(R.id.inNamSinh);
        Button btnUpdate = view.findViewById(R.id.btnThemTV);
        Button btnCancel = view.findViewById(R.id.btnHuyThem);

        edTenTV.setText(tv.getHoTen());
        edNamSinh.setText(tv.getNamSinh());

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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edTenTV.getText().toString();
                String namsinh = edNamSinh.getText().toString();
                int id = tv.getMaTV();

                boolean check = dao.capNhatTV(id,hoten,namsinh);
                if(hoten.isEmpty() || namsinh.isEmpty()){
                    if(hoten.equals("")){
                        inTenTV.setError("Vui lòng không để trống Họ Tên");
                    }else{
                        inTenTV.setError(null);
                    }

                    if(namsinh.equals("")){
                        inNamSinh.setError("Vui lòng không để trống năm sinh");
                    }else{
                        inNamSinh.setError(null);
                    }
                }else{
                    if(check){
                        loadData();
                        Toast.makeText(context, "Cập nhật nhân viên thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Cập nhật nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenTV.setText("");
                edNamSinh.setText("");
            }
        });
    }

    private void loadData(){
        list.clear();
        list = dao.layDanhSach();
        notifyDataSetChanged();
    }
}
