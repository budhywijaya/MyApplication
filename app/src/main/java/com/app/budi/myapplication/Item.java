package com.app.budi.myapplication;

/**
 * Created by Valued User on 5/31/2017.
 */
public class Item {
    private int id;
    private String nama;
    private String lokasi;

    private byte[] image;

    public Item(String nama, String lokasi, byte[] image,int id) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
