package android.exemple.com.moncabinetmedical.service;

import android.exemple.com.moncabinetmedical.model.Medecin;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lenovo on 25/10/2015.
 */
public interface IApiMethods {
    @GET("/login/authentif.php")
    public void getMessage(@Query("username") String message, Callback<List<Medecin>> response);
}
