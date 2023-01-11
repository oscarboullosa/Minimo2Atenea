package edu.upc.eetac.dsa.minimodos.retrofit;

import java.util.List;

import edu.upc.eetac.dsa.minimodos.domain.Repository;
import edu.upc.eetac.dsa.minimodos.domain.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    String URL = "https://api.github.com/users/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("{user}/repos")
    Call<List<Repository>> getRepositories(@Path("user") String user);

    @GET("{user}/following")
    Call<List<User>> getFollowing(@Path("user") String user);

    @GET("{user}/followers")
    Call<List<User>> getFollowers(@Path("user") String user);
}
