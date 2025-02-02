package com.example.pmdm_ut05_tarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddHeroActivity extends AppCompatActivity {
    private EditText editTextRealName, editTextHeroName, editTextDescription;
    private Button buttonSave;
    private TextInputLayout layoutNombreReal, layoutNombreHeroe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);

        editTextRealName = findViewById(R.id.editTextRealName);
        editTextHeroName = findViewById(R.id.editTextHeroName);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSave = findViewById(R.id.buttonSave);
        layoutNombreReal = findViewById(R.id.layoutNombreReal);
        layoutNombreHeroe = findViewById(R.id.layoutNombreHeroe);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHero();
            }
        });
    }
    private void saveHero() {
        String realName = Objects.requireNonNull(editTextRealName.getText()).toString().trim();
        String heroName = Objects.requireNonNull(editTextHeroName.getText()).toString().trim();
        String description = Objects.requireNonNull(editTextDescription.getText()).toString().trim();

        boolean isValid = true;

        if (realName.isEmpty()) {
            layoutNombreReal.setError("Este campo es obligatorio");
            isValid = false;
        } else {
            layoutNombreReal.setError(null);
        }

        if (heroName.isEmpty()) {
            layoutNombreHeroe.setError("Este campo es obligatorio");
            isValid = false;
        } else {
            layoutNombreHeroe.setError(null);
        }

        if (description.isEmpty()) {
            editTextDescription.setError("Este campo es obligatorio");
            isValid = false;
        } else {
            editTextDescription.setError(null);
        }

        if (isValid) {

            Intent resultIntent = new Intent();
            resultIntent.putExtra("Nombre Real", realName);
            resultIntent.putExtra("Nombre Heroe", heroName);
            resultIntent.putExtra("Descripción", description);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }


}
