package com.rontikeky.mycampus.otpuser.blucampuruser.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 16/12/2017.
 */

public class MyEventDetailReponse {

    @SerializedName("id")
    public int id;
    @SerializedName("id_user")
    public String idUser;
    @SerializedName("judul_acara")
    public String judulAcara;
    @SerializedName("isi_acara")
    public String isiAcara;
    @SerializedName("tempat_acara")
    public String tempatAcara;
    @SerializedName("contact_person_acara")
    public String contactPersonAcara;
    @SerializedName("jumlah_peserta")
    public String jumlahPeserta;
    @SerializedName("tanggal_acara")
    public String tanggalAcara;
    @SerializedName("jam_acara")
    public String jamAcara;
    @SerializedName("foto_acara")
    public String fotoAcara;
    @SerializedName("status_acara")
    public String statusAcara;
    @SerializedName("kode")
    public Kode kode;

    public static class Kode {
        @SerializedName("kode")
        public String kode;

        public String getKode() {
            return kode;
        }
    }


    public int getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getJudulAcara() {
        return judulAcara;
    }

    public String getIsiAcara() {
        return isiAcara;
    }

    public String getTempatAcara() {
        return tempatAcara;
    }

    public String getContactPersonAcara() {
        return contactPersonAcara;
    }

    public String getJumlahPeserta() {
        return jumlahPeserta;
    }

    public String getTanggalAcara() {
        return tanggalAcara;
    }

    public String getJamAcara() {
        return jamAcara;
    }

    public String getFotoAcara() {
        return fotoAcara;
    }

    public String getStatusAcara() {
        return statusAcara;
    }

    public Kode getKode() {
        return kode;
    }
}
