package com.example.dam_uc3_danielnavarro;

import static com.example.dam_uc3_danielnavarro.R.id.spinner_anio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner_opciones);

        Spinner spinnerDias = findViewById(R.id.spinner_dias);

        Spinner spinnerMeses = findViewById(R.id.spinner_meses);
        Spinner spinnerAnio = findViewById(spinner_anio);
        String[] datos = {"Masculino", "Femenino"};

        // Crea un adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Arreglo para los días del mes (será llenado dinámicamente según el mes seleccionado)
        List<String> listaDias = new ArrayList<>();
        /*
        for(int i=0;i<=30;i++){
            listaDias.add(i);
        }*/

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        // Arreglo para los años (por ejemplo, desde 1900 hasta el año actual)
        List<String> listaAnios = new ArrayList<>();
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2006; i <= yearActual; i++) {
            listaAnios.add(String.valueOf(i));
        }

        // Crear un adaptador para los días del mes (inicialmente vacío)
        ArrayAdapter<String> adapterDias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDias);
        adapterDias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDias.setAdapter(adapterDias);

// Crear un adaptador para los meses del año
        ArrayAdapter<String> adapterMeses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMeses.setAdapter(adapterMeses);

// Crear un adaptador para los años
        ArrayAdapter<String> adapterAnios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaAnios);
        adapterAnios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnio.setAdapter(adapterAnios);

        spinner.setAdapter(adapter);

        spinnerMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el mes seleccionado (posición del array + 1 porque los meses en Calendar van de 0 a 11)
                int mesSeleccionado = position + 1;

                // Calcular cuántos días tiene el mes seleccionado
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, mesSeleccionado - 1); // Meses en Calendar van de 0 a 11
                int maxDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                // Crear lista de días del mes actualizada
                List<String> listaDiasNueva = new ArrayList<>();
                for (int i = 1; i <= maxDias; i++) {
                    listaDiasNueva.add(String.valueOf(i));
                }

                // Actualizar adaptador de días del mes
                ArrayAdapter<String> adapterDiasNuevo = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, listaDiasNueva);
                adapterDiasNuevo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDias.setAdapter(adapterDiasNuevo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acción a realizar cuando no se selecciona nada (opcional)
            }
        });

        Button btn1 = findViewById(R.id.button_register);
        btn1.setOnClickListener(view -> Toast.makeText(this, "DATOS ENVIADOS", Toast.LENGTH_SHORT).show());




    }


}