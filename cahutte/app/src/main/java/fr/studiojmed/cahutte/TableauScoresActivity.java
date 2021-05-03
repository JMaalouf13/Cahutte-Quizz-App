package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableauScoresActivity extends AppCompatActivity {
    TextView nom1, nom2, nom3 , nomDer, score1, score2, score3, scoreDer;
    int iscore1, iscore2,iscore3,iscoreDer;
    String strnom, strnom1, strnom2, strnom3;
    Button retour; //TableauScores bouton
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_scores);
        this.nom1 = (TextView) findViewById(R.id.textNom1);
        this.nom2 = (TextView) findViewById(R.id.textNom2);
        this.nom3 = (TextView) findViewById(R.id.textNom3);
        this.nomDer = (TextView) findViewById(R.id.textNomDer);
        this.score1 = (TextView) findViewById(R.id.textScore1);
        this.score2 = (TextView) findViewById(R.id.textScore2);
        this.score3 = (TextView) findViewById(R.id.textScore3);
        this.scoreDer = (TextView) findViewById(R.id.textScoreDer);
        this.retour = (Button) findViewById(R.id.retour);  //Dans le onCreate


        this.iscore1 = -1;
        this.iscore2 = -1;
        this.iscore3 = -1;
        this.iscoreDer = -1;

        this.strnom = "Nom";
        this.strnom1 = "Nom1";
        this.strnom2 = "Nom2";
        this.strnom3 = "Nom3";
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableauScoresActivity.this, MainActivity.class));
                finish();
            }
        });

        String nom = LoadData("nom1");
        if (!nom.equals("")){
            nom1.setText(nom);
            strnom1 = nom;
        }
        nom = LoadData("nom2");
        if (!nom.equals("")){
            nom2.setText(nom);
            strnom2 = nom;
        }
        nom = LoadData("nom3");
        if (!nom.equals("")){
            nom3.setText(nom);
            strnom3 = nom;
        }
        nom = LoadData("nom");
        if (!nom.equals("")){
            nomDer.setText(nom);
            strnom = nom;
        }

        String score = LoadData("score1");
        if (!score.equals("")){
            score1.setText("Score : "+score);
            iscore1 = Integer.parseInt(score);
        }
        score = LoadData("score2");
        if (!score.equals("")){
            score2.setText("Score : "+score);
            iscore2 = Integer.parseInt(score);
        }
        score = LoadData("score3");
        if (!score.equals("")){
            score3.setText("Score : "+score);
            iscore3 = Integer.parseInt(score);
        }
        score = LoadData("score3");
        if (!score.equals("")){
            score3.setText("Score : "+score);
            iscore3 = Integer.parseInt(score);
        }
        score = LoadData("scoreDer");
        if (!score.equals("")){
            scoreDer.setText("Score : "+score);
            iscoreDer = Integer.parseInt(score);
        }

        if( iscore1 < iscoreDer) {

            SaveDataNbr(this.iscore2, "score3");
            SaveDataNbr(this.iscore1, "score2");
            SaveDataNbr(this.iscoreDer, "score1");

            SaveData(this.strnom2, "nom3");
            SaveData(this.strnom1, "nom2");
            SaveData(this.strnom, "nom1");

            String str;
            str = "Score : "+iscoreDer;
            score1.setText(str);
            str = "Score : "+iscore1;
            score2.setText(str);
            str = "Score : "+iscore2;
            score3.setText(str);

            nom1.setText(strnom);
            nom2.setText(strnom1);
            nom3.setText(strnom2);
        }
        else {
            if( iscore2 < iscoreDer && iscore1 > iscoreDer) {
                SaveDataNbr(this.iscore2, "score3");
                SaveDataNbr(this.iscoreDer, "score2");

                SaveData(this.strnom2, "nom3");
                SaveData(this.strnom, "nom2");

                String str;
                str = "Score : "+iscoreDer;
                score2.setText(str);
                str = "Score : "+iscore2;
                score3.setText(str);

                nom2.setText(strnom);
                nom3.setText(strnom2);

            }
            else {
                if( iscore3 < iscoreDer && iscore2 > iscoreDer) {
                    SaveDataNbr(this.iscoreDer, "score3");
                    SaveData(this.strnom, "nom3");

                    String str;
                    str = "Score : "+iscoreDer;
                    score3.setText(str);
                    nom3.setText(strnom);
                }
            }
        }



    }
    public String LoadData(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
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

    public boolean SaveData(String str, String key) {
        boolean flag = false;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,str );
        try {
            editor.apply();
            flag = true;
        } catch (Error error){
            Log.e("ErrorSaveData", error.toString());
        }
        return flag;
    }
}