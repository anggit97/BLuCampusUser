package com.rontikeky.mycampus.otpuser.blucampuruser.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 15/12/2017.
 */

public class DaftarEventResponse {

    @SerializedName("status")
    public String status;

    public String getStatus() {
        return status;
    }
}
