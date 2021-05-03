package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionDificultyActivity extends AppCompatActivity {

    public TextView textTheme;
    public Button hard;
    public Button medium;
    public Button easy;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DIFICULTY_SELECT = "dificulty";

    public String themeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_dificulty);
        this.textTheme  = (TextView) findViewById(R.id.textTitreDificulty);

        this.hard = (Button) findViewById(R.id.buttonHard);
        this.medium = (Button) findViewById(R.id.buttonMed);
        this.easy = (Button) findViewById(R.id.buttonEasy);

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveData("hard", DIFICULTY_SELECT);

                Intent typeQuestionActivity = new Intent(getApplicationContext(), TypeQuestionActivity.class );
                startActivity(typeQuestionActivity);
                finish();
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveData("medium", DIFICULTY_SELECT);

                Intent typeQuestionActivity = new Intent(getApplicationContext(), TypeQuestionActivity.class );
                startActivity(typeQuestionActivity);
                finish();
            }
        });
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveData("easy", DIFICULTY_SELECT);

                Intent typeQuestionActivity = new Intent(getApplicationContext(), TypeQuestionActivity.class );
                startActivity(typeQuestionActivity);
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