package com.example.a21punitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerSource, spinnerDestination;
    private EditText inputValue;
    private Button convertButton, btnLength, btnWeight, btnTemperature;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise UI components
        spinnerSource = findViewById(R.id.spinner_source);
        spinnerDestination = findViewById(R.id.spinner_destination);
        inputValue = findViewById(R.id.input_value);
        convertButton = findViewById(R.id.btn_convert);
        resultText = findViewById(R.id.text_result);
        btnLength = findViewById(R.id.btnLength);
        btnWeight = findViewById(R.id.btnWeight);
        btnTemperature = findViewById(R.id.btnTemperature);

        // Default category which is Length
        setSpinnerItems(new String[]{"Inch", "Foot", "Yard", "Mile"});

        // Category button listeners
        btnLength.setOnClickListener(v -> setSpinnerItems(new String[]{"Inch", "Foot", "Yard", "Mile"}));
        btnWeight.setOnClickListener(v -> setSpinnerItems(new String[]{"Pound", "Ounce", "Ton"}));
        btnTemperature.setOnClickListener(v -> setSpinnerItems(new String[]{"Celsius", "Fahrenheit", "Kelvin"}));

        // Convert button action
        convertButton.setOnClickListener(view -> {
            String sourceUnit = spinnerSource.getSelectedItem().toString();
            String destinationUnit = spinnerDestination.getSelectedItem().toString();
            String inputStr = inputValue.getText().toString();

            if (!inputStr.isEmpty()) {
                double input = Double.parseDouble(inputStr);
                double result = convertUnits(sourceUnit, destinationUnit, input);
                resultText.setText("Result: " + result + " " + destinationUnit);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinnerItems(String[] units) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);
    }

    private double convertUnits(String from, String to, double value) {
        double baseValue;
        switch (from) {
            case "Inch": baseValue = value * 2.54; break;
            case "Foot": baseValue = value * 30.48; break;
            case "Yard": baseValue = value * 91.44; break;
            case "Mile": baseValue = value * 160934; break;
            case "Pound": baseValue = value * 0.453592; break;
            case "Ounce": baseValue = value * 28.3495; break;
            case "Ton": baseValue = value * 907.185; break;
            case "Celsius": baseValue = value; break;
            case "Fahrenheit": baseValue = (value - 32) / 1.8; break;
            case "Kelvin": baseValue = value - 273.15; break;
            default: return 0.0;
        }

        switch (to) {
            case "Inch": return baseValue / 2.54;
            case "Foot": return baseValue / 30.48;
            case "Yard": return baseValue / 91.44;
            case "Mile": return baseValue / 160934;
            case "Pound": return baseValue / 0.453592;
            case "Ounce": return baseValue / 28.3495;
            case "Ton": return baseValue / 907.185;
            case "Celsius": return baseValue;
            case "Fahrenheit": return (baseValue * 1.8) + 32;
            case "Kelvin": return baseValue + 273.15;
            default: return 0.0;
        }
    }
}
