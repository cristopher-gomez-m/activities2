package com.example.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class Crear_cuenta extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText cedula;
    private EditText telefono;
    private Button registrarButton;
    private Button cancelarButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        Spinner nacionalidades = findViewById(R.id.spn_nacionalidad);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.labels_nacionalidad,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nacionalidades.setAdapter(adapter);

        Spinner genero = findViewById(R.id.spn_genero);

        ArrayAdapter<CharSequence> adapter_genero = ArrayAdapter.createFromResource(this,R.array.labels_genero,
                android.R.layout.simple_spinner_item);
        adapter_genero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter_genero);

        ImageView button = findViewById(R.id.imageView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v);
            }
        });

        nombre = findViewById(R.id.txt_nombre);
        apellido= findViewById(R.id.txt_apellido);
        cedula= findViewById(R.id.txt_cedula);
        telefono=findViewById(R.id.txt_telefono);
        Button borrarButton = findViewById(R.id.btn_borrar);
        registrarButton = findViewById(R.id.btn_registrar);
        cancelarButton = findViewById(R.id.btn_cancelar);
        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreTexto = nombre.getText().toString();
                String apellidoTexto = apellido.getText().toString();
                String texto = nombreTexto + " " + apellidoTexto;
                //Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
                Toast.makeText(Crear_cuenta.this, texto, Toast.LENGTH_SHORT).show();
            }
        });


        borrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                apellido.setText("");
                cedula.setText("");
                telefono.setText("");
            }
        });
    }

    public void cancelar(View view){
        finish();
    }

    public void showDatePicker(View view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Obtener la fecha seleccionada y formatearla como desees
                String selectedDate = String.format(Locale.getDefault(), "%02d-%02d-%04d", dayOfMonth, monthOfYear + 1, year);

                // Establecer la fecha seleccionada en el EditText
                EditText etFecha = findViewById(R.id.editTextDate);
                etFecha.setText(selectedDate);
            }
        };

        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear el DatePickerDialog con la fecha actual y el listener
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();
    }

}