package com.example.datamahasiswa;

public class ModelMahasiswa {

    private String nama;
    private String NPM;
    private String key;

    public ModelMahasiswa(){

    }

    public ModelMahasiswa(String nama, String NPM) {
        this.nama = nama;
        this.NPM = NPM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNPM() {
        return NPM;
    }

    public void setNPM(String NPM) {
        this.NPM = NPM;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
