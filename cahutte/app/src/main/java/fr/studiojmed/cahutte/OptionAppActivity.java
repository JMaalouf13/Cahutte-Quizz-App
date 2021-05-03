package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OptionAppActivity extends AppCompatActivity {
    EditText nom;
    Button retour, save ;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NOM = "nom";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_app);
        this.nom = findViewById(R.id.editTextTextPersonName);
        this.retour = findViewById(R.id.retour2);
        this.save = findViewById(R.id.saveNom);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveData(nom.getText().toString(), NOM);
                startActivity(new Intent(OptionAppActivity.this, MainActivity.class));
                finish();
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionAppActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    public boolean SaveData(String str, String key) {
        boolean flag = false;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, str );

        try {
            editor.apply();
            flag = true;
        } catch (Error error){
            Log.e("ErrorSaveData", error.toString());
        }
        return flag;
    }
}