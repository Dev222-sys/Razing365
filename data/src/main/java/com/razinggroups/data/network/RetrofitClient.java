package com.razinggroups.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://365.raizinggroup.com/api_crm/";
    private static com.razinggroups.data.network.RetrofitClient mInstance;
    private Retrofit retrofit;

    public RetrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

public  static synchronized com.razinggroups.data.network.RetrofitClient getInstance()
{
    if (mInstance==null)
    {
      mInstance=new com.razinggroups.data.network.RetrofitClient();

    }
    return mInstance;
}
public ApiInterface getApi()
{
    return retrofit.create(ApiInterface.class);

}

}
