package com.example.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CODE = 1;
    private Toolbar toolbar;
    private EditText txt_usuario;
    private EditText txt_password;
    private CheckBox chkRecordarCredenciales;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Grupo 6");

        txt_usuario = findViewById(R.id.txt_usuario);
        txt_password = findViewById(R.id.txt_password);

        chkRecordarCredenciales = findViewById(R.id.checkBox);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("usuario") && sharedPreferences.contains("password")) {
            String nombreUsuario = sharedPreferences.getString("usuario", "");
            String clave = sharedPreferences.getString("password", "");
            // Aquí puedes realizar la lógica para iniciar sesión automáticamente con los datos guardados
            // y navegar a la pantalla principal de tu proyecto
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        Button button_aceptar = findViewById(R.id.button_aceptar);
        button_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCredenciales();
            }
        });

        Button salirButton = findViewById(R.id.button2);
        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y la elimina de la pila de actividades
                System.exit(0); // Finaliza el proceso de la aplicación
            }
        });

        Button button_crear_cuenta = findViewById(R.id.button_crear_cuenta);
        button_crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Crear_cuenta.class);
                startActivity(intent);
            }
        });
    }

    public void validarCredenciales() {
        txt_usuario = findViewById(R.id.txt_usuario);
        txt_password = findViewById(R.id.txt_password);
        String usuarioValido = "alex";
        String passwordValido = "123";
        String usuarioIngresado = txt_usuario.getText().toString();
        String passwordIngresado = txt_password.getText().toString();

        if (usuarioIngresado.equals(usuarioValido) && passwordIngresado.equals(passwordValido)) {
            Toast.makeText(this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            if (chkRecordarCredenciales.isChecked()) {
                // Guardar la información en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", usuarioIngresado);
                editor.putString("password", passwordIngresado);
                editor.apply();
                guardarRegistroEnArchivo(usuarioIngresado,passwordIngresado);
            }
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarRegistroEnArchivo(String usuario, String password) {
        Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menu_barra = getMenuInflater();
        menu_barra.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.acercade) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}