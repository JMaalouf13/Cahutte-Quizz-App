package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlay, buttonOption, buttonTabScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.buttonPlay = (Button) findViewById(R.id.buttonPlay);
        this.buttonOption = (Button) findViewById(R.id.buttonOption);
        this.buttonTabScores  = (Button) findViewById(R.id.buttonTabScores);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionThemeActivity = new Intent(getApplicationContext(), OptionThemeActivity.class );
                startActivity(optionThemeActivity);
                finish();
            }
        });
        buttonTabScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tableauScoreActivity = new Intent(getApplicationContext(), TableauScoresActivity.class );
                startActivity(tableauScoreActivity);
                finish();

            }
        });
        buttonOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionAppActivity = new Intent(getApplicationContext(), OptionAppActivity.class );
                startActivity(optionAppActivity);
                finish();
            }
        });
    }
}