package com.example.datamahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {
    EditText edNama,edNPM;
    Button btn_simpan;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        edNama = findViewById(R.id.edNama);
        edNPM = findViewById(R.id.edNPM);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama=edNama.getText().toString();
                String getNPM=edNPM.getText().toString();
                if (getNama.isEmpty()){
                    edNama.setError("Inputkan Nama");
                }else if (getNPM.isEmpty()){
                    edNPM.setError("inputkan NPM");
                }else{
                    database.child("Mahasiswa").push().setValue(new ModelMahasiswa(getNama,getNPM)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TambahActivity.this,"Data Berhasil di simpan",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TambahActivity.this,MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahActivity.this,"Data gagal di simpan",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}