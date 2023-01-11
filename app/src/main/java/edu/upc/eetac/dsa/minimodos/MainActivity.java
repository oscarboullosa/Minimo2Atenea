package edu.upc.eetac.dsa.minimodos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import edu.upc.eetac.dsa.minimodos.domain.User;
import edu.upc.eetac.dsa.minimodos.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button buttonExplore;
    EditText user;
    String editTextTextPersonName;
    //private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonExplore=findViewById(R.id.buttonExplore);
        user=findViewById(R.id.editTextTextPersonName);
        //progressBar=findViewById(R.id.progressBar);
    }
    public void buttonExplore(View view){
        //progressBar.setVisibility(View.VISIBLE);
        editTextTextPersonName=String.valueOf(user.getText());
        Api service= Api.retrofit.create(Api.class);
        Call<User> call = service.getUser(editTextTextPersonName);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //progressBar.setVisibility(View.GONE);
                User user = response.body();
                SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor Obj_editor = preferencias.edit();
                Obj_editor.putString("avatar",user.getAvatar_url());
                Obj_editor.putString("username",editTextTextPersonName);
                Obj_editor.apply();
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_LONG).show();
                //progressBar.setVisibility(View.GONE);
            }
        });
    }
}