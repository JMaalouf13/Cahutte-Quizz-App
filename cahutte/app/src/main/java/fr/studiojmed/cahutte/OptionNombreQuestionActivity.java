package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OptionNombreQuestionActivity extends AppCompatActivity {
    EditText nbrQuestion;
    Button buttonValidation;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NBR_QUESTIONS = "nbrQuestion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_nombre_question);
        this.nbrQuestion = findViewById(R.id.editNbrQuestion);
        this.buttonValidation = findViewById(R.id.buttonValidation);

        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nbrQuestion.getText().toString().length() <1)
                {
                    SaveDataNbr(10, NBR_QUESTIONS);
                }
                else{
                    int nbr = Integer.parseInt(nbrQuestion.getText().toString());

                    if(nbr >= 40){
                        SaveDataNbr(39, NBR_QUESTIONS);
                    } else {
                        SaveDataNbr(nbr, NBR_QUESTIONS);
                    }

                }

                Intent gameActivity = new Intent(getApplicationContext(), GameActivity.class );
                startActivity(gameActivity);
                finish();
            }
        });
    }

    public boolean SaveDataEditTxt(EditText editTxt, String key) {
        boolean flag = false;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String editTxtValue = editTxt.getText().toString();
        editor.putString(key, editTxtValue );

        try {
            editor.apply();
            flag = true;
        } catch (Error error){
            Log.e("ErrorSaveData", error.toString());
        }
        return flag;
    }
    public boolean SaveDataNbr(int nbr, String key) {
        boolean flag = false;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String nbrValue = String.valueOf(nbr);
        editor.putString(key, nbrValue );

        try {
            editor.apply();
            flag = true;
        } catch (Error error){
            Log.e("ErrorSaveData", error.toString());
        }
        return flag;
    }
}