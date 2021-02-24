package com.razinggroups.data.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager minst;
    private static Context mct;
    private static final String SHARD_PERFNAME="myshardperf624";
    private static final String KEY_ID="id";

    private static final String NAME="first_name";
    private static final String Logintype="mobile";
    private static final String KYC_ID="kyc_id";

    private static final String DATAKYC_ID="datakyc_id";



    private SharedPrefManager(Context context){
        mct=context;
    }
    public static synchronized SharedPrefManager getInstans(Context context){
        if (minst==null){
            minst=new SharedPrefManager(context);
        }
        return minst;
    }



    public boolean userLogin(String id, String name, String phone){

        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_ID,id);
        editor.putString(NAME,name);

        editor.putString(Logintype,phone);



        editor.apply();
        return true;
    }

    public boolean kyc_id(String kyc_id){

        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KYC_ID,kyc_id);
        editor.apply();
        return true;
    }
    public boolean datakyc_id(String datakyc_id){

        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(DATAKYC_ID,datakyc_id);
        editor.apply();
        return true;
    }
    public boolean datakyc_id(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DATAKYC_ID, null) != null;
    }
    public String getdatakyc_id(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DATAKYC_ID,null);

    }
    public boolean kyc_id(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KYC_ID, null) != null;
    }

    public String getkyc_id(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KYC_ID,null);

    }
    public boolean logintype( String logintype ){

        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(Logintype,logintype);



        editor.apply();
        return true;
    }





    public boolean isLogin(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID, null) != null;
    }
    public boolean name(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NAME, null) != null;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;

    }




    public String getUserMailId(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID,null);

    }
    public String getUsername(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NAME,null);

    }


    public String getLogintype(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Logintype,null);

    }





}
