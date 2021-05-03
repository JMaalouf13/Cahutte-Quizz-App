package fr.studiojmed.cahutte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TypeQuestionActivity extends AppCompatActivity {

    public Button closedType;
    public Button openType;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TYPE_QUESTION = "typeQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_question);

        AlertDialog.Builder builder;

        this.closedType = (Button) findViewById(R.id.closedType);
        this.openType = (Button) findViewById(R.id.openType);

        builder = new AlertDialog.Builder(this);
        AlertDialog alert = builder.create();
        alert.setTitle("Le Vrais / Faux n'est pas encore disponible");


        closedType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alert.show();
                /*Intent optionNombreQuestionActivity = new Intent(getApplicationContext(), OptionNombreQuestionActivity.class );
                startActivity(optionNombreQuestionActivity);
                finish();*/
            }

        });

        openType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                SaveData("multiple", TYPE_QUESTION);

                Intent optionNombreQuestionActivity = new Intent(getApplicationContext(), OptionNombreQuestionActivity.class );
                startActivity(optionNombreQuestionActivity);
                finish();
            }

        });
    }

    public boolean SaveData(String string, String key) {
        boolean flag = false;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, string );

        try {
            editor.apply();
            flag = true;
        } catch (Error error){
            Log.e("ErrorSaveData", error.toString());
        }
        return flag;
    }
}