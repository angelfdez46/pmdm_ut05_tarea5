package com.example.pmdm_ut05_tarea;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetalleHeroeActivity extends AppCompatActivity {
    private EditText editTextRealName, editTextHeroName, editTextDescription;
    private Button btnOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_heroe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editTextRealName = findViewById(R.id.editTextRealName);
        editTextHeroName = findViewById(R.id.editTextHeroName);
        editTextDescription = findViewById(R.id.editTextDescription);
        btnOpciones = findViewById(R.id.buttonOptions);

        if (getIntent().hasExtra("hero")) {
            Hero hero = (Hero) getIntent().getSerializableExtra("hero");

            if (hero != null) {
                editTextRealName.setText(hero.getRealName());
                editTextHeroName.setText(hero.getHeroName());
                editTextDescription.setText(hero.getDescription());
            }
        }

        btnOpciones.setOnClickListener(this::showOptionsMenu);
    }

    private void showOptionsMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_detalle_heroe_options, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_show_location:
                    showLocation();
                    return true;
                case R.id.action_email:
                    sendEmail();
                    return true;
                case R.id.action_call:
                    makeCall();
                    return true;
                case R.id.action_web:
                    openWeb();
                    return true;
                case R.id.action_whatsapp:
                    sendWhatsAppMessage();
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    private void showLocation() {
        String geoUri = "geo:37.7749,-122.4194?q=37.7749,-122.4194(Superhéroe)";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hero@example.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hola héroe");
        startActivity(intent);
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+123456789"));
        startActivity(intent);
    }

    private void openWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.superheroes.com"));
        startActivity(intent);
    }

    private void sendWhatsAppMessage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+123456789&text=Hola%20superh%C3%A9roe"));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle_heroe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
