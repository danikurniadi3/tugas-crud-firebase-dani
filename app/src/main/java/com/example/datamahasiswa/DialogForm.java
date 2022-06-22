package com.example.datamahasiswa;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DialogForm extends DialogFragment {
    String nama , npm , pilih , key;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public  DialogForm (String nama, String npm ,String key , String pilih){
        this.nama= nama;
        this.npm = npm;
        this.key =key;
        this.pilih = pilih;
    }
    TextView tnama, tnpm;
    Button btn_simpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tambah,container,false);
        tnama = view.findViewById(R.id.edNama);
        tnpm =view.findViewById(R.id.edNPM);
        btn_simpan = view.findViewById(R.id.btn_simpan);

        tnama.setText(nama);
        tnpm.setText(npm);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tnama.getText().toString();
                String anpm = tnpm.getText().toString();
                if (pilih.equals("Ubah")){
                    database.child("Mahasiswa").child(key).setValue(new ModelMahasiswa(nama,anpm)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(view.getContext(), "Berhasil di Update", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Gagal di Update", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
