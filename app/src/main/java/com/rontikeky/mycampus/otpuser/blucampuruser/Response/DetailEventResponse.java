package com.rontikeky.mycampus.otpuser.blucampuruser.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anggit on 15/12/2017.
 */

public class DetailEventResponse {


    @SerializedName("acara")
    public Acara acara;
    @SerializedName("peserta")
    public List<Peserta> peserta;
    @SerializedName("user")
    public List<User> user;

    public static class Acara {
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
    }

    public static class Peserta {
        @SerializedName("id_user")
        public String idUser;
        @SerializedName("status")
        public String status;

        public Peserta(String idUser) {
            this.idUser = idUser;
        }

        public String getIdUser() {
            return idUser;
        }

        public String getStatus() {
            return status;
        }
    }

    public static class StatusHadir {
        @SerializedName("status")
        public String status;
    }

    public static class User {
        @SerializedName("id")
        public int id;
        @SerializedName("username")
        public String username;
        @SerializedName("name")
        public String name;
        @SerializedName("email")
        public String email;
        @SerializedName("tanggal_lahir")
        public String tanggalLahir;
        @SerializedName("alamat")
        public String alamat;
        @SerializedName("telp")
        public String telp;
        @SerializedName("is_permission")
        public String isPermission;
        @SerializedName("created_at")
        public String createdAt;
        @SerializedName("updated_at")
        public String updatedAt;
        @SerializedName("status_hadir")
        public StatusHadir statusHadir;

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getTelp() {
            return telp;
        }

        public String getIsPermission() {
            return isPermission;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public StatusHadir getStatusHadir() {
            return statusHadir;
        }
    }

    public Acara getAcara() {
        return acara;
    }

    public List<Peserta> getPeserta() {
        return peserta;
    }

    public List<User> getUser() {
        return user;
    }
}
