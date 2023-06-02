package com.example.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
public class MainActivity2 extends AppCompatActivity {
    private Button btnBorrarDatos;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.reunion2);
        btnBorrarDatos = findViewById(R.id.btn_borrar_datos);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnBorrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borrar la información de SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("usuario");
                editor.remove("password");
                editor.apply();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}