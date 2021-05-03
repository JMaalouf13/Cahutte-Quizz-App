package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class OptionThemeActivity extends AppCompatActivity {
    public Button save;
    public Spinner dropdown;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String THEME_SELECT = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_theme);

        this.save = (Button) findViewById(R.id.buttonTheme1);

        //get the spinner from the xml.
        this.dropdown = findViewById(R.id.spinner1);

        //create a list of items for the spinner.
        String[] items = new String[]{"1 General Knowledge" , "2 Entertainment: Books", "3 Entertainment: Film","4 Entertainment: Music","5 Entertainment: Musicals, Theatres",
                "6 Entertainment: Television", "7 Entertainment: Video Games","8 Entertainment: Board Games","9 Science: Nature","10 Science: Computers","11 Science: Mathematics",
                "12 Mythology","13 Sports","14 Geography","15 History","16 Politics","17 Art","18 Celebrities","19 Animals","20 Vehicles","21 Entertainment: Comics","22 Science: Gadgets",
                "23 Entertainment: Japanese Anime, Manga","24 Entertainment: Cartoon, Animations"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(dropdown.getSelectedItem().toString().split(" ")[0])+8;
                String selectItem = String.valueOf(i);
                Log.e(selectItem,"C'est le i");
                SaveData(selectItem, THEME_SELECT);
                Intent optionDificultyActivity = new Intent(getApplicationContext(), OptionDificultyActivity.class );
                startActivity(optionDificultyActivity);
                finish();
            }

        });

    }
   public boolean SaveData(String srtring, String key) {
        boolean flag = false;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();;
        editor.putString(key, srtring );

        try {
            editor.apply();
            flag = true;
        } catch (Error error){
            Log.e("ErrorSaveData", error.toString());
        }
        return flag;
    }
}