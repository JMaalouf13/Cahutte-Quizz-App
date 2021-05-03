package fr.studiojmed.cahutte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    //Introduction des variables
    private TextView mTextViewResult , numQuestion,  txtScore, txtcategory, txtdifficulty; //Utilisé pour titre question
    private RequestQueue mQueue;
    public List<Questions> questions; //Stocke les questions après premier appel API
    public Button rep1;  //4 boutons pour les 4 réponses possibles
    public Button rep2;
    public Button rep3;
    public Button rep4;
    public String rightAns; //Stocke la bonne réponse, déterminé lors de l'appel de chaque question
    public String URL; //Url, qui se construit avec les arguments suivants
    public int amount;//nombre de question
    public int category;
    public String difficulty;
    public String type;
    int numquest, score; //Question n° X, commençant à 0


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NBR_QUESTIONS = "nbrQuestion";
    public static final String DIFICULTY_SELECT = "dificulty";
    public static final String TYPE_QUESTION = "typeQuestion";
    public static final String THEME_SELECT = "theme";
    public static final String SCOREDER = "scoreDer";

    AlertDialog.Builder builder; //Pour faire l'alerte, nécessaire
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextViewResult = findViewById(R.id.text_view_result);
        numQuestion = findViewById(R.id.textNumQuestion);
        txtScore = findViewById(R.id.textViewScore);

        questions = new ArrayList<>();

        txtcategory = findViewById(R.id.category);
        txtdifficulty = findViewById(R.id.difficulty);

        rep1 = findViewById(R.id.button_parse);
        rep2 = findViewById(R.id.button_parse2);
        rep3 = findViewById(R.id.button_parse3);
        rep4 = findViewById(R.id.button_parse4);
        rightAns = "";

        builder = new AlertDialog.Builder(this);
        mQueue = Volley.newRequestQueue(this);

        numquest = 0;
        score = 0;

        /*Ici, tu appelles les variables pour donner l'ordre à l'API*/

        amount = Integer.parseInt(LoadData(NBR_QUESTIONS));
        category = Integer.parseInt(LoadData(THEME_SELECT));
        difficulty = LoadData(DIFICULTY_SELECT);
        type = LoadData(TYPE_QUESTION) ;

        URL = "https://opentdb.com/api.php?amount="+amount+"&category="+category+"&difficulty="+difficulty+"&type="+type;
        Log.d("URL", URL);

        jsonParse(numquest); //Premier appel sur l'API, mettre les premières réponses sur les boutons

        rep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numquest < amount -1){
                    if (checkAnswer((String) rep1.getText())){ //Vérifie si la réponse est la bonne
                        numquest += 1;
                        score += 1;
                        txtScore.setText("Votre score : "+score );
                        SaveDataNbr(score, SCOREDER);
                        refreshQuestions(numquest);
                    }
                    else{

                        AlertDialog alert = builder.create();
                        alert.setTitle("Perdu! la bonne réponse était : " + rightAns);
                        alert.show();
                        numquest += 1;
                        refreshQuestions(numquest);
                    }
                }
                else {
                    Intent tableauScoreActivity = new Intent(getApplicationContext(), TableauScoresActivity.class );
                    startActivity(tableauScoreActivity);
                    finish();
                }

            }
        });
        rep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numquest < amount -1){
                    if (checkAnswer((String) rep2.getText())){ //Vérifie si la réponse est la bonne
                        numquest += 1;
                        score += 1;
                        txtScore.setText("Votre score : "+score );
                        SaveDataNbr(score, SCOREDER);
                        refreshQuestions(numquest);
                    }
                    else{

                        AlertDialog alert = builder.create();
                        alert.setTitle("Perdu! la bonne réponse était : " + rightAns);
                        alert.show();
                        numquest += 1;
                        refreshQuestions(numquest);
                    }
                }
                else {
                    Intent tableauScoreActivity = new Intent(getApplicationContext(), TableauScoresActivity.class );
                    startActivity(tableauScoreActivity);
                    finish();
                }

            }
        });
        rep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numquest < amount -1 ){
                    if (checkAnswer((String) rep3.getText())){ //Vérifie si la réponse est la bonne
                        numquest += 1;
                        score += 1;
                        txtScore.setText("Votre score : "+score );
                        SaveDataNbr(score, SCOREDER);
                        refreshQuestions(numquest);
                    }
                    else{

                        AlertDialog alert = builder.create();
                        alert.setTitle("Perdu! la bonne réponse était : " + rightAns);
                        alert.show();
                        numquest += 1;
                        refreshQuestions(numquest);
                    }
                }
                else {
                    Intent tableauScoreActivity = new Intent(getApplicationContext(), TableauScoresActivity.class );
                    startActivity(tableauScoreActivity);
                    finish();
                }
            }
        });
        rep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numquest < amount - 1 ){
                    if (checkAnswer((String) rep4.getText())){ //Vérifie si la réponse est la bonne
                        numquest += 1;
                        score += 1;
                        txtScore.setText("Votre score : "+score );
                        SaveDataNbr(score, SCOREDER);
                        refreshQuestions(numquest);
                    }
                    else{

                        AlertDialog alert = builder.create();
                        alert.setTitle("Perdu! la bonne réponse était : " + rightAns);
                        alert.show();
                        numquest += 1;
                        refreshQuestions(numquest);
                    }
                }
                else {
                    Intent tableauScoreActivity = new Intent(getApplicationContext(), TableauScoresActivity.class );
                    startActivity(tableauScoreActivity);
                    finish();
                }
            }
        });
    }
    private void jsonParse(int number) {

        String url = URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        //mTextViewResult.append("TEST1");
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            //mTextViewResult.append(response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject quest = jsonArray.getJSONObject(i);
                                String category = quest.getString("category");
                                String type = quest.getString("type");
                                String difficulty = quest.getString("difficulty");
                                String question = quest.getString("question");
                                String correct_answer = quest.getString("correct_answer");

                                question = question.replaceAll("&quot;","'");
                                question = question.replaceAll("&#039;","'");
                                correct_answer = correct_answer.replaceAll("&quot;","'");
                                correct_answer = correct_answer.replaceAll("&#039;","'");
                                //JSONArray incorrect = response.getJSONArray("incorrect_answers");
                                JSONArray incorrect_answers = quest.getJSONArray("incorrect_answers"); //Mise en blocs des différents champs de l'API (En respectant Strings et Jsonarrays)



                                questions.add(new Questions(category,type,difficulty,question,correct_answer,incorrect_answers)); //On remplit notre objets questions

                                /*mTextViewResult.append("category : "+ category+ "\n" +
                                        "type : "+ type+ "\n" +
                                        "difficulty : "+ difficulty+ "\n" +
                                        "question : "+ question+ "\n" +
                                        "correct_answer : "+ correct_answer+ "\n" +
                                        "incorrect_answers" + incorrect_answers + "\n" +
                                        "\n\n");*/
                            }
                            Log.d(questions.get(number).getIncorrect_answers().get(0).toString(), "onResponse: ");
                            mTextViewResult.setText(questions.get(number).getQuestion().toString());  //On affiche la question n°X
                            //mTextViewResult.setText(questions.get(number).getIncorrect_answers().get(0).toString());
                            ArrayList<String> answers = new ArrayList<String>();
                            rightAns = (questions.get(number).getCorrect_answer().toString());  //On stocke la bonne réponse
                            answers.add(questions.get(number).getIncorrect_answers().get(0).toString());
                            answers.add(questions.get(number).getIncorrect_answers().get(1).toString());
                            answers.add(questions.get(number).getIncorrect_answers().get(2).toString());
                            answers.add(questions.get(number).getCorrect_answer().toString());  //On met les 4 réponses dans une liste
                            Collections.shuffle(answers); //Randomize ordre des réponses
                            numQuestion.setText("Question "+(numquest+1)+" / "+amount);
                            rep1.setText(answers.get(0)); //On met les réponses randomisées dans les 4 cases
                            rep2.setText(answers.get(1));
                            rep3.setText(answers.get(2));
                            rep4.setText(answers.get(3));

                            txtcategory.setText(questions.get(number).getCategory().toString());
                            txtdifficulty.setText(difficulty);


                            /*mTextViewResult.append(questions.get(0).getQuestion().toString() +"\n\n" +
                                    questions.get(0).getIncorrect_answers().get(0).toString()+"\n" +
                                    questions.get(0).getIncorrect_answers().get(1).toString()+"\n" +
                                    questions.get(0).getIncorrect_answers().get(2).toString()+"\n" +
                                    questions.get(0).getCorrect_answer().toString()+"\n");

                            mTextViewResult.append(questions.get(1).getQuestion().toString() +"\n\n" +
                                    questions.get(1).getIncorrect_answers().get(0).toString()+"\n" +
                                    questions.get(1).getIncorrect_answers().get(1).toString()+"\n" +
                                    questions.get(1).getIncorrect_answers().get(2).toString()+"\n" +
                                    questions.get(1).getCorrect_answer().toString()+"\n");*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public boolean checkAnswer(String choice){
        if (choice.equals(rightAns)){ //Si la réponse choisie est la bonne, on renvoie True

            return true;
        }
        else{
            return false;
        }

    }

    public void refreshQuestions(int number){  //On rappelle le remplissage des boutons avec Randomizer et tout
        mTextViewResult.setText(questions.get(number).getQuestion().toString());
        //mTextViewResult.setText(questions.get(number).getIncorrect_answers().get(0).toString());
        ArrayList<String> answers = new ArrayList<String>();
        rightAns = (questions.get(number).getCorrect_answer().toString());
        try {
            answers.add(questions.get(number).getIncorrect_answers().get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            answers.add(questions.get(number).getIncorrect_answers().get(1).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            answers.add(questions.get(number).getIncorrect_answers().get(2).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        answers.add(questions.get(number).getCorrect_answer().toString());
        numQuestion.setText("Question "+numquest+" / "+amount);
        Collections.shuffle(answers);
        rep1.setText(answers.get(0));
        rep2.setText(answers.get(1));
        rep3.setText(answers.get(2));
        rep4.setText(answers.get(3));

        txtcategory.setText(questions.get(number).getCategory().toString());
        txtdifficulty.setText(difficulty);
    }

    public String LoadData (String key) {
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
}