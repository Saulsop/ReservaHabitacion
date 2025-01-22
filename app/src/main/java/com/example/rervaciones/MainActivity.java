package com.example.rervaciones;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editTextCheckin;
    private EditText editTextCheckout;
    private EditText editTextPersonas;
    private Button buttonReservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        editTextCheckin = findViewById(R.id.editTextCheckin);
        editTextCheckout = findViewById(R.id.editTextCheckout);
        editTextPersonas = findViewById(R.id.editTextPersonas);
        buttonReservar = findViewById(R.id.buttonReservar);

        // Configurar los selectores de fecha
        editTextCheckin.setOnClickListener(v -> showDatePickerDialog(editTextCheckin));
        editTextCheckout.setOnClickListener(v -> showDatePickerDialog(editTextCheckout));

        // Configurar el botón de reserva
        buttonReservar.setOnClickListener(v -> {
            if (validarCampos()) {
                String fechaEntrada = editTextCheckin.getText().toString();
                String fechaSalida = editTextCheckout.getText().toString();
                String personas = editTextPersonas.getText().toString();

                // Aquí puedes agregar la lógica para procesar la reserva
                Toast.makeText(this, "Reserva realizada con éxito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, yearSelected, monthSelected, daySelected) -> {
                    String date = String.format(Locale.getDefault(), "%02d/%02d/%d",
                            daySelected, monthSelected + 1, yearSelected);
                    editText.setText(date);
                }, year, month, day);

        // Establecer fecha mínima como hoy
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private boolean validarCampos() {
        if (editTextCheckin.getText().toString().isEmpty()) {
            editTextCheckin.setError("Seleccione fecha de entrada");
            return false;
        }
        if (editTextCheckout.getText().toString().isEmpty()) {
            editTextCheckout.setError("Seleccione fecha de salida");
            return false;
        }
        if (editTextPersonas.getText().toString().isEmpty()) {
            editTextPersonas.setError("Ingrese número de personas");
            return false;
        }
        return true;
    }
}