package edu.upc.eetac.dsa.minimodos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.minimodos.adapters.AdapterDatos;
import edu.upc.eetac.dsa.minimodos.domain.Repository;
import edu.upc.eetac.dsa.minimodos.domain.User;
import edu.upc.eetac.dsa.minimodos.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    ImageView avatar;
    TextView numRepositories;
    TextView numFollowing;
    AdapterDatos adapterDatos;
    RecyclerView recyclerView;
    List<User> listFollowers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);

        avatar = findViewById(R.id.avatarFollower);
        numRepositories = findViewById(R.id.numRepositories);
        numFollowing = findViewById(R.id.following);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        Picasso.get().load(sharedPreferences.getString("avatar","")).into(avatar);

        getInfo();
    }
    public void getInfo(){
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<Repository>> call = service.getRepositories(preferencias.getString("username",""));

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> lista = response.body();
                numRepositories.setText("Repositories: " + lista.size());

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Api service2 = Api.retrofit.create(Api.class);
        Call<List<User>> call2 = service2.getFollowing(preferencias.getString("username",""));

        call2.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> lista = response.body();
                numFollowing.setText("Following: " + lista.size());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        Api service3 = Api.retrofit.create(Api.class);
        Call<List<User>> call3 = service3.getFollowers(preferencias.getString("username",""));

        call3.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listFollowers = response.body();
                adapterDatos = new AdapterDatos(listFollowers, new AdapterDatos.OnItemClickListener() {
                    @Override
                    public void onItemClick(User user) {
                        Toast.makeText(getApplicationContext(), "Has clicado: " + user.getLogin(), Toast.LENGTH_LONG).show();
                    }
                });
                recyclerView.setAdapter(adapterDatos);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });
    }
}