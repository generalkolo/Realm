package com.semanientreprise.realm;

import com.semanientreprise.realm.Model.Profiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by GeneralKolo on 2018/03/17.
 */

public interface APISERVICE {

    //Retrofit For profiles
    @POST("getprofiles.php")
    Call<List<Profiles>> getProfiles(
    );
}