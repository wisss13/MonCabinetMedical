package android.exemple.com.moncabinetmedical.network;

import android.exemple.com.moncabinetmedical.model.WrapObjToNetwork;

import org.json.JSONArray;

/**
 * Created by lenovo on 08/11/2015.
 */
public interface Transaction {
    WrapObjToNetwork doBefore();

    void doAfter(JSONArray jsonArray);
}
